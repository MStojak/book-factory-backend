package hr.fer.is.app.service.impl;

import hr.fer.is.app.converter.PublisherMapper;
import hr.fer.is.app.domain.Publisher;
import hr.fer.is.app.domain.dto.PublisherDTO;
import hr.fer.is.app.repository.PublisherRepository;
import hr.fer.is.app.service.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link Publisher}.
 */
@Service
@Transactional
public class PublisherServiceImpl implements PublisherService {

    private final Logger log = LoggerFactory.getLogger(PublisherServiceImpl.class);

    private final PublisherRepository publisherRepository;

    private final PublisherMapper publisherMapper;

    public PublisherServiceImpl(PublisherRepository publisherRepository, PublisherMapper publisherMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherMapper = publisherMapper;
    }

    @Override
    public Mono<PublisherDTO> save(PublisherDTO publisherDTO) {
        log.debug("Request to save Publisher : {}", publisherDTO);
        return publisherRepository.save(publisherMapper.toEntity(publisherDTO)).map(publisherMapper::toDto);
    }

    @Override
    public Mono<PublisherDTO> update(PublisherDTO publisherDTO) {
        log.debug("Request to update Publisher : {}", publisherDTO);
        return publisherRepository.save(publisherMapper.toEntity(publisherDTO)).map(publisherMapper::toDto);
    }

    @Override
    public Mono<PublisherDTO> partialUpdate(PublisherDTO publisherDTO) {
        log.debug("Request to partially update Publisher : {}", publisherDTO);

        return publisherRepository
            .findById(publisherDTO.getId())
            .map(existingPublisher -> {
                publisherMapper.partialUpdate(existingPublisher, publisherDTO);

                return existingPublisher;
            })
            .flatMap(publisherRepository::save)
            .map(publisherMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PublisherDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Publishers");
        return publisherRepository.findAllBy(pageable).map(publisherMapper::toDto);
    }

    public Mono<Long> countAll() {
        return publisherRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PublisherDTO> findOne(Long id) {
        log.debug("Request to get Publisher : {}", id);
        return publisherRepository.findById(id).map(publisherMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Publisher : {}", id);
        return publisherRepository.deleteById(id);
    }
}
