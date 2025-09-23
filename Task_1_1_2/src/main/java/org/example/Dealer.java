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
     * Plays the dealer's turn according to the house rules.
     * The dealer reveals their hidden card and must hit until their hand score is 17 or higher.
     *
     * @param deck The deck to draw cards from.
     */
    public void playTurn(Deck deck) {
        if (this.getHand().getCards().size() > 1) {
            this.getHand().getCards().get(1).reveal();
        }
        System.out.println(this.getName() + "'s hand: " + this.getHand()
                + " (Score: " + this.getScore() + ")");

        while (this.getHand().getScore() < 17) {
            System.out.println("Dealer hits.");
            this.addCard(deck.dealCard());
            System.out.println(this.getName() + "'s hand: " + this.getHand()
                    + " (Score: " + this.getScore() + ")");
        }
    }
}