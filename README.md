# Infix to Postfix Calculator GUI Application

This Java application provides a graphical user interface (GUI) for performing mathematical calculations based on a given mathematical expression. The application uses a custom expression tokenizer and supports basic arithmetic operations.

## Features
- **Input Interface**: Allows users to input mathematical expressions through a text field.
- **Calculation Process Visualization**: Displays the step-by-step calculation process, indicating the current operation being processed.
- **Operation Stack and Numeration Stack Visualization**: Shows the content of the operation stack and numeration stack during the calculation.
- **Output Display**: Presents the final result of the mathematical expression after calculation.

## How to Use
1. Enter a mathematical expression in the provided text field.
2. Set the desired wait time (in milliseconds) for the visualization of each calculation step.
3. Click the "Calculate" button to start the calculation process.
4. View the step-by-step calculation process, operation stack, and numeration stack on the GUI.
5. Once the calculation is complete, the final result will be displayed in the output section.

## Code Structure
- **MyPanel Class**: Main class representing the GUI panel, handling user input, initiating the calculation process, and updating the display.
- **Tokenizer Class**: Responsible for tokenizing the input mathematical expression and providing utility methods for integer checking and stack operations.

## Notes
- The application is designed to visualize the calculation steps for educational purposes and may not cover all mathematical expressions.