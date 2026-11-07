package com.oopsie.sceneControllers;

import javafx.util.Duration;

import javafx.fxml.FXML;

import com.oopsie.Main;
import com.oopsie.animations.FadeHandler;

import javafx.animation.PauseTransition;

public class splashScreenController {
 
    @FXML
    public void initialize()
    {
        PauseTransition pause = new PauseTransition(Duration.seconds(5));

        pause.setOnFinished(eh ->
            {
                FadeHandler.addFadeOut((1), () -> {
                try {
                    Main.changeRoot("mainMenu");
                    FadeHandler.addFadeIn(1);
                    
                } catch (Exception e) {
                    // TODO: handle exception
                }
            });
            }
        );

        pause.play();
    }
}
