package org.example;

/**
 * Central place for Blackjack rule constants.
 */
public final class GameRules {
    private GameRules() {}

    /** Maximum hand value (also bust threshold). */
    public static final int MAX_SCORE = 21;

    /** Score that constitutes an immediate blackjack for the player. */
    public static final int BLACKJACK_SCORE = 21;

    /** Dealer stands on this value (classic rule: stand on 17). */
    public static final int DEALER_STANDS_ON = 17;
}