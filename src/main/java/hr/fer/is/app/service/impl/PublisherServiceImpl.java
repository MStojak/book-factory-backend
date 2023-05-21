package hr.fer.is.app.service.impl;

import hr.fer.is.app.converter.PublisherMapper;
import hr.fer.is.app.domain.Publisher;
import hr.fer.is.app.domain.dto.PublisherDTO;
import hr.fer.is.app.repository.PublisherRepository;
import hr.fer.is.app.service.PublisherService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    public PublisherServiceImpl(PublisherRepository publisherRepository, PublisherMapper publisherMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherMapper = publisherMapper;
    }

    @Override
    public PublisherDTO create(PublisherDTO publisherDTO) {
        Publisher publisher = publisherMapper.toEntity(publisherDTO);
        return publisherMapper.toDto(publisherRepository.save(publisher));
    }

    @Override
    public PublisherDTO update(Long id, PublisherDTO publisherDTO) {
        Publisher publisher = publisherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Publisher not found"));
        publisherMapper.updateEntityFromDto(publisherDTO, publisher);
        return publisherMapper.toDto(publisherRepository.save(publisher));
    }

    @Override
    public void delete(Long id) {
        Publisher publisher = publisherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Publisher not found"));
        publisherRepository.delete(publisher);
    }

    @Override
    public List<PublisherDTO> getAll() {
        return publisherRepository.findAll().stream().map(publisherMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PublisherDTO getById(Long id) {
        Publisher publisher = publisherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Publisher not found"));
        return publisherMapper.toDto(publisher);
    }

    @Override
    public Publisher getRawById(Long id) {
        return publisherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Publisher not found"));
    }
}