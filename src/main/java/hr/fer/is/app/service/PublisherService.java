package hr.fer.is.app.service;

import hr.fer.is.app.domain.Publisher;
import hr.fer.is.app.domain.dto.PublisherDTO;

import java.util.List;

public interface PublisherService {
    PublisherDTO create(PublisherDTO publisherDTO);

    PublisherDTO update(Long id, PublisherDTO publisherDTO);

    void delete(Long id);

    List<PublisherDTO> getAll();

    PublisherDTO getById(Long id);

    Publisher getRawById(Long id);
}