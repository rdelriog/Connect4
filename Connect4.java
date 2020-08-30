/*  Name: Martin Ricardo Del Rio Grageda
 *  PennKey: mrdrg
 *  Recitation: 201
 *  This program creates a Connect4 game, it uses the Board and Disk class
 * to create the objects of the game
 * 
 * Execution: Connect4.java 
 */
public class Connect4 {
  //Global variables to identify the stage of the game
  private static boolean playing = true;
  private static boolean playingAI = true;
  //Variable that tells if AI goes first
  private static boolean aiFirst = false;
  
  public static void main(String[] args) {
    int turn = 0;                             //Counter of turns
    PennDraw.clear();
    PennDraw.setCanvasSize(700, 650);         //Adjust the size of the screen
    PennDraw.setXscale(0, 7);
    PennDraw.setYscale(0, 6.5);
    //Infinite loop
    while (true) {
      turn = 0;
      //Display the startup page
      startupPage();
      PennDraw.clear();
      //Create a new board and draw it
      Board b = new Board();
      b.draw(turn % 2);
      int player = 1;
      //Mode player vs player
      while (playing) {
        //Case of draw
        if (turn == 42) {
          gameOver(3);
        }
        //Detect column and draw disk
        if (PennDraw.mousePressed()) {
          if (b.getClick(turn % 2)) {
            if (b.isOver(turn % 2)) {
              b.draw((turn + 1) % 2);
              gameOver(turn % 2);
              break;
            }
            turn++;
          }
          while (PennDraw.mousePressed()) {
            //Protection against multiple clicks
          } 
        }
        b.draw(turn % 2);
      }
      // Mode single player
      while (playingAI) {
        //Case of a draw
        if (turn == 42) {
          gameOver(3);
        }
        //Case when AI goes first
        if (aiFirst) {
          //Click in a random column and draw disk (AI's turn)
          if (turn % 2 == 0) {
            if (b.automaticClick(2)) {
              if (b.isOver(2)) {
                b.draw(0);
                gameOver(2);
                break;
              }
              turn++;
            }
            b.draw(2);
            //Human's turn
          } 
          else {
            if (PennDraw.mousePressed()) {
              if (b.getClick(0)) {
                if (b.isOver(0)) {
                  b.draw(2);
                  gameOver(0);
                  break;
                }
                turn++;
              }
              while (PennDraw.mousePressed()) {
                //Protection against multiple clicks
              } 
            }
            b.draw(0);
          }
        } //Case when the human starts
        else {
          //AI's turn
          if (turn % 2 == 1) {
            if (b.automaticClick(2)) {
              if (b.isOver(2)) {
                b.draw(0);
                gameOver(2);
                break;
              }
              turn++;
            }
            b.draw(2);
          } //Human's turn 
          else {
            if (PennDraw.mousePressed()) {
              if (b.getClick(0)) {
                if (b.isOver(0)) {
                  b.draw(2);
                  gameOver(0);
                  break;
                }
                turn++;
              }
              while (PennDraw.mousePressed()) {
                //Protection against multiple clicks
              } 
            }
            b.draw(0);
          }
        }
      }
    }
  }
  
  /**
   * This function draws the startup page as two rectangles
   * showing two game modes: "player vs player" and "player vs AI"
   * It doesn't return anything
   */
  private static void startupPage() {
    PennDraw.clear();
    PennDraw.setFontSize(40);
    PennDraw.setPenColor(PennDraw.RED);
    PennDraw.filledRectangle(1.75, 3, 1.75, 3.5);
    PennDraw.setPenColor(PennDraw.WHITE);
    PennDraw.text(1.75, 3, "Player vs Player");
    PennDraw.setPenColor(PennDraw.GREEN);
    PennDraw.filledRectangle(5.25, 3, 1.75, 3.5);
    PennDraw.setPenColor(PennDraw.WHITE);
    PennDraw.text(5.25, 3, "Player vs AI");
    //Delay 100 ms
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    //Identify side of the screen and set variables accordingly
    while (!PennDraw.mousePressed()) {
      //do nothing
    }
    if (PennDraw.mousePressed()) {
      double x = PennDraw.mouseX();
      if (x < 3.5) {
        playing = true;
        playingAI = false;
      } else {
        playing = false;
        playingAI = true;
        if (Math.random() < 0.5) {
          aiFirst = true;
        }
      }
    }
    //Delay 300 ms to avoid accidental first click on game
    try {
      Thread.sleep(300);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  /**
   * This function draws the final screen showing the winner or a draw.
   * @Param the player that won:
   * 0 - player 1
   * 1 - player 2
   * 2 - AI
   * 3 - draw
   * It doesn't return anything
   */
  private static void gameOver(int player) {
    PennDraw.clear(100, 100, 100, 150);
    PennDraw.setPenColor(PennDraw.BLACK);
    PennDraw.setFontSize(80);
    if (player == 2) {
      PennDraw.text(3.5, 3, "AI wins!");
    }
    else if (player == 3) {
      PennDraw.text(3.5, 3, "It's a draw");
    }
    else {
      PennDraw.text(3.5, 3, "Player " + (player + 1) + " WINS!");
    }
    //Delay of 3 seconds while the result is shown
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

