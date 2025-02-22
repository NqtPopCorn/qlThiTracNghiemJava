/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.qlthitracnghiem.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author light
 */
public class ExamDTOq {
    private String testCode;
    private String exOrder;
    private String exCode;
    private String exQuesIDs; // Or consider a List<Integer> if you parse the IDs

    // Constructors (at least a no-args constructor and a constructor with all fields)

    public ExamDTOq() {
        // No-argument constructor
    }

    public ExamDTOq(String testCode, String exOrder, String exCode, String exQuesIDs) {
        this.testCode = testCode;
        this.exOrder = exOrder;
        this.exCode = exCode;
        this.exQuesIDs = exQuesIDs;
    }

    // Getters and Setters (essential)

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

    public String getExQuesIDs() {
        return exQuesIDs;
    }

    public void setExQuesIDs(String exQuesIDs) {
        this.exQuesIDs = exQuesIDs;
    }

    // Optional: toString() method for debugging

    @Override
    public String toString() {
        return "ExamDTO{" +
                "testCode='" + testCode + '\'' +
                ", exOrder='" + exOrder + '\'' +
                ", exCode='" + exCode + '\'' +
                ", exQuesIDs='" + exQuesIDs + '\'' +
                '}';
    }


    // Important: Method to parse exQuesIDs into a List<Integer> (if needed)
    public List<Integer> getQuestionIdsAsList() {
        if (exQuesIDs == null || exQuesIDs.isEmpty()) {
            return new ArrayList<>(); // Return an empty list if exQuesIDs is null or empty
        }

        List<Integer> questionIds = new ArrayList<>();
        String[] ids = exQuesIDs.split(","); // Assuming comma-separated IDs

        for (String idStr : ids) {
            try {
                int id = Integer.parseInt(idStr.trim());
                questionIds.add(id);
            } catch (NumberFormatException e) {
                // Handle parsing errors (e.g., log, throw exception, skip the ID)
                System.err.println("Error parsing question ID: " + idStr);
            }
        }
        return questionIds;
    }
}
