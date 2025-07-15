package com.ahwalroads.backend.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Checkpoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private Double latitude;
    private Double longitude;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "checkpoint", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CheckpointStatus> statuses = new ArrayList<>();

    // === Getters & Setters ===
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public List<CheckpointStatus> getStatuses() { return statuses; }
    public void setStatuses(List<CheckpointStatus> statuses) { this.statuses = statuses; }

    // === Constructors ===
    public Checkpoint() {}

    public Checkpoint(Long id, String name, String city, Double latitude, Double longitude,
                      LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // === Builder Manual ===
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Checkpoint checkpoint;

        public Builder() {
            checkpoint = new Checkpoint();
        }

        public Builder name(String name) {
            checkpoint.setName(name);
            return this;
        }

        public Builder city(String city) {
            checkpoint.setCity(city);
            return this;
        }

        public Builder latitude(Double latitude) {
            checkpoint.setLatitude(latitude);
            return this;
        }

        public Builder longitude(Double longitude) {
            checkpoint.setLongitude(longitude);
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            checkpoint.setCreatedAt(createdAt);
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            checkpoint.setUpdatedAt(updatedAt);
            return this;
        }

        public Checkpoint build() {
            return checkpoint;
        }
    }
}