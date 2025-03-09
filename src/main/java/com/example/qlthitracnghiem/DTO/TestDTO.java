package com.example.qlthitracnghiem.DTO;

import java.time.LocalDateTime;

public class TestDTO {
    private int testID;
    private String testCode;
    private String testTitle;
    private int testTime;
    private int testLimit;
    private LocalDateTime testDate;
    private int testStatus;

    // Getters and Setters
    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public int getTestTime() {
        return testTime;
    }

    public void setTestTime(int testTime) {
        this.testTime = testTime;
    }

    public int getTestLimit() {
        return testLimit;
    }

    public void setTestLimit(int testLimit) {
        this.testLimit = testLimit;
    }

    public LocalDateTime getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDateTime testDate) {
        this.testDate = testDate;
    }

    public int getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(int testStatus) {
        this.testStatus = testStatus;
    }

    @Override
    public String toString() {
        return String.format("TestDTO{testID:%d, testCode: %s, testTitle: %s, testStatus: %d}", testID, testCode,
                testTitle, testStatus);
    }

}
