package hr.fer.is.app.controller;

import hr.fer.is.app.domain.dto.CoverTypeDTO;
import hr.fer.is.app.service.CoverTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/coverTypes")
public class CoverTypeController {
    private final CoverTypeService coverTypeService;

    @Autowired
    public CoverTypeController(CoverTypeService coverTypeService) {
        this.coverTypeService = coverTypeService;
    }

    @GetMapping
    public ResponseEntity<List<CoverTypeDTO>> getAllCoverTypes() {
        return ResponseEntity.ok(coverTypeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoverTypeDTO> getCoverType(@PathVariable Integer id) {
        return ResponseEntity.ok(coverTypeService.getById(id));
    }
}
