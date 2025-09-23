package org.example;

/**
 * Outcomes of a single Blackjack round.
 *
 * <p>Returned by the game logic to describe the terminal state of the round.</p>
 */
public enum RoundResult {
    PLAYER_WINS,
    DEALER_WINS,
    PLAYER_BUST,
    DEALER_BUST,
    PUSH, // ничья
    BLACKJACK
}