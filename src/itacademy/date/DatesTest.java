package itacademy.date;//package test.by.itacademy.date;
//
//
//import org.junit.Test;
//
//import java.sql.Date;
//import java.time.Clock;
//import java.time.Duration;
//import java.time.Instant;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.time.OffsetDateTime;
//import java.time.OffsetTime;
//import java.time.Period;
//import java.time.ZoneId;
//import java.time.ZonedDateTime;
//import java.time.format.DateTimeFormatter;
//import java.time.temporal.ChronoField;
//import java.time.temporal.ChronoUnit;
//import java.util.Locale;
//
//public class DatesTest extends LocaleTest {
//
//    public DatesTest() {
//        super();
//    }
//
//    @Test
//    public void zonedDateTimeTest1() {
//        ZonedDateTime now = ZonedDateTime.now();
//        System.out.println(now);
//        System.out.println(ZonedDateTime.now(Clock.systemUTC()));
//        System.out.println(ZonedDateTime.now(ZoneId.of("Europe/Moscow")));
//        System.out.println(ZoneId.getAvailableZoneIds());
//    }
//
//    @Test
//    public void zonedDateTimeTest2() {
//        ZonedDateTime now = ZonedDateTime.now();
//        System.out.println(now);
//        System.out.println(now.get(ChronoField.YEAR));
//        System.out.println(now.getDayOfYear());
//        System.out.println(now.getDayOfWeek());
//        System.out.println(now.minus(5L, ChronoUnit.DAYS));
//        System.out.println(now.truncatedTo(ChronoUnit.HOURS));
//    }
//
//    @Test
//    public void instantTest() {
//        System.out.println(Instant.now());
//    }
//
//    @Test
//    public void localTests() {
//        System.out.println(LocalDate.now());
//        System.out.println(LocalTime.now());
//        System.out.println(LocalDateTime.now());
//    }
//
//    @Test
//    public void offsetTests() {
//        System.out.println(OffsetTime.now());
//        System.out.println(OffsetDateTime.now());
//    }
//
//    @Test
//    public void testPeriod() {
//        LocalDate startDate = LocalDate.now().minusDays(5L).minusMonths(1L);
//        LocalDate endDate = LocalDate.now();
//        Period between = Period.between(startDate, endDate);
//        System.out.println(between.getYears());
//        System.out.println(between.getMonths());
//        System.out.println(between.getDays());
//        System.out.println(ChronoUnit.WEEKS.between(startDate, endDate));
//    }
//
//    @Test
//    public void testDuration() {
//        LocalDateTime start = LocalDateTime.now().minus(12L, ChronoUnit.HOURS);
//        LocalDateTime end = LocalDateTime.now();
//        Duration between = Duration.between(start, end);
//        System.out.println(between.toString());
//        System.out.println(between.getSeconds());
//    }
//
//    @Test
//    public void testDateTimeFormatter() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a", Locale.CANADA);
//        System.out.println(formatter.format(LocalDateTime.now()));
////        System.out.println(LocalDateTime.parse("14-09-2018 12:48 AM", formatter));
////        System.out.println(LocalDate.parse("14-09-2018 12:48", formatter));
////        System.out.println(LocalTime.parse("14-09-2018 12:48", formatter));
////        System.out.println(LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT));
//    }
//
//    @Test
//    public void oldDateTimeApi() {
//        Date date = new Date(12341234345L);
//        System.out.println(date.toLocalDate());
//        System.out.println(Date.valueOf(LocalDate.now()));
//    }
//}
