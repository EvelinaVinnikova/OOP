package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.example.GameRules;

/**
 * Represents a hand of cards held by a participant.
 */
public class Hand {
    private final List<Card> cards;

    /**
     * Constructs an empty hand.
     */
    public Hand() {
        this.cards = new ArrayList<>();
    }

    /**
     * Adds a card to this hand.
     *
     * @param card the card to add.
     */
    public void addCard(Card card) {
        this.cards.add(card);
    }

    /**
     * Calculates the score of the hand according to Blackjack rules.
     * Aces are counted as 11 unless it causes a bust, in which case they are counted as 1.
     *
     * @return the total score of the hand.
     */
    public int getScore() {
        int score = 0;
        int aceCount = 0;

        for (Card card : cards) {
            score += card.getValue();
            if (card.getRank() == Rank.ACE) {
                aceCount++;
            }
        }

        while (score > GameRules.MAX_SCORE && aceCount > 0) {
            score -= 10;
            aceCount--;
        }

        return score;
    }

    /**
     * Clears all cards from the hand.
     */
    public void clear() {
        this.cards.clear();
    }

    /**
     * Gets the list of cards in the hand.
     *
     * @return the list of cards.
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Returns a string representation of the hand.
     *
     * @return a string showing the cards in the hand.
     */
    @Override
    public String toString() {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(",", "[", "]"));


    }
}