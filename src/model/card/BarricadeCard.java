package model.card;

import action.command.CommandType;
import action.command.PromptCommand;
import action.command.SimpleCommandFactory;
import action.request.IntRangeRequest;
import model.Player;
import view.map.MapView;

/**
 * Created by Ethan on 16/4/27.
 */
public class BarricadeCard extends Card {

    public BarricadeCard() {
        this.name = "路障卡";
        this.description = "可以在前后 8 步之内安放一个路障,任意玩家经过路障时会停在路障所在位置不能前行";
        this.value = 5;
        this.cardType = CardType.BARRICADE_CARD;
    }

    @Override
    public boolean use(Player player) {
        IntRangeRequest request = (IntRangeRequest) SimpleCommandFactory.createCommand(CommandType.INT_RANGE_REQUEST);
        request.setFloor(-8).setCeiling(+8).setQuestionStr("您正在使用路障卡，请输入您要放置路障的位置(-8~8)：");
        PromptCommand command = (PromptCommand) SimpleCommandFactory.createCommand(CommandType.PROMPT_COMMAND);
        if (MapView.getDistantSpot(player, request.getAnswer()).isBlocked()) {
            command.setCommandStr("该位置已有路障");
            return false;
        } else {
            MapView.getDistantSpot(player, request.getAnswer()).setBlocked(true);
            command.setCommandStr("已成功放置路障");
            return true;
        }
    }
}
