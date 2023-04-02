package com.multiplayercardgame.backend.entities;

public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }


    public boolean matches(Card other) {
        if (other == null) {
            return false;
        }
        return this.suit == other.suit || this.rank == other.rank;
    }

    /*public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }*/

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}