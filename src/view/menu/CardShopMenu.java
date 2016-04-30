package view.menu;

import model.Player;
import model.card.Card;
import model.card.CardType;
import model.card.SimpleCardFactory;
import view.util.TuiInput;
import view.util.TuiOutput;

import java.util.Scanner;

/**
 * Created by Ethan on 16/5/1.
 */
public class CardShopMenu {
    public static void displayCardShopMenu(Player player) {
        System.out.println("##############################################");
        while (true) {
            System.out.println("欢迎来到道具店，您有"+player.getTicket()+"张点券");
            System.out.println("您现在可以购买以下道具卡(输入0退出)：");

            CardType[] type = CardType.values();
            Card[] cards = new Card[type.length];
            for (int i = 0; i < type.length; i++) {
                cards[i] = SimpleCardFactory.createCard(type[i]);
                TuiOutput.printTable((i+1)+"、"+cards[i].getName()+"：$"+cards[i].getValue());
            }
            System.out.println();

            int choiceNum = TuiInput.readInt(new Scanner(System.in), 0, type.length);
            if (choiceNum == 0){
                return;
            }else if (player.getTicket() >= cards[choiceNum-1].getValue()){
                player.setTicket(player.getTicket()-cards[choiceNum-1].getValue());
                player.addCard(type[choiceNum-1]);
                System.out.println("购买成功");
            } else {
                System.out.println("您没有足够的点券购买该道具卡");
            }
        }
    }
}
