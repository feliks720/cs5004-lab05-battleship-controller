# CS 5004 Object-Oriented Design - Lab 5: Battleship Game Controller

## Overview

This repository contains the starter code for Lab 5 of the course CS 5004 Object-Oriented Design. In this lab, students will work on implementing the controller for the Battleship game. The model was implemented during Lab 4, and we have provided a View interface and a View implementation. The controller interface is also provided.

## Project Structure

- `src/battleship/Main.java`: Contains the main method to run the Battleship game.
- `src/battleship/BattleshipController.java`: Interface for the Battleship controller.
- `src/battleship/BattleshipConsoleController.java`: Console-based implementation of the Battleship controller.
- `src/battleship/BattleshipModelImpl.java`: Placeholder for the model implementation from Lab 4.
- `src/battleship/BattleshipView.java`: Interface for the Battleship view.
- `src/battleship/BattleshipConsoleView.java`: Console-based implementation of the Battleship view.

## Tasks

1. **Implement the Controller**:
    - Complete the `BattleshipConsoleController` class by implementing the `playGame` method.
    - The controller should handle user input and update the view based on the model's state.

2. **Testing the Controller**:
    - Mock the `BattleshipModel` and the `BattleshipView` to test the controller.
    - Write unit tests to ensure the controller interacts correctly with the model and view.

## Guidelines for Testing the Controller

1. **Setup**:
    - Create a test class for `BattleshipConsoleController`.

2. **Mocking the Model and View**:
    - Create a mock instance of `BattleshipModel` and `BattleshipView`.
    - Define the behavior of the mock model for different scenarios (e.g., game start, making guesses, game over).

3. **Writing Tests**:
    - Test the `playGame` method to ensure it correctly processes user input and updates the view.
    - Verify that the controller calls the appropriate methods on the model and view.
    - Use assertions to check the expected outcomes.

## Keep in mind

1. **End of Game Actions:**
    - At the end of the game, the controller should ask the view to display a "game over" message.
    - The view should also reveal the ship positions on the grid.

2. **Parsing User Input:**
    - Students are required to implement their own `parseGuess` method within the controller.
    - This method should convert the user's input string (e.g., `"A5"`) into integer `row` and `column` values before passing them to the model.
    - Basic input validation can be included in `parseGuess` to handle incorrect formats or out-of-range values.

3. **Optional Features:**
    - The `playAgain` method is not mandatory for this assignment.
    - Students may choose to add this feature if they wish to allow consecutive games without restarting the program.

**Example of Game Interaction:**
```
Welcome to Battleship!
Guesses Made: 0
Maximum Guesses Allowed: 10
Current Game Grid:
  0 1 2 3 4 5 6 7 8 9 
A _ _ _ _ _ _ _ _ _ _ 
B _ _ _ _ _ _ _ _ _ _ 
C _ _ _ _ _ _ _ _ _ _ 
D _ _ _ _ _ _ _ _ _ _ 
E _ _ _ _ _ _ _ _ _ _ 
F _ _ _ _ _ _ _ _ _ _ 
G _ _ _ _ _ _ _ _ _ _ 
H _ _ _ _ _ _ _ _ _ _ 
I _ _ _ _ _ _ _ _ _ _ 
J _ _ _ _ _ _ _ _ _ _ 
Enter your guess (row and column, e.g., A5): J3
It's a MISS!
Guesses Made: 1
Maximum Guesses Allowed: 10
Current Game Grid:
  0 1 2 3 4 5 6 7 8 9 
A _ _ _ _ _ _ _ _ _ _ 
B _ _ _ _ _ _ _ _ _ _ 
C _ _ _ _ _ _ _ _ _ _ 
D _ _ _ _ _ _ _ _ _ _ 
E _ _ _ _ _ _ _ _ _ _ 
F _ _ _ _ _ _ _ _ _ _ 
G _ _ _ _ _ _ _ _ _ _ 
H _ _ _ _ _ _ _ _ _ _ 
I _ _ _ _ _ _ _ _ _ _ 
J _ _ _ M _ _ _ _ _ _ 
Enter your guess (row and column, e.g., A5): C8
It's a HIT!
Guesses Made: 2
Maximum Guesses Allowed: 10
Current Game Grid:
  0 1 2 3 4 5 6 7 8 9 
A _ _ _ _ _ _ _ _ _ _ 
B _ _ _ _ _ _ _ _ _ _ 
C _ _ _ _ _ _ _ _ H _ 
D _ _ _ _ _ _ _ _ _ _ 
E _ _ _ _ _ _ _ _ _ _ 
F _ _ _ _ _ _ _ _ _ _ 
G _ _ _ _ _ _ _ _ _ _ 
H _ _ _ _ _ _ _ _ _ _ 
I _ _ _ _ _ _ _ _ _ _ 
J _ _ _ M _ _ _ _ _ _ 
Enter your guess (row and column, e.g., A5): K4
Error: Invalid guess coordinates.
Guesses Made: 2
Maximum Guesses Allowed: 10
Current Game Grid:
  0 1 2 3 4 5 6 7 8 9 
A _ _ _ _ _ _ _ _ _ _ 
B _ _ _ _ _ _ _ _ _ _ 
C _ _ _ _ _ _ _ _ H _ 
D _ _ _ _ _ _ _ _ _ _ 
E _ _ _ _ _ _ _ _ _ _ 
F _ _ _ _ _ _ _ _ _ _ 
G _ _ _ _ _ _ _ _ _ _ 
H _ _ _ _ _ _ _ _ _ _ 
I _ _ _ _ _ _ _ _ _ _ 
J _ _ _ M _ _ _ _ _ _ 
Enter your guess (row and column, e.g., A5): C8
Error: Cell has already been guessed.
Guesses Made: 2
Maximum Guesses Allowed: 10
Current Game Grid:
  0 1 2 3 4 5 6 7 8 9 
A _ _ _ _ _ _ _ _ _ _ 
B _ _ _ _ _ _ _ _ _ _ 
C _ _ _ _ _ _ _ _ H _ 
D _ _ _ _ _ _ _ _ _ _ 
E _ _ _ _ _ _ _ _ _ _ 
F _ _ _ _ _ _ _ _ _ _ 
G _ _ _ _ _ _ _ _ _ _ 
H _ _ _ _ _ _ _ _ _ _ 
I _ _ _ _ _ _ _ _ _ _ 
J _ _ _ M _ _ _ _ _ _ 
Enter your guess (row and column, e.g., A5): 
```
