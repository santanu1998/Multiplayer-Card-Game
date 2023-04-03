package com.multiplayercardgame.backend;

import com.multiplayercardgame.backend.services.Game;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of players: ");
        int numPlayers = scanner.nextInt();
        if (numPlayers < 2 || numPlayers > 4) {
            throw new Exception("Invalid number of players. The game can be played with 2 to 4 players only.");
        }
        scanner.nextLine(); // consume the newline character
        String[] playerNames = new String[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name for player " + (i + 1) + ": ");
            playerNames[i] = scanner.nextLine();
        }

        List<String> playerList = Arrays.asList(playerNames);
        Game game = new Game(playerList);
        game.play();
    }
}


    /*
    private List<Player> players;
    private List<Card> deck;
    private List<Card> discardPile;
    private int currentPlayerIndex;
    private int direction;
    private boolean gameEnded;
    private Random random;

    public Main() {
        players = new ArrayList<Player>();
        deck = new ArrayList<Card>();
        discardPile = new ArrayList<Card>();
        currentPlayerIndex = 0;
        direction = 1;
        gameEnded = false;
        random = new Random();
    }

    public void startGame(int numPlayers) {
        if (numPlayers < 2 || numPlayers > 4) {
            throw new IllegalArgumentException("Number of players should be between 2 and 4.");
        }
        initializeDeck();
        shuffleDeck();
        dealCards(numPlayers);
        startTurns();
    }

    private void initializeDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
    }

    private void shuffleDeck() {
        Collections.shuffle(deck, random);
    }

    private void dealCards(int numPlayers) {
        for (int i = 0; i < numPlayers; i++) {
            Player player = new Player();
            for (int j = 0; j < 5; j++) {
                player.addCardToHand(drawCardFromDeck());
            }
            players.add(player);
        }
    }

    private void startTurns() {
        while (!gameEnded) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("Current player: " + currentPlayer.getName());
            Card topCard = getTopCardOnDiscardPile();
            if (topCard == null) {
                System.out.println("Discard pile is empty.");
            } else {
                System.out.println("Top card on discard pile: " + topCard.toString());
            }
            System.out.println("Current player's hand: " + currentPlayer.getHand().toString());
            if (currentPlayer.getHand().isEmpty()) {
                System.out.println("Player " + currentPlayer.getName() + " has won the game!");
                gameEnded = true;
                break;
            }
            boolean cardPlayed = false;
            while (!cardPlayed) {
                Card playedCard = currentPlayer.playCard(getTopCardOnDiscardPile());
                if (playedCard != null) {
                    discardPile.add(playedCard);
                    System.out.println("Player " + currentPlayer.getName() + " played " + playedCard.toString());
                    handleActionCard(playedCard);
                    cardPlayed = true;
                } else {
                    System.out.println("Player " + currentPlayer.getName() + " cannot play any cards and must draw.");
                    Card drawnCard = drawCardFromDeck();
                    if (drawnCard == null) {
                        System.out.println("Draw pile is empty. Game ends in a draw.");
                        gameEnded = true;
                        break;
                    } else {
                        System.out.println("Player " + currentPlayer.getName() + " drew " + drawnCard.toString());
                        currentPlayer.addCardToHand(drawnCard);
                    }
                }
            }
            updateCurrentPlayerIndex();
        }
    }


    private Card drawCardFromDeck() {
        if (deck.isEmpty()) {
            return null;
        }
        return deck.remove(0);
    }

    private Card getTopCardOnDiscardPile() {
        if (discardPile.isEmpty()) {
            return null;
        }
        return discardPile.get(discardPile.size() - 1);
    }
    private void handleActionCard(Card card) {
        switch (card.getRank()) {
            case ACE -> skipNextPlayer();
            case KING -> reverseTurnOrder();
            case QUEEN -> {
                Player nextPlayer = getNextPlayer();
                nextPlayer.addCardToHand(drawCardFromDeck());
                nextPlayer.addCardToHand(drawCardFromDeck());
            }
            case JACK -> {
                Player nextPlayer = getNextPlayer();
                nextPlayer.addCardToHand(drawCardFromDeck());
                nextPlayer.addCardToHand(drawCardFromDeck());
                nextPlayer.addCardToHand(drawCardFromDeck());
                nextPlayer.addCardToHand(drawCardFromDeck());
            }
        }
    }

    private void skipNextPlayer() {
        Player nextPlayer = getNextPlayer();
        System.out.println("Player " + nextPlayer.getName() + " is skipped.");
        updateCurrentPlayerIndex();
    }

    private void reverseTurnOrder() {
        direction *= -1;
        System.out.println("Direction reversed.");
    }

    private Player getNextPlayer() {
        int nextPlayerIndex = currentPlayerIndex + direction;
        if(nextPlayerIndex < 0) {
            nextPlayerIndex = players.size() - 1;
        } else if(nextPlayerIndex >= players.size()) {
            nextPlayerIndex = 0;
        }
        return players.get(nextPlayerIndex);
    }

    private void updateCurrentPlayerIndex() {
        currentPlayerIndex += direction;
        if(currentPlayerIndex < 0) {
            currentPlayerIndex = players.size() - 1;
        } else if(currentPlayerIndex >= players.size()) {
            currentPlayerIndex = 0;
        }
    }

    public static void main(String[] args) {
        Main cardGame = new Main();
        cardGame.startGame(2);
    }
}
/*
    private static final int HAND_SIZE = 5;
    private static final int NUM_PLAYERS = 2;
    private static final int MAX_PLAYERS = 4;

    private final List<Player> players;
    private final Deck deck;
    private final List<Card> discardPile;
    private int currentPlayerIndex;
    private boolean reverseOrder;
    private int cardsToDraw;
    public CardGame(int numPlayers) {
        if (numPlayers < 2 || numPlayers > MAX_PLAYERS) {
            throw new IllegalArgumentException("Number of players must be between 2 and " + MAX_PLAYERS);
        }

        players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player("Player " + (i+1)));
        }

        deck = new Deck();
        deck.shuffle();

        discardPile = new ArrayList<>();
        discardPile.add(deck.drawCard());

        currentPlayerIndex = 0;
        reverseOrder = false;
        cardsToDraw = 0;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;
        while (!gameOver) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println(currentPlayer.getName() + "'s turn:");
            System.out.println("Top card on discard pile: " + discardPile.get(discardPile.size()-1));
            System.out.println("Your hand: " + currentPlayer.getHand());
            System.out.println("Enter the index of the card you want to play, or enter -1 to draw a card:");
            int choice = scanner.nextInt();
            if (choice == -1) {
                if (cardsToDraw > 0) {
                    int numCardsToDraw = Math.min(cardsToDraw, deck.getCards().size());
                    for (int i = 0; i < numCardsToDraw; i++) {
                        Card drawnCard = deck.drawCard();
                        currentPlayer.addCardToHand(drawnCard);
                        System.out.println("You drew a " + drawnCard);
                    }
                    cardsToDraw = 0;
                } else {
                    Card drawnCard = deck.drawCard();
                    currentPlayer.addCardToHand(drawnCard);
                    System.out.println("You drew a " + drawnCard);
                }
            } else {
                Card selectedCard = currentPlayer.playCard(discardPile.get(choice));
                if (isValidPlay(selectedCard)) {
                    discardPile.add(selectedCard);
                    System.out.println("You played a " + selectedCard);
                    applyCardAction(selectedCard);
                    if (currentPlayer.getHandSize() == 0) {
                        System.out.println(currentPlayer.getName() + " wins!");
                        gameOver = true;
                    }
                } else {
                    System.out.println("That is not a valid play. Please try again.");
                }
            }
            currentPlayerIndex = getNextPlayerIndex();
        }
        scanner.close();
    }

    private boolean isValidPlay(Card card) {
        Card topCard = discardPile.get(discardPile.size()-1);
        return card.getRank() == topCard.getRank() || card.getSuit() == topCard.getSuit();
    }

    private void applyCardAction(Card card) {
        switch (card.getRank()) {
            case ACE -> currentPlayerIndex = getNextPlayerIndex();
            case KING -> reverseOrder = !reverseOrder;
            case QUEEN -> cardsToDraw += 2;
            case JACK -> cardsToDraw += 4;
            default -> {
            }
        }
    }

    private int getNextPlayerIndex() {
        int numPlayers = players.size();
        if (cardsToDraw > 0) {
            reverseOrder = false;
        }
        if (reverseOrder) {
            return (currentPlayerIndex - 1 + numPlayers) % numPlayers;
        } else {
            return (currentPlayerIndex + 1) % numPlayers;
        }
    }

    public static void main(String[] args) {
        CardGame game = new CardGame(NUM_PLAYERS);
        game.play();
    }

    *//*public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // initialize players
        List<String> playerNames = new ArrayList<>();
        System.out.println("Enter player names (4 players only):");
        for (int i = 0; i < 4; i++) {
            String playerName = scanner.nextLine();
            playerNames.add(playerName);
        }
        Game game = new Game(playerNames);

        // start game
        game.start();
    }*//*
}*/


