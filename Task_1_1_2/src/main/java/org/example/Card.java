package org.example;

public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getValue() {
        return rank.getValue();
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        // Для красивого вывода в консоль, например "ACE of SPADES"
        return rank + " of " + suit;
    }
}
