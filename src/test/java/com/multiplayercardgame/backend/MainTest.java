/*
package com.multiplayercardgame.backend;

import com.multiplayercardgame.backend.entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class MainTest {
    private Game game;
    private List<String> playerNames;

    @BeforeEach
    void setUp() {
        playerNames = Arrays.asList("Alice", "Bob", "Charlie");
        game = new Game(playerNames);
    }

    @Test
    void testDealCards() {
        game.play();
        for (Player player : game.getPlayers()) {
            Assertions.assertEquals(5, player.getHand().size(), "Player should have 5 cards.");
        }
        Assertions.assertEquals(1, game.getDiscardPile().size(), "Discard pile should have 1 card.");
    }

    @Test
    void testIsOver() {
        for (Player player : game.getPlayers()) {
            player.getHand().clear();
        }
        Assertions.assertTrue(game.isOver(), "Game should be over when all players have empty hands.");
    }

    @Test
    void testGetWinner() {
        game.getPlayers().get(0).getHand().clear();
        game.getPlayers().get(2).getHand().clear();
        Assertions.assertEquals(game.getPlayers().get(1), game.getWinner(), "Player with non-empty hand should win.");
    }

    @Test
    void testHandleActionCard_Ace() {
        game.handleActionCard(new Card(Suit.SPADES, Rank.ACE));
        Assertions.assertEquals(1, game.getNextPlayerIndex(), "Next player should be skipped.");
    }

    @Test
    void testHandleActionCard_King() {
        game.handleActionCard(new Card(Suit.SPADES, Rank.KING));
        Assertions.assertTrue(game.isReverseOrder(), "Playing order should be reversed.");
    }

    @Test
    void testHandleActionCard_Queen() {
        int nextPlayerIndex = game.getNextPlayerIndex();
        game.handleActionCard(new Card(Suit.SPADES, Rank.QUEEN));
        Assertions.assertEquals(2, game.getDrawCards(), "Next player should draw two cards.");
        Assertions.assertEquals(nextPlayerIndex, game.getNextPlayerIndex(), "Next player should not change.");
    }

    @Test
    void testHandleActionCard_Jack() {
        int nextPlayerIndex = game.getNextPlayerIndex();
        game.handleActionCard(new Card(Suit.SPADES, Rank.JACK));
        Assertions.assertEquals(4, game.getDrawCards(), "Next player should draw four cards.");
        Assertions.assertEquals(nextPlayerIndex, game.getNextPlayerIndex(), "Next player should not change.");
    }
}
*/
