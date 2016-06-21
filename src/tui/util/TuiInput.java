package tui.util;

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

    public static double readDouble(Scanner scanner) {
        while (true) {
            try {
                System.out.print(">> ");
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.println("格式错误，请输入整数：");
            }
        }
    }

    public static int readCard(Scanner scanner, int cardNum) {
        while (true) {
            try {
                System.out.print(">> ");
                String s = scanner.nextLine().trim();
                if (s.equals("x")) {
                    return -1;
                } else if (s.equals("h")) {
                    return 99;
                } else {
                    int num = Integer.parseInt(s);
                    if (num >= 0 && num < cardNum)
                        return num;
                    else
                        System.out.println("超出范围：" + 0 + "~" + (cardNum - 1));
                }
            } catch (NumberFormatException ex) {
                System.out.println("格式错误，请输入整数：");
            }
        }
    }
}
