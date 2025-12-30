package com.example.medneduro.z03_Project.BWJ;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


public class Comment2DDto {

    /* =========================
       1️⃣ 필드 선언 (DB 컬럼과 1:1 매핑)
       ========================= */

    // PK (시퀀스로 생성)
    private Long commentId;

    // FK / 식별자
    private Long medMriId;     // MRI 폴더 ID
    private String staffId;    // 의료진 ID
    private String patientId;  // 환자 ID

    // 위치 / 슬라이스 정보
    private String axis;       // axial / coronal / sagittal
    private Integer sliceIndex;

    @JsonProperty("x_coord")
    @JsonAlias("xCoord")
    private Double xCoord;

    // 정규화 좌표 (0 ~ 1)
    @JsonProperty("y_coord")
    @JsonAlias("yCoord")
    private Double yCoord;

    // 코멘트 내용
    private String content;

    // 생성 시각
    private Timestamp createdAt;

    /* =========================
       2️⃣ 기본 생성자 (필수)
       ========================= */
    // ✔ Spring / MyBatis는 기본 생성자를 사용해 객체를 생성함
    public Comment2DDto() {
    }

    /* =========================
       3️⃣ Getter / Setter
       - Java Bean 규칙
       - MyBatis, Jackson(JSON), Spring 전부 이 규칙 사용
       ========================= */

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getMedMriId() {
        return medMriId;
    }

    public void setMedMriId(Long medMriId) {
        this.medMriId = medMriId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getAxis() {
        return axis;
    }

    public void setAxis(String axis) {
        this.axis = axis;
    }

    public Integer getSliceIndex() {
        return sliceIndex;
    }

    public void setSliceIndex(Integer sliceIndex) {
        this.sliceIndex = sliceIndex;
    }

    public Double getXCoord() {
        return xCoord;
    }

    public void setXCoord(Double xCoord) {
        this.xCoord = xCoord;
    }

    public Double getYCoord() {
        return yCoord;
    }

    public void setYCoord(Double yCoord) {
        this.yCoord = yCoord;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /* =========================
       4️⃣ toString (디버깅용, 선택)
       ========================= */
    @Override
    public String toString() {
        return "Comment2DDto{" +
                "commentId=" + commentId +
                ", medMriId=" + medMriId +
                ", staffId='" + staffId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", axis='" + axis + '\'' +
                ", sliceIndex=" + sliceIndex +
                ", xCoord=" + xCoord +
                ", yCoord=" + yCoord +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}