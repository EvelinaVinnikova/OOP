package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class BlackJackGameTest {
    private Player player;
    private Dealer dealer;
    private Deck fakeDeck;

    /**
     * This method will be performed BEFORE EACH test.
     * He drops the state, creating new pure objects.
     */
    @BeforeEach
    void setUp() {
        player = new Player("Player");
        dealer = new Dealer();
        fakeDeck = new Deck();
        fakeDeck.clear();
    }

    @Test
    void testPlayGame_PlayerHitsAndBusts() {
        // Arrange: Player (10, 6) -> hits KING -> Busts (26). Dealer has (5, 8).
        fakeDeck.addCard(new Card(Suit.CLUBS, Rank.TEN));      // Player
        fakeDeck.addCard(new Card(Suit.HEARTS, Rank.FIVE));    // Dealer
        fakeDeck.addCard(new Card(Suit.SPADES, Rank.SIX));     // Player
        fakeDeck.addCard(new Card(Suit.DIAMONDS, Rank.EIGHT)); // Dealer
        fakeDeck.addCard(new Card(Suit.CLUBS, Rank.KING));     // Player if hit

        Scanner fakeScanner = new Scanner("h" + System.lineSeparator() + "no");
        BlackjackGame game = new BlackjackGame(fakeDeck, player, dealer, fakeScanner);

        // Act & Assert
        RoundResult result = game.playRound();
        assertEquals(RoundResult.PLAYER_BUST, result);
    }

    @Test
    void testPlayRound_PlayerStandsAndWins() {
        // Arrange: Player gets 19 (10, 9). Dealer gets 17 (10, 7). Player wins
        fakeDeck.addCard(new Card(Suit.DIAMONDS, Rank.SEVEN)); // Dealer
        fakeDeck.addCard(new Card(Suit.SPADES, Rank.NINE));   // Player
        fakeDeck.addCard(new Card(Suit.HEARTS, Rank.TEN));   // Dealer
        fakeDeck.addCard(new Card(Suit.CLUBS, Rank.TEN));    // Player

        Scanner fakeScanner = new Scanner("s");
        BlackjackGame game = new BlackjackGame(fakeDeck, player, dealer, fakeScanner);

        // Act & Assert
        RoundResult result = game.playRound();
        assertEquals(RoundResult.PLAYER_WINS, result);
    }

    @Test
    void testPlayRound_Push() {
        // Arrange: Player gets 19 (10, 9). Dealer gets 19 (10, 9). Push.
        fakeDeck.addCard(new Card(Suit.DIAMONDS, Rank.NINE)); // Dealer
        fakeDeck.addCard(new Card(Suit.SPADES, Rank.NINE));   // Player
        fakeDeck.addCard(new Card(Suit.HEARTS, Rank.TEN));   // Dealer
        fakeDeck.addCard(new Card(Suit.CLUBS, Rank.TEN));    // Player

        Scanner fakeScanner = new Scanner("s");
        BlackjackGame game = new BlackjackGame(fakeDeck, player, dealer, fakeScanner);

        // Act & Assert
        RoundResult result = game.playRound();
        assertEquals(RoundResult.PUSH, result);
    }
}
