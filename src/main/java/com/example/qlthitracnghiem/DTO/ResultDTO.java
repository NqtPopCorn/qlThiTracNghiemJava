/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.qlthitracnghiem.DTO;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Asus
 */
public class ResultDTO {
    
    private int rsNum;
    private int userID;
    private String exCode;
    private String[] rsAnswer;
    private Long rsMark;
    private LocalDateTime rsDate;
    
    public ResultDTO() {
        
    }

    public ResultDTO(int rsNum, int userID, String exCode, String[] rsAnswer, Long rsMark, LocalDateTime rsDate) {
        this.rsNum = rsNum;
        this.userID = userID;
        this.exCode = exCode;
        this.rsAnswer = rsAnswer;
        this.rsMark = rsMark;
        this.rsDate = rsDate;
    }

    public int getRsNum() {
        return rsNum;
    }

    public void setRsNum(int rsNum) {
        this.rsNum = rsNum;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getExCode() {
        return exCode;
    }

    public void setExCode(String exCode) {
        this.exCode = exCode;
    }

    public String[] getRsAnswer() {
        return rsAnswer;
    }

    public void setRsAnswer(String[] rsAnswer) {
        this.rsAnswer = rsAnswer;
    }

    public Long getRsMark() {
        return rsMark;
    }

    public void setRsMark(Long rsMark) {
        this.rsMark = rsMark;
    }

    public LocalDateTime getRsDate() {
        return rsDate;
    }

    public void setRsDate(LocalDateTime rsDate) {
        this.rsDate = rsDate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ResultDTO other = (ResultDTO) obj;
        if (this.rsNum != other.rsNum) {
            return false;
        }
        if (this.userID != other.userID) {
            return false;
        }
        if (!Objects.equals(this.exCode, other.exCode)) {
            return false;
        }
        if (!Objects.equals(this.rsAnswer, other.rsAnswer)) {
            return false;
        }
        if (!Objects.equals(this.rsMark, other.rsMark)) {
            return false;
        }
        return Objects.equals(this.rsDate, other.rsDate);
    }

    
    
}
