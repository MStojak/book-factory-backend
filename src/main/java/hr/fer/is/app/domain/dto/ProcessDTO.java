package hr.fer.is.app.domain.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProcessDTO {
    private Integer id;
    private String name;
    private String description;
}