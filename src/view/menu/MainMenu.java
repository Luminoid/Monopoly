package view.menu;

import model.Dice;
import model.Kernal;
import model.Player;
import model.card.Card;
import model.card.SimpleCardFactory;
import model.spot.Spot;
import model.spot.SpotType;
import util.PlayerOrientation;
import view.map.MapView;
import view.util.TuiInput;
import view.util.TuiOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Ethan on 16/4/29.
 */
public class MainMenu {
    public static void displayMainMenu(Player player) {
        boolean opt = true;
        while (opt) {
            System.out.println("-----------------------------------------------------------------");
            String orientationStr = player.getOrientation().equals(PlayerOrientation.FORWARD) ? "顺时针" : "逆时针";
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
                    showSpotInfo(player);
                    break;
                case 5:
                    showPropertyInfo();
                    break;
                case 6:
                    advance(player);
                    opt = false;
                    break;
                case 7:
                    player.giveUp();
                    opt = false;
                    break;
            }
        }
    }

    private static void displayCardMenu(Player player) {
        ArrayList<Card> usableCards = new ArrayList<>();
        player.getCards().keySet().stream().forEach(e -> usableCards.add(SimpleCardFactory.createCard(e)));
        int cardNum = usableCards.size();
        if (cardNum == 0) {
            System.out.println("您现在没有道具卡可以使用");
        } else {
            System.out.println("您现在拥有的道具如下:");
            for (int i = 0; i < cardNum; i++) {
                TuiOutput.printTable(i + "、" + usableCards.get(i).getName() + " × " +
                        player.getCards().get(usableCards.get(i).getCardType()) + " ");
            }
            System.out.println("\n请输入您想要使用的卡片编号<输入h获得帮助，输入x返回上一层>：");
            int choice = TuiInput.readCard(new Scanner(System.in), cardNum);
            if (choice == -1) {
                // Do nothing
            } else if (choice == 99) {
                usableCards.stream().
                        map(e -> (e.getName() + "： \t" + e.getDescription())).forEach(System.out::println);
            } else {
                Card usedCard = usableCards.get(choice);
                if (usedCard.use(player)) {
                    player.useCard(usedCard.getCardType());
                } else {
                    System.out.println("本情形不能使用该卡片");
                }
            }
        }
    }

    private static void showWarning(Player player) {
        for (int i = 1; i <= 10; i++) {
            if (MapView.getDistantSpot(player, i).isBlocked()) {
                System.out.println("前方" + i + "步内有路障");
            }
        }
    }

    private static void showSpotInfo(Player player) {
        System.out.println("请输入要查看的位置(前后10格)：");
        int distanceNum = TuiInput.readInt(new Scanner(System.in), -10, 10);
        int position = player.checkPosition(distanceNum);
        Spot spot = Kernal.getInstance().getMap().getSpots().get(position);
        spot.getSpotInfo().stream().forEach(System.out::println);
    }

    private static void showPropertyInfo() {
        String[] title = {"玩家名 ", "现金", "存款", "点券", "房产总额", "资产总额"};
        Arrays.asList(title).stream().forEach(TuiOutput::printTable);
        System.out.println();
        Kernal.getInstance().getPlayers().stream().forEach(e -> {
            String[] info = new String[6];
            info[0] = e.getName();
            info[1] = String.format("%.1f", e.getCash());
            info[2] = String.format("%.1f", e.getDeposit());
            info[3] = String.valueOf(e.getTicket());
            double estateValue =
                    e.getHouses().stream().map(house -> house.getBasePrice() * house.getLevel()).reduce(0.0, (a, b) -> a + b);
            info[4] = String.format("%.1f", estateValue);
            info[5] = String.format("%.1f", e.getCash() + e.getDeposit() + estateValue);

            Arrays.asList(info).stream().forEach(TuiOutput::printTable);
            System.out.println();
        });
    }

    private static void advance(Player player) {
        int diceValue = Dice.getInstance().rollDice();
        int step;
        for (step = 0; step < diceValue; step++) {
            if (MapView.getPlayerSpot(player).getSpotType()== SpotType.BankSpot){
                MapView.getPlayerSpot(player).passByEvent(player);
            } else if (MapView.getDistantSpot(player, 1).isBlocked()) {
                MapView.getDistantSpot(player, 1).setBlocked(false);
                player.move();
                step++;
                break;
            }
            player.move();
        }
        System.out.println("玩家 " + player.getName() + " 前进了" + step + "步");
        MapView.getPlayerSpot(player).arriveEvent(player);
    }
}