/*
import com.multiplayercardgame.backend.entities.Card;
import com.multiplayercardgame.backend.entities.Deck;
import com.multiplayercardgame.backend.entities.Player;
import com.multiplayercardgame.backend.entities.Rank;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private final Deck deck;
    private final List<Player> players;
    private final List<Card> discardPile;
    private int currentPlayerIndex;
    private boolean clockwise;
    private boolean skip;

    public Main(List<String> playerNames) {
        this.deck = new Deck();
        this.players = new ArrayList<>();
        for (String playerName : playerNames) {
            this.players.add(new Player(playerName));
        }
        this.discardPile = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.clockwise = true;
        this.skip = false;
    }

    public void start() {
        // shuffle the deck
        this.deck.shuffle();

        // deal 5 cards to each player
        for (int i = 0; i < 5; i++) {
            for (Player player : this.players) {
                Card card = this.deck.drawCard();
                if (card != null) {
                    player.addCardToHand(card);
                }
            }
        }

        // start the game
        while (true) {
            Player currentPlayer = this.players.get(this.currentPlayerIndex);
            Card topCard = this.discardPile.isEmpty() ? null : this.discardPile.get(this.discardPile.size() - 1);
            Card playedCard = currentPlayer.playCard(topCard);
            if (playedCard != null) {
                this.discardPile.add(playedCard);
                if (playedCard.getRank() == Rank.ACE) {
                    this.skip = true;
                } else if (playedCard.getRank() == Rank.KING) {
                    this.clockwise = !this.clockwise;
                } else if (playedCard.getRank() == Rank.QUEEN) {
                    this.drawCards(2);
                } else if (playedCard.getRank() == Rank.JACK) {
                    this.drawCards(4);
                }
            } else {
                Card drawnCard = this.deck.drawCard();
                if (drawnCard != null) {
                    currentPlayer.addCardToHand(drawnCard);
                } else {
// game ends in a draw if there are no more cards to draw
                    System.out.println("Game ends in a draw.");
                    return;
                }
            }
            // check if the current player has won
            if (currentPlayer.getHand().isEmpty()) {
                System.out.println(currentPlayer.getName() + " has won the game!");
                return;
            }

            // update current player index
            int nextPlayerIndex = this.getNextPlayerIndex();
            if (this.skip) {
                nextPlayerIndex = this.getNextPlayerIndex();
                this.skip = false;
            }
            this.currentPlayerIndex = nextPlayerIndex;
        }
    }

    private void drawCards(int numCards) {
        int nextPlayerIndex = this.getNextPlayerIndex();
        for (int i = 0; i < numCards; i++) {
            Card drawnCard = this.deck.drawCard();
            if (drawnCard != null) {
                this.players.get(nextPlayerIndex).addCardToHand(drawnCard);
            } else {
                // game ends in a draw if there are no more cards to draw
                System.out.println("Game ends in a draw.");
                return;
            }
            nextPlayerIndex = this.getNextPlayerIndex(nextPlayerIndex);
        }
    }

    private int getNextPlayerIndex() {
        int nextPlayerIndex = this.currentPlayerIndex;
        if (this.clockwise) {
            nextPlayerIndex = (nextPlayerIndex + 1) % this.players.size();
        } else {
            nextPlayerIndex = (nextPlayerIndex - 1 + this.players.size()) % this.players.size();
        }
        return nextPlayerIndex;
    }

    private int getNextPlayerIndex(int currentIndex) {
        int nextPlayerIndex = currentIndex;
        if (this.clockwise) {
            nextPlayerIndex = (nextPlayerIndex + 1) % this.players.size();
        } else {
            nextPlayerIndex = (nextPlayerIndex - 1 + this.players.size()) % this.players.size();
        }
        return nextPlayerIndex;
    }
    public static void main(String[] args) {
        List<String> playerNames = new ArrayList<String>();
        playerNames.add("Alice");
        playerNames.add("Bob");
        playerNames.add("Charlie");
        Main game = new Main(playerNames);
        game.start();
    }
}

*/