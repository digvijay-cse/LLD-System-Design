// Author: Digvijay Pandey (digvijay_cse@icloud.com)
// Date  : June 2023
package org.digvijay.lld.snakeandladder;

public class Board {
  private int size;
  private Cell[] cells;

  public Board(int size) {
    this.size = size;
    cells = new Cell[size];
    initializeBoard();
  }

  private void initializeBoard() {
    // Create cells and set their type and destination
    cells[0] = new Cell(0, CellType.START);
    // Set other cells as normal by default
    for (int i = 1; i < size - 1; i++) {
      cells[i] = new Cell(i, CellType.NORMAL);
    }
    cells[size - 1] = new Cell(size - 1, CellType.END);
    // Set ladder cells
    cells[2] = new Ladder(2, 15);
    cells[7] = new Ladder(7, 21);
    // Set snake cells
    cells[11] = new Snake(11, 5);
    cells[19] = new Snake(19, 8);
  }

  public void movePlayer(Player player, int diceValue) {
    int currentPosition = player.getPosition();
    int newPosition = currentPosition + diceValue;
    if (newPosition >= size) {
      return;
    }
    Cell newCell = cells[newPosition];
    if (newCell instanceof SpecialCell specialCell) {
      player.setPosition(specialCell.getDestination());
    } else {
      player.setPosition(newPosition);
    }
  }

  public int getSize() {
    return size;
  }
}