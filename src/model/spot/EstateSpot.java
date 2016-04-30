package model.spot;

import action.command.CommandType;
import action.command.ErrorCommand;
import action.command.PromptCommand;
import action.command.SimpleCommamdFactory;
import action.event.EstateEvent;
import action.event.EventType;
import action.event.SimpleEventFactory;
import action.request.YesOrNoRequest;
import model.Player;
import util.EstateAction;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ethan on 16/4/28.
 */
public class EstateSpot extends Spot {
    private final int MAX_LEVEL = 6;
    private Player owner;
    private double basePrice;
    private int level;

    public EstateSpot(double basePrice) {
        this.typeName = "土地";
        this.description = (owner == null ? "可供出售的土地" : "已被购买的土地");
        this.spotType = SpotType.EstateSpot;
        this.basePrice = basePrice;
        this.level = 1;
    }

    private void askPurchasing(Player player) {
        YesOrNoRequest request = (YesOrNoRequest) SimpleCommamdFactory.createCommand(CommandType.YES_OR_NO_REQUEST);
        request.setQuestionStr("当前土地可供出售，价格为¥" + basePrice + "，是否购买？");
        if (request.getAnswer()) {
            if (player.pay(getPurchasingPrice())) {
                setOwner(player);
                player.addHouse(this);
                EstateEvent command = (EstateEvent) SimpleEventFactory.createEvent(EventType.ESTETE_EVENT);
                command.setEstateAction(EstateAction.PURCHASE).setSpot(this).setFee(getPurchasingPrice());
                command.toggle(player);
            } else {
                ErrorCommand command = (ErrorCommand) SimpleCommamdFactory.createCommand(CommandType.ERROR_COMMAND);
                command.setCommandStr("您的现金不足以购买此土地！");
            }
        }
    }

    private void askLevelUp(Player player) {
        YesOrNoRequest request = (YesOrNoRequest) SimpleCommamdFactory.createCommand(CommandType.YES_OR_NO_REQUEST);
        request.setQuestionStr("您拥有当前土地，是否花费¥" + getLevelUpPrice() + "来升级？");
        if (request.getAnswer()) {
            if (player.pay(getLevelUpPrice())) {
                level += 1;
                EstateEvent event = (EstateEvent) SimpleEventFactory.createEvent(EventType.ESTETE_EVENT);
                event.setEstateAction(EstateAction.LEVEL_UP).setSpot(this).setFee(getLevelUpPrice());
                event.toggle(player);
            } else {
                ErrorCommand command = (ErrorCommand) SimpleCommamdFactory.createCommand(CommandType.ERROR_COMMAND);
                command.setCommandStr("您的现金不足以升级此土地！");
            }
        }
    }

    private void chargeFee(Player player) {
        player.charge(getPassByPrice());
        EstateEvent command = (EstateEvent) SimpleEventFactory.createEvent(EventType.ESTETE_EVENT);
        command.setEstateAction(EstateAction.PAY_FEE).setSpot(this).setFee(getPassByPrice());
        command.toggle(player);
    }

    @Override
    public void arriveEvent(Player player) {
        if (owner == null) {
            askPurchasing(player);
        } else if (owner == player && level != MAX_LEVEL) {
            askLevelUp(player);
        } else if (level == MAX_LEVEL) {
            PromptCommand command = (PromptCommand) SimpleCommamdFactory.createCommand(CommandType.PROMPT_COMMAND);
            command.setCommandStr("该土地已升至顶级");
        } else {
            chargeFee(player);
        }
    }

    @Override
    public String getDescription() {
        return owner == null ? "可供出售的土地" : "已被购买的土地";
    }

    public List<String> getSpotInfo() {
        List<String> spotInfo = new LinkedList<>();
        spotInfo.add("地名：" + this.getLocName());
        spotInfo.add("类型：" + this.typeName);
        spotInfo.add("简介：" + getDescription());
        if (owner != null) {
            spotInfo.add("等级：" + level);
            spotInfo.add("过路费：" + getPassByPrice());
        } else {
            spotInfo.add("购买费：" + getPurchasingPrice());
        }
        return spotInfo;
    }

    public double getPurchasingPrice() {
        return level * basePrice;
    }

    public double getLevelUpPrice() {
        return level * basePrice * 0.5;
    }

    public double getPassByPrice() {
        return level * basePrice * 0.1;
    }

    public void sell() {
        owner = null;
        level = 1;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
