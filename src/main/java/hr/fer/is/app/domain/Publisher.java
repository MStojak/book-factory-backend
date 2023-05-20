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
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Publisher.
 */
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Publisher implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Long id;

    @NotNull(message = "must not be null")
    @Column(name = "name")
    private String name;

    @NotNull(message = "must not be null")
    @Column(name = "oib")
    private String oib;

    @Transient
    @JsonIgnoreProperties(value = { "publisher", "coverType" }, allowSetters = true)
    private Set<PrintJob> printJobs = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Publisher)) {
            return false;
        }
        return id != null && id.equals(((Publisher) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Publisher{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", oib='" + getOib() + "'" +
            "}";
    }
}
