package com.ahwalroads.backend.dto;

public class CheckpointUpdateRequest {
    private String name;
    private String city;
    private String status;
    private String sourceText;
    private Long sourceMessageId;
    private Double latitude;
    private Double longitude;
    private String effectiveAt;

    // === Getters & Setters ===
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getSourceText() { return sourceText; }
    public void setSourceText(String sourceText) { this.sourceText = sourceText; }

    public Long getSourceMessageId() { return sourceMessageId; }
    public void setSourceMessageId(Long sourceMessageId) { this.sourceMessageId = sourceMessageId; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public String getEffectiveAt() { return effectiveAt; }
    public void setEffectiveAt(String effectiveAt) { this.effectiveAt = effectiveAt; }
}