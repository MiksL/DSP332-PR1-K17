package com.k17.pr1k17;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

import java.util.LinkedList;
import java.util.Random;


public class MainController {
    @FXML
    private GridPane numbers;

    @FXML
    private ComboBox<String> algorithmChoice;

    @FXML
    private ComboBox<String> whoStarts;

    @FXML
    private Slider slider;

    @FXML
    private Label sliderValue;

    @FXML
    private Label numberSumPreview;

    @FXML
    private Button OkButton;

    @FXML
    private Label totalPoints;

    @FXML
    private Label bankPoints;

    @FXML
    private Label winnerLabel;

    @FXML
    private Button playAgain;

    private int sum = 0;

    // A Linked list that stores the game numbers using the GameNumber class
    private LinkedList<GameNumber> gameNumbers = new LinkedList<>();

    // Short that stores if less than 2 numbers are selected
    private short totalNumbersSelected = 0;

    private boolean playerStarts; // true if player starts -> player is maximizer, false if computer starts -> computer is maximizer


    @FXML
    private void initialize() {

        // Set values for choice boxes
        algorithmChoice.setItems(FXCollections.observableArrayList("Minimax", "Alpha-Beta"));
        algorithmChoice.setValue("Minimax");

        whoStarts.setItems(FXCollections.observableArrayList("Computer", "Human"));
        whoStarts.setValue("Computer");

        numbers.setMinSize(400,100);
        numbers.setAlignment(javafx.geometry.Pos.CENTER);
        numbers.setHgap(0);
        numbers.setStyle("-fx-font-size: 25px;");

        // Add a listener to the slider value property to update the label
        slider.valueProperty().addListener((observable, oldValue, newValue) ->
                sliderValue.setText((int) slider.getValue() + " Numbers"));
    }

    @FXML
    // When Ok button is pressed, string of random numbers is displayed
    private void handleOkButton() {
        numbers.getChildren().clear();

        int count = (int) slider.getValue();

        Random random = new Random();
        for (int i = 0; i < count; i++) {
            short currentlyGeneratedNumber = (short) (random.nextInt(9) + 1);

            gameNumbers.add(new GameNumber(currentlyGeneratedNumber));

            ToggleButton button = new ToggleButton(Integer.toString(currentlyGeneratedNumber));
            button.setId(Integer.toString(i)); // Set the button's ID to its value
            button.setPadding(new Insets(5, 5, 5, 5));
            button.getStyleClass().add("numberButton");

            button.selectedProperty().addListener((observable, wasSelected, isSelected) -> {
                // Get the object from the gameNumbers list using the ID of the button
                int index = Integer.parseInt(button.getId());
                GameNumber currentNumber = gameNumbers.get(index);

                // Check if the button is selected
                if (isSelected) {
                    // Check if any neighbouring number is valid and selected
                    boolean nextSelected = index < gameNumbers.size() - 1 && gameNumbers.get(index + 1).isSelected();
                    boolean prevSelected = index > 0 && gameNumbers.get(index - 1).isSelected();

                    // If no numbers or only one number with a matching neighbour is selected, select the current number
                    if(totalNumbersSelected == 0  || (totalNumbersSelected < 2 && (nextSelected || prevSelected)))
                    {
                        currentNumber.setSelected(true);
                        totalNumbersSelected++;
                    }
                    else // If no valid number is selected, deselect the current number
                    {
                        button.setSelected(false);
                    }
                } else if (currentNumber.isSelected())
                {
                    // If the current number isSelected(), unselect it and decrement totalNumbersSelected
                    currentNumber.setSelected(false);
                    totalNumbersSelected--;
                }

                // TODO: Optimize updateSumPreview() method and subsequent calls to it
                updateSumPreview();
            });

            // Adds the button to the grid pane
            numbers.add(button, i, 0);
        }
    }

    @FXML
    private void updateSumPreview()
    {
        sum = 0;
        for (GameNumber gameNumber : gameNumbers) {
            if (gameNumber.isSelected()) {
                sum += gameNumber.getValue();
            }
        }
        numberSumPreview.setText("Sum: " + sum);
    }

    @FXML
    // Once you press start button, you can't change any parameters anymore
    private void handleStartButtonAction() {
        algorithmChoice.setDisable(true);
        whoStarts.setDisable(true);
        playerStarts = whoStarts.getValue().equals("Human"); // This will be passed to know who is the maximizer
        slider.setDisable(true);
        OkButton.setDisable(true);

        // TODO: Implement game logic, algorithm calls, player-computer turns, etc.

        // First turn GameState set
        GameState gameState = new GameState(Short.parseShort(totalPoints.getText()), Short.parseShort(bankPoints.getText()), gameNumbers);

        // Game data that will be passed to the algorithms:
        // 1. Current game point tallies - totalPoints and bankPoints
        // 2. Current board state - gameNumbers LinkedList

    }

    @FXML
    // Method to check for the winner
    private void checkWinner() {
        // Get the numbers left
        int numbersLeft = numbers.getChildren().size();

        if (numbersLeft == 1) // If only one number is left, then points are counted and a winner is declared
        {
            short finalBankPoints = Short.parseShort(bankPoints.getText());
            short finalTotalPoints = Short.parseShort(totalPoints.getText());

            String winnerMessage = "";

            boolean isBankEven = finalBankPoints % 2 == 0;
            boolean isTotalEven = finalTotalPoints % 2 == 0;
            boolean isComputerStarting = whoStarts.getValue().equals("Computer");

            // Check who is the winner
            if(isBankEven && isTotalEven)
            {
                winnerMessage = isComputerStarting ? "Computer wins!" : "Human wins!";
            }
            else if(!isBankEven && !isTotalEven)
            {
                winnerMessage = isComputerStarting ? "Human wins!" : "Computer wins!";
            }
            else
            {
                winnerMessage = "It's a draw!";
            }

            winnerLabel.setText(winnerMessage);
            playAgain.setVisible(true);
        }
    }

    public boolean isPlayerStarting() {
        return playerStarts;
    }

    @FXML
        // Play again button
    void newGame() {
        // Reset choice boxes, slider, labels
        whoStarts.setValue("Computer");
        algorithmChoice.setValue("Minimax");
        slider.setValue(slider.getMin());
        totalPoints.setText("0");
        bankPoints.setText("0");
        numbers.getChildren().clear();
        winnerLabel.setText("");
        sliderValue.setText("");

        // Make disabled things active again
        algorithmChoice.setDisable(false);
        whoStarts.setDisable(false);
        slider.setDisable(false);
        OkButton.setDisable(false);

        // Hide play again button
        playAgain.setVisible(false);
    }



}