package com.example.smsys.enumeration;

/**
 * @author srehcs
 */

public enum Status {
    STUDENT_UP("STUDENT_UP"),
    STUDENT_DOWN("STUDENT_DOWN");

    private final String status;
    Status(String status) {
        this.status = status;
    }
    public String getStatus() {
        return this.status;
    }
}

