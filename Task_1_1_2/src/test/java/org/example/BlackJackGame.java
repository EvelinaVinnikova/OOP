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
     * Этот метод выполняется ПЕРЕД КАЖДЫМ тестом,
     * создавая чистые объекты для полной независимости тестов.
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
        // Arrange: Цель: Игрок-10, Дилер-5, Игрок-6, Дилер-8. Хит игрока - Король (10).
        // Итог: У игрока 16 -> 26 (перебор).
        // Добавляем карты в ОБРАТНОМ порядке раздачи.
        fakeDeck.addCard(new Card(Suit.CLUBS, Rank.TEN));      // 1. Уйдет игроку
        fakeDeck.addCard(new Card(Suit.HEARTS, Rank.FIVE));    // 2. Уйдет дилеру
        fakeDeck.addCard(new Card(Suit.SPADES, Rank.SIX));     // 3. Уйдет игроку
        fakeDeck.addCard(new Card(Suit.DIAMONDS, Rank.EIGHT)); // 4. Уйдет дилеру
        fakeDeck.addCard(new Card(Suit.CLUBS, Rank.KING));     // 5. Уйдет игроку при хите

        Scanner fakeScanner = new Scanner("h" + System.lineSeparator() + "no");
        BlackjackGame game = new BlackjackGame(fakeDeck, player, dealer, fakeScanner);

        // Act & Assert
        RoundResult result = game.playRound();
        assertEquals(RoundResult.PLAYER_BUST, result);
    }

    @Test
    void testPlayRound_PlayerStandsAndWins() {
        // Arrange: Цель: Игрок получит 10 + 9 (19). Дилер получит 10 + 7 (17). Игрок побеждает.
        // Добавляем карты в ОБРАТНОМ порядке раздачи.
        fakeDeck.addCard(new Card(Suit.SPADES, Rank.NINE));   // 1. Игроку
        fakeDeck.addCard(new Card(Suit.DIAMONDS, Rank.SEVEN)); // 2. Дилеру
        fakeDeck.addCard(new Card(Suit.CLUBS, Rank.TEN));    // 3. Игроку
        fakeDeck.addCard(new Card(Suit.HEARTS, Rank.TEN));   // 4. Дилеру

        Scanner fakeScanner = new Scanner("s");
        BlackjackGame game = new BlackjackGame(fakeDeck, player, dealer, fakeScanner);

        // Act & Assert
        RoundResult result = game.playRound();
        assertEquals(RoundResult.PLAYER_WINS, result);
    }

    @Test
    void testPlayRound_Push() {
        // Arrange: Цель: Игрок получит 10 + 9 (19). Дилер получит 10 + 9 (19). Ничья.
        // Добавляем карты в ОБРАТНОМ порядке раздачи.
        fakeDeck.addCard(new Card(Suit.CLUBS, Rank.TEN));    // 1. Игроку
        fakeDeck.addCard(new Card(Suit.HEARTS, Rank.TEN));   // 2. Дилеру
        fakeDeck.addCard(new Card(Suit.SPADES, Rank.NINE));   // 3. Игроку
        fakeDeck.addCard(new Card(Suit.DIAMONDS, Rank.NINE)); // 4. Дилеру

        Scanner fakeScanner = new Scanner("s");
        BlackjackGame game = new BlackjackGame(fakeDeck, player, dealer, fakeScanner);

        // Act & Assert
        RoundResult result = game.playRound();
        assertEquals(RoundResult.PUSH, result);
    }
}

