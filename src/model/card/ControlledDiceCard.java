package model.card;

import action.command.CommandType;
import action.command.SimpleCommamdFactory;
import action.request.ControlledDiceRequest;
import model.Dice;
import model.Player;

/**
 * Created by Ethan on 16/4/27.
 */
public class ControlledDiceCard extends Card {
    int diceValue;

    public ControlledDiceCard() {
        this.name = "遥控骰子";
        this.description = "使用时可以任意控制骰子的结果,结果只能是 1-6";
        this.value = 3;
        this.cardType = CardType.CONTROLLED_DICE_CARD;
    }

    @Override
    public boolean use(Player player) {
        ControlledDiceRequest request = (ControlledDiceRequest) SimpleCommamdFactory.createCommand(CommandType.CONTROLLED_DICE_REQUEST);
        request.setQuestionStr("您正在使用遥控骰子，请输入您要控制的点数(1~6)：");
        Dice.getInstance().setValue(request.getAnswer());
        return true;
    }

    public int getDiceValue() {
        return diceValue;
    }

    public void setDiceValue(int diceValue) {
        this.diceValue = diceValue;
    }
}
