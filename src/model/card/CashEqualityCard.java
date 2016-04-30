package model.card;

/**
 * Created by Ethan on 16/4/27.
 */
public class CashEqualityCard extends Card {
    public CashEqualityCard() {
        this.name = "均富卡";
        this.description = "将所有人的现金平均分配";
    }

    @Override
    public boolean use() {
        return false;
    }
}
