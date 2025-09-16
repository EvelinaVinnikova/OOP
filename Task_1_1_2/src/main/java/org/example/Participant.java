package org.example;

public abstract class Participant {
    protected final Hand hand;
    protected final String name;

    public Participant(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public int getScore() {
        return hand.getScore();
    }

    public boolean isBusted() {
        return getScore() > 21;
    }
}
