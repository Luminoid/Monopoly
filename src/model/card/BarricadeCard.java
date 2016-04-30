package model.card;

/**
 * Created by Ethan on 16/4/27.
 */
public class BarricadeCard extends Card {

    public BarricadeCard() {
        this.name = "路障卡";
        this.description = "可以在前后 8 步之内安放一个路障,任意玩家经过路障时会停在路障所在位置不能前行";
    }

    @Override
    public boolean use() {
        return false;
    }
}
