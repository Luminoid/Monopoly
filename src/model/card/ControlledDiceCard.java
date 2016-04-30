package model.card;

/**
 * Created by Ethan on 16/4/27.
 */
public class ControlledDiceCard extends Card {

    public ControlledDiceCard() {
        this.name = "遥控骰子";
        this.description = "使用时可以任意控制骰子的结果,结果只能是 1-6";
    }

    @Override
    public boolean use() {
        return false;
    }
}
