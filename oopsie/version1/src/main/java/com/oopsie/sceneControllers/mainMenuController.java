package com.oopsie.sceneControllers;

import java.io.IOException;

import com.oopsie.Main;
import com.oopsie.logic.*;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class mainMenuController {
    
    // images
    private Image startNormal;
    private Image startPressed;
    private Image creditsNormal;
    private Image creditsPressed;
    private Image helpNormal;
    private Image helpPressed;
    private Image exitNormal;
    private Image exitPressed;

    @FXML 
    private Button startButton;

    @FXML 
    private Button helpButton;

    
    @FXML 
    private Button creditsButton;

    
    @FXML 
    private Button quitButton;

    @FXML 
    private Button homeButton;



    @FXML
    public void initialize()
    {

        startNormal = new Image(getClass().getResourceAsStream("/com/oopsie/images/buttons/StartButton.png"));
        startPressed = new Image(getClass().getResourceAsStream("/com/oopsie/images/buttons/StartButtonPressed.png"));

        creditsNormal = new Image(getClass().getResourceAsStream("/com/oopsie/images/buttons/CreditsButton.png"));
        creditsPressed = new Image(getClass().getResourceAsStream("/com/oopsie/images/buttons/CreditsButtonPressed.png"));

        helpNormal = new Image(getClass().getResourceAsStream("/com/oopsie/images/buttons/HelpButton.png"));
        helpPressed = new Image(getClass().getResourceAsStream("/com/oopsie/images/buttons/HelpButtonPressed.png"));

        exitNormal = new Image(getClass().getResourceAsStream("/com/oopsie/images/buttons/ExitButton.png"));
        exitPressed = new Image(getClass().getResourceAsStream("/com/oopsie/images/buttons/ExitButtonPressed.png"));

        setupButtonHover(creditsButton, creditsNormal, creditsPressed);
        setupButtonHover(helpButton, helpNormal, helpPressed);
        setupButtonHover(quitButton, exitNormal, exitPressed);
        setupButtonHover(startButton, startNormal, startPressed);

        addHoverAnimation(startButton);
        addHoverAnimation(helpButton);
        addHoverAnimation(creditsButton);
        addHoverAnimation(quitButton);
        

    }

    private void setupButtonHover(Button button, Image normalImage, Image hoverImage) {
        addHoverAnimation(button);

        ImageView buttonImageView = (ImageView) button.getGraphic();

        if (buttonImageView != null) {
            button.hoverProperty().addListener((obs, oldValue, isHovered) -> {
                if (isHovered) {
                    buttonImageView.setImage(hoverImage);
                } else {
                    buttonImageView.setImage(normalImage);
                }
            });
        }
    }


    private void addHoverAnimation(Button curBut) {
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
        gameplayLoop.setIsRunning(true);

        Main.changeRoot("gameWindow");
    }

    @FXML
    private void switchToPrimary() throws IOException {
        Main.changeRoot("mainMenu");
    }

       @FXML
    private void switchToTitle() throws IOException {
        Main.changeRoot("mainMenu");
    }




    @FXML 
    private void quitGame()
    {
        Platform.exit();
        System.exit(0);
        
    }


}
