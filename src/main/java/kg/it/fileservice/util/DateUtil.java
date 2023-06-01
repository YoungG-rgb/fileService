package kg.it.fileservice.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class DateUtil {

    public static String dayOfMonth(LocalDate date) {
        return String.valueOf(date.getDayOfMonth());
    }

    public static String year(LocalDate date) {
        return String.valueOf(date.getYear());
    }

    public static String month(LocalDate date) {
        return date.getMonth().name();
    }
}
