package action.event;

import action.command.CommandType;
import action.command.PromptCommand;
import action.command.SimpleCommandFactory;
import model.Player;
import model.spot.EstateSpot;
import util.EstateAction;
import util.FormatTool;

/**
 * Created by Ethan on 16/4/30.
 */
public class EstateEvent extends Event {
    private EstateSpot spot;
    private double fee;
    private EstateAction estateAction;

    @Override
    public void toggle(Player player) {
        PromptCommand command = (PromptCommand) SimpleCommandFactory.createCommand(CommandType.PROMPT_COMMAND);
        switch (getEstateAction()) {
            case PURCHASE:
                command.setCommandStr(player.getName() + " 已花¥" + FormatTool.formatMoney(getFee()) + "购买" +
                        getSpot().getLocName());
                break;
            case LEVEL_UP:
                command.setCommandStr(player.getName() + " 已花¥" + FormatTool.formatMoney(getFee()) + "升级" +
                        getSpot().getLocName() + "至" + getSpot().getLevel() + "级");
                break;
            case PAY_FEE:
                command.setCommandStr(player.getName() + " 路过" + getSpot().getLocName() + "，支付¥" +
                        FormatTool.formatMoney(getFee()) + "过路费");
                break;
        }
    }

    public EstateSpot getSpot() {
        return spot;
    }

    public EstateEvent setSpot(EstateSpot spot) {
        this.spot = spot;
        return this;
    }

    public double getFee() {
        return fee;
    }

    public EstateEvent setFee(double fee) {
        this.fee = fee;
        return this;
    }

    public EstateAction getEstateAction() {
        return estateAction;
    }

    public EstateEvent setEstateAction(EstateAction estateAction) {
        this.estateAction = estateAction;
        return this;
    }
}
