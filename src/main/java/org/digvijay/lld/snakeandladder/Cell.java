// Author: Digvijay Pandey (digvijay_cse@icloud.com)
// Date  : June 2023
package org.digvijay.lld.snakeandladder;

public class Cell {
  private int position;
  private CellType type;

  public Cell(int position, CellType type) {
    this.position = position;
    this.type = type;
  }

  public int getPosition() {
    return position;
  }

  public CellType getType() {
    return type;
  }
}