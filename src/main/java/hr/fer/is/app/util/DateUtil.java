package hr.fer.is.app.util;

import hr.fer.is.app.domain.dto.PrintJobDTO;
import hr.fer.is.app.service.impl.PrintJobServiceImpl;

import javax.validation.ValidationException;
import java.time.LocalDate;
import java.time.ZoneId;


public class DateUtil {
    public static void check(PrintJobDTO value) {
        LocalDate today = LocalDate.now();
        LocalDate deadlineToCompare = value.getDeadline().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate startDateToCompare = value.getStartDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        if(!(value.getStartDate().before(value.getDeadline()) && deadlineToCompare.isAfter(today) && (startDateToCompare.isEqual(today) || startDateToCompare.isAfter(today)))){
            throw new ValidationException("Invalid dates");
        }
    }
}
