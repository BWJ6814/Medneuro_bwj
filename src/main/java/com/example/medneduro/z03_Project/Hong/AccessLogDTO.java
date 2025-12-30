package com.example.medneduro.z03_Project.Hong;

public class AccessLogDTO {
    private int medMriId;
    private String doctorId;
    @IpMasking
    private String accessIp; // 원본 IP
    private String accessDt;

    // getter에서 마스킹된 값을 반환하도록 설계
    public String getMaskedIp() {
        return SecurityUtil.maskIp(this.accessIp);
    }

    public int getMedMriId() {
        return medMriId;
    }

    public void setMedMriId(int medMriId) {
        this.medMriId = medMriId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getAccessIp() {
        return accessIp;
    }

    public void setAccessIp(String accessIp) {
        this.accessIp = accessIp;
    }

    public String getAccessDt() {
        return accessDt;
    }

    public void setAccessDt(String accessDt) {
        this.accessDt = accessDt;
    }
}