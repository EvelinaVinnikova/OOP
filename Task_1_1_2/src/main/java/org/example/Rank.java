package org.example;

/**
 * Represents the ranks of cards in a standard deck, along with their point values.
 */
public enum Rank {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),   // Валет
    QUEEN(10),  // Дама
    KING(10),   // Король
    ACE(11);    // Туз (по умолчанию 11)

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}