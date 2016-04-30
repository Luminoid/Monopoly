package model.card;

import model.Player;

import java.util.ArrayList;

/**
 * Created by Ethan on 16/4/27.
 */
public abstract class Card {
    protected String name;
    protected String description;
    protected CardType cardType;
    protected int value;

    public abstract boolean use(Player player);

    public static CardType generateCard() {
        ArrayList<CardType> cards = new ArrayList<>();
        int totalWeight = 0;
        for (CardType type : CardType.values()) {
            int num = SimpleCardFactory.createCard(type).getValue(); // weight
            totalWeight += num;
            for (int i = 0; i < num; i++) {
                cards.add(type);
            }
        }
        int index = (int) (Math.random() * totalWeight);
        return cards.get(index);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }
}
