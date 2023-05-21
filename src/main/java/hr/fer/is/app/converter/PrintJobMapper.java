package hr.fer.is.app.converter;

import hr.fer.is.app.domain.PrintJob;
import hr.fer.is.app.domain.dto.PrintJobDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = CentralConfig.class, componentModel = "spring")
public interface PrintJobMapper {

    @Mapping(source = "publisher.id", target = "publisherId")
    @Mapping(source = "coverType.id", target = "coverTypeId")
    PrintJobDTO toDTO(PrintJob printJob);

    @InheritInverseConfiguration
    PrintJob toEntity(PrintJobDTO printJobDTO);

    @Mapping(target = "publisher", ignore = true)
    @Mapping(target = "coverType", ignore = true)
    void updateEntityFromDto(PrintJobDTO printJobDTO, @MappingTarget PrintJob printJob);
}
