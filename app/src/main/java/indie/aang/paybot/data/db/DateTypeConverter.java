package indie.aang.paybot.data.db;


import androidx.room.TypeConverter;

import java.util.Date;


/**
 * Created by AangJnr on 18, September, 2018 @ 5:52 PM
 * Work Mail cibrahim@grameenfoundation.org
 * Personal mail aang.jnr@gmail.com
 */

public class DateTypeConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestampInMillis(Date date) {


        return date == null ? null : date.getTime();
    }
}