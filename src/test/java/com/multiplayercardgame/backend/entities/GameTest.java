
package com.multiplayercardgame.backend.entities;

import com.multiplayercardgame.backend.services.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class GameTest {
    private Game game;

    @BeforeEach
    void setUp() {
        List<String> playerNames = Arrays.asList("Alice", "Bob", "Charlie");
        game = new Game(playerNames);
    }

    @Test
    void testDealCards() {
        game.dealCards();
        int totalCardsDealt = game.players.stream().mapToInt(player -> player.getHand().size()).sum();
        assertEquals(15, totalCardsDealt);
    }

    @Test
    void testGetNextPlayerIndex() {
        assertEquals(1, game.getNextPlayerIndex()); // next player after the first player
        game.reverseOrder = true;
        assertEquals(2, game.getNextPlayerIndex()); // next player when playing order is reversed
    }

    @Test
    void testIsOver() {
        // game is not over
        assertFalse(game.isOver());
        // game is over when all players except one have empty hands
        game.players.get(0).setHand(List.of(new Card(Suit.HEARTS, Rank.ACE)));
        game.players.get(1).setHand(List.of(new Card(Suit.DIAMONDS, Rank.KING)));
        assertTrue(game.isOver());
    }

    /*@Test
    void testGetWinner() {
        // no winner when game is not over
        assertNull(game.getWinner());
        // winner is the player with an empty hand
        game.players.get(1).setHand(List.of(new Card(Suit.HEARTS, Rank.ACE)));
        assertEquals(game.players.get(1), game.getWinner());
    }

    @Test
    void testHandleActionCard() {
        // test Ace action card
        game.handleActionCard(new Card(Suit.HEARTS, Rank.ACE));
        assertEquals(1, game.currentPlayerIndex); // next player should be skipped
        // test King action card
        game.handleActionCard(new Card(Suit.DIAMONDS, Rank.KING));
        assertTrue(game.reverseOrder); // playing order should be reversed
        // test Queen action card
        int initialHandSize = game.players.get(1).getHand().size();
        game.handleActionCard(new Card(Suit.CLUBS, Rank.QUEEN));
        assertEquals(initialHandSize + 2, game.players.get(1).getHand().size()); // next player should draw two cards
        // test Jack action card
        initialHandSize = game.players.get(2).getHand().size();
        game.handleActionCard(new Card(Suit.SPADES, Rank.JACK));
        assertEquals(initialHandSize + 1, game.players.get(2).getHand().size()); // next player should draw one card
    }*/
}