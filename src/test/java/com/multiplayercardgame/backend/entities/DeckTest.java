package com.multiplayercardgame.backend.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    @Test
    public void testSize() {
        Deck deck = new Deck();
        assertEquals(52, deck.size());
    }

    @Test
    public void testIsEmpty() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            assertFalse(deck.isEmpty());
            deck.dealCard();
        }
        assertTrue(deck.isEmpty());
    }

    @Test
    public void testShuffle() {
        Deck deck1 = new Deck();
        Deck deck2 = new Deck();
        deck2.shuffle();
        // compare the first 10 cards to see if they are different
        for (int i = 0; i < 10; i++) {
            Card card1 = deck1.dealCard();
            Card card2 = deck2.dealCard();
            assertNotEquals(card1, card2);
        }
    }
}