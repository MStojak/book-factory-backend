package hr.fer.is.app.domain.dto;

import hr.fer.is.app.domain.Publisher;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link Publisher} entity.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PublisherDTO implements Serializable {

    private Long id;

    @NotNull(message = "must not be null")
    private String name;

    @NotNull(message = "must not be null")
    private String oib;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PublisherDTO)) {
            return false;
        }

        PublisherDTO publisherDTO = (PublisherDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, publisherDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PublisherDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", oib='" + getOib() + "'" +
            "}";
    }
}
