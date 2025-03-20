package battleship;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;


/**
 * JUnit4 tests for BattleshipConsoleController.
 */
public class BattleshipConsoleControllerTest {

  /**
   * A simple mock implementation of BattleshipModel.
   */
  private static class MockModel implements BattleshipModel {
    StringBuilder log = new StringBuilder();
    boolean gameOver = false;
    int guessCount = 0;

    @Override
    public void startGame() {
      log.append("startGame called\n");
    }

    @Override
    public boolean makeGuess(int row, int col) {
      log.append("makeGuess called with row: " + row + ", col: " + col + "\n");
      guessCount++;
      // For testing, assume that a guess at A5 (row 0, col 5) is a hit
      // that sinks the ship and ends the game.
      if (row == 0 && col == 5) {
        gameOver = true;
        return true;
      }
      return false;
    }

    @Override
    public boolean isGameOver() {
      return gameOver;
    }

    @Override
    public boolean areAllShipsSunk() {
      return gameOver; // For testing, if game is over assume all ships are sunk.
    }

    @Override
    public int getGuessCount() {
      return guessCount;
    }

    @Override
    public int getMaxGuesses() {
      return 10;
    }

    @Override
    public CellState[][] getCellGrid() {
      // Return a dummy 10x10 grid for testing.
      return new CellState[10][10];
    }

    @Override
    public ShipType[][] getShipGrid() {
      // Return a dummy 10x10 grid for testing.
      return new ShipType[10][10];
    }
  }

  /**
   * A simple mock implementation of BattleshipView.
   */
  private static class MockView implements BattleshipView {
    StringBuilder log = new StringBuilder();

    @Override
    public void displayWelcomeMessage() throws IOException {
      log.append("displayWelcomeMessage called\n");
    }

    @Override
    public void displayPromptMessage() throws IOException {
      log.append("displayPromptMessage called\n");
    }

    @Override
    public void displayCellGrid(CellState[][] cellGrid) throws IOException {
      log.append("displayCellGrid called\n");
    }

    @Override
    public void displayShipGrid(ShipType[][] shipGrid) throws IOException {
      log.append("displayShipGrid called\n");
    }

    @Override
    public void displayGuessCount(int currentGuesses) throws IOException {
      log.append("displayGuessCount called with " + currentGuesses + "\n");
    }

    @Override
    public void displayMaxGuesses(int maxGuesses) throws IOException {
      log.append("displayMaxGuesses called with " + maxGuesses + "\n");
    }

    @Override
    public void displayErrorMessage(String message) throws IOException {
      log.append("displayErrorMessage called with: " + message + "\n");
    }

    @Override
    public void displayGameOver(boolean win) throws IOException {
      log.append("displayGameOver called with win: " + win + "\n");
    }

    @Override
    public void displayHitMessage() throws IOException {
      log.append("displayHitMessage called\n");
    }

    @Override
    public void displayMissMessage() throws IOException {
      log.append("displayMissMessage called\n");
    }
  }

  /**
   * Tests that a valid hit (e.g., "A5") triggers the model and view appropriately.
   */
  @Test
  public void testPlayGameHit() {
    // Simulate input: a valid guess "A5" followed by end-of-input.
    StringReader input = new StringReader("A5\n");
    MockView view = new MockView();
    BattleshipConsoleController controller =
        new BattleshipConsoleController(input, view);
    MockModel model = new MockModel();

    controller.playGame(model);

    // Verify the model's makeGuess was called with row 0 and col 5.
    assertTrue(model.log.toString().contains("makeGuess called with row: 0, col: 5"));
    // Verify the view displayed a hit message.
    assertTrue(view.log.toString().contains("displayHitMessage called"));
    // Verify that at game end, the game over and ship grid display methods are called.
    assertTrue(view.log.toString().contains("displayGameOver called"));
    assertTrue(view.log.toString().contains("displayShipGrid called"));
  }

  /**
   * Tests that an invalid input (e.g., "K4") is handled by displaying an error message,
   * then a valid guess ("A0") is processed.
   */
  @Test
  public void testPlayGameInvalidInput() {
    // Simulate input: first an invalid guess "K4" (invalid row), then a valid guess "A0".
    StringReader input = new StringReader("K4\nA0\n");
    MockView view = new MockView();
    BattleshipConsoleController controller =
        new BattleshipConsoleController(input, view);
    // Use a mock model where A0 returns false (a miss).
    MockModel model = new MockModel() {
      @Override
      public boolean makeGuess(int row, int col) {
        log.append("makeGuess called with row: " + row + ", col: " + col + "\n");
        guessCount++;
        return false;
      }
    };

    controller.playGame(model);

    String viewLog = view.log.toString();
    // Verify that the error message for invalid coordinates is shown.
    assertTrue(viewLog.contains("displayErrorMessage called with: Invalid guess coordinates."));
    // Verify that after the invalid guess, a valid guess ("A0") is processed and results in a miss.
    assertTrue(viewLog.contains("displayMissMessage called"));
  }
}
