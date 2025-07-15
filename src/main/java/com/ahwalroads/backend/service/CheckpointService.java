package com.ahwalroads.backend.service;

import com.ahwalroads.backend.dto.CheckpointUpdateRequest;
import com.ahwalroads.backend.entity.Checkpoint;
import com.ahwalroads.backend.entity.CheckpointStatus;
import com.ahwalroads.backend.repository.CheckpointRepository;
import com.ahwalroads.backend.repository.CheckpointStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class CheckpointService {

    private final CheckpointRepository checkpointRepository;
    private final CheckpointStatusRepository statusRepository;

    public CheckpointService(CheckpointRepository checkpointRepository,
                             CheckpointStatusRepository statusRepository) {
        this.checkpointRepository = checkpointRepository;
        this.statusRepository = statusRepository;
    }

    public void saveCheckpointUpdate(CheckpointUpdateRequest request) {
        // ✅ تأكد ما في تكرار للرسالة
        Optional<CheckpointStatus> existing = statusRepository.findBySourceMessageId(request.getSourceMessageId());
        if (existing.isPresent()) {
            System.out.println("⏩ الرسالة " + request.getSourceMessageId() + " موجودة مسبقًا، تم تجاهلها.");
            return;
        }

        Checkpoint checkpoint = checkpointRepository
                .findByNameIgnoreCase(request.getName())
                .orElseGet(() -> checkpointRepository.save(new Checkpoint.Builder()
                        .name(request.getName())
                        .city(request.getCity())
                        .latitude(request.getLatitude())
                        .longitude(request.getLongitude())
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build()));

        checkpoint.setUpdatedAt(LocalDateTime.now());
        checkpointRepository.save(checkpoint);

        CheckpointStatus status = new CheckpointStatus.Builder()
                .checkpoint(checkpoint)
                .status(request.getStatus())
                .sourceText(request.getSourceText())
                .sourceMessageId(request.getSourceMessageId())
                .scrapedAt(LocalDateTime.now())
                .effectiveAt(parseIso(request.getEffectiveAt()))
                .createdAt(LocalDateTime.now())
                .build();

        statusRepository.save(status);
    }

    private LocalDateTime parseIso(String iso) {
        return LocalDateTime.parse(iso, DateTimeFormatter.ISO_DATE_TIME);
    }
}