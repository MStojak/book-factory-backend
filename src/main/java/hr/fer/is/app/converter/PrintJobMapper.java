package hr.fer.is.app.converter;

import hr.fer.is.app.domain.PrintJob;
import hr.fer.is.app.domain.dto.CoverTypeDTO;
import hr.fer.is.app.domain.dto.PrintJobDTO;
import hr.fer.is.app.domain.CoverType;
import hr.fer.is.app.domain.Publisher;
import hr.fer.is.app.domain.dto.PublisherDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link PrintJob} and its DTO {@link PrintJobDTO}.
 */
@Mapper(componentModel = "spring")
public interface PrintJobMapper extends EntityMapper<PrintJobDTO, PrintJob> {
    @Mapping(target = "publisher", source = "publisher", qualifiedByName = "publisherId")
    @Mapping(target = "coverType", source = "coverType", qualifiedByName = "coverTypeId")
    PrintJobDTO toDto(PrintJob s);

    @Named("publisherId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PublisherDTO toDtoPublisherId(Publisher publisher);

    @Named("coverTypeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CoverTypeDTO toDtoCoverTypeId(CoverType coverType);
}
