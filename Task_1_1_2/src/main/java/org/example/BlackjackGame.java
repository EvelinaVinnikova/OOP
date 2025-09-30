package org.example;

import org.example.GameRules;
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

            switch (result) {
                case PLAYER_WINS:
                case DEALER_WINS:
                case PUSH:
                    System.out.println("\n--- Final Scores ---");
                    System.out.println(player.getName() + "'s hand: " + player.getHand()
                            + " (Score: " + player.getScore() + ")");
                    System.out.println(dealer.getName() + "'s hand: " + dealer.getHand()
                            + " (Score: " + dealer.getScore() + ")");

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

                default:
                    throw new IllegalStateException("Unhandled RoundResult: " + result);
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
     */
    protected RoundResult playRound() {
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

        if (player.getScore() == GameRules.BLACKJACK_SCORE) {
            return RoundResult.BLACKJACK;
        }

        playerTurn();

        if (!player.isBusted()) {
            dealerTurn();
        }

        return determineWinner();
    }

    /**
     * Manages the player's turn, allowing them to hit or stand.
     */
    private void playerTurn() {
        while (true) {
            System.out.println("Hit or Stand? (h/s)");
            if (!scanner.hasNext()) {
                break;
            }
            String choice = scanner.next().trim().toLowerCase();

            boolean hit = choice.equals("h") || choice.equals("hit") || choice.equals("1")
                    || choice.equals("y") || choice.equals("yes");
            boolean stand = choice.equals("s") || choice.equals("stand") || choice.equals("0")
                    || choice.equals("n") || choice.equals("no");

            if (hit) {
                player.addCard(deck.dealCard());
                System.out.println(player.getName() + "'s hand: " + player.getHand()
                        + " (Score: " + player.getScore() + ")");
                if (player.isBusted()) {
                    break;
                }
            } else if (stand) {
                break;
            } else {
                break;
            }
        }
    }

    /**
     * Manages the dealer's turn by telling the dealer object to play its turn.
     */
    private void dealerTurn() {
        System.out.println("\nDealer's turn...");
        dealer.playTurn(deck);
    }

    /**
     * Determines the winner of the round based on the final state of the hands.
     * This method now handles all outcomes, including busts.
     */
    private RoundResult determineWinner() {
        if (player.isBusted()) {
            return RoundResult.PLAYER_BUST;
        }
        if (dealer.isBusted()) {
            return RoundResult.DEALER_BUST;
        }
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