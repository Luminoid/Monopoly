package view.util;

/**
 * Created by Ethan on 16/4/30.
 */
public class TuiOutput {
    public static void printTable(String str) {
        if (str.length() < 4)
            System.out.print(str + "\t\t\t\t");
        else if (str.length() >= 4 && str.length() < 8)
            System.out.print(str + "\t\t\t");
        else if (str.length() >= 8 && str.length() < 12)
            System.out.print(str + "\t\t");
        else {
            System.out.print(str + "\t");
        }
    }
}
