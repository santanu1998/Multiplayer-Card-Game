package com.multiplayercardgame.backend.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    @Test
    public void testGetSuit() {
        Card card = new Card(Suit.DIAMONDS, Rank.EIGHT);
        assertEquals(Suit.DIAMONDS, card.getSuit());
    }

    @Test
    public void testGetRank() {
        Card card = new Card(Suit.HEARTS, Rank.JACK);
        assertEquals(Rank.JACK, card.getRank());
    }

    @Test
    public void testMatchesSameSuit() {
        Card card1 = new Card(Suit.CLUBS, Rank.THREE);
        Card card2 = new Card(Suit.CLUBS, Rank.KING);
        assertTrue(card1.matches(card2));
    }

    @Test
    public void testMatchesSameRank() {
        Card card1 = new Card(Suit.SPADES, Rank.ACE);
        Card card2 = new Card(Suit.HEARTS, Rank.ACE);
        assertTrue(card1.matches(card2));
    }

    @Test
    public void testMatchesDifferentSuitAndRank() {
        Card card1 = new Card(Suit.HEARTS, Rank.FOUR);
        Card card2 = new Card(Suit.DIAMONDS, Rank.QUEEN);
        assertFalse(card1.matches(card2));
    }

    @Test
    public void testMatchesNull() {
        Card card1 = new Card(Suit.SPADES, Rank.JACK);
        assertFalse(card1.matches(null));
    }
}