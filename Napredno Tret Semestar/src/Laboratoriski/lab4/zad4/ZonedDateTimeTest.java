package Laboratoriski.lab4.zad4;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeTest {
    public static void main(String[] args) {
        System.out.println(zonedDateTimeOf());
        System.out.println(zonedDateTimeParse());
        System.out.println(zonedDateTimeFormat());
        System.out.println(toPST());
        System.out.println(sameInstantAs());
        System.out.println(sameLocalAs());
    }

    static ZonedDateTime zonedDateTimeOf() {

        ZonedDateTime zdt = ZonedDateTime.of(2015,07,10,2,14,25,000, ZoneId.of("Asia/Tokyo"));
        return zdt;
    }

    static ZonedDateTime zonedDateTimeParse() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS z");
        ZonedDateTime zdt = ZonedDateTime.parse("2015-06-18 23:07:25.000 Asia/Tokyo", formatter);
        return zdt;
    }

    static String zonedDateTimeFormat() {
        ZonedDateTime zdt = DateAndTimes.ZDT_20150618_23073050;
        String zdtFormat = zdt.format(DateTimeFormatter.ISO_DATE_TIME);
        zdtFormat = zdtFormat.replaceAll("-","_");
        zdtFormat = zdtFormat.replace("[Asia/Tokyo]", "");
        zdtFormat = zdtFormat.replace("T", "_");
        zdtFormat = zdtFormat.replace("+09:00", "_JST");
        zdtFormat = zdtFormat.replaceAll(":","_");
        zdtFormat = zdtFormat.replace(".5", "");
        return zdtFormat;
    }

    static ZonedDateTime toPST() {
        LocalDateTime ldt = DateAndTimes.LDT_20150618_23073050;
        String ldtFormat = ldt.format(DateTimeFormatter.ISO_DATE_TIME);
        ZonedDateTime zdt = ZonedDateTime.parse(ldtFormat + "-07:00[America/Los_Angeles]");
        return zdt;
    }

    static ZonedDateTime sameInstantAs() {
        ZonedDateTime zdt = DateAndTimes.ZDT_20150618_23073050;
        ZonedDateTime date = zdt.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));
        String zdtFormat = date.format(DateTimeFormatter.ISO_DATE_TIME);
        ZonedDateTime formatDate = ZonedDateTime.parse(zdtFormat);
        return formatDate;
    }

    static ZonedDateTime sameLocalAs() {
        ZonedDateTime zdt = DateAndTimes.ZDT_20150618_23073050;
        ZonedDateTime date = zdt.withZoneSameLocal(ZoneId.of("America/Los_Angeles"));
        String zdtFormat = date.format(DateTimeFormatter.ISO_DATE_TIME);
        ZonedDateTime formatDate = ZonedDateTime.parse(zdtFormat);
        return formatDate;
    }

    static class DateAndTimes {
        public static final LocalDateTime LDT_20150618_23073050 = LocalDateTime.of(2015, 6, 18, 23, 7, 30, 500000000);
        public static final ZonedDateTime ZDT_20150618_23073050 = ZonedDateTime.of(LDT_20150618_23073050, ZoneId.of("Asia/Tokyo"));
    }
}
