package com.ahwalroads.backend.repository;

import com.ahwalroads.backend.entity.Checkpoint;
import com.ahwalroads.backend.entity.CheckpointStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckpointRepository extends JpaRepository<Checkpoint, Long> {

    List<Checkpoint> findByCityIgnoreCase(String city);
    Optional<Checkpoint> findByNameIgnoreCase(String name);

    @Query("""
    SELECT c FROM Checkpoint c
    LEFT JOIN FETCH c.statuses s
    WHERE s.createdAt = (
        SELECT MAX(s2.createdAt)
        FROM CheckpointStatus s2
        WHERE s2.checkpoint = c
    )
""")
    List<Checkpoint> findAllWithLatestStatus();
}
