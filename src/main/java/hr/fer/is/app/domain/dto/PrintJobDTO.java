package hr.fer.is.app.domain.dto;

import hr.fer.is.app.validation.CustomValidation;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@CustomValidation
public class PrintJobDTO {
    private Long id;
    private String bookTitle;
    private String writerName;
    private Long publisherId;
    private Integer pageNumber;
    private Integer coverTypeId;
    private Integer editionNumber;
    private String description;
    private Date startDate;
    private Date deadline;
    private boolean archive;

}
