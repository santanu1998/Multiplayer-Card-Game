package com.multiplayercardgame.backend.entities;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void drawCard(Deck deck) {
        hand.add(deck.dealCard());
    }

    public void playCard(Card card, Deck discardPile) {
        hand.remove(card);
        discardPile.push(card);
    }

    public Card chooseCard(Card topCard) {
        for (Card card : hand) {
            if (card.getSuit() == topCard.getSuit() || card.getRank() == topCard.getRank()) {
                return card;
            }
        }
        return null;
    }
}
/*    private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public Card playCard(Card topCard) {
        for (Card card : hand) {
            if (card.getRank() == topCard.getRank() || card.getSuit() == topCard.getSuit()) {
                hand.remove(card);
                return card;
            }
        }
        return null;
    }

    public void drawCard(Card card) {
        hand.add(card);
    }

    public boolean hasCards() {
        return !hand.isEmpty();
    }

    public String toString() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }
}*/
    /*private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void drawCard(Card card) {
        hand.add(card);
    }

    public void playCard(int index) {
        if (index < 0 || index >= hand.size()) {
            throw new IllegalArgumentException("Invalid card index");
        }
        hand.remove(index);
    }*/
    /*private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getHandSize() {
        return hand.size();
    }

    public boolean hasCard(Card card) {
        return hand.contains(card);
    }

    public Card playCard(int index) {
        if (index < 0 || index >= hand.size()) {
            return null;
        }
        return hand.remove(index);
    }

    public void drawCard(Card card) {
        hand.add(card);
    }

    public Card chooseCardToPlay(Card topCard) {
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            if (card.getSuit() == topCard.getSuit() || card.getRank() == topCard.getRank()) {
                return card;
            }
        }
        return null;
    }*/


/*
import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }

    public Player() {
        hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public Card removeCardFromHand(int index) {
        return hand.remove(index);
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getHandSize() {
        return hand.size();
    }

    public Card playCard(Card topCard) {
        for (int i = 0; i < this.hand.size(); i++) {
            Card card = this.hand.get(i);
            if (card.matches(topCard)) {
                return this.hand.remove(i);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name + "'s hand: " + hand;
    }
}

    private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void addCardToHand(Card card) {
        this.hand.add(card);
    }

    public Card playCard(Card topCard) {
        for (int i = 0; i < this.hand.size(); i++) {
            Card card = this.hand.get(i);
            if (card.matches(topCard)) {
                return this.hand.remove(i);
            }
        }
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public int getHandSize() {
        return hand.size();
    }*/