package com.oopsie.sceneControllers;

import java.io.IOException;
import java.io.InputStream;

import com.oopsie.Main;
import com.oopsie.animations.ButtonAnimations;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class nameScreenController {




    @FXML
    public void initialize()
    {

    }

    @FXML
    private void switchToChooseSupervisor() throws IOException {
        Main.changeRoot("supervisorChoose");
    }
}