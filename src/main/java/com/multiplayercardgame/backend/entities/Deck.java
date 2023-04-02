package com.multiplayercardgame.backend.entities;

import java.util.*;

public class Deck {
    private final Stack<Card> cards;
    List<Card> cardList = new ArrayList<>();
    public Deck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cardList.add(new Card(suit, rank));
            }
        }
        cards = new Stack<>();
        for (Card card : cardList) {
            cards.push(card);
        }
    }

    public Card dealCard() {
        return cards.pop();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public void push(Card card) {
        cards.push(card);
    }

    public void shuffle() {
        Collections.shuffle(cardList, new Random());
    }
}
    /*private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards, new Random());
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }

    public int size() {
        return cards.size();
    }*/

/*import java.util.*;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        shuffle();
    }

    public Card drawCard() {
        return cards.remove(cards.size() - 1);
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    *//*public int getNumCards() {
        c
    }*//*

    public void shuffle() {
        Collections.shuffle(cards, new Random());
    }
}*/
