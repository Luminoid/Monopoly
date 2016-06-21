package tui.menu;

import model.Player;
import util.FormatTool;
import tui.util.TuiInput;

import java.util.Scanner;

/**
 * Created by Ethan on 16/5/1.
 */
public class BankMenu {
    public static void displayBankMenu(Player player) {
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        while (true) {
            System.out.println("欢迎来到银行，您有现金¥" +
                    FormatTool.formatMoney(player.getCash()) + "元，存款¥" + FormatTool.formatMoney(player.getDeposit()) + "元。");
            System.out.println("您现在可以执行如下操作：");
            System.out.println("1 - 存款");
            System.out.println("2 - 取款");
            System.out.println("0 - 退出银行");

            int choiceNum = TuiInput.readInt(new Scanner(System.in), 0, 2);
            switch (choiceNum) {
                case 1:
                    depositMoney(player);
                    break;
                case 2:
                    withdrawMoney(player);
                    break;
                default:
                    return;
            }
        }
    }

    private static void depositMoney(Player player) {
        System.out.println("请输入您想存入的现金：");
        double num = TuiInput.readDouble(new Scanner(System.in));
        if (num > player.getCash()) {
            System.out.println("您没有足够的现金");
        } else {
            player.setCash(player.getCash() - num);
            player.setDeposit(player.getDeposit() + num);
        }
    }

    private static void withdrawMoney(Player player) {
        System.out.println("请输入您想取出的现金：");
        double num = TuiInput.readDouble(new Scanner(System.in));
        if (num > player.getCash()) {
            System.out.println("您没有足够的存款");
        } else {
            player.setCash(player.getCash() + num);
            player.setDeposit(player.getDeposit() - num);
        }
    }
}
