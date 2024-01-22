package am.itspace.mytodo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateUtil {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    public static Date stringToDate(String date) throws ParseException {
        return SDF.parse(date);
    }

    public static String dateToString(Date date) {
        return SDF.format(date);
    }

}
