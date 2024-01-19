package com.amazon.ata.kindlepublishingservice.publishing;

import com.amazon.ata.kindlepublishingservice.dao.CatalogDao;
import com.amazon.ata.kindlepublishingservice.dao.PublishingStatusDao;
import com.amazon.ata.kindlepublishingservice.dynamodb.models.CatalogItemVersion;
import com.amazon.ata.kindlepublishingservice.enums.PublishingRecordStatus;
import com.amazon.ata.kindlepublishingservice.exceptions.BookNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class BookPublishTask implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(BookPublishTask.class);
    private BookPublishRequestManager manager;
    private PublishingStatusDao publishingStatusDao;
    private CatalogDao catalogDao;

    @Inject
    public BookPublishTask(BookPublishRequestManager manager, PublishingStatusDao publishingStatusDao, CatalogDao catalogDao) {
        this.manager = manager;
        this.publishingStatusDao = publishingStatusDao;
        this.catalogDao = catalogDao;
    }

    @Override
    public void run() {
        LOGGER.info("BookPublishTask executed.");
        BookPublishRequest request = manager.getBookPublishRequestToProcess();
        if (request != null) {
            publishingStatusDao.setPublishingStatus(request.getPublishingRecordId(), PublishingRecordStatus.IN_PROGRESS, request.getBookId());
            try {
                CatalogItemVersion book = catalogDao.createOrUpdateBook(KindleFormatConverter.format(request));
                publishingStatusDao.setPublishingStatus(request.getPublishingRecordId(), PublishingRecordStatus.SUCCESSFUL, book.getBookId());
                LOGGER.info("BookPublishTask completed successfully.");
            } catch (BookNotFoundException e) {
                publishingStatusDao.setPublishingStatus(request.getPublishingRecordId(), PublishingRecordStatus.FAILED, request.getBookId());
                LOGGER.error("BookPublishTask failed.");
            }
        } else {
            LOGGER.info("No BookPublishRequest to process.");
        }
    }
}
