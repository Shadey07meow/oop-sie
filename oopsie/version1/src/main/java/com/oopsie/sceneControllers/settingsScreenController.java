
package com.oopsie.sceneControllers;

import java.io.IOException;

import com.oopsie.Main;

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

        // Make settings panel draggable
        titleBar.setOnMousePressed(event -> {

            mouseX = event.getSceneX()
                    - settingsPanel.getTranslateX();

            mouseY = event.getSceneY()
                    - settingsPanel.getTranslateY();

        });

        titleBar.setOnMouseDragged(event -> {

            settingsPanel.setTranslateX(
                    event.getSceneX() - mouseX);

            settingsPanel.setTranslateY(
                    event.getSceneY() - mouseY);

        });

    }

    @FXML
    private void closeSettings() {

        settingsPanel.getScene()
                .getRoot()
                .setVisible(false);

        settingsPanel.getScene()
                .getRoot()
                .setManaged(false);

    }

    @FXML
    private void quitToMenu() throws IOException {

        Main.changeRoot("mainMenu");

    }

}
