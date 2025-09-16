package org.example;

/**
 * Represents a playing card with a suit and a rank.
 */
public class Card {
    private final Suit suit;
    private final Rank rank;

    /**
     * Constructs a card with a specified suit and rank.
     *
     * @param suit the suit of the card.
     * @param rank the rank of the card.
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Gets the point value of the card.
     *
     * @return the integer value of the card's rank.
     */
    public int getValue() {
        return rank.getValue();
    }

    /**
     * Gets the rank of the card.
     *
     * @return the Rank enum constant.
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Returns a string representation of the card.
     *
     * @return a string like "ACE of SPADES".
     */
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}