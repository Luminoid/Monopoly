package model.card;

/**
 * Created by Ethan on 16/4/27.
 */
public class LotteryCard extends Card {
    public LotteryCard() {
        this.name = "彩票卡";
        this.description = "使用时可以操作本月的彩票开奖结果";
    }

    @Override
    public boolean use() {
        return false;
    }
}
