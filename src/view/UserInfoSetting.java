package view;

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
                int num = scanner.nextInt();
                if (num >= 2 && num <= 4){
                    Kernal.createInstance(num);
                    break;
                }else {
                    System.out.println("您输入的玩家个数不在范围内，请重新输入：");
                }
            } catch (Exception ex) {
                System.out.println("您的输入格式不正确，请重新输入：");
            }
        }
        // Cash
        System.out.println("请输入玩家的初始现金(默认为5000)：");
        double cash;
        while (true){
            try {
                System.out.print(">> ");
                if (!scanner.hasNext()){
                    cash = 5000.0;
                } else {
                    cash = scanner.nextDouble();
                }
                break;
            } catch (Exception ex){
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
                if (!scanner.hasNext()){
                    Kernal.getInstance().setDate(2016, 4, 1);
                } else {
                    int year = scanner.nextInt();
                    int month = scanner.nextInt();
                    int day = scanner.nextInt();
                    Kernal.getInstance().setDate(year, month, day);
                }
                break;
            } catch (Exception ex) {
                System.out.println("您的输入格式不正确，请重新输入：");
            }
        }
    }
}
