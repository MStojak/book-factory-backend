package hr.fer.is.app.converter;

import hr.fer.is.app.domain.CoverType;
import hr.fer.is.app.domain.Publisher;
import hr.fer.is.app.domain.dto.CoverTypeDTO;
import hr.fer.is.app.domain.dto.PublisherDTO;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

@MapperConfig
public interface CentralConfig {

    @Mapping(source = "id", target = "publisherId")
    PublisherDTO publisherToPublisherDTO(Publisher publisher);

    @Mapping(source = "id", target = "coverTypeId")
    CoverTypeDTO coverTypeToCoverTypeDTO(CoverType coverType);
}