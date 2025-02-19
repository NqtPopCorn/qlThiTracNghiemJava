
package com.example.qlthitracnghiem.DTO;

public class ExamDTO {
    private String testCode;
    private String exOrder;
    private String exCode;
    private String[] ex_quesIDs;

    public ExamDTO() {
    }

    public ExamDTO(String testCode, String exOrder, String exCode, String[] ex_quesIDs) {
        this.testCode = testCode;
        this.exOrder = exOrder;
        this.exCode = exCode;
        this.ex_quesIDs = ex_quesIDs;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getExOrder() {
        return exOrder;
    }

    public void setExOrder(String exOrder) {
        this.exOrder = exOrder;
    }

    public String getExCode() {
        return exCode;
    }

    public void setExCode(String exCode) {
        this.exCode = exCode;
    }

    public String[] getEx_quesIDs() {
        return ex_quesIDs;
    }

    public void setEx_quesIDs(String[] ex_quesIDs) {
        this.ex_quesIDs = ex_quesIDs;
    }
}