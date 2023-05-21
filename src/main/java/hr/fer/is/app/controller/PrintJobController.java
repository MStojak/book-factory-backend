package hr.fer.is.app.controller;

import hr.fer.is.app.domain.dto.PrintJobDTO;
import hr.fer.is.app.service.PrintJobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/printJobs")
public class PrintJobController {

    private final PrintJobService printJobService;

    public PrintJobController(PrintJobService printJobService) {
        this.printJobService = printJobService;
    }

    @GetMapping
    public ResponseEntity<List<PrintJobDTO>> getAllPrintJobs() {
        return ResponseEntity.ok(printJobService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrintJobDTO> getPrintJob(@PathVariable Long id) {
        return ResponseEntity.ok(printJobService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PrintJobDTO> createPrintJob(@RequestBody @Valid PrintJobDTO printJobDTO) {
        return ResponseEntity.ok(printJobService.save(printJobDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrintJobDTO> updatePrintJob(@PathVariable Long id, @Valid @RequestBody PrintJobDTO printJobDTO) {
        return ResponseEntity.ok(printJobService.update(id, printJobDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrintJob(@PathVariable Long id) {
        printJobService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(params = "publisherId")
    public ResponseEntity<List<PrintJobDTO>> getPrintJobsByPublisherId(@RequestParam Long publisherId) {
        return ResponseEntity.ok(printJobService.findAllByPublisherId(publisherId));
    }
}
