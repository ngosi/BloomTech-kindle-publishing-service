package com.amazon.ata.kindlepublishingservice.converters;

import com.amazon.ata.kindlepublishingservice.dynamodb.models.PublishingStatusItem;
import com.amazon.ata.kindlepublishingservice.enums.PublishingRecordStatus;
import com.amazon.ata.kindlepublishingservice.models.PublishingStatusRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PublishingStatusItemConverterTest {

    @Test
    public void toRecord_returnsPublishingStatusRecord() {
        // GIVEN
        PublishingStatusItem item = new PublishingStatusItem();
        item.setPublishingRecordId("publishingstatus.123");
        item.setStatus(PublishingRecordStatus.IN_PROGRESS);
        item.setStatusMessage("Processing started at 2020-02-28 13:03:29.111");
        item.setBookId("book.123");

        // WHEN
        PublishingStatusRecord record = PublishingStatusItemConverter.toRecord(item);

        // THEN
        assertEquals(item.getStatus().toString(), record.getStatus(), "Status field not converted properly.");
        assertEquals(item.getStatusMessage(), record.getStatusMessage(), "Status message field not converted properly.");
        assertEquals(item.getBookId(), record.getBookId(), "Book Id field not converted properly.");
    }
}
