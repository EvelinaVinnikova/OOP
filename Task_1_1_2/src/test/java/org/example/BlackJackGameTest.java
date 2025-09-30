package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;
import org.junit.jupiter.api.Test;


class BlackJackGameTest {
    @Test
    void testPlayGame_PlayerHitsAndBusts() {
        // Preparation
        // Players gets  10 и 6 (16). Dealer gets 5 и 8 (13). Next card is King (10).
        Deck fakeDeck = new Deck();
        fakeDeck.clear();
        fakeDeck.addCard(new Card(Suit.CLUBS, Rank.TEN)); // Player's card 1
        fakeDeck.addCard(new Card(Suit.HEARTS, Rank.FIVE)); // Dealer's card 1
        fakeDeck.addCard(new Card(Suit.SPADES, Rank.SIX)); // Player's card 2
        fakeDeck.addCard(new Card(Suit.DIAMONDS, Rank.EIGHT)); // Dealer's card 2
        fakeDeck.addCard(new Card(Suit.CLUBS, Rank.KING)); // Player's card 3

        String simulatedInput = "h" + System.lineSeparator() + "no";;
        Scanner fakeScanner = new Scanner(simulatedInput);

        Player player = new Player("Player");
        Dealer dealer = new Dealer();
        BlackjackGame game = new BlackjackGame(fakeDeck, player, dealer, fakeScanner);

        // Act
        RoundResult result = game.playRound();

        // Assert
        assertEquals(RoundResult.PLAYER_BUST, result);
    }

    @Test
    void testPlayGame_PlayerStandsAndWins() {
        // Player gets 10 + 10 (20).Dealer gets 10 + 7 (17)
        Deck fakeDeck = new Deck();
        fakeDeck.clear();
        fakeDeck.addCard(new Card(Suit.CLUBS, Rank.TEN));    // Player
        fakeDeck.addCard(new Card(Suit.HEARTS, Rank.TEN));   // Dealer
        fakeDeck.addCard(new Card(Suit.SPADES, Rank.KING));   // Player (+ 10)
        fakeDeck.addCard(new Card(Suit.DIAMONDS, Rank.SEVEN)); // Dealer (hidden)

        // Simulating the input: "s" (stand), then "no"
        String simulatedInput = "s" + System.lineSeparator() + "no";
        Scanner fakeScanner = new Scanner(simulatedInput);

        Player player = new Player("Player");
        Dealer dealer = new Dealer();
        BlackjackGame game = new BlackjackGame(fakeDeck, player, dealer, fakeScanner);

        // Act
        game.playGame();

        // Assert
        assertFalse(player.isBusted(), "Player should not be busted.");
        assertFalse(dealer.isBusted(), "Dealer should not be busted.");
        assertEquals(20, player.getScore(), "Player's score should be 20.");
        assertEquals(17, dealer.getScore(), "Dealer's score should be 17.");
    }

    @Test
    void testPlayGame_Push() {
        // Player gets 10 + 9 (19). Dealer gets 10 + 9 (19).
        Deck fakeDeck = new Deck();
        fakeDeck.clear();
        fakeDeck.addCard(new Card(Suit.CLUBS, Rank.TEN));    // Player
        fakeDeck.addCard(new Card(Suit.HEARTS, Rank.TEN));   // Dealer
        fakeDeck.addCard(new Card(Suit.SPADES, Rank.NINE));   // Player
        fakeDeck.addCard(new Card(Suit.DIAMONDS, Rank.NINE)); // Dealer (hidden)

        // Simulating the input: "s" (stand), then "no".
        String simulatedInput = "s" + System.lineSeparator() + "no";
        Scanner fakeScanner = new Scanner(simulatedInput);

        Player player = new Player("Player");
        Dealer dealer = new Dealer();
        BlackjackGame game = new BlackjackGame(fakeDeck, player, dealer, fakeScanner);

        // Act
        game.playGame();

        // Assert
        assertEquals(19, player.getScore(), "Player's score should be 19.");
        assertEquals(19, dealer.getScore(), "Dealer's score should be 19.");
    }

}

