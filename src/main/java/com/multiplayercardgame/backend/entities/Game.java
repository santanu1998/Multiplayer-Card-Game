package com.multiplayercardgame.backend.entities;

import java.util.*;

public class Game {
    private final Deck deck;
    private final Stack<Card> discardPile;
    public final List<Player> players;
    public int currentPlayerIndex;
    public boolean reverseOrder;
    private int drawCards;
    private final Random random;

    public Game(List<String> playerNames) {
        this.deck = new Deck();
        this.deck.shuffle();
        this.discardPile = new Stack<>();
        this.players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(new Player(name));
        }
        currentPlayerIndex = 0;
        reverseOrder = false;
        drawCards = 0;
        random = new Random();
    }

    public void play() {
        dealCards();
        Card topCard = discardPile.pop();
        while (!isOver()) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("It's " + currentPlayer.getName() + "'s turn.");
            System.out.println("Top card: " + topCard);
            System.out.println("Your hand: " + currentPlayer.getHand());
            Card card = currentPlayer.chooseCard(topCard);
            if (card != null) {
                System.out.println(currentPlayer.getName() + " played " + card);
                currentPlayer.playCard(card, deck);
                handleActionCard(card);
                topCard = card;
            } else {
                if (deck.isEmpty()) {
                    System.out.println("The deck is empty. Game over!");
                    break;
                }
                System.out.println(currentPlayer.getName() + " drew a card.");
                currentPlayer.drawCard(deck);
                drawCards = 0;
            }
            System.out.println();
            currentPlayerIndex = getNextPlayerIndex();
        }
        Player winner = getWinner();
        if (winner != null) {
            System.out.println("Game over! " + winner.getName() + " wins!");
        } else {
            System.out.println("Game over! Match is drawn.");
        }
    }

    public void dealCards() {
        for (int i = 0; i < 5; i++) {
            for (Player player : players) {
                player.drawCard(deck);
            }
        }
        discardPile.push(deck.dealCard());
    }

    public int getNextPlayerIndex() {
        int increment = reverseOrder ? -1 : 1;
        int nextPlayerIndex = currentPlayerIndex + increment;
        if (nextPlayerIndex < 0) {
            nextPlayerIndex = players.size() - 1;
        } else if (nextPlayerIndex == players.size()) {
            nextPlayerIndex = 0;
        }
        return nextPlayerIndex;
    }

    public boolean isOver() {
        int countPlayers = 0;
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                countPlayers++;
            }
        }
        return countPlayers == 1;
    }

    public Player getWinner() {
        Player winner = null;
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                winner = player;
                break;
            }
        }
        return winner;
    }

    public void handleActionCard(Card card) {
        Rank rank = card.getRank();
        if (rank == Rank.ACE) {
            System.out.println("Next player skipped!");
            currentPlayerIndex = getNextPlayerIndex();
        } else if (rank == Rank.KING) {
            System.out.println("Playing order reversed!");
            reverseOrder = !reverseOrder;
        } else if (rank == Rank.QUEEN) {
            System.out.println("Player " + players.get(getNextPlayerIndex()).getName() + " mustdraw two cards.");
            Player nextPlayer = players.get(getNextPlayerIndex());
            nextPlayer.drawCard(deck);
            nextPlayer.drawCard(deck);
            drawCards += 2;
        } else if (rank == Rank.JACK) {
            System.out.println("Player " + players.get(getNextPlayerIndex()).getName() + " must draw a card.");
            Player nextPlayer = players.get(getNextPlayerIndex());
            nextPlayer.drawCard(deck);
            drawCards += 4;
        }
        if (drawCards > 0) {
            Player nextPlayer = players.get(getNextPlayerIndex());
            for (int i = 0; i < drawCards; i++) {
                System.out.println("Player " + nextPlayer.getName() + " must draw a card.");
                nextPlayer.drawCard(deck);
            }
            drawCards = 0;
        }
    }
}














