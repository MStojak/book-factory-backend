package hr.fer.is.app.domain.dto;

import hr.fer.is.app.domain.CoverType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link CoverType} entity.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CoverTypeDTO implements Serializable {

    private Long id;

    @NotNull(message = "must not be null")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CoverTypeDTO)) {
            return false;
        }

        CoverTypeDTO coverTypeDTO = (CoverTypeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, coverTypeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "CoverTypeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
