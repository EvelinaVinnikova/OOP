package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;
import org.junit.jupiter.api.Test;


class BlackJackGameTest {
    @Test
    void testPlayGame_PlayerHitsAndBusts() {
        Deck fakeDeck = new Deck();
        fakeDeck.clear();
    
        fakeDeck.addCard(new Card(Suit.CLUBS, Rank.KING));     // H1 = 10
    
        fakeDeck.addCard(new Card(Suit.DIAMONDS, Rank.EIGHT)); // D2
        fakeDeck.addCard(new Card(Suit.SPADES, Rank.SIX));     // P2
        fakeDeck.addCard(new Card(Suit.HEARTS, Rank.FIVE));    // D1
        fakeDeck.addCard(new Card(Suit.CLUBS, Rank.TEN));      // P1
    
        Scanner fakeScanner = new Scanner("h" + System.lineSeparator() + "no");
    
        Player player = new Player("Player");
        Dealer dealer = new Dealer();
        BlackjackGame game = new BlackjackGame(fakeDeck, player, dealer, fakeScanner);
    
        RoundResult result = game.playRound();
    
        assertEquals(RoundResult.PLAYER_BUST, result);
    }


    @Test
    void testPlayGame_PlayerStandsAndWins() {
        // Crave: P:10, D:10, P:10, D:7  => finally P=20, D=17, Player stands
        Deck fakeDeck = new Deck();
        fakeDeck.clear();
        fakeDeck.addCard(new Card(Suit.DIAMONDS, Rank.SEVEN)); // D2 (hidden)
        fakeDeck.addCard(new Card(Suit.SPADES, Rank.KING));    // P2 (10)
        fakeDeck.addCard(new Card(Suit.HEARTS, Rank.TEN));     // D1 (10)
        fakeDeck.addCard(new Card(Suit.CLUBS, Rank.TEN));      // P1 (10)
    
        String simulatedInput = "s" + System.lineSeparator() + "no";
        Scanner fakeScanner = new Scanner(simulatedInput);
    
        Player player = new Player("Player");
        Dealer dealer = new Dealer();
        BlackjackGame game = new BlackjackGame(fakeDeck, player, dealer, fakeScanner);
    
        game.playGame();
    
        assertFalse(player.isBusted());
        assertFalse(dealer.isBusted());
        assertEquals(20, player.getScore());
        assertEquals(17, dealer.getScore());
    }

    @Test
    void testPlayGame_Push() {
        // Crave: P:10, D:10, P:9, D:9  => finally 19:19, PUSH
        Deck fakeDeck = new Deck();
        fakeDeck.clear();
        fakeDeck.addCard(new Card(Suit.DIAMONDS, Rank.NINE));  // D2 (hidden)
        fakeDeck.addCard(new Card(Suit.SPADES, Rank.NINE));    // P2 (9)
        fakeDeck.addCard(new Card(Suit.HEARTS, Rank.TEN));     // D1 (10)
        fakeDeck.addCard(new Card(Suit.CLUBS, Rank.TEN));      // P1 (10)
    
        String simulatedInput = "s" + System.lineSeparator() + "no";
        Scanner fakeScanner = new Scanner(simulatedInput);
    
        Player player = new Player("Player");
        Dealer dealer = new Dealer();
        BlackjackGame game = new BlackjackGame(fakeDeck, player, dealer, fakeScanner);
    
        game.playGame();
    
        assertEquals(19, player.getScore());
        assertEquals(19, dealer.getScore());
    }


}



