package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck();
    }

    @Test
    @DisplayName("A new deck should have 52 cards")
    void newDeckHas52Cards() {
        for (int i = 0; i < 52; i++) {
            assertNotNull(deck.dealCard(), "Card should not be null at position " + i);
        }
        assertNull(deck.dealCard(), "Deck should be empty after 52 cards are dealt");
    }

    @Test
    @DisplayName("Dealing a card should remove it from the deck")
    void dealCardRemovesCard() {
        Card firstCard = deck.dealCard();
        assertNotNull(firstCard);

        Deck newDeck = new Deck();
        int matches = 0;
        for (int i = 0; i < 52; i++) {
            if (firstCard.toString().equals(newDeck.dealCard().toString())) {
                matches++;
            }
        }
    }
}