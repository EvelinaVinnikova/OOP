package org.example;

/**
 * Represents the dealer in the Blackjack game.
 */
public class Dealer extends Participant {
    /**
     * Constructs a new Dealer.
     */
    public Dealer() {
        super("Dealer");
    }

    /**
     * Displays the dealer's initial hand, hiding the second card.
     */
    public void showFirstHand() {
        System.out.println(this.name + "'s hand: [" + hand.getCards().get(0) + ", <hidden>]");
    }
}