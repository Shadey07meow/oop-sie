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
import com.oopsie.animations.ButtonAnimations;

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

        ButtonAnimations.addHoverImage(creditsButton, creditsNormal, creditsPressed);
        ButtonAnimations.addHoverImage(helpButton, helpNormal, helpPressed);
        ButtonAnimations.addHoverImage(quitButton, exitNormal, exitPressed);
        ButtonAnimations.addHoverImage(startButton, startNormal, startPressed);

        ButtonAnimations.addHoverAnimation(startButton);
        ButtonAnimations.addHoverAnimation(helpButton);
        ButtonAnimations.addHoverAnimation(creditsButton);
        ButtonAnimations.addHoverAnimation(quitButton);
        

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

        Main.changeRoot("nameScreen");
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