/*package com.multiplayercardgame.backend.entities;

import java.util.*;

public class Game {
    private final Deck deck;
    private final Stack<Card> discardPile;
    private final List<Player> players;
    private int currentPlayerIndex;
    private boolean reverseOrder;
    private int drawCards;
    private final Random random;

    public Game(List<String> playerNames) {
        this.deck = new Deck();
        this.deck.shuffle();
        this.discardPile = new Stack<>();
        this.players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(new Player(name));
        }
        currentPlayerIndex = 0;
        reverseOrder = false;
        drawCards = 0;
        random = new Random();
    }

    public void play() {
        dealCards();
        Card topCard = discardPile.pop();
        while (!isOver()) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("It's " + currentPlayer.getName() + "'s turn.");
            System.out.println("Top card: " + topCard);
            System.out.println("Your hand: " + currentPlayer.getHand());
            Card card = currentPlayer.chooseCard(topCard);
            if (card != null) {
                System.out.println(currentPlayer.getName() + " played " + card);
                currentPlayer.playCard(card, deck);
                handleActionCard(card);
                topCard = card;
            } else {
                if (deck.isEmpty()) {
                    System.out.println("The deck is empty. Game over!");
                    break;
                }
                System.out.println(currentPlayer.getName() + " drew a card.");
                currentPlayer.drawCard(deck);
                drawCards = 0;
            }
            System.out.println();
            currentPlayerIndex = getNextPlayerIndex();
        }
        Player winner = getWinner();
        if (winner != null) {
            System.out.println("Game over! " + winner.getName() + " wins!");
        } else {
            System.out.println("Game over! Match is drawn.");
        }
    }

    private void dealCards() {
        for (int i = 0; i < 5; i++) {
            for (Player player : players) {
                player.drawCard(deck);
            }
        }
        discardPile.push(deck.dealCard());
    }

    private int getNextPlayerIndex() {
        int increment = reverseOrder ? -1 : 1;
        int nextPlayerIndex = currentPlayerIndex + increment;
        if (nextPlayerIndex < 0) {
            nextPlayerIndex = players.size() - 1;
        } else if (nextPlayerIndex == players.size()) {
            nextPlayerIndex = 0;
        }
        return nextPlayerIndex;
    }

    private boolean isOver() {
        int countPlayers = 0;
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                countPlayers++;
            }
        }
        return countPlayers == 1;
    }

    private Player getWinner() {
        Player winner = null;
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                winner = player;
                break;
            }
        }
        return winner;
    }

    private void handleActionCard(Card card) {
        Rank rank = card.getRank();
        if (rank == Rank.ACE) {
            System.out.println("Next player skipped!");
            currentPlayerIndex = getNextPlayerIndex();
        } else if (rank == Rank.KING) {
            System.out.println("Playing order reversed!");
            reverseOrder = !reverseOrder;
        } else if (rank == Rank.QUEEN) {
            System.out.println("Player " + players.get(getNextPlayerIndex()) + " must draw two cards!");
            players.get(getNextPlayerIndex()).drawCard(deck);
            players.get(getNextPlayerIndex()).drawCard(deck);
            drawCards += 2;
        } else if (rank == Rank.JACK) {
            System.out.println("Player " + players.get(getNextPlayerIndex()) + " must draw one card!");
            players.get(getNextPlayerIndex()).drawCard(deck);
            drawCards += 4;
        }
        if (drawCards > 0) {
            System.out.println("Player " + players.get(getNextPlayerIndex()) + " must draw " + drawCards + " card(s)!");
            for (int i = 0; i < drawCards; i++) {
                players.get(getNextPlayerIndex()).drawCard(deck);
            }
            drawCards = 0;
        }
    }
}*/



