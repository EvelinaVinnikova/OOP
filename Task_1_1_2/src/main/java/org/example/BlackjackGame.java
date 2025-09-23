package org.example;

import java.util.Scanner;

/**
 * The main class that orchestrates the Blackjack game logic.
 */
public class BlackjackGame {

    private final Deck deck;
    private final Player player;
    private final Dealer dealer;
    private final Scanner scanner;

    /**
     * Constructs a new game instance. Dependencies are injected for testability.
     */
    public BlackjackGame(Deck deck, Player player, Dealer dealer, Scanner scanner) {
        this.deck = deck;
        this.player = player;
        this.dealer = dealer;
        this.scanner = scanner;
    }

    /**
     * Starts and manages the main game loop. This method is now responsible for printing results.
     */
    public void playGame() {
        System.out.println("Welcome to Blackjack!");

        while (true) {
            RoundResult result = playRound();

            // Вся логика вывода теперь здесь
            switch (result) {
                case PLAYER_WINS:
                case DEALER_WINS:
                case PUSH:
                    // <-- Показываем финальные очки только в этих случаях
                    System.out.println("\n--- Final Scores ---");
                    System.out.println(player.getName() + ": " + player.getScore());
                    // Раскрываем карту дилера перед показом его очков
                    dealer.getHand().getCards().get(1).reveal();
                    System.out.println(dealer.getName() + ": " + dealer.getScore());

                    if (result == RoundResult.PLAYER_WINS) {
                        System.out.println(player.getName() + " wins!");
                    } else if (result == RoundResult.DEALER_WINS) {
                        System.out.println(dealer.getName() + " wins!");
                    } else {
                        System.out.println("It's a push (tie)!");
                    }
                    break;

                case PLAYER_BUST:
                    System.out.println(player.getName() + " busts! Dealer wins.");
                    break;
                case DEALER_BUST:
                    System.out.println("Dealer busts! " + player.getName() + " wins!");
                    break;
                case BLACKJACK:
                    System.out.println("Blackjack! " + player.getName() + " wins!");
                    break;
            }

            System.out.println("\nPlay another round? (yes/no)");
            String response = scanner.next();
            if (!response.equalsIgnoreCase("yes")) {
                break;
            }
        }
        System.out.println("Thanks for playing!");
    }

    /**
     * Manages the logic for a single round of Blackjack and returns the result.
     * This method no longer prints final results.
     */
    private RoundResult playRound() {
        deck.shuffle();
        player.getHand().clear();
        dealer.getHand().clear();

        player.addCard(deck.dealCard());
        dealer.addCard(deck.dealCard());
        player.addCard(deck.dealCard());
        dealer.addCard(deck.dealCard());

        System.out.println("\n--- New Round ---");
        System.out.println(player.getName() + "'s hand: " + player.getHand()
                + " (Score: " + player.getScore() + ")");
        dealer.getHand().getCards().get(1).hide();
        System.out.println("Dealer's hand: " + dealer.getHand());

        if (player.getScore() == 21) {
            return RoundResult.BLACKJACK;
        }

        RoundResult playerResult = playerTurn();
        if (playerResult != null) { // Если игрок проиграл (bust), раунд заканчивается
            return playerResult;
        }

        RoundResult dealerResult = dealerTurn();
        if (dealerResult != null) { // Если дилер проиграл (bust), раунд заканчивается
            return dealerResult;
        }

        return determineWinner(); // Если никто не проиграл, определяем победителя по очкам
    }

    /**
     * Manages the player's turn. Returns PLAYER_BUST if the player busts, otherwise null.
     */
    private RoundResult playerTurn() {
        while (true) {
            System.out.println("Hit or Stand? (h/s)");
            String choice = scanner.next();

            if (choice.equalsIgnoreCase("h")) {
                player.addCard(deck.dealCard());
                System.out.println(player.getName() + "'s hand: " + player.getHand()
                        + " (Score: " + player.getScore() + ")");
                if (player.isBusted()) {
                    return RoundResult.PLAYER_BUST; // <-- Возвращаем результат, а не печатаем
                }
            } else if (choice.equalsIgnoreCase("s")) {
                return null; // Игрок остановился, игра продолжается
            }
        }
    }

    /**
     * Manages the dealer's turn. Returns DEALER_BUST if the dealer busts, otherwise null.
     */
    private RoundResult dealerTurn() {
        System.out.println("\nDealer's turn...");
        dealer.playTurn(deck); // Логика хода дилера спрятана в его классе
        if (dealer.isBusted()) {
            return RoundResult.DEALER_BUST; // <-- Возвращаем результат, а не печатаем
        }
        return null; // Дилер остановился, игра продолжается
    }

    /**
     * Compares final scores and returns the winner.
     */
    private RoundResult determineWinner() {
        if (player.getScore() > dealer.getScore()) {
            return RoundResult.PLAYER_WINS;
        } else if (dealer.getScore() > player.getScore()) {
            return RoundResult.DEALER_WINS;
        } else {
            return RoundResult.PUSH;
        }
    }

    /**
     * The main entry point for the application.
     */
    public static void main(String[] args) {
        Deck realDeck = new Deck();
        Player player = new Player("Player");
        Dealer dealer = new Dealer();
        Scanner consoleScanner = new Scanner(System.in);

        BlackjackGame game = new BlackjackGame(realDeck, player, dealer, consoleScanner);
        game.playGame();
    }
}