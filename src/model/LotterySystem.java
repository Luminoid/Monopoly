package model;

import action.command.CommandType;
import action.command.PromptCommand;
import action.command.SimpleCommamdFactory;
import util.Tool;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Ethan on 16/4/30.
 */
public class LotterySystem {
    private static Hashtable<Player, Integer> lotteryRecord = new Hashtable<>();
    private static double lotteryPrize;
    private static int winningNumber;

    public static void buyLottery(Player player, int num) {
        lotteryRecord.put(player, num);
        player.pay(100);
    }

    public static void drawLottery() {
        lotteryPrize = (Math.random() * 10000) + 5000;
        if (winningNumber != -1) {
            winningNumber = (int) (Math.random() * 100);
        }

        List<Player> lotteryWinners = lotteryRecord.entrySet().stream().filter(e -> e.getValue() == winningNumber).
                map(Map.Entry::getKey).distinct().collect(Collectors.toList());
        int winnerNum = lotteryWinners.size();
        PromptCommand command = (PromptCommand) SimpleCommamdFactory.createCommand(CommandType.PROMPT_COMMAND);
        if (winnerNum == 0) {
            command.setCommandStr("没有人中奖");
        } else {
            double lotteryPrizePortion = lotteryPrize / winnerNum;
            lotteryWinners.stream().forEach(e -> {
                e.addCash(lotteryPrizePortion);
                command.setCommandStr(e.getName() + "中了" + Tool.formatMoney(lotteryPrizePortion));
            });
        }
        clearRecord();
    }

    public static void clearRecord() {
        lotteryRecord.clear();
        winningNumber = -1;
    }

    public static void setWinningNumber(int winningNumber) {
        LotterySystem.winningNumber = winningNumber;
    }
}
