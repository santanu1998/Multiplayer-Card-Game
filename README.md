
# Multiplayer Card Game

### This is a multiplayer card game that supports multiple players (up to 4) and different types of cards (e.g. number cards, action cards, etc.). The game follows the following rules:

1. Each player starts with a hand of 5 cards.

2. The game starts with a deck of 52 cards (a standard deck of playing cards).

3. Players take turns playing cards from their hand, following a set of rules that define what cards can be played when.

4. A player can only play a card if it matches either the suit or the rank of the top card on the discard pile.

5. If a player cannot play a card, they must draw a card from the draw pile. If the draw pile is empty, the game ends in a draw and no player is declared a winner.

6. The game ends when one player runs out of cards who is declared the winner.








## Requirements

### Java, Go, or Node.js
## Usage

1. Clone the repository.

* git clone https://github.com/santanu1998/Multiplayer-Card-Game

2. Change directory to the cloned repository.

* cd multiplayer-card-game

3. Run the game.

* java -cp . com.multiplayercardgame.backend.Main

4. Follow the on-screen instructions to play the game.
## Game Rules

* Aces, Kings, Queens and Jacks are action cards. When one of these is played the following actions occur:

    * Ace (A): Skip the next player in turn

    * Kings (K): Reverse the sequence of who plays next

    * Queens (Q): +2

    * Jacks (J): +4

* Actions are not stackable i.e. if Q is played by player 1 then player 2 draws two cards and cannot play a Q from his hand on that turn even if available.

## Design

The game is designed using the following classes:

    * Card: Represents a playing card, with a rank and a suit.

    * Deck: Represents a deck of playing cards, with 52 cards.

    * Player: Represents a player of the game, with a name and a hand of cards.

    * Game: Represents the game itself, with a deck of cards, a discard pile, and a list of players.

## Running Tests

The tests for this project can be run using any standard testing framework for the language used. To run the tests for the Java version of this project, you can use JUnit.

1. Change directory to the cloned repository.

* cd multiplayer-card-game

2. Run the tests.

* java -cp .:./lib/junit-4.13.2.jar org.junit.runner.JUnitCore com.multiplayercardgame.backend.entities.PlayerTest com.multiplayercardgame.backend.entities.DeckTest

## Evaluation Criteria

#### The solution will be evaluated based on the following factors:

* Simple design: Does the code have a clear and simple design? Is it easy to understand and modify?

* Readability: Is the code well-organized and easy to read? Are the naming conventions clear and consistent?

* Modelling: Are the objects and classes used in the code well-designed and appropriate for the problem at hand?

* Maintainability: Is the code easy to maintain and modify? Are there any potential areas of concern or technical debt?

* Testability: Are there comprehensive unit tests for the code? Does the code have a high degree of test coverage?
