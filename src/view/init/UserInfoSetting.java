package view.init;

import model.Kernal;
import model.Player;

import java.util.Scanner;

/**
 * Created by Ethan on 16/4/27.
 */
public class UserInfoSetting {
    public static void userInfoInput() {
        Scanner scanner = new Scanner(System.in);
        // Player Number
        System.out.println("请输入游戏的玩家人数(2-4)：");
        while (true) {
            try {
                System.out.print(">> ");
                int num = Integer.parseInt(scanner.nextLine());
                if (num >= 2 && num <= 4) {
                    Kernal.createInstance(num);
                    break;
                } else {
                    System.out.println("您输入的玩家个数不在范围内，请重新输入：");
                }
            } catch (Exception ex) {
                System.out.println("您的输入格式不正确，请重新输入：");
            }
        }
        // Cash
        System.out.println("请输入玩家的初始现金(默认为5000)：");
        double cash;
        while (true) {
            try {
                System.out.print(">> ");
                String s = scanner.nextLine();
                if (s.length() == 0) {
                    cash = 5000.0;
                } else {
                    cash = Double.parseDouble(s);
                }
                break;
            } catch (Exception ex) {
                System.out.println("您的输入格式不正确，请重新输入：");
            }
        }
        // Player name
        for (int i = 1; i <= Kernal.getInstance().getPlayerNum(); i++) {
            System.out.println("请输入玩家" + i + "的名字：");
            System.out.print(">> ");
            String name = scanner.nextLine().trim();
            Kernal.getInstance().addPlayer(new Player(name, cash));
        }
        // Start time
        System.out.println("请输入游戏的开始时间，格式: yyyy mm dd。默认为2016 4 1");
        while (true) {
            try {
                System.out.print(">> ");

                String s = scanner.nextLine();
                if (s.length() == 0) {
                    Kernal.getInstance().setDate(2016, 4, 1);
                } else {
                    String[] dateArr = s.split(" ");
                    int year = Integer.parseInt(dateArr[0]);
                    int month = Integer.parseInt(dateArr[1]);
                    int day = Integer.parseInt(dateArr[2]);
                    Kernal.getInstance().setDate(year, month, day);
                }
                break;
            } catch (Exception ex) {
                System.out.println("您的输入格式不正确，请重新输入：");
            }
        }
        // End time
        System.out.println("请输入游戏的结束时间，格式: yyyy mm dd。默认为2016 8 31");
        while (true) {
            try {
                System.out.print(">> ");

                String s = scanner.nextLine();
                if (s.length() == 0) {
                    Kernal.getInstance().setEndDate(2016, 8, 1);
                } else {
                    String[] dateArr = s.split(" ");
                    int year = Integer.parseInt(dateArr[0]);
                    int month = Integer.parseInt(dateArr[1]);
                    int day = Integer.parseInt(dateArr[2]);
                    Kernal.getInstance().setEndDate(year, month, day);
                }
                break;
            } catch (Exception ex) {
                System.out.println("您的输入格式不正确，请重新输入：");
            }
        }
    }
}