/*
public class Game {
    private final Deck deck;
    private final Stack<Card> discardPile;
    private final List<Player> players;
    private int currentPlayerIndex;
    private boolean reverseOrder;
    private int drawCards;

    public Game(List<String> playerNames) {
        this.deck = new Deck();
        this.discardPile = new Stack<>();
        this.players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(new Player(name));
        }
        currentPlayerIndex = 0;
        reverseOrder = false;
        drawCards = 0;
    }

    public void play() {
        Collections.shuffle(deck.getCards()); // shuffle the deck before dealing cards
        dealCards();
        Card topCard = discardPile.pop();
        while (!isOver()) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("It's " + currentPlayer.getName() + "'s turn.");
            System.out.println("Top card: " + topCard);
            System.out.println("Your hand: " + currentPlayer.getHand());
            Card card = currentPlayer.chooseCard(topCard);
            if (card != null) {
                System.out.println(currentPlayer.getName() + " played " + card);
                currentPlayer.playCard(card, deck);
                handleActionCard(card);
                topCard = card;
            } else {
                if (deck.isEmpty()) {
                    System.out.println("The deck is empty. Game over!");
                    return;
                }
                System.out.println(currentPlayer.getName() + " drew a card.");
                currentPlayer.drawCard(deck);
                drawCards = 0;
            }
            System.out.println();
            currentPlayerIndex = getNextPlayerIndex();
        }
        Player winner = getWinner();
        if (winner != null) {
            System.out.println("Game over! " + winner.getName() + " wins!");
        } else {
            System.out.println("Match is drawn!");
        }
    }

    private void dealCards() {
        for (int i = 0; i < 5; i++) {
            for (Player player : players) {
                player.drawCard(deck);
            }
        }
        discardPile.push(deck.dealCard());
    }

    private int getNextPlayerIndex() {
        int increment = reverseOrder ? -1 : 1;
        int nextPlayerIndex = currentPlayerIndex + increment;
        if (nextPlayerIndex < 0) {
            nextPlayerIndex = players.size() - 1;
        } else if (nextPlayerIndex == players.size()) {
            nextPlayerIndex = 0;
        }
        return nextPlayerIndex;
    }

    private boolean isOver() {
        int numPlayersWithCards = 0;
        for (Player player : players) {
            if (!player.getHand().isEmpty()) {
                numPlayersWithCards++;
            }
        }
        return numPlayersWithCards == 1;
    }
    private Player getWinner() {
        for (Player player : players) {
            if (!player.getHand().isEmpty()) {
                return player;
            }
        }
        return null;
    }

    private void handleActionCard(Card card) {
        switch (Suit.values()) {
            case :
                drawCards += 2;
                break;
            case DRAW_FOUR:
                drawCards += 4;
                break;
            case REVERSE:
                reverseOrder = !reverseOrder;
                break;
            case SKIP:
                currentPlayerIndex = getNextPlayerIndex();
                break;
            default:
                break;
        }
        if (drawCards > 0) {
            Player nextPlayer = players.get(getNextPlayerIndex());
            System.out.println(nextPlayer.getName() + " drew " + drawCards + " cards.");
            for (int i = 0; i < drawCards; i++) {
                nextPlayer.drawCard(deck);
            }
            drawCards = 0;
        }
    }

}

/*
import java.util.*;

public class Game {
    private final Deck deck;
    private final Stack<Card> discardPile;
    private final List<Player> players;
    private int currentPlayerIndex;
    private boolean reverseOrder;
    private int drawCards;

    public Game(List<String> playerNames) {
        this.deck = new Deck();
        this.discardPile = new Stack<>();
        this.players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(new Player(name));
        }
        currentPlayerIndex = 0;
        reverseOrder = false;
        drawCards = 0;
    }

    public void play() {
        dealCards();
        Card topCard = discardPile.pop();
        while (!isOver()) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("It's " + currentPlayer.getName() + "'s turn.");
            System.out.println("Top card: " + topCard);
            System.out.println("Your hand: " + currentPlayer.getHand());
            Card card = currentPlayer.chooseCard(topCard);
            if (card != null) {
                System.out.println(currentPlayer.getName() + " played " + card);
                currentPlayer.playCard(card, deck);
                handleActionCard(card);
                topCard = card;
            } else {
                if (deck.isEmpty()) {
                    System.out.println("The deck is empty. Game over!");
                    return;
                }
                System.out.println(currentPlayer.getName() + " drew a card.");
                currentPlayer.drawCard(deck);
                drawCards = 0;
            }
            System.out.println();
            currentPlayerIndex = getNextPlayerIndex();
        }
        Player winner = getWinner();
        if (winner != null) {
            System.out.println("Game over! " + winner.getName() + " wins!");
        }
    }
    private void dealCards() {
        for (int i = 0; i < 5; i++) {
            for (Player player : players) {
                player.drawCard(deck);
            }
        }
        discardPile.push(deck.dealCard());
    }

    private int getNextPlayerIndex() {
        int increment = reverseOrder ? -1 : 1;
        int nextPlayerIndex = currentPlayerIndex + increment;
        if (nextPlayerIndex < 0) {
            nextPlayerIndex = players.size() - 1;
        } else if (nextPlayerIndex == players.size()) {
            nextPlayerIndex = 0;
        }
        return nextPlayerIndex;
    }

    private boolean isOver() {
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private Player getWinner() {
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return player;
            }
        }
        return null;
    }

    private void handleActionCard(Card card) {
        Rank rank = card.getRank();
        if (rank == Rank.ACE) {
            System.out.println("Next player skipped!");
            currentPlayerIndex = getNextPlayerIndex();
        } else if (rank == Rank.KING) {
            System.out.println("Playing order reversed!");
            reverseOrder = !reverseOrder;
        } else if (rank == Rank.QUEEN) {
            drawCards += 2;
        } else if (rank == Rank.JACK) {
            drawCards += 4;
        }
        if (drawCards > 0) {
            Player currentPlayer = players.get(currentPlayerIndex);
            for (int i = 0; i < drawCards; i++) {
                if (deck.isEmpty()) {
                    System.out.println("The deck is empty. Game over!");
                    return;
                }
                System.out.println(currentPlayer.getName() + " drew a card.");
                currentPlayer.drawCard(deck);
            }
            drawCards = 0;
        }
    }
}

/*    private final List<Player> players;
    private final Deck deck;
    private final List<Card> discardPile;
    private final Random random;
    private final Map<Rank, Integer> rankActions;
    private final Map<String, Player> playerMap;
    private boolean reverseOrder;
    private boolean skipNextPlayer;

    public Game(List<String> playerNames) {
        if (playerNames.size() < 2 || playerNames.size() > 4) {
            throw new IllegalArgumentException("Number of players must be between 2 and 4.");
        }

        this.players = new ArrayList<>();
        this.playerMap = new HashMap<>();
        for (String playerName : playerNames) {
            Player player = new Player(playerName);
            players.add(player);
            playerMap.put(playerName, player);
        }

        this.deck = new Deck();
        this.discardPile = new ArrayList<>();
        this.random = new Random();
        this.rankActions = new HashMap<>();
        rankActions.put(Rank.ACE, 1);
        rankActions.put(Rank.KING, 2);
        rankActions.put(Rank.QUEEN, 3);
        rankActions.put(Rank.JACK, 4);
        this.reverseOrder = false;
        this.skipNextPlayer = false;

        // Shuffle the deck
        deck.shuffle();

        // Deal 5 cards to each player
        for (Player player : players) {
            List<Card> hand = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                hand.add(deck.drawCard());
            }
            player.setHand(hand);
        }

        // Move one card from the deck to the discard pile to start the game
        discardPile.add(deck.drawCard());
    }

    public void play() {
        int currentPlayerIndex = 0;

        while (true) {
            Player currentPlayer = players.get(currentPlayerIndex);

            System.out.println("It's " + currentPlayer.getName() + "'s turn.");

            Card topCard = discardPile.get(discardPile.size() - 1);

            // If the previous player played a reverse card, switch the order of play
            if (reverseOrder) {
                Collections.reverse(players);
                reverseOrder = false;
            }

            // If the previous player played a skip card, skip this player's turn
            if (skipNextPlayer) {
                System.out.println(currentPlayer.getName() + " was skipped!");
                skipNextPlayer = false;
                currentPlayerIndex = getNextPlayerIndex(currentPlayerIndex);
                continue;
            }

            boolean playedCard = false;

            while (!playedCard) {
                List<Card> playableCards = new ArrayList<>();
                for (Card card : currentPlayer.getHand()) {
                    if (card.getSuit() == topCard.getSuit() || card.getRank() == topCard.getRank()) {
                        playableCards.add(card);
                    }
                }

                if (playableCards.size() > 0) {
                    // The player has at least one card they can play
                    System.out.println(currentPlayer.getName() + " can play the following cards:");
                    for (int i = 0; i < playableCards.size(); i++) {
                        System.out.println((i + 1) + ": " + playableCards.get(i));
                    }

                    int cardIndex = getValidIntegerInput("Enter the index of the card you want to play: ", 1, playableCards.size()) - 1;
                    Card selectedCard = playableCards.get(cardIndex);
                    System.out.println(currentPlayer.getName() + " played " + selectedCard + ".");
                    currentPlayer.playCard(selectedCard);
                    discardPile.add(selectedCard);
                    playedCard = true;
                    // If the played card is a reverse card, switch the order of play
                    if (selectedCard.getRank() == Rank.REVERSE) {
                        reverseOrder = !reverseOrder;
                    }

                    // If the played card is a skip card, skip the next player's turn
                    if (selectedCard.getRank() == Rank.SKIP) {
                        skipNextPlayer = true;
                    }

                    // If the played card is a draw 2 card, make the next player draw 2 cards
                    if (selectedCard.getRank() == Rank.DRAW_TWO) {
                        Player nextPlayer = getNextPlayer(currentPlayer);
                        drawCards(nextPlayer, 2);
                    }

                    // If the played card is a wild card, ask the player to choose a suit
                    if (selectedCard.getRank() == Rank.WILD) {
                        Suit chosenSuit = getValidEnumInput("Choose a suit: ", Suit.class);
                        topCard = new Card(chosenSuit, Rank.WILD);
                        System.out.println(currentPlayer.getName() + " chose " + chosenSuit + ".");
                    } else {
                        topCard = selectedCard;
                    }

                    // If the player has no cards left, they win
                    if (currentPlayer.getHand().isEmpty()) {
                        System.out.println(currentPlayer.getName() + " wins!");
                        return;
                    }
                } else {
                    // The player has no cards they can play
                    System.out.println(currentPlayer.getName() + " has no playable cards and must draw a card.");
                    drawCards(currentPlayer, 1);
                }
            }

            currentPlayerIndex = getNextPlayerIndex(currentPlayerIndex);
        }
    }

    private void drawCards(Player player, int count) {
        for (int i = 0; i < count; i++) {
            Card drawnCard = deck.drawCard();
            System.out.println(player.getName() + " drew " + drawnCard + ".");
            player.addCardToHand(drawnCard);
        }
    }

    private int getNextPlayerIndex(int currentPlayerIndex) {
        int nextPlayerIndex;
        if (reverseOrder) {
            nextPlayerIndex = currentPlayerIndex - 1;
            if (nextPlayerIndex < 0) {
                nextPlayerIndex = players.size() - 1;
            }
        } else {
            nextPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
        return nextPlayerIndex;
    }

    private Player getNextPlayer(Player currentPlayer) {
        int currentPlayerIndex = players.indexOf(currentPlayer);
        int nextPlayerIndex = getNextPlayerIndex(currentPlayerIndex);
        return players.get(nextPlayerIndex);
    }

    private int getValidIntegerInput(String prompt, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int input;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.print(prompt);
                scanner.next();
            }
            input = scanner.nextInt();
        } while (input < min || input > max);
        return input;
    }

    private <T extends Enum<T>> T getValidEnumInput(String prompt, Class<T> enumClass) {
        Scanner scanner = new Scanner(System.in);
        T input;
        do {
            System.out.print(prompt);
            String inputString = scanner.nextLine().toUpperCase();
            try {
                input = Enum.valueOf(enumClass, inputString);
            } catch (IllegalArgumentException e) {
                input = null;
            }
        } while (input == null);
        return input;
    }

}*/

