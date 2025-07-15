package com.ahwalroads.backend.repository;

import com.ahwalroads.backend.entity.Checkpoint;
import com.ahwalroads.backend.entity.CheckpointStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CheckpointStatusRepository extends JpaRepository<CheckpointStatus, Long> {
    Optional<CheckpointStatus> findBySourceMessageId(Long sourceMessageId);
    CheckpointStatus findTopByCheckpointOrderByCreatedAtDesc(Checkpoint checkpoint);
}
