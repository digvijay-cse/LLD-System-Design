// Author: Digvijay Pandey (digvijay_cse@icloud.com)
// Date  : June 2023
package org.digvijay.lld.snakeandladder;

import java.util.Random;

public class Player {
  private String name;
  private int position;

  public Player(String name) {
    this.name = name;
    this.position = 0;
  }

  public String getName() {
    return name;
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public int rollDice() {
    Random random = new Random();
    // Returns a random number between 1 and 6
    return random.nextInt(6) + 1;
  }
}
