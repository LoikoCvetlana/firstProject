package itacademy.date;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static java.time.temporal.ChronoUnit.DAYS;

public class TaskDemo {

    @Test
    public void task1() {
        System.out.println(LocalDate.of(2020, 6, 25));
    }

    @Test
    public void task2() {
        System.out.println(LocalDate.now().plus(3L, ChronoUnit.MONTHS));
    }

    @Test
    public void task3() {
        LocalDate localDate = LocalDate.of(2017, 5, 5);
        System.out.println(String.format("%02d.%02d.%d",
                localDate.get(ChronoField.DAY_OF_MONTH),
                localDate.get(ChronoField.MONTH_OF_YEAR),
                localDate.get(ChronoField.YEAR)
                ));
    }

    @Test
    public void task4() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println(LocalDate.parse("26-03-2014", formatter));
    }

    @Test
    public void task5() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.of(2020, 6, 25);
        System.out.println(DAYS.between(startDate, endDate));
        System.out.println(Period.between(startDate, endDate).getDays());
    }

    @Test
    public void task6() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.of(2020, 6, 25);
        Duration duration = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay());
        System.out.println(duration.getSeconds());

        System.out.println(LocalDateTime.of(startDate, LocalTime.MIDNIGHT));
    }

    @Test
    public void task7() {
        LocalDate now = LocalDate.now();
        System.out.println(now);
        TemporalAdjuster temporalAdjuster =
                date -> date.plus(42L, DAYS);

//        TemporalAdjuster temporalAdjuster2 = new TemporalAdjuster() {
//            @Override
//            public Temporal adjustInto(Temporal temporal) {
//                return temporal.plus(42L, ChronoUnit.DAYS);
//            }
//        };

        System.out.println(now.with(temporalAdjuster));
    }

    @Test
    public void task8() {
        TemporalAdjuster temporalAdjuster =
                date -> {
                    long betweenNextYear = DAYS.between(date, date.with(TemporalAdjusters.firstDayOfNextYear()));
                    long betweenCurrentYear = DAYS.between(date.with(TemporalAdjusters.firstDayOfYear()), date);

                    return betweenCurrentYear < betweenNextYear
                            ? date.with(TemporalAdjusters.firstDayOfYear())
                            : date.with(TemporalAdjusters.firstDayOfNextYear());
                };
        System.out.println(LocalDate.of(2018, 5, 5).with(temporalAdjuster));
    }
}
