// Author: Digvijay Pandey (digvijay_cse@icloud.com)
// Date  : June 2023
package org.digvijay.lld.snakeandladder;

public class SpecialCell extends Cell {
  private int destination;

  public SpecialCell(int position, CellType type, int destination) {
    super(position, type);
    this.destination = destination;
  }

  public int getDestination() {
    return destination;
  }
}
