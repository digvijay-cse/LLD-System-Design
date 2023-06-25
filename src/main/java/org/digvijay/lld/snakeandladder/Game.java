// Author: Digvijay Pandey (digvijay_cse@icloud.com)
// Date  : June 2023
package org.digvijay.lld.snakeandladder;

import java.util.ArrayList;
import java.util.List;

class Game {
  private Board board;
  private List<Player> players;
  private List<Player> winners;
  private Player currentPlayer;

  public Game(int boardSize, List<Player> player) {
    board = new Board(boardSize);
    this.players = player;
    winners = new ArrayList<>();
  }

  public void startGame() {
    currentPlayer = players.get(0);
    System.out.println("Snake and Ladder game started!");
    System.out.println("Player order: ");
    for (Player player : players) {
      System.out.println(player.getName());
    }
    System.out.println("Let's  play!!!");
  }

  public void playTurn() {
    int diceValue = currentPlayer.rollDice();
    System.out.println(currentPlayer.getName() + " rolled a " + diceValue);
    System.out.println(currentPlayer.getName() + " current position: " + currentPlayer.getPosition());
    board.movePlayer(currentPlayer, diceValue);
    System.out.println(currentPlayer.getName() + " moved to position: " + currentPlayer.getPosition());

    if (currentPlayer.getPosition() == board.getSize() - 1) {
      System.out.println(currentPlayer.getName() + " has reached the end.");

      winners.add(currentPlayer); // Add the player to the winners list
      players.remove(currentPlayer); // Remove the player from the players list

      // Check if all players except one have reached the End cell
      if (players.size() == 1) {
        System.out.println("!!!Game over!!!");

        Player lastPlayer = players.get(0);
        winners.add(lastPlayer); // Add the last remaining player to the winners list

        // Display rankings
        System.out.println("Here is the match rankings:");
        for (int i = 0; i < winners.size(); i++) {
          Player rankedPlayer = winners.get(i);
          System.out.println((i + 1) + ". " + rankedPlayer.getName());
        }

        System.exit(0); // End the game
      }
    }

    currentPlayer = getNextPlayer();
  }

  private Player getNextPlayer() {
    int currentIndex = players.indexOf(currentPlayer);
    int nextIndex = (currentIndex + 1) % players.size();
    return players.get(nextIndex);
  }
}