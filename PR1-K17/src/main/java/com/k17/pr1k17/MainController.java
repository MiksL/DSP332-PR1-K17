package com.k17.pr1k17;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

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
    private Button OkButton;

    @FXML
    private Label totalPoints;

    @FXML
    private Label bankPoints;

    @FXML
    private TextField numberSum;

    @FXML
    private Label winnerLabel;

    @FXML
    private Button playAgain;

    private int sum = 0;
    private ToggleButton lastButton = null;


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
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            sliderValue.setText((int) slider.getValue() + " Numbers");
        });

        numberSum.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleTextFieldInput();
            }
        });

    }

    @FXML
    // When Ok button is pressed, string of random numbers is displayed
    private void handleOkButton() {
        numbers.getChildren().clear();

        int count = (int) slider.getValue();

        Random random = new Random();
        for (int i = 0; i < count; i++) {
            ToggleButton button = new ToggleButton(Integer.toString(random.nextInt(9)+1));
            button.setId(Integer.toString(i)); // Set the button's ID to its value
            button.setPadding(new Insets(5, 5, 5, 5));

            button.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    if (lastButton == null || Math.abs(Integer.parseInt(lastButton.getId()) - Integer.parseInt(button.getId())) == 1) {
                        sum += Integer.parseInt(button.getText());
                        numberSum.setText(numberSum.getText() + button.getText() + "+");
                        lastButton = button;
                    } else
                    {

                    }
                }
            });

            numbers.add(button, i, 0);
        }

        /*
        int count = (int) slider.getValue();
        StringBuilder randomNumbers = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            randomNumbers.append(random.nextInt(9)+1);
        }
        numbers.setText(String.valueOf(randomNumbers));
        */
    }

    @FXML
    // Once you press start button, you can't change any parameters anymore
    private void handleStartButtonAction() {
        algorithmChoice.setDisable(true);
        whoStarts.setDisable(true);
        slider.setDisable(true);
        OkButton.setDisable(true);

        // numberSum text field becomes visible
        numberSum.setVisible(true);
    }

    @FXML
    // Sum function
    private void handleTextFieldInput() {
        // Get the input from the text field
        String input = numberSum.getText();

        // Split the input string by '+' to extract individual numbers
        String[] numbersToSum = input.split("\\+");

        // Check if there's only one number entered
        if (numbersToSum.length < 2) {
            return;
        }

        int sum = 0;

        // Calculate the sum of the numbers
        for (String number : numbersToSum) {
            sum += Integer.parseInt(number.trim());
        }

        /*
        String originalString = numbers.getText();
        String concatenatedNumbers = input.replace("+", "");

        // Check if the entered numbers match the concatenated numbers
        if (originalString.contains(concatenatedNumbers)) {

            // If the sum is less than 7, increment total points by 1
            if (sum > 7) {
                int totalPointsValue = Integer.parseInt(totalPoints.getText());
                totalPoints.setText(String.valueOf(totalPointsValue + 1));

            } else if (sum < 7) {
                // If the sum is less than 7, deduct 1 point from total points
                int totalPointsValue = Integer.parseInt(totalPoints.getText());
                if (totalPointsValue > 0) {
                    totalPoints.setText(String.valueOf(totalPointsValue - 1));

                } else {
                    // Ensure total points cannot go negative
                    totalPoints.setText("0");
                }

            } else {
                // If the sum is equal to 7, add 1 point to bankPoints
                int bankPointsValue = Integer.parseInt(bankPoints.getText());
                bankPoints.setText(String.valueOf(bankPointsValue + 1));
            }

            // Update numbers string
            if (sum < 7) {
                originalString = originalString.replaceFirst(concatenatedNumbers, "3");
            } else if (sum == 7) {
                originalString = originalString.replaceFirst(concatenatedNumbers, "2");
            } else {
                originalString = originalString.replaceFirst(concatenatedNumbers, "1");
            }
            numbers.setText(originalString);
            checkWinner();
        }
        numberSum.clear();

         */
    }

    @FXML
    // Method to check for the winner
    private void checkWinner() {
        String numbersLeft = "10"; //numbers.getText();
        if (numbersLeft.length() == 1) {
            int bank = Integer.parseInt(bankPoints.getText());
            int total = Integer.parseInt(totalPoints.getText());

            String winner;
            if (bank % 2 == 0 && total % 2 == 0) {
                if (whoStarts.getValue().equals("Computer")) {
                    winner = "Computer wins!";
                } else {
                    winner = "You win!";
                }
            } else if (bank % 2 != 0 && total % 2 != 0) {
                if (whoStarts.getValue().equals("Computer")) {
                    winner = "You win!";
                } else {
                    winner = "Computer wins!";
                }
            } else {
                winner = "It's a draw!";
            }
            winnerLabel.setText(winner);
            playAgain.setVisible(true);
        }
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

        // Hide text field, play again button
        numberSum.setVisible(false);
        playAgain.setVisible(false);
    }



}