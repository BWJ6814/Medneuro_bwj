package com.example.medneduro.z03_Project.Hong;

public class TumorAnnotationDTO {
    private int mriId;              // MRI_ID (FK)
    private int writerAccountId;    // WRITER_ACCOUNT_ID
    private String coordinateJson;  // COORDINATE_JSON (CLOB -> String)
    private String isAiGenerated;   // IS_AI_GENERATED (char(1) -> String)
    private Double confidenceScore; // CONFIDENCE_SCORE (number(5,4) -> Double)

    public TumorAnnotationDTO() {
    }

    public TumorAnnotationDTO(int mriId, int writerAccountId, String coordinateJson, String isAiGenerated, Double confidenceScore) {
        this.mriId = mriId;
        this.writerAccountId = writerAccountId;
        this.coordinateJson = coordinateJson;
        this.isAiGenerated = isAiGenerated;
        this.confidenceScore = confidenceScore;
    }

    public int getMriId() {
        return mriId;
    }

    public void setMriId(int mriId) {
        this.mriId = mriId;
    }

    public int getWriterAccountId() {
        return writerAccountId;
    }

    public void setWriterAccountId(int writerAccountId) {
        this.writerAccountId = writerAccountId;
    }

    public String getCoordinateJson() {
        return coordinateJson;
    }

    public void setCoordinateJson(String coordinateJson) {
        this.coordinateJson = coordinateJson;
    }

    public String getIsAiGenerated() {
        return isAiGenerated;
    }

    public void setIsAiGenerated(String isAiGenerated) {
        this.isAiGenerated = isAiGenerated;
    }

    public Double getConfidenceScore() {
        return confidenceScore;
    }

    public void setConfidenceScore(Double confidenceScore) {
        this.confidenceScore = confidenceScore;
    }
}