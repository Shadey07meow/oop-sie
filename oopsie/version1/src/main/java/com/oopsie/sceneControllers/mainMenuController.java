package com.oopsie.sceneControllers;

import java.io.IOException;
import com.oopsie.Main;


import javafx.scene.control.*;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.fxml.FXML;

public class mainMenuController {


    @FXML 
    private Button startButton;

    @FXML 
    private Button helpButton;

    
    @FXML 
    private Button creditsButton;

    
    @FXML 
    private Button quitButton;


    @FXML
    public void initialize()
    {
        addHoverAnimation(startButton);
        addHoverAnimation(helpButton);
        addHoverAnimation(creditsButton);
        addHoverAnimation(quitButton);
        

    }


    private void addHoverAnimation(Button curBut)
    {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(150), curBut);
        scaleUp.setToX(1.1); 
        scaleUp.setToY(1.1); 

        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(150), curBut);
        scaleDown.setToX(1.0); 
        scaleDown.setToY(1.0); 

        curBut.hoverProperty().addListener((observable, oldValue, isHovered) -> {
            if (isHovered) {
                scaleDown.stop();       // Stop shrinking if it was in the middle of it
                scaleUp.playFromStart(); // Start growing smoothly
            } else {
                scaleUp.stop();         // Stop growing if it was in the middle of it
                scaleDown.playFromStart(); // Start shrinking smoothly
            }
        });
    }

    @FXML
    private void switchToCredits() throws IOException {
        Main.changeRoot("creditScreen");
    }

    @FXML
    private void switchToHelp() throws IOException {
        Main.changeRoot("helpScreen");
    }

        @FXML
    private void switchToGame() throws IOException {
        Main.changeRoot("gameWindow");
    }




    @FXML 
    private void quitGame()
    {
        Platform.exit();
        System.exit(0);
    }


}
