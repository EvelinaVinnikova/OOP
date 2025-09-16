package org.example;

import java.util.Scanner;

public class BlackjackGame {

    private final Deck deck;
    private final Player player;
    private final Dealer dealer;
    private final Scanner scanner;

    public BlackjackGame() {
        this.deck = new Deck();
        this.player = new Player("Player");
        this.dealer = new Dealer();
        this.scanner = new Scanner(System.in);
    }

    public void playGame() {
        System.out.println("Welcome to Blackjack!");

        while (true) {
            playRound();
            System.out.println("\nPlay another round? (yes/no)");
            String response = scanner.next();
            if (!response.equalsIgnoreCase("yes")) {
                break;
            }
        }
        System.out.println("Thanks for playing!");
    }

    private void playRound() {
        deck.shuffle();
        player.getHand().clear();
        dealer.getHand().clear();

        player.addCard(deck.dealCard());
        dealer.addCard(deck.dealCard());
        player.addCard(deck.dealCard());
        dealer.addCard(deck.dealCard());

        System.out.println("\n--- New Round ---");
        System.out.println(player.getName() + "'s hand: " + player.getHand() + " (Score: " + player.getScore() + ")");
        dealer.showFirstHand();

        if (player.getScore() == 21) {
            System.out.println("Blackjack! " + player.getName() + " wins!");
            return;
        }

        playerTurn();

        if (!player.isBusted()) {
            dealerTurn();
        }

        // Определение победителя
        determineWinner();
    }

    private void playerTurn() {
        while (true) {
            System.out.println("Hit or Stand? (h/s)");
            String choice = scanner.next();

            if (choice.equalsIgnoreCase("h")) {
                player.addCard(deck.dealCard());
                System.out.println(player.getName() + "'s hand: " + player.getHand() + " (Score: " + player.getScore() + ")");
                if (player.isBusted()) {
                    System.out.println(player.getName() + " busts! Dealer wins.");
                    break;
                }
            } else if (choice.equalsIgnoreCase("s")) {
                break;
            }
        }
    }

    private void dealerTurn() {
        System.out.println("\nDealer's turn...");
        System.out.println(dealer.getName() + "'s hand: " + dealer.getHand() + " (Score: " + dealer.getScore() + ")");

        while (dealer.getScore() < 17) {
            System.out.println("Dealer hits.");
            dealer.addCard(deck.dealCard());
            System.out.println(dealer.getName() + "'s hand: " + dealer.getHand() + " (Score: " + dealer.getScore() + ")");
        }

        if (dealer.isBusted()) {
            System.out.println("Dealer busts! " + player.getName() + " wins!");
        }
    }

    private void determineWinner() {
        // Победитель определяется только если ни у кого нет перебора
        if (!player.isBusted() && !dealer.isBusted()) {
            System.out.println("\n--- Final Scores ---");
            System.out.println(player.getName() + ": " + player.getScore());
            System.out.println(dealer.getName() + ": " + dealer.getScore());

            if (player.getScore() > dealer.getScore()) {
                System.out.println(player.getName() + " wins!");
            } else if (dealer.getScore() > player.getScore()) {
                System.out.println(dealer.getName() + " wins!");
            } else {
                System.out.println("It's a push (tie)!");
            }
        }
    }

    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame();
        game.playGame();
    }
}