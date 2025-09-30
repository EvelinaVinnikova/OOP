package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.Scanner;


class BlackjackGameTest {
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
}