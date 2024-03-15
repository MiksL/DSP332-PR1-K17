package com.k17.pr1k17;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import javafx.collections.FXCollections;
import javafx.scene.control.Slider;
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
    private Button startButton;



    @FXML
    private void initialize() {
        // Set values for choice boxes
        algorithmChoice.setItems(FXCollections.observableArrayList("Minimax", "Alpha-Beta"));
        whoStarts.setItems(FXCollections.observableArrayList("Computer", "Human"));

        // Add a listener to the slider value property to update the label
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            sliderValue.setText("Slider Value: " + (int) slider.getValue());
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
    }


}