package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        // Проходим по всем мастям
        for (Suit suit : Suit.values()) {
            // Проходим по всем рангам
            for (Rank rank : Rank.values()) {
                // Добавляем новую карту в колоду
                this.cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public Card dealCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(cards.size() - 1);
    }
}