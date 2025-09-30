package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DealerTest {

    /**
     * Testing the case: Dealer have to take 1 card and stop.
     * First he is given 16 cards. In fake deck of cards, the next is "five".
     * Testing that after the Dealer's turn he will have 3 cards and final score is 21.
     */
    @Test
    void testPlayTurn_DealerHitsOnce() {
        // Preparation
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Suit.HEARTS, Rank.TEN));
        dealer.addCard(new Card(Suit.CLUBS, Rank.SIX));

        Deck fakeDeck = new Deck();
        fakeDeck.clear();
        fakeDeck.addCard(new Card(Suit.DIAMONDS, Rank.FIVE));

        // Act
        dealer.playTurn(fakeDeck);

        // Assert
        assertEquals(3, dealer.getHand().getCards().size());
        assertEquals(21, dealer.getScore());
    }
}