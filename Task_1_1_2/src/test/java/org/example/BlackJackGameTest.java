package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlackJackGameTest {

    private Player player;
    private Dealer dealer;
    private Deck   fakeDeck;

    /** Колода для тестов: отключаем shuffle(), чтобы раздача была детерминированной. */
    static class NoShuffleDeck extends Deck {
        @Override public void shuffle() { /* no-op for tests */ }
    }

    @BeforeEach
    void setUp() {
        player  = new Player("Player");
        dealer  = new Dealer();
        fakeDeck = new NoShuffleDeck();
        fakeDeck.clear();
    }


    @Test
    void testPlayGame_PlayerHitsAndBusts() {
        // Хотим: P:10, D:5, P:6, D:8, затем игрок тянет K → 16+10 = 26 → PLAYER_BUST
        // У нас LIFO-колода → кладём в обратном порядке: сначала H1, потом D2,P2,D1,P1
        fakeDeck.addCard(new Card(Suit.CLUBS, Rank.KING));     // H1 = 10 (карта для хита)
        fakeDeck.addCard(new Card(Suit.DIAMONDS, Rank.EIGHT)); // D2
        fakeDeck.addCard(new Card(Suit.SPADES,   Rank.SIX));   // P2
        fakeDeck.addCard(new Card(Suit.HEARTS,   Rank.FIVE));  // D1
        fakeDeck.addCard(new Card(Suit.CLUBS,    Rank.TEN));   // P1

        Scanner in = new Scanner("h\nno\n");
        BlackjackGame game = new BlackjackGame(fakeDeck, player, dealer, in);

        RoundResult result = game.playRound();
        assertEquals(RoundResult.PLAYER_BUST, result);
    }

    @Test
    void testPlayRound_PlayerStandsAndWins() {
        // Хотим: P=19 (10+9), D=17 (10+7) → PLAYER_WINS
        fakeDeck.addCard(new Card(Suit.DIAMONDS, Rank.SEVEN)); // D2
        fakeDeck.addCard(new Card(Suit.SPADES,   Rank.NINE));  // P2
        fakeDeck.addCard(new Card(Suit.HEARTS,   Rank.TEN));   // D1
        fakeDeck.addCard(new Card(Suit.CLUBS,    Rank.TEN));   // P1

        Scanner in = new Scanner("s\n");
        BlackjackGame game = new BlackjackGame(fakeDeck, player, dealer, in);

        RoundResult result = game.playRound();
        assertEquals(RoundResult.PLAYER_WINS, result);
    }

    @Test
    void testPlayRound_Push() {
        // Хотим: P=19 (10+9), D=19 (10+9) → PUSH
        fakeDeck.addCard(new Card(Suit.DIAMONDS, Rank.NINE));  // D2
        fakeDeck.addCard(new Card(Suit.SPADES,   Rank.NINE));  // P2
        fakeDeck.addCard(new Card(Suit.HEARTS,   Rank.TEN));   // D1
        fakeDeck.addCard(new Card(Suit.CLUBS,    Rank.TEN));   // P1

        Scanner in = new Scanner("s\n");
        BlackjackGame game = new BlackjackGame(fakeDeck, player, dealer, in);

        RoundResult result = game.playRound();
        assertEquals(RoundResult.PUSH, result);
    }


    @Test
    void testPlayRound_ImmediateBlackjack() {
        // Игрок получает A + 10 сразу → BLACKJACK (ранний возврат из playRound())
        fakeDeck.addCard(new Card(Suit.CLUBS,    Rank.SEVEN)); // D2 (неважно)
        fakeDeck.addCard(new Card(Suit.HEARTS,   Rank.KING));  // P2 = 10
        fakeDeck.addCard(new Card(Suit.DIAMONDS, Rank.NINE));  // D1 (неважно)
        fakeDeck.addCard(new Card(Suit.SPADES,   Rank.ACE));   // P1 = A

        Scanner in = new Scanner("s\n"); // до хода не дойдёт
        BlackjackGame game = new BlackjackGame(fakeDeck, player, dealer, in);

        RoundResult result = game.playRound();
        assertEquals(RoundResult.BLACKJACK, result);
    }

    @Test
    void testPlayRound_DealerBustsOnHit() {
        // Игрок стоит; дилер 16 (9+7) берёт ещё 10 → 26 → DEALER_BUST
        fakeDeck.addCard(new Card(Suit.CLUBS,    Rank.KING));  // H1 = 10 (добор дилера)
        fakeDeck.addCard(new Card(Suit.DIAMONDS, Rank.SEVEN)); // D2 = 7
        fakeDeck.addCard(new Card(Suit.SPADES,   Rank.NINE));  // P2 = 9 (игрок >= 15, но стоит)
        fakeDeck.addCard(new Card(Suit.HEARTS,   Rank.NINE));  // D1 = 9 (итого у дилера 16)
        fakeDeck.addCard(new Card(Suit.CLUBS,    Rank.SIX));   // P1 = 6

        Scanner in = new Scanner("s\n"); // stand
        BlackjackGame game = new BlackjackGame(fakeDeck, player, dealer, in);

        RoundResult result = game.playRound();
        assertEquals(RoundResult.DEALER_BUST, result);
    }
}