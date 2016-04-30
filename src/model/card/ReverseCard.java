package model.card;

/**
 * Created by Ethan on 16/4/27.
 */
public class ReverseCard extends Card {
    public ReverseCard() {
        this.name = "转向卡";
        this.description = "使目标掉转方向";
    }

    @Override
    public boolean use() {
        return false;
    }
}
