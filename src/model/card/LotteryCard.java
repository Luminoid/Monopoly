package model.card;

import action.command.CommandType;
import action.command.SimpleCommandFactory;
import action.request.IntRangeRequest;
import model.LotterySystem;
import model.Player;

/**
 * Created by Ethan on 16/4/27.
 */
public class LotteryCard extends Card {
    public LotteryCard() {
        this.name = "彩票卡";
        this.description = "使用时可以操作本月的彩票开奖结果";
        this.value = 3;
        this.cardType = CardType.LOTTERY_CARD;
    }

    @Override
    public boolean use(Player player) {
        IntRangeRequest request = (IntRangeRequest) SimpleCommandFactory.createCommand(CommandType.INT_RANGE_REQUEST);
        request.setFloor(0).setCeiling(99).setQuestionStr("您正在使用彩票卡，请输入本月的彩票开奖结果(0~99)：");
        LotterySystem.setWinningNumber(request.getAnswer());
        return true;
    }
}
