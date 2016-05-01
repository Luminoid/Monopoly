package util;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Ethan on 16/5/1.
 */
public class DateTool {
    public static boolean isEndOfMonth(GregorianCalendar date) {
        int month1 = date.get(Calendar.MONTH);
        date.add(Calendar.DATE, 1);
        int month2 = date.get(Calendar.MONTH);
        date.add(Calendar.DATE, -1);

        return month1 != month2;
    }

    public static boolean isWeekday(GregorianCalendar date) {
        return !(date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
    }
}
