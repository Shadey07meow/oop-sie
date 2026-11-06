package com.oopsie.sceneControllers;

import java.io.IOException;

import com.oopsie.Main;
import com.oopsie.animations.ButtonAnimations;
import com.oopsie.animations.FadeHandler;
import com.oopsie.logic.gameplayLoop;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class creditsController {
    
    // images
    private Image exitNormal;
    private Image exitPressed;
    
    @FXML 
    private Button quitButton;

    @FXML
    public void initialize()
    {


        exitNormal = new Image(getClass().getResourceAsStream("/com/oopsie/images/buttons/ExitButton.png"));
        exitPressed = new Image(getClass().getResourceAsStream("/com/oopsie/images/buttons/ExitButtonPressed.png"));

        ButtonAnimations.addHoverImage(quitButton, exitNormal, exitPressed);

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
                FadeHandler.addFadeOut((0.3), () -> {
            try {
                Main.changeRoot("mainMenu");
                FadeHandler.addFadeIn(0.3);
                
            } catch (Exception e) {
                // TODO: handle exception
            }
        });
    }


    

    @FXML 
    private void quitGame()
    {
        Platform.exit();
        System.exit(0);
        
    }


}
