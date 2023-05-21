package hr.fer.is.app.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serial;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String address;

    private String phone;

    private Date dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private UserRole role;

    @Column(nullable = false)
    private Boolean retired;

    private Date dateOfEmployment;

    private char gender;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        return id != null && id.equals(((User) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
