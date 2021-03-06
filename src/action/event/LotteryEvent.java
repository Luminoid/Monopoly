package action.event;

import action.command.CommandType;
import action.command.PromptCommand;
import action.command.SimpleCommandFactory;
import action.request.IntRangeRequest;
import action.request.YesOrNoRequest;
import javafx.application.Platform;
import model.LotterySystem;
import model.Player;

/**
 * Created by Ethan on 16/4/30.
 */
public class LotteryEvent extends Event {
    @Override
    public void toggle(Player player) {
            if (player.getCash() >= 100) {
                YesOrNoRequest request = (YesOrNoRequest) SimpleCommandFactory.createCommand(CommandType.YES_OR_NO_REQUEST);
                request.setQuestionStr("您现在位于彩票店，是否花费¥100购买彩票？");
                if (request.getAnswer()) {
                    Platform.runLater(() -> {
                        IntRangeRequest intRangeRequest =
                                (IntRangeRequest) SimpleCommandFactory.createCommand(CommandType.INT_RANGE_REQUEST);
                        intRangeRequest.setFloor(0).setCeiling(99).setQuestionStr("请输入您要购买的点数(0~99)：");
                        LotterySystem.buyLottery(player, intRangeRequest.getAnswer());
                    });
                }
            } else {
                PromptCommand command = (PromptCommand) SimpleCommandFactory.createCommand(CommandType.PROMPT_COMMAND);
                command.setCommandStr("您的资金不足以买彩票");
            }

    }
}
