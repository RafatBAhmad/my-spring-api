package com.ahwalroads.backend.controller;

import com.ahwalroads.backend.dto.CheckpointResponse;
import com.ahwalroads.backend.dto.CheckpointUpdateRequest;
import com.ahwalroads.backend.entity.Checkpoint;
import com.ahwalroads.backend.entity.CheckpointStatus;
import com.ahwalroads.backend.repository.CheckpointRepository;
import com.ahwalroads.backend.repository.CheckpointStatusRepository;
import com.ahwalroads.backend.service.CheckpointService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/checkpoints")
@CrossOrigin
public class CheckpointController {

    private final CheckpointService service;
    private final CheckpointRepository checkpointRepository;
    private final CheckpointStatusRepository statusRepository;

    public CheckpointController(CheckpointService service,CheckpointRepository checkpointRepository,
                                CheckpointStatusRepository statusRepository) {
        this.service = service;
        this.checkpointRepository = checkpointRepository;
        this.statusRepository = statusRepository;
    }

    @PostMapping("/update")
    public void updateCheckpoint(@RequestBody CheckpointUpdateRequest request) {
        service.saveCheckpointUpdate(request);

    }

    @GetMapping("/all")
    public List<CheckpointResponse> getAllCheckpoints() {
        List<Checkpoint> checkpoints = checkpointRepository.findAll();

        return checkpoints.stream().map(cp -> {
            CheckpointStatus latest = statusRepository.findTopByCheckpointOrderByCreatedAtDesc(cp);
            CheckpointResponse res = new CheckpointResponse();
            res.setName(cp.getName());
            res.setCity(cp.getCity());
            res.setLatitude(cp.getLatitude());
            res.setLongitude(cp.getLongitude());
            if (latest != null) {
                res.setStatus(latest.getStatus());
                res.setUpdatedAt(latest.getCreatedAt().toString());
                res.setSourceText(latest.getSourceText()); // ✅ هذا هو السطر الناقص
            } else {
                res.setStatus("غير معروف");
                res.setUpdatedAt(null);
                res.setSourceText(""); // ✅ إذا لم يوجد حالة
            }
            return res;
        }).toList();
    }


    @GetMapping("/by-city")
    public List<CheckpointResponse> getByCity(@RequestParam("city") String city) {
        List<Checkpoint> checkpoints = checkpointRepository.findByCityIgnoreCase(city);
        return checkpoints.stream().map(cp -> {
            CheckpointStatus latest = statusRepository.findTopByCheckpointOrderByCreatedAtDesc(cp);
            CheckpointResponse res = new CheckpointResponse();
            res.setName(cp.getName());
            res.setCity(cp.getCity());
            res.setLatitude(cp.getLatitude());
            res.setLongitude(cp.getLongitude());
            res.setStatus(latest != null ? latest.getStatus() : "غير معروف");
            res.setUpdatedAt(latest != null ? latest.getCreatedAt().toString() : null);
            res.setSourceText(latest != null ? latest.getSourceText() : ""); // ✅ أضف هذا السطر
            return res;
        }).toList();
    }


    @GetMapping("/cities")
    public List<String> getAvailableCities() {
        return checkpointRepository.findAll().stream()
                .map(cp -> cp.getCity())
                .filter(city -> city != null && !city.isBlank() && !city.equalsIgnoreCase("غير معروف"))
                .distinct()
                .sorted()
                .toList();
    }
}
