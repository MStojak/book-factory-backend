package hr.fer.is.app.converter;


import hr.fer.is.app.domain.Publisher;
import hr.fer.is.app.domain.dto.PublisherDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Publisher} and its DTO {@link PublisherDTO}.
 */
@Mapper(componentModel = "spring")
public interface PublisherMapper extends EntityMapper<PublisherDTO, Publisher> {}