/*
import java.util.ArrayList;
import java.util.List;

public class Game {
    private int numPlayers;
    private List<Player> players;
    private Deck deck;
    private List<Card> discardPile;
    private int currentPlayerIndex;
    private boolean clockwise;
    private int turnSkip;
    private int drawCount;

    public Game(int numPlayers) {
        if (numPlayers < 2 || numPlayers > 4) {
            throw new IllegalArgumentException("Number of players must be between 2 and 4");
        }
        this.numPlayers = numPlayers;
        players = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            players.add(new Player("player " + i));
        }
        deck = new Deck();
        deck.shuffle();
        discardPile = new ArrayList<>();
        discardPile.add(deck.deal());
        currentPlayerIndex = 0;
        clockwise = true;
        turnSkip = 0;
        drawCount = 0;
        dealInitialCards();
    }

    private void dealInitialCards() {
        for (Player player : players) {
            for (int i = 0; i < 5; i++) {
                player.drawCard(deck.deal());
            }
        }
    }

    public void start() {
        while (true) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("It is " + currentPlayer.getName() + "'s turn.");

            if (turnSkip > 0) {
                System.out.println(currentPlayer.getName() + " is skipped this turn.");
                turnSkip--;
                nextPlayer();
                continue;
            }

            if (drawCount > 0) {
                System.out.println(currentPlayer.getName() + " must draw " + drawCount + " cards.");
                for (int i = 0; i < drawCount; i++) {
                    Card drawnCard = deck.deal();
                    System.out.println(currentPlayer.getName() + " draws " + drawnCard.toString());
                    currentPlayer.drawCard(drawnCard);
                }
                drawCount = 0;
                nextPlayer();
                continue;
            }

            System.out.println("Top card on discard pile: " + discardPile.get(discardPile.size() - 1));

            boolean playedCard = false;
            for (Card card : currentPlayer.getHand()) {
                if (card.matches(discardPile.get(discardPile.size() - 1))) {
                    System.out.println(currentPlayer.getName() + " plays " + card.toString());
                    currentPlayer.playCard(card);
                    discardPile.add(card);
                    playedCard = true;

                    if (card.isActionCard()) {
                        handleActionCard(card);
                    }

                    if (currentPlayer.getHand().size() == 0) {
                        System.out.println(currentPlayer.getName() + " has won the game!");
                        return;
                    }

                    nextPlayer();
                    break;
                }
            }

            if (!playedCard) {
                System.out.println(currentPlayer.getName() + " cannot play a card and draws a card.");
                Card drawnCard = deck.deal();
                currentPlayer.drawCard(drawnCard);
                discardPile.add(drawnCard);

                if (drawnCard.isActionCard()) {
                    handleActionCard(drawnCard);
                }

                if (deck.getRemainingCards() == 0) {
                    System.out.println("The deck is empty. The game ends in a draw.");
                    return;
                }

                nextPlayer();
            }
        }
    }

    private void handleActionCard(Card card) {
        switch (card.getRank()) {
            case ACE -> {
                System.out.println("Next player is skipped.");
                turnSkip++;
            }
            case KING -> {
                System.out.println("Playing order is reversed.");
                clockwise = !clockwise;
            }
            case QUEEN -> {
                System.out.println("Next player draws two cards.");
                drawCount += 2;
            }
            case JACK -> {
                System.out.println("Next player draws four cards.");
                drawCount += 4;
            }
            default -> {
            }
        }
    }

    private void nextPlayer() {
        if (clockwise) {
            currentPlayerIndex = (currentPlayerIndex + 1) % numPlayers;
        } else {
            currentPlayerIndex = (currentPlayerIndex - 1 + numPlayers) % numPlayers;
        }
    }
}
/*    private int numPlayers;
    private List<Player> players;
    private Deck deck;
    private List<Card> discardPile;
    private int currentPlayerIndex;
    private boolean clockwise;
    private int turnSkip;
    private int drawCount;

    public Game(int numPlayers) {
        if (numPlayers < 2 || numPlayers > 4) {
            throw new IllegalArgumentException("Number of players must be between 2 and 4");
        }
        this.numPlayers = numPlayers;
        players = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            players.add(new Player("player " + i));
        }
        deck = new Deck();
        deck.shuffle();
        discardPile = new ArrayList<>();
        discardPile.add(deck.deal());
        currentPlayerIndex = 0;
        clockwise = true;
        turnSkip = 0;
        drawCount = 0;
        dealInitialCards();
    }

    private void dealInitialCards() {
        for (Player player : players) {
            for (int i = 0; i < 5; i++) {
                player.drawCard(deck.deal());
            }
        }
    }

    public void playCard(int index) {
        Player currentPlayer = players.get(currentPlayerIndex);
        Card card = currentPlayer.getHand().get(index);
        if (isValidPlay(card)) {
            discardPile.add(card);
            currentPlayer.playCard(index);
            handleActionCard(card);
            checkForWinner();
            handleTurn();
        } else {
            throw new IllegalArgumentException("Invalid card play");
        }
    }

    private boolean isValidPlay(Card card) {
        Card topCard = discardPile.get(discardPile.size() - 1);
        return card.getRank() == topCard.getRank() || card.getSuit().equals(topCard.getSuit());
    }

    private void handleActionCard(Card card) {
        switch (card.getRank()) {
            case ACE -> handleSkipTurn();
            case KING -> handleReverseTurn();
            case QUEEN -> handleDrawCards(2);
            case JACK -> handleDrawCards(4);
            default -> {
            }
        }
    }

    private void handleSkipTurn() {
        turnSkip++;
        handleTurn();
    }

    private void handleReverseTurn() {
        clockwise = !clockwise;
        handleTurn();
    }

    private void handleDrawCards(int count) {
        drawCount += count;
        for (int i = 0; i < count; i++) {
            Player nextPlayer = getNextPlayer();
            nextPlayer.drawCard(deck.deal());
        }
        handleTurn();
    }

    private void handleTurn() {
        if (turnSkip > 0) {
            turnSkip--;
            getNextPlayerIndex();
        } else if (drawCount > 0) {
            drawCount--;
        } else {
            getNextPlayerIndex();
        }
    }

    private void getNextPlayerIndex() {
        if (clockwise) {
            currentPlayerIndex = (currentPlayerIndex + 1) % numPlayers;
        } else {
            currentPlayerIndex = (currentPlayerIndex - 1 + numPlayers) % numPlayers;
        }
    }

    private Player getNextPlayer() {
        int nextPlayerIndex;
        if (clockwise) {
            nextPlayerIndex = (currentPlayerIndex + 1) % numPlayers;
        } else {
            nextPlayerIndex = (currentPlayerIndex - 1 + numPlayers) % numPlayers;
        }
        return players.get(nextPlayerIndex);
    }

    private void checkForWinner() {
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                System.out.println(player.getName() + " wins!");
                System.exit(0);
            }
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Deck getDeck() {
        return deck;
    }

    public List<Card> getDiscardPile() {
        return discardPile;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public boolean isClockwise() {
        return clockwise;
    }

    public int getTurnSkip() {
        return turnSkip;
    }

    public int getDrawCount() {
        return drawCount;
    }

}*/

