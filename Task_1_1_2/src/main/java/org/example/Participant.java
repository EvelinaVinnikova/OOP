package org.example;

import org.example.GameRules;

/**
 * Represents a participant in the Blackjack game, either a player or a dealer.
 */
public abstract class Participant {
    protected final Hand hand;
    protected final String name;

    /**
     * Constructs a participant with a given name.
     *
     * @param name the name of the participant.
     */
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
        return getScore() > GameRules.MAX_SCORE;
    }
}