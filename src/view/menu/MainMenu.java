package view.menu;

import model.Player;
import util.Orientation;
import view.map.MapView;
import view.util.TuiInput;

import java.util.Scanner;

/**
 * Created by Ethan on 16/4/29.
 */
public class MainMenu {
    public static void displayMainMenu(Player player) {
        System.out.println("-----------------------------------------------------------------");
        String orientationStr = player.getOrientation().equals(Orientation.FORWARD) ? "顺时针" : "逆时针";
        System.out.println("现在是玩家 " + player.getName() + " 的操作时间，您的前进方向是" + orientationStr + "。");
        System.out.println("您现在可以执行如下操作：");
        System.out.println("0 - 查看地图");
        System.out.println("1 - 查看原始地图");
        System.out.println("2 - 使用道具");
        System.out.println("3 - 前方10步内示警");
        System.out.println("4 - 查看前后指定步数的具体信息");
        System.out.println("5 - 查看玩家的资产信息");
        System.out.println("6 - 扔骰子");
        System.out.println("7 - 认输！");
        System.out.println("请选择：");

        int choiceNum = TuiInput.readInt(new Scanner(System.in), 0, 7);
        switch (choiceNum) {
            case 0:
                MapView.printMapwithPlayer();
                break;
            case 1:
                MapView.printMap();
                break;
            case 2:
                displayCardMenu(player);
                break;
            case 3:
                showWarning(player);
                break;
            case 4:
                showSpotInfo();
                break;
            case 5:
                showPropertyInfo();
                break;
            case 6:
                rollDice();
                break;
            case 7:
                player.giveUp();
                break;
        }
    }

    private static void displayCardMenu(Player player) {

    }

    private static void showWarning(Player player) {

    }

    private static void showSpotInfo(Player player) {
        int distanceNum = TuiInput.readInt(new Scanner(System.in), -10, 10);

        System.out.println();
        MapView.getPlayerSpot(player);
    }
}
