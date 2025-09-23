package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a standard 52-card deck.
 */
public class Deck {

    private final List<Card> cards;

    /**
     * Constructs a new, ordered deck of 52 cards.
     */
    public Deck() {
        this.cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                this.cards.add(new Card(suit, rank));
            }
        }
    }

    /**
     * Shuffles the deck into a random order.
     */
    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    /**
     * Deals one card from the top of the deck.
     *
     * @return the card removed from the deck, or null if the deck is empty.
     */
    public Card dealCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(cards.size() - 1);
    }
}