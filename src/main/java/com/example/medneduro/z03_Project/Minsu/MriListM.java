package com.example.medneduro.z03_Project.Minsu;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MriListM {
    private String patientName;   // 환자 이름
    private String birthDate;     // 생년월일 (VARCHAR2)
    private String gender;        // 성별 (CHAR)
    private String fileName;      // 파일명
    private Date lastCheckTime;   // 확인한 시간 (java.util.Date)

    public MriListM() {
    }

    public MriListM(String patientName, String birthDate, String gender, String fileName, Date lastCheckTime) {
        this.patientName = patientName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.fileName = fileName;
        this.lastCheckTime = lastCheckTime;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getLastCheckTime() {
        return lastCheckTime;
    }

    public void setLastCheckTime(Date lastCheckTime) {
        this.lastCheckTime = lastCheckTime;
    }
}