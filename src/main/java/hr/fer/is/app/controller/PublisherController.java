package hr.fer.is.app.controller;

import hr.fer.is.app.domain.dto.PublisherDTO;
import hr.fer.is.app.service.PublisherService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping
    public PublisherDTO create(@RequestBody PublisherDTO publisherDTO) {
        return publisherService.create(publisherDTO);
    }

    @PutMapping("/{id}")
    public PublisherDTO update(@PathVariable Long id, @RequestBody PublisherDTO publisherDTO) {
        return publisherService.update(id, publisherDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        publisherService.delete(id);
    }

    @GetMapping
    public List<PublisherDTO> getAll() {
        return publisherService.getAll();
    }

    @GetMapping("/{id}")
    public PublisherDTO getById(@PathVariable Long id) {
        return publisherService.getById(id);
    }
}
