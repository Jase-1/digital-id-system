package com.digitalid.audit;

import java.util.ArrayList;
import java.util.List;

public class AuditLog implements AuditObserver {

    private static AuditLog instance;
    private final List<String> logs;

    private AuditLog() {
        logs = new ArrayList<>();
    }

    public static AuditLog getInstance() {
        if (instance == null) {
            instance = new AuditLog();
        }
        return instance;
    }

    @Override
    public void onEvent(String event) {
        logs.add(event);
        System.out.println("[AUDIT] " + event);
    }

    public void printAllLogs() {
        System.out.println("\n--- Audit Log ---");
        for (String log : logs) {
            System.out.println(log);
        }
        System.out.println("-----------------\n");
    }
}
