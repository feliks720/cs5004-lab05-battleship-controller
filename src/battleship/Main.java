package battleship;

import java.io.InputStreamReader;

/**
 * The Main class contains the main method that runs the Battleship game.
 */
public class Main {
  /**
   * The main method that runs the Battleship game.
   *
   * @param args the command-line arguments
   */
  public static void main(String[] args) {
    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;
    BattleshipView view = new BattleshipConsoleView(output);
    new BattleshipConsoleController(input, view).playGame(new BattleshipModelImpl());
  }
}
