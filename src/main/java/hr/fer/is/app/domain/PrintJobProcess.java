package hr.fer.is.app.domain;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "print_job_process")
public class PrintJobProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private PrintJob printJob;

    @ManyToOne
    @JoinColumn(name = "process_id", nullable = false)
    private Process process;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final PrintJobProcess that = (PrintJobProcess) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
