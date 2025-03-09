package dev.invest.utils;

import com.google.protobuf.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DateUtils {

    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        Instant instant = Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos());

        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static java.sql.Timestamp toSqlTimestamp(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        Instant instant = Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos());

        return java.sql.Timestamp.from(instant);
    }
}
