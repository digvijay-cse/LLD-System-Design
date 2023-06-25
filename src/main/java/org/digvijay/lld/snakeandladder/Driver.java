// Author: Digvijay Pandey (digvijay_cse@icloud.com)
// Date  : June 2023
package org.digvijay.lld.snakeandladder;

import java.util.ArrayList;
import java.util.List;

public class Driver {
  public static void main(String[] args) {
    // Add players.
    List<Player> playerNames = new ArrayList<>();
    playerNames.add(new Player("Player 1"));
    playerNames.add(new Player("Player 2"));
    playerNames.add(new Player("Player 3"));
    // Create a game instance and start the game.
    Game game = new Game(100, playerNames);
    game.startGame();

    while (true) {
      game.playTurn();
    }
  }
}

