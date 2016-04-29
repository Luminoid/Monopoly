package view.util;

import java.util.Scanner;

/**
 * Created by Ethan on 16/4/29.
 */
public class TuiInput {
    public static int readInt(Scanner scanner, int floor, int ceiling) {
        while (true) {
            try {
                System.out.print(">> ");
                int num = Integer.parseInt(scanner.nextLine().trim());
                if (num >= floor && num <= ceiling) {
                    return num;
                } else {
                    System.out.println("超出范围：" + floor + "~" + ceiling);
                }
            } catch (NumberFormatException ex) {
                System.out.println("格式错误，请输入整数：");
            }
        }
    }
}
