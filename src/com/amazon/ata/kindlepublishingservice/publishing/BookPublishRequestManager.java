package com.amazon.ata.kindlepublishingservice.publishing;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.Queue;

public class BookPublishRequestManager {
    private Queue<BookPublishRequest> requests;

    @Inject
    public BookPublishRequestManager() {
        requests = new LinkedList<>();
    }

    public void addBookPublishRequest(BookPublishRequest request) {
        requests.offer(request);
    }

    public BookPublishRequest getBookPublishRequestToProcess() {
        return requests.poll();
    }
}
