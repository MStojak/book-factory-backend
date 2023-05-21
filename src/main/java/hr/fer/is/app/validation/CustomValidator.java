package hr.fer.is.app.validation;

import hr.fer.is.app.domain.dto.PrintJobDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZoneId;

public class CustomValidator implements ConstraintValidator<CustomValidation, PrintJobDTO> {

    @Override
    public boolean isValid(@NotNull PrintJobDTO value, ConstraintValidatorContext context) {
        LocalDate today = LocalDate.now();
        LocalDate deadlineToCompare = value.getDeadline().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate startDateToCompare = value.getStartDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return value.getStartDate().before(value.getDeadline()) && deadlineToCompare.isAfter(today) && (startDateToCompare.isEqual(today) || startDateToCompare.isAfter(today));
    }
}
