/*  Name: Martin Ricardo Del Rio Grageda
 *  PennKey: mrdrg
 *  Recitation: 201
 *
 *  Creates a a disc represnting one piece moved by the player
 *  to be used by board
 */
public class Disk {
  private int player, column, row;
  //Constructor of a disk
  public Disk(int player, int column, int row) {
    this.player = player;
    this.column = column;
    this.row = row;
  }
  
  public void draw() {
    switch (this.player) {
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
        throw new RuntimeException("Trying to draw a null disk");
    }
    PennDraw.filledCircle(this.column + .5, this.row + .5, .45);
  }
   /**
   * Getter function that returns the player
   */
  public int getPlayer() {
    return player;
  }
}