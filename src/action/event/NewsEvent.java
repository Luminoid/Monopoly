package action.event;

import action.command.CommandType;
import action.command.PromptCommand;
import action.command.SimpleCommandFactory;
import model.Kernel;
import model.Player;
import model.card.Card;
import util.FormatTool;

import java.util.stream.Stream;

/**
 * Created by Ethan on 16/4/30.
 */
public class NewsEvent extends Event {
    @Override
    public void toggle(Player player) {
        int newsIndex = (int) (Math.random() * 5);
        switch (newsIndex) {
            case 1:
                newsOneOccur();
                break;
            case 2:
                newsTwoOccur();
                break;
            case 3:
                newsThreeOccur();
                break;
            case 4:
                newsFourOccur();
                break;
            case 5:
                newsFiveOccur();
                break;
        }
    }

    private static void newsOneOccur() {
        double reward = Math.random() * 1000 + 1000;
        PromptCommand command = (PromptCommand) SimpleCommandFactory.createCommand(CommandType.PROMPT_COMMAND);
        int maxHouseNum = Kernel.getInstance().getPlayers().stream().
                max((a, b) -> a.getHouses().size() - b.getHouses().size()).get().getHouses().size();
        Stream<Player> richOwners = Kernel.getInstance().getPlayers().stream().filter(e -> e.getHouses().size() == maxHouseNum);
        richOwners.forEach(e -> {
            e.addCash(reward);
            command.setCommandStr("公开表扬第一地主" + e.getName() + "奖励¥" + FormatTool.formatMoney(reward) + "元");
        });
    }

    private static void newsTwoOccur() {
        double reward = Math.random() * 1000 + 1000;
        PromptCommand command = (PromptCommand) SimpleCommandFactory.createCommand(CommandType.PROMPT_COMMAND);
        int minHouseNum = Kernel.getInstance().getPlayers().stream().
                min((a, b) -> a.getHouses().size() - b.getHouses().size()).get().getHouses().size();
        Stream<Player> poorOwners = Kernel.getInstance().getPlayers().stream().filter(e -> e.getHouses().size() == minHouseNum);
        poorOwners.forEach(e -> {
            e.addCash(reward);
            command.setCommandStr("公开补助土地最少者" + e.getName() + "，¥" + FormatTool.formatMoney(reward) + "元");
        });
    }

    private static void newsThreeOccur() {
        Kernel.getInstance().getPlayers().forEach(e -> e.setDeposit(e.getDeposit() * 1.1));
        PromptCommand command = (PromptCommand) SimpleCommandFactory.createCommand(CommandType.PROMPT_COMMAND);
        command.setCommandStr("银行加发储金红利每个人得到存款10%");
    }

    private static void newsFourOccur() {
        Kernel.getInstance().getPlayers().forEach(e -> e.setDeposit(e.getDeposit() * 0.9));
        PromptCommand command = (PromptCommand) SimpleCommandFactory.createCommand(CommandType.PROMPT_COMMAND);
        command.setCommandStr("所有人缴纳财产税10%");
    }

    private static void newsFiveOccur() {
        Kernel.getInstance().getPlayers().forEach(e -> e.addCard(Card.generateCard()));
        PromptCommand command = (PromptCommand) SimpleCommandFactory.createCommand(CommandType.PROMPT_COMMAND);
        command.setCommandStr("每个人得到一张卡片");
    }
}
