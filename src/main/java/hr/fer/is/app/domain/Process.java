package hr.fer.is.app.domain;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "process")
public class Process {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Process process = (Process) o;

        return id.equals(process.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
