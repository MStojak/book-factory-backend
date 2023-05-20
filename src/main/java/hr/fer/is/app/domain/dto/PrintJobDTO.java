package hr.fer.is.app.domain.dto;

import hr.fer.is.app.domain.PrintJob;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link PrintJob} entity.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PrintJobDTO implements Serializable {

    private Long id;

    @NotNull(message = "must not be null")
    private String bookTitle;

    @NotNull(message = "must not be null")
    private String writerName;

    @NotNull(message = "must not be null")
    private Integer pageNumber;

    @NotNull(message = "must not be null")
    private Integer editionNumber;

    private String description;

    @NotNull(message = "must not be null")
    private LocalDate startDate;

    @NotNull(message = "must not be null")
    private LocalDate deadline;

    @NotNull(message = "must not be null")
    private Boolean archive;

    private PublisherDTO publisher;

    private CoverTypeDTO coverType;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PrintJobDTO)) {
            return false;
        }

        PrintJobDTO printJobDTO = (PrintJobDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, printJobDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "PrintJobDTO{" +
            "id=" + getId() +
            ", bookTitle='" + getBookTitle() + "'" +
            ", writerName='" + getWriterName() + "'" +
            ", pageNumber=" + getPageNumber() +
            ", editionNumber=" + getEditionNumber() +
            ", description='" + getDescription() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", deadline='" + getDeadline() + "'" +
            ", archive='" + getArchive() + "'" +
            ", publisher=" + getPublisher() +
            ", coverType=" + getCoverType() +
            "}";
    }
}
