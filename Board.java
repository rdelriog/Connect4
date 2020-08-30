/*  Name: Martin Ricardo Del Rio Grageda
 *  PennKey: mrdrg
 *  Recitation: 201
 *
 *  Creates a board of Connect4 and performs different actions
 *  It is to be used by Connect
 */

public class Board {
  private Disk[][] disks;    //two dimensional array of disks
  //Constructor of the board
  public Board() {
    disks = new Disk[8][6];
  }
  /**
   * This function draws the board with the disks on it
   * It doesn't return anything
   */
  public void draw(int player) {
    //Draw the board as a grid
    for (int i = 0; i < disks[1].length; i++) {
      for (int j = 0; j < disks.length; j++) {
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.square(j + 0.5, i + 0.5 , 0.5);
      }
    }
    //Draw the disks on the array
    for (Disk[] d : disks) {
      for (Disk e : d) {
        if (e != null) {
          e.draw();
        }
      }
    }
    //Show the next turn's player
    switch (player) {
      case 0:
        PennDraw.setPenColor(PennDraw.RED);
        break;
      case 1:
        PennDraw.setPenColor(PennDraw.YELLOW);
        break;
      case 2:
        PennDraw.setPenColor(PennDraw.BLACK);
        break;
      default:
        throw new RuntimeException("Trying to write a null player");
    }
    PennDraw.setFontSize(30);
    PennDraw.text(3, 6.25, "It's");
    PennDraw.filledCircle(3.5, 6.25, 0.2);
    PennDraw.text(4, 6.25, "turn");
  }
  /**
   * This function creates a disk on the top of a column clicked if
   * the column isn't already full. Returns true if the disk was created
   * and false otherwise
   * @Param the player who clicked
   */
  public boolean getClick(int player) {
    int col = (int) PennDraw.mouseX();
    if (!isFull(disks[col])) {
      int row = getTopPosition(disks[col]);
      disks[col][row] = new Disk(player, col, row);
      return true;
    }
    return false;
  }
  /**
   * This function creates a disk on the top of a random column if
   * the column isn't already full. Returns true if the disk was created
   * and false otherwise
   * @Param the player in turn
   */
  public boolean automaticClick(int player) {
    int col = (int) (Math.random() * 7);
    if (!isFull(disks[col])) {
      int row = getTopPosition(disks[col]);
      disks[col][row] = new Disk(player, col, row);
      return true;
    }
    return false;
  }
  /**
   * This functions checks if the game has been won by any player
   * Checks if there are 4 in a row of the same player vertically,
   * horizontally and diagonally. Returns true if is over and false
   * otherwise
   * @Param the player in turn
   */
  public boolean isOver(int player) {
    if (checkHor(player) || checkVer(player) || checkDiag(player) || 
        checkDiag2(player)) {  
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * This function returns true if an array contains only 1's and 2's
   * meaning that it's full of players 1 and 2 disks and false otherwise
   * @Param the column to analize
   */
  private boolean isFull(Disk[] d) {
    for (Disk i : d) {
      if (i == null) {
        return false;
      }
    }
    return true;
  }
  /**
   * This function returns the next position available for a disk
   * @Param the column of disks
   */
  private int getTopPosition(Disk[] d) {
    for (int i = 0; i < d.length; i++) {
      if (d[i] == null) {
        return i;
      }
    }
    //Just to fulfil the return needed, we throw an exception
    throw new RuntimeException("This column was full"); 
  }
  /**
   * This function checks if there are 4 disks in a row Vertically
   * returns true if there are and false otherwise
   * @Param the player in turn
   */
  private boolean checkVer(int player) {
    int match = 0;
    for (Disk[] ds : disks) {
      for (Disk d : ds) {                   //Iterate over all the disks
        if (d != null) {                    //Avoid null disks
          //If it matches, increase the counter
          if (d.getPlayer() == player) {
            match++;
            if (match == 4) {
              return true;
            }
            //If the sequence is interrupted, restart
          } else {
            match = 0;
          }
        }
      }
      //Change of column
      match = 0;
    }
    return false;
  }
  /**
   * This function checks if there are 4 in a row Horizontally. Returns true
   * if there are and false otherwise
   * @Param the player in turn
   */
  private boolean checkHor(int player) {
    int match = 0;
    //Iterate over the array the other way
    for (int i = 0; i < disks[0].length; i++) {
      for (int j = 0; j < disks.length; j++) {
        if (disks[j][i] != null) {
          //If it matches, increase the counter
          if (disks[j][i].getPlayer() == player) {
            match++;
            if (match == 4) {
              return true;
            }
            //If the sequence is interrupted, restart
          } else {
            match = 0;
          }
        }
        else {
          match = 0;
        }
      }
      //Change of row
      match = 0;
    }
    return false;
  }
  /**
   * This function checks if there are 4 in a row Diagonally to the right.
   * Returns true if there are and false otherwise
   * @Param the player in turn
   */
  private boolean checkDiag(int player) {
    //We start by selecting the initial values of the coordinates
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 3; j++) {
        int k = i;
        int l = j;
        int match = 0;
        //Iterate over the combinations we care about
        while (k < 7 && l < 6) {
          if ((disks[k][l]) != null) {
            if (disks[k][l].getPlayer() == player) {
              match++;
              if (match == 4) {
                return true;
              }
            } else {
              match = 0;
            }
          } else {
            match = 0;
          }
          //Increase the coordinates
          k++;
          l++;
        }
      }
    }
    return false;
  }
  /**
   * This function checks if there are 4 in a row Diagonally to the left.
   * Returns true if there are and false otherwise
   * @Param the player in turn
   */
  private boolean checkDiag2(int player) {
    //We start by selecting the initial values of the coordinates
    for (int i = 6; i > 2; i--) {
      for (int j = 0; j < 3; j++) {
        int k = i;
        int l = j;
        int match = 0;
        //Iterate over the combinations we care about
        while (k >= 0 && l < 6) {
          if ((disks[k][l]) != null) {
            if (disks[k][l].getPlayer() == player) {
              match++;
              if (match == 4) {
                return true;
              }
            } else {
              match = 0;
            }
          } else {
            match = 0;
          }
          //change the coordinates
          k--;
          l++;
        }
      }
    }
    return false;
  }
}