/*
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game {
    private List<Player> players;
    private Deck deck;
    private List<Card> discardPile;
    private boolean reverse;
    private int currentPlayerIndex;
    public Game(List<String> playerNames) {
        players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(new Player(playerName));
        }
        deck = new Deck();
        discardPile = new ArrayList<>();
        reverse = false;
        currentPlayerIndex = 0;
    }

    public void start() {
        // Shuffle the deck and deal 5 cards to each player
        deck.shuffle();
        for (Player player : players) {
            for (int i = 0; i < 5; i++) {
                player.drawCard(deck.draw());
            }
        }

        // Set the top card of the discard pile
        discardPile.add(deck.draw());

        // Play the game
        while (true) {
            // Get the current player and their hand
            Player currentPlayer = players.get(currentPlayerIndex);
            List<Card> hand = currentPlayer.getHand();

            // Print the current state of the game
            System.out.println("Top card: " + discardPile.get(discardPile.size() - 1));
            System.out.println(currentPlayer.getName() + "'s turn. Your hand: ");
            for (int i = 0; i < hand.size(); i++) {
                System.out.println((i + 1) + ". " + hand.get(i));
            }

            // Get the card the player wants to play or draw a card if they can't play
            Card cardToPlay = getCardToPlay(currentPlayer, hand);
            if (cardToPlay == null) {
                System.out.println("Drawing a card...");
                Card drawnCard = deck.draw();
                currentPlayer.drawCard(drawnCard);
                System.out.println("You drew a " + drawnCard);

                // Check if the deck is empty
                if (deck.isEmpty()) {
                    System.out.println("The deck is empty. The game ends in a draw.");
                    return;
                }
            } else {
                // Play the card and check for special actions
                hand.remove(cardToPlay);
                discardPile.add(cardToPlay);
                checkSpecialActions(cardToPlay);

                // Check if the player has won
                if (hand.isEmpty()) {
                    System.out.println(currentPlayer.getName() + " has won the game!");
                    return;
                }
            }

            // Move to the next player
            currentPlayerIndex = getNextPlayerIndex();
        }
    }

    private Card getCardToPlay(Player player, List<Card> hand) {
        // Get the top card of the discard pile
        Card topCard = discardPile.get(discardPile.size() - 1);

        // Check if the player has any cards they can play
        for (Card card : hand) {
            if (card.getRank() == topCard.getRank() || card.getSuit() == topCard.getSuit()) {
                System.out.println("Which card would you like to play? (Enter the number next to the card)");
                Scanner scanner = new Scanner(System.in);
                int cardIndex = scanner.nextInt() - 1;
                return hand.get(cardIndex);
            }
        }

        // If the player doesn't have any cards they can play, return null
        return null;
    }

    private void checkSpecialActions(Card card) {
        switch (card.getRank()) {

            case SKIP:
                System.out.println("Skipping the next player...");
                currentPlayerIndex = getNextPlayerIndex();
                break;
            case REVERSE:
                System.out.println("Reversing direction...");
                reverse = !reverse;
                break;
            case DRAW_TWO:
                System.out.println("Drawing two cards...");
                Player nextPlayer = getNextPlayer();
                nextPlayer.drawCard(deck.draw());
                nextPlayer.drawCard(deck.draw());
                break;
            case WILD:
                System.out.println("Choosing a new color...");
                Suit newSuit = getNewSuit();
                card.setSuit(newSuit);
                break;
            case DRAW_FOUR:
                System.out.println("Drawing four cards and choosing a new color...");
                Player nextPlayer2 = getNextPlayer();
                nextPlayer2.drawCard(deck.draw());
                nextPlayer2.drawCard(deck.draw());
                nextPlayer2.drawCard(deck.draw());
                nextPlayer2.drawCard(deck.draw());
                Suit newSuit2 = getNewSuit();
                card.setSuit(newSuit2);
                break;
            default:
                break;
        }
    }

    private Suit getNewSuit() {
        System.out.println("Choose a new color: ");
        for (int i = 0; i < Suit.values().length; i++) {
            System.out.println((i + 1) + ". " + Suit.values()[i]);
        }
        Scanner scanner = new Scanner(System.in);
        int suitIndex = scanner.nextInt() - 1;
        return Suit.values()[suitIndex];
    }

    private int getNextPlayerIndex() {
        int nextPlayerIndex = currentPlayerIndex + (reverse ? -1 : 1);
        if (nextPlayerIndex < 0) {
            nextPlayerIndex = players.size() - 1;
        } else if (nextPlayerIndex >= players.size()) {
            nextPlayerIndex = 0;
        }
        return nextPlayerIndex;
    }

    private Player getNextPlayer() {
        int nextPlayerIndex = getNextPlayerIndex();
        return players.get(nextPlayerIndex);
    }
}

*/
/*
import java.util.ArrayList;
import java.util.List;

public class Game {
    *//*

 */
