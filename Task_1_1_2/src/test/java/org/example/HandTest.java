package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand();
    }

    @Test
    @DisplayName("Test score with simple cards")
    void testSimpleScore() {
        hand.addCard(new Card(Suit.HEARTS, Rank.FIVE));
        hand.addCard(new Card(Suit.CLUBS, Rank.KING)); // 5 + 10 = 15
        assertEquals(15, hand.getScore());
    }

    @Test
    @DisplayName("Test score with one Ace as 11")
    void testAceAsEleven() {
        hand.addCard(new Card(Suit.HEARTS, Rank.ACE));
        hand.addCard(new Card(Suit.CLUBS, Rank.NINE)); // 11 + 9 = 20
        assertEquals(20, hand.getScore());
    }

    @Test
    @DisplayName("Test score with one Ace as 1 after a bust")
    void testAceAsOne() {
        hand.addCard(new Card(Suit.HEARTS, Rank.SEVEN));
        hand.addCard(new Card(Suit.DIAMONDS, Rank.EIGHT));
        hand.addCard(new Card(Suit.CLUBS, Rank.ACE)); // 7 + 8 + 11 = 26 -> 7 + 8 + 1 = 16
        assertEquals(16, hand.getScore());
    }

    @Test
    @DisplayName("Test score with two Aces")
    void testTwoAces() {
        hand.addCard(new Card(Suit.HEARTS, Rank.ACE));
        hand.addCard(new Card(Suit.CLUBS, Rank.ACE)); // 11 + 1 = 12
        assertEquals(12, hand.getScore());
    }

    @Test
    @DisplayName("Test busting")
    void testBust() {
        hand.addCard(new Card(Suit.HEARTS, Rank.TEN));
        hand.addCard(new Card(Suit.CLUBS, Rank.TEN));
        hand.addCard(new Card(Suit.DIAMONDS, Rank.TWO)); // 10 + 10 + 2 = 22
        assertTrue(hand.getScore() > 21);
    }
}