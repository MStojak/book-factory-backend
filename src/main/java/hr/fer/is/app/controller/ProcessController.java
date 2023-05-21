package hr.fer.is.app.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import hr.fer.is.app.domain.dto.ProcessDTO;
import hr.fer.is.app.service.ProcessService;

@RestController
@RequestMapping("/api/processes")
public class ProcessController {

    private final ProcessService processService;

    public ProcessController(ProcessService processService) {
        this.processService = processService;
    }

    @GetMapping
    public ResponseEntity<List<ProcessDTO>> getAllProcesses() {
        return ResponseEntity.ok(processService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcessDTO> getProcess(@PathVariable Integer id) {
        return ResponseEntity.ok(processService.getById(id));
    }
}