/*private final Deck deck;
    private final List<Player> players;
    private final List<Card> discardPile;
    private int currentPlayerIndex;
    private boolean clockwise;
    private boolean skip;

    public Game(List<String> playerNames) {
        this.deck = new Deck();
        this.players = new ArrayList<>();
        for (String playerName : playerNames) {
            this.players.add(new Player(playerName));
        }
        this.discardPile = new ArrayList<>();
        this.discardPile.add(deck.draw());
        this.currentPlayerIndex = 0;
        this.clockwise = true;
        this.skip = false;
    }

    public void startGame() {
        // deal initial hands to players
        for (Player player : players) {
            for (int i = 0; i < 5; i++) {
                player.addCardToHand(deck.draw());
            }
        }

        // start the game
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // print game status
            System.out.println("Current player: " + players.get(currentPlayerIndex).getName());
            System.out.println("Top card on discard pile: " + discardPile.get(discardPile.size()-1));

            // get player input
            Player currentPlayer = players.get(currentPlayerIndex);
            List<Card> playableCards = getPlayableCards(currentPlayer);
            if (playableCards.size() > 0) {
                System.out.println("Your hand: " + currentPlayer.getHand());
                System.out.println("Playable cards: " + playableCards);
                System.out.println("Enter the index of the card you want to play (or -1 to draw): ");
                int cardIndex = scanner.nextInt();
                if (cardIndex == -1) {
                    Card drawnCard = deck.draw();
                    System.out.println("You drew: " + drawnCard);
                    currentPlayer.addCardToHand(drawnCard);
                    continue;
                }
                else if (cardIndex < -1 || cardIndex >= currentPlayer.getHand().size()) {
                    System.out.println("Invalid input. Try again.");
                    continue;
                }
                else {
                    Card playedCard = currentPlayer.playCard(discardPile.get(cardIndex));
                    discardPile.add(playedCard);
                    handleActionCard(playedCard);
                    System.out.println("You played: " + playedCard);
                }
            } else {
                Card drawnCard = deck.draw();
                System.out.println("You drew: " + drawnCard);
                currentPlayer.addCardToHand(drawnCard);
            }

            // check if player has won
            if (currentPlayer.getHand().size() == 0) {
                System.out.println(currentPlayer.getName() + " wins!");
                break;
            }

            // update current player index
            if (skip) {
                skip = false;
            } else {
                currentPlayerIndex = getNextPlayerIndex(currentPlayerIndex);
            }
        }
    }

    private List<Card> getPlayableCards(Player player) {
        List<Card> playableCards = new ArrayList<>();
        Card topCard = discardPile.get(discardPile.size()-1);
        for (Card card : player.getHand()) {
            if (card.getRank() == topCard.getRank() || card.getSuit() == topCard.getSuit()) {
                playableCards.add(card);
            }
        }
        return playableCards;
    }

    private void handleActionCard(Card card) {
        switch (card.getRank()) {
            case ACE -> {
                skip = true;
                System.out.println("Next player is skipped!");
            }
            case KING -> {
                clockwise = !clockwise;
                System.out.println("Direction has been reversed!");
            }
            case QUEEN -> {
                Player nextPlayer = players.get(getNextPlayerIndex(currentPlayerIndex));
                nextPlayer.addCardToHand(deck.draw());
                nextPlayer.addCardToHand(deck.draw());
                System.out.println("Next player drew 2 cards!");
            }
            case JACK -> {
                Player nextNextPlayer = players.get(getNextPlayerIndex(getNextPlayerIndex(currentPlayerIndex)));
                nextNextPlayer.addCardToHand(deck.draw());
                nextNextPlayer.addCardToHand(deck.draw());
                nextNextPlayer.addCardToHand(deck.draw());
                nextNextPlayer.addCardToHand(deck.draw());
                System.out.println("Next player drew 4 cards!");
            }
            default -> {
            }
        }
    }
    private int getNextPlayerIndex(int currentPlayerIndex) {
        int nextPlayerIndex;
        if (clockwise) {
            nextPlayerIndex = (currentPlayerIndex + 1) % players.size();
        } else {
            nextPlayerIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
        }
        return nextPlayerIndex;
    }
}*//*
 */
