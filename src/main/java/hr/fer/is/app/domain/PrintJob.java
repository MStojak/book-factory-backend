package hr.fer.is.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import javax.persistence.Column;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * A PrintJob.
 */
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PrintJob  {


    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Long id;

    @NotNull(message = "must not be null")
    @Column(name = "book_title")
    private String bookTitle;

    @NotNull(message = "must not be null")
    @Column(name = "writer_name")
    private String writerName;

    @NotNull(message = "must not be null")
    @Column(name = "page_number")
    private Integer pageNumber;

    @NotNull(message = "must not be null")
    @Column(name = "edition_number")
    private Integer editionNumber;

    @Column(name = "description")
    private String description;

    @NotNull(message = "must not be null")
    @Column(name = "start_date")
    private LocalDate startDate;

    @NotNull(message = "must not be null")
    @Column(name = "deadline")
    private LocalDate deadline;

    @NotNull(message = "must not be null")
    @Column(name = "archive")
    private Boolean archive;

    @Transient
    @JsonIgnoreProperties(value = { "printJobs" }, allowSetters = true)
    private Publisher publisher;

    @Transient
    @JsonIgnoreProperties(value = { "printJobs" }, allowSetters = true)
    private CoverType coverType;

    @Column(name = "publisher_id")
    private Long publisherId;

    @Column(name = "cover_type_id")
    private Long coverTypeId;
}