package hr.fer.is.app.converter;


import hr.fer.is.app.domain.Publisher;
import hr.fer.is.app.domain.dto.PublisherDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

    Publisher toEntity(PublisherDTO publisherDTO);

    PublisherDTO toDto(Publisher publisher);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(PublisherDTO dto, @MappingTarget Publisher entity);
}
