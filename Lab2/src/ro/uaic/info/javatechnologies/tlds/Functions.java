package ro.uaic.info.javatechnologies.tlds;

import ro.uaic.info.javatechnologies.models.Record;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Functions {
    public static int MINIMUM_MINUTES = 1;

    public static List<Record> filterByCategory(List<Record> list, String category) {
        return list.stream().filter(record -> record.getCategory().equals(category)).collect(Collectors.toList());
    }

    public static String filterByDate(List<Record> list) {
        Date now = new Date();
        return list.stream().filter(record -> ChronoUnit.MINUTES.between(record.getDate().toInstant(), now.toInstant()) < MINIMUM_MINUTES)
                .map(Record::getKey)
                .collect(Collectors.joining(","));
    }
}
