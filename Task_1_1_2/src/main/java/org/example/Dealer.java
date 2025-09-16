package org.example;

public class Dealer extends Participant {
    public Dealer() {
        super("Dealer");
    }

    public void showFirstHand() {
        System.out.println(this.name + "'s hand: [" + hand.getCards().get(0) + ", <hidden>]");
    }
}