package com.ahwalroads.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CheckpointStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Checkpoint checkpoint;

    private String status;
    private String sourceText;
    private Long sourceMessageId;

    private LocalDateTime scrapedAt;
    private LocalDateTime effectiveAt;
    private LocalDateTime createdAt;

    // === Getters & Setters ===
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Checkpoint getCheckpoint() { return checkpoint; }
    public void setCheckpoint(Checkpoint checkpoint) { this.checkpoint = checkpoint; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getSourceText() { return sourceText; }
    public void setSourceText(String sourceText) { this.sourceText = sourceText; }

    public Long getSourceMessageId() { return sourceMessageId; }
    public void setSourceMessageId(Long sourceMessageId) { this.sourceMessageId = sourceMessageId; }

    public LocalDateTime getScrapedAt() { return scrapedAt; }
    public void setScrapedAt(LocalDateTime scrapedAt) { this.scrapedAt = scrapedAt; }

    public LocalDateTime getEffectiveAt() { return effectiveAt; }
    public void setEffectiveAt(LocalDateTime effectiveAt) { this.effectiveAt = effectiveAt; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // === Constructors ===
    public CheckpointStatus() {}

    public CheckpointStatus(Long id, Checkpoint checkpoint, String status, String sourceText, Long sourceMessageId,
                            LocalDateTime scrapedAt, LocalDateTime effectiveAt, LocalDateTime createdAt) {
        this.id = id;
        this.checkpoint = checkpoint;
        this.status = status;
        this.sourceText = sourceText;
        this.sourceMessageId = sourceMessageId;
        this.scrapedAt = scrapedAt;
        this.effectiveAt = effectiveAt;
        this.createdAt = createdAt;
    }

    // === Manual Builder ===
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final CheckpointStatus status;

        public Builder() {
            status = new CheckpointStatus();
        }

        public Builder checkpoint(Checkpoint cp) {
            status.setCheckpoint(cp);
            return this;
        }

        public Builder status(String val) {
            status.setStatus(val);
            return this;
        }

        public Builder sourceText(String val) {
            status.setSourceText(val);
            return this;
        }

        public Builder sourceMessageId(Long val) {
            status.setSourceMessageId(val);
            return this;
        }

        public Builder scrapedAt(LocalDateTime val) {
            status.setScrapedAt(val);
            return this;
        }

        public Builder effectiveAt(LocalDateTime val) {
            status.setEffectiveAt(val);
            return this;
        }

        public Builder createdAt(LocalDateTime val) {
            status.setCreatedAt(val);
            return this;
        }

        public CheckpointStatus build() {
            return status;
        }
    }
}