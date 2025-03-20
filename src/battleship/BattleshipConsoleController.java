package battleship;

import java.io.IOException;
import java.util.Scanner;

/**
 * Console-based controller for the Battleship game.
 * Handles user input and updates the view based on the model's state.
 */
public class BattleshipConsoleController implements BattleshipController {

  private final Readable in;
  private final BattleshipView view;

  /**
   * Constructs a BattleshipConsoleController with the given input and view.
   *
   * @param in   the Readable source for user input
   * @param view the BattleshipView for displaying game state and messages
   * @throws IllegalArgumentException if either argument is null
   */
  public BattleshipConsoleController(Readable in, BattleshipView view) {
    if (in == null || view == null) {
      throw new IllegalArgumentException("Input and view cannot be null");
    }
    this.in = in;
    this.view = view;
  }

  /**
   * Plays a single game of Battleship. The controller repeatedly prompts the user for a guess,
   * validates and parses the input, updates the model, and then displays the updated game state.
   * When the game is over, the controller displays a game-over message and reveals the ship grid.
   *
   * @param model the BattleshipModel to play the game with
   * @throws IllegalArgumentException if the model is null
   */
  @Override
  public void playGame(BattleshipModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    Scanner scanner = new Scanner(this.in);

    try {
      model.startGame();
      view.displayWelcomeMessage();

      while (!model.isGameOver()) {
        // Display game status
        view.displayGuessCount(model.getGuessCount());
        view.displayMaxGuesses(model.getMaxGuesses());
        view.displayCellGrid(model.getCellGrid());
        view.displayPromptMessage();

        // Read user input
        if (!scanner.hasNextLine()) {
          break;
        }
        String inputLine = scanner.nextLine();

        // Parse the user's guess input
        int[] guessCoords = parseGuess(inputLine);
        if (guessCoords == null) {
          view.displayErrorMessage("Invalid guess coordinates.");
          continue;
        }

        // Attempt to make a guess on the model and update the view based on result
        try {
          boolean hit = model.makeGuess(guessCoords[0], guessCoords[1]);
          if (hit) {
            view.displayHitMessage();
          } else {
            view.displayMissMessage();
          }
        } catch (IllegalArgumentException | IllegalStateException e) {
          view.displayErrorMessage(e.getMessage());
        }
      }

      // Game over: determine if the player has won and display the final status
      boolean win = model.areAllShipsSunk();
      view.displayGameOver(win);
      view.displayShipGrid(model.getShipGrid());
    } catch (IOException e) {
      throw new RuntimeException("I/O error occurred", e);
    }
  }

  /**
   * Parses a guess from a string input. The expected format is a letter (A-J)
   * followed by a number (0-9), for example, "A5" or "J3". The method converts the letter to a
   * row index (0-based) and the number to a column index.
   *
   * @param guess the user's guess as a String
   * @return an int array of length 2, where index 0 is the row and index 1 is the column,
   *         or null if the input is invalid
   */
  private int[] parseGuess(String guess) {
    if (guess == null) {
      return null;
    }
    guess = guess.trim().toUpperCase();
    // Expect at least 2 characters (one letter and at least one digit)
    if (guess.length() < 2) {
      return null;
    }
    char rowChar = guess.charAt(0);
    // Valid rows are A-J (for a 10x10 grid)
    if (rowChar < 'A' || rowChar > 'J') {
      return null;
    }
    String colStr = guess.substring(1);
    int col;
    try {
      col = Integer.parseInt(colStr);
    } catch (NumberFormatException e) {
      return null;
    }
    // Valid columns are 0-9
    if (col < 0 || col > 9) {
      return null;
    }
    int row = rowChar - 'A';
    return new int[]{row, col};
  }
}
