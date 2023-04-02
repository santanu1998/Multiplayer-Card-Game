package com.multiplayercardgame.backend.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    public void testPlayer() {
        String name = "John";
        Player player = new Player(name);
        assertEquals(name, player.getName());
        assertEquals(0, player.getHand().size());
    }

    @Test
    public void testChooseCard() {
        Player player = new Player("John");
        Card card1 = new Card(Suit.HEARTS, Rank.TEN);
        Card card2 = new Card(Suit.SPADES, Rank.TWO);
        player.getHand().add(card1);
        player.getHand().add(card2);
        Card chosenCard = player.chooseCard(new Card(Suit.HEARTS, Rank.FIVE));
        assertEquals(card1, chosenCard);
        chosenCard = player.chooseCard(new Card(Suit.CLUBS, Rank.FIVE));
        assertNull(chosenCard);
    }
}
