package hr.fer.is.app.domain;

import lombok.Data;
import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "print_job")
public class PrintJob {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String bookTitle;

    @Column(nullable = false)
    private String writerName;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @Column(nullable = false)
    private Integer pageNumber;

    @ManyToOne
    @JoinColumn(name = "cover_type_id", nullable = false)
    private CoverType coverType;

    @Column(nullable = false)
    private Integer editionNumber;

    private String description;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date deadline;

    @Column(nullable = false)
    private Boolean archive;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final PrintJob printJob = (PrintJob) o;

        return id.equals(printJob.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}