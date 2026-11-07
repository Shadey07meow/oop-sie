
package com.oopsie.sceneControllers;

import java.io.IOException;

import com.oopsie.Main;
import com.oopsie.audio.AudioManager;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class settingsScreenController {

    @FXML
    private VBox settingsPanel;

    @FXML
    private HBox titleBar;

    @FXML
    private Slider musicSlider;

    @FXML
    private Slider soundSlider;

    private double mouseX;
    private double mouseY;

    @FXML
    public void initialize() {

        // Set slider values based on AudioManager
        musicSlider.setValue(AudioManager.getMusicVolume() * 100);
        soundSlider.setValue(AudioManager.getSoundVolume() * 100);

        // MUSIC SLIDER
        musicSlider.valueProperty().addListener(
            (observable, oldValue, newValue) -> {

                double volume = newValue.doubleValue() / 100.0;

                AudioManager.setMusicVolume(volume);
            }
        );

        // SOUND EFFECT SLIDER
        soundSlider.valueProperty().addListener(
            (observable, oldValue, newValue) -> {

                double volume = newValue.doubleValue() / 100.0;

                AudioManager.setSoundVolume(volume);
            }
        );

        // DRAGGING SETTINGS WINDOW
        titleBar.setOnMousePressed(event -> {

            mouseX = event.getSceneX();
            mouseY = event.getSceneY();

        });

        titleBar.setOnMouseDragged(event -> {

            double newX = settingsPanel.getTranslateX()
                    + event.getSceneX() - mouseX;

            double newY = settingsPanel.getTranslateY()
                    + event.getSceneY() - mouseY;

            mouseX = event.getSceneX();
            mouseY = event.getSceneY();

            double maxX = (settingsPanel.getScene().getWidth()
                    - settingsPanel.getWidth()) / 2;

            double maxY = (settingsPanel.getScene().getHeight()
                    - settingsPanel.getHeight()) / 2;

            newX = Math.max(-maxX, Math.min(newX, maxX));
            newY = Math.max(-maxY, Math.min(newY, maxY));

            settingsPanel.setTranslateX(newX);
            settingsPanel.setTranslateY(newY);

        });

    }

    @FXML
    private void closeSettings() {
        AudioManager.playEffect(AudioManager.Effect.SelectButton2);
        Main.closeSettings();

    }

    @FXML
    private void quitToMenu() throws IOException {

        AudioManager.playEffect(AudioManager.Effect.SelectButton2);
        Main.changeRoot("mainMenu");

        Main.closeSettings();

    }

}
