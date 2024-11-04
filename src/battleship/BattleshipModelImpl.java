package battleship;

// TODO
// Replace this file with your model implementation from lab 4
public class BattleshipModelImpl implements BattleshipModel {
  @Override
  public void startGame() {
  }

  @Override
  public boolean makeGuess(int row, int col) {
    return false;
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public boolean areAllShipsSunk() {
    return false;
  }

  @Override
  public int getGuessCount() {
    return 0;
  }

  @Override
  public int getMaxGuesses() {
    return 0;
  }

  @Override
  public CellState[][] getCellGrid() {
    return new CellState[0][];
  }

  @Override
  public ShipType[][] getShipGrid() {
    return new ShipType[0][];
  }
}