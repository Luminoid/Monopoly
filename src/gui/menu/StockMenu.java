package gui.menu;

import model.Kernel;
import model.Player;
import tui.util.TuiInput;
import tui.util.TuiOutput;
import util.FormatTool;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Ethan on 16/5/1.
 */
public class StockMenu {
    public static void displayStockMenu(Player player) {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("欢迎进入股票系统");

        while (true) {
            System.out.println("您现在可以执行以下操作：");
            System.out.println("1 - 查看交易市场里的所有股票");
            System.out.println("2 - 查看自己持有的所有股票");
            System.out.println("3 - 买股票");
            System.out.println("4 - 卖股票");
            System.out.println("0 - 退出");

            int choiceNum = TuiInput.readInt(new Scanner(System.in), 0, 4);
            switch (choiceNum) {
                case 1:
                    displayAllStocks();
                    break;
                case 2:
                    displayOwnStocks(player);
                    break;
                case 3:
                    buyStockMenu(player);
                    break;
                case 4:
                    sellStockMenu(player);
                    break;
                case 0:
                    return;
            }
        }
    }

    public static void displayAllStocks() {
        System.out.println("序号\t\t\t\t股票名\t\t\t每股单价\t\t\t涨跌幅");
        Kernel.getInstance().getStockMarket().getStocks().forEach(e -> {
            String[] info = new String[4];
            info[0] = e.getId() + "";
            info[1] = e.getName();
            info[2] = FormatTool.formatMoney(e.getPrice());
            info[3] = FormatTool.formatRate(e.getFloatRate());
            Arrays.asList(info).stream().forEach(TuiOutput::printTable);
            System.out.println();
        });
    }

    public static void displayOwnStocks(Player player) {
        if (player.getStocks().keySet().size() == 0) {
            System.out.println("您手头没有股票");
            return;
        }
        System.out.println("序号\t\t\t\t股票名\t\t\t每股单价\t\t\t涨跌幅\t\t\t持有数");
        player.getStocks().keySet().forEach(e -> {
            String[] info = new String[5];
            info[0] = e.getId() + "";
            info[1] = e.getName();
            info[2] = FormatTool.formatMoney(e.getPrice());
            info[3] = FormatTool.formatRate(e.getFloatRate());
            info[4] = player.getStocks().get(e) + "";
            Arrays.asList(info).stream().forEach(TuiOutput::printTable);
            System.out.println();
        });
    }

    public static void buyStockMenu(Player player) {
        while (true) {
            try {
                System.out.println("格式：b x n  -->  买入序号为x的股票n股");
                System.out.print(">> ");
                String s = new Scanner(System.in).nextLine().trim();
                String[] sArr = s.split(" ");
                if (sArr[0].equals("b")) {
                    if (Kernel.getInstance().getStockMarket().
                            buyStock(player, Integer.parseInt(sArr[1]), Integer.parseInt(sArr[2]))) {
                        return;
                    }
                } else {
                    System.out.println("输入格式错误");
                }
            } catch (Exception ex) {
                System.out.println("输入格式错误");
            }
        }
    }

    public static void sellStockMenu(Player player) {
        while (true) {
            try {
                System.out.println("格式：s x n  -->  卖出序号为x的股票n股");
                System.out.print(">> ");
                String s = new Scanner(System.in).nextLine().trim();
                String[] sArr = s.split(" ");
                if (sArr[0].equals("s")) {
                    if (Kernel.getInstance().getStockMarket().
                            sellStock(player, Integer.parseInt(sArr[1]), Integer.parseInt(sArr[2]))) {
                        return;
                    }
                } else {
                    System.out.println("输入格式错误");
                }
            } catch (Exception ex) {
                System.out.println("输入格式错误");
            }
        }
    }
}
