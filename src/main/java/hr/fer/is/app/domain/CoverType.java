package hr.fer.is.app.domain;

import lombok.Data;
import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "cover_type")

public class CoverType implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "must not be null")
    @Column(name = "name")
    private String name;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final CoverType coverType = (CoverType) o;

        return id.equals(coverType.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
