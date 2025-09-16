package org.example;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public int getScore() {
        int score = 0;
        int aceCount = 0; // Счетчик тузов

        for (Card card : cards) {
            score += card.getValue();
            if (card.getRank() == Rank.ACE) {
                aceCount++;
            }
        }

        // Если у нас есть тузы и счет > 21,
        // вычитаем 10 за каждый туз, пока счет не станет <= 21
        while (score > 21 && aceCount > 0) {
            score -= 10;
            aceCount--;
        }

        return score;
    }

    @Override
    public String toString() {
        return cards.toString();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void clear() {
        this.cards.clear();
    }
}