package view.menu;

import model.Kernal;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Ethan on 16/4/29.
 */
public class RoundStartMenu {
    public static void displayRoundMenu() {
        GregorianCalendar date = Kernal.getInstance().getDate();
        System.out.println
                ("今天是" + date.get(Calendar.YEAR) + "年" + (date.get(Calendar.MONTH) + 1) + "月" + date.get(Calendar.DAY_OF_MONTH) + "日");
        System.out.println("================================================================================\n");
    }
}
