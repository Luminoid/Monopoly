package model.card;

import action.command.CommandType;
import action.command.PromptCommand;
import action.command.SimpleCommandFactory;
import action.request.IntRangeRequest;
import model.Dice;
import model.Player;

/**
 * Created by Ethan on 16/4/27.
 */
public class ControlledDiceCard extends Card {

    public ControlledDiceCard() {
        this.name = "遥控骰子";
        this.description = "使用时可以任意控制骰子的结果,结果只能是 1-6";
        this.value = 3;
        this.cardType = CardType.CONTROLLED_DICE_CARD;
    }

    @Override
    public boolean use(Player player) {
        IntRangeRequest request = (IntRangeRequest) SimpleCommandFactory.createCommand(CommandType.INT_RANGE_REQUEST);
        request.setFloor(1).setCeiling(6).setQuestionStr("您正在使用遥控骰子，请输入您要控制的点数(1~6)：");
        Dice.getInstance().setValue(request.getAnswer());
        PromptCommand command = (PromptCommand) SimpleCommandFactory.createCommand(CommandType.PROMPT_COMMAND);
        command.setCommandStr("已成功控制点数");
        return true;
    }
}
