package com.digitalid.audit;

public interface AuditObserver {
    void onEvent(String event);
}
