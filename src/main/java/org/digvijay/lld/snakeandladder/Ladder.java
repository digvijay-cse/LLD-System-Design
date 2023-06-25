// Author: Digvijay Pandey (digvijay_cse@icloud.com)
// Date  : June 2023
package org.digvijay.lld.snakeandladder;

public class Ladder extends SpecialCell {
  public Ladder(int position, int destination) {
    super(position, CellType.NORMAL, destination);
  }
}
