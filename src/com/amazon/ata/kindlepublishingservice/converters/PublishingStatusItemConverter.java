package com.amazon.ata.kindlepublishingservice.converters;

import com.amazon.ata.kindlepublishingservice.dynamodb.models.PublishingStatusItem;
import com.amazon.ata.kindlepublishingservice.models.PublishingStatusRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Converters for PublishingStatus related objects.
 */
public class PublishingStatusItemConverter {
    private PublishingStatusItemConverter() {}

    /**
     * Converts the given {@link PublishingStatusItem} object into the corresponding PublishingStatusRecord object.
     *
     * @param publishingStatusItem PublishingStatusItem item to convert.
     * @return Publishing Status Record object.
     */
    public static PublishingStatusRecord toRecord(PublishingStatusItem publishingStatusItem) {
        return PublishingStatusRecord.builder()
            .withStatus(publishingStatusItem.getStatus().toString())
            .withStatusMessage(publishingStatusItem.getStatusMessage())
            .withBookId(publishingStatusItem.getBookId())
            .build();
    }

    public static List<PublishingStatusRecord> toRecords(List<PublishingStatusItem> publishingStatusItems) {
        List<PublishingStatusRecord> records = new ArrayList<>();
        for (PublishingStatusItem item : publishingStatusItems) {
            records.add(toRecord(item));
        }

        return records;
    }
}
