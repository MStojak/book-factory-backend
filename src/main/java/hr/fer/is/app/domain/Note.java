package hr.fer.is.app.domain;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String noteText;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Note note = (Note) o;

        if (!id.equals(note.id)) return false;
        if (!noteText.equals(note.noteText)) return false;
        return user.equals(note.user);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + noteText.hashCode();
        result = 31 * result + user.hashCode();
        return result;
    }
}