/*

    private final Deck deck;
    private final List<Player> players;
    private final List<Card> discardPile;
    private int currentPlayerIndex;
    private boolean clockwise;
    private boolean skip;

    public Game(List<String> playerNames) {
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
                Card card = this.deck.draw();
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
                    this.drawCards(currentPlayerIndex + (this.clockwise ? 1 : -1), 2);
                } else if (playedCard.getRank() == Rank.JACK) {
                    this.drawCards(currentPlayerIndex + (this.clockwise ? 1 : -1), 4);
                }
            } else {
                Card drawnCard = this.deck.draw();
                if (drawnCard != null) {
                    currentPlayer.addCardToHand(drawnCard);
                } else {
                    // deck is empty, game ends in draw
                    System.out.println("Draw! No winner.");
                    return;
                }
            }

            if (currentPlayer.getHandSize() == 0) {
                // current player has won
                System.out.println(currentPlayer.getName() + " wins!");
                return;
            }

            if (this.skip) {
                // skip next player
                this.skip = false;
                this.currentPlayerIndex += this.clockwise ? 2 : -2;
            } else {
                this.currentPlayerIndex += this.clockwise ? 1 : -1;
            }
            if (this.currentPlayerIndex < 0) {
                this.currentPlayerIndex += this.players.size();
            }
            if (this.currentPlayerIndex >= this.players.size()) {
                this.currentPlayerIndex -= this.players.size();
            }
        }
    }

    private void drawCards(int playerIndex, int numCards) {
        for (int i = 0; i < numCards; i++) {
            Player player = this.players.get(playerIndex);
            Card card = this.deck.draw();
            if (card != null) {
                player.addCardToHand(card);
            }
        }
    }
}


*/

