package com.k17.pr1k17;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.scene.input.KeyCode;
import java.util.Random;


public class MainController {
    @FXML
    private Label numbers;

    @FXML
    private ChoiceBox<String> algorithmChoice;

    @FXML
    private ChoiceBox<String> whoStarts;

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
    private void initialize() {
        // Set values for choice boxes
        algorithmChoice.setItems(FXCollections.observableArrayList("Minimax", "Alpha-Beta"));
        whoStarts.setItems(FXCollections.observableArrayList("Computer", "Human"));

        // Add a listener to the slider value property to update the label
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            sliderValue.setText("Slider Value: " + (int) slider.getValue());
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
        int count = (int) slider.getValue();
        StringBuilder randomNumbers = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            randomNumbers.append(random.nextInt(9)+1);
        }
        numbers.setText(String.valueOf(randomNumbers));
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
        }
        numberSum.clear();
    }




}