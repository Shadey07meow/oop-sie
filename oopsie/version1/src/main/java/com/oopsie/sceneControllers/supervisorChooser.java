package com.oopsie.sceneControllers;


import java.io.IOException;
import java.io.InputStream;

import com.oopsie.Main;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class supervisorChooser {

    @FXML
    private void switchToGame() throws IOException {
        Main.changeRoot("gameWindow");
    }
} 