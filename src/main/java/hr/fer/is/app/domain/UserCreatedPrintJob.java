package hr.fer.is.app.domain;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "user_created_print_job")
public class UserCreatedPrintJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "print_job_id", nullable = false)
    private PrintJob printJob;

    @Column(nullable = false)
    private Boolean isCoordinator;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final UserCreatedPrintJob that = (UserCreatedPrintJob) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
