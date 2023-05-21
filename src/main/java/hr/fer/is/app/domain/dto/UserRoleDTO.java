package hr.fer.is.app.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Data
public class UserRoleDTO implements Serializable {

    private Long id;

    @NotNull(message = "must not be null")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserRoleDTO)) {
            return false;
        }

        UserRoleDTO userRoleDTO = (UserRoleDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, userRoleDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserRoleDTO{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                "}";
    }
}
