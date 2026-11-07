package com.oopsie.sceneControllers;

import java.io.IOException;

import com.oopsie.Main;
import com.oopsie.animations.ButtonAnimations;
import com.oopsie.animations.FadeHandler;
import com.oopsie.audio.AudioManager;
import com.oopsie.logic.gameplayLoop;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

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
    private Image leadButtonNormal;
    private Image leadButtonPressed;


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
    private Button leadButton;



    @FXML
    public void initialize()
    {
        AudioManager.playMusic(AudioManager.Music.MainMenuMusic);

        startNormal = new Image(getClass().getResourceAsStream("/com/oopsie/images/buttons/StartButton.png"));
        startPressed = new Image(getClass().getResourceAsStream("/com/oopsie/images/buttons/StartButtonPressed.png"));

        creditsNormal = new Image(getClass().getResourceAsStream("/com/oopsie/images/buttons/CreditsButton.png"));
        creditsPressed = new Image(getClass().getResourceAsStream("/com/oopsie/images/buttons/CreditsButtonPressed.png"));

        helpNormal = new Image(getClass().getResourceAsStream("/com/oopsie/images/buttons/HelpButton.png"));
        helpPressed = new Image(getClass().getResourceAsStream("/com/oopsie/images/buttons/HelpButtonPressed.png"));

        exitNormal = new Image(getClass().getResourceAsStream("/com/oopsie/images/buttons/ExitButton.png"));
        exitPressed = new Image(getClass().getResourceAsStream("/com/oopsie/images/buttons/ExitButtonPressed.png"));

        leadButtonNormal = new Image(getClass().getResourceAsStream("/com/oopsie/images/buttons/LeaderboardButton.png"));
        leadButtonPressed = new Image(getClass().getResourceAsStream("/com/oopsie/images/buttons/LeaderboardButtonPressed.png"));


        ButtonAnimations.addHoverImage(creditsButton, creditsNormal, creditsPressed);
        ButtonAnimations.addHoverImage(helpButton, helpNormal, helpPressed);
        ButtonAnimations.addHoverImage(quitButton, exitNormal, exitPressed);
        ButtonAnimations.addHoverImage(startButton, startNormal, startPressed);
        ButtonAnimations.addHoverImage(leadButton, leadButtonNormal, leadButtonPressed);

        ButtonAnimations.addHoverAnimation(startButton);
        ButtonAnimations.addHoverAnimation(helpButton);
        ButtonAnimations.addHoverAnimation(creditsButton);
        ButtonAnimations.addHoverAnimation(quitButton);
        ButtonAnimations.addHoverAnimation(leadButton);
        

    }


    @FXML
    private void switchToCredits() throws IOException {
        AudioManager.playEffect(AudioManager.Effect.SelectButton);
        FadeHandler.addFadeOut((0.3), () -> {
            try {
                Main.changeRoot("creditScreen");


                FadeHandler.addFadeIn(0.3);
                
            } catch (Exception e) {
                // TODO: handle exception
            }
        });
    }

    @FXML
    private void switchToHelp() throws IOException {
        AudioManager.playEffect(AudioManager.Effect.SelectButton);
        FadeHandler.addFadeOut((0.3), () -> {
            try {
                Main.changeRoot("helpScreen");
                FadeHandler.addFadeIn(0.3);
                
            } catch (Exception e) {
                // TODO: handle exception
            }
        });
    }

        @FXML
    private void switchToGame() throws IOException {
        gameplayLoop.setIsRunning(true);
        AudioManager.playEffect(AudioManager.Effect.SelectButton);
        FadeHandler.addFadeOut((0.3), () -> {
            try {
                Main.changeRoot("nameScreen");
                FadeHandler.addFadeIn(0.3);
                
            } catch (Exception e) {
                // TODO: handle exception
            }
        });
    }

    @FXML
    private void switchToPrimary() throws IOException {
        FadeHandler.addFadeIn(1);

        Main.changeRoot("mainMenu");
    }

       @FXML
    private void switchToTitle() throws IOException {
        Main.changeRoot("mainMenu");
    }

    @FXML
    private void switchToLeaderboard(ActionEvent event) throws IOException {
        AudioManager.playEffect(AudioManager.Effect.SelectButton);
        FadeHandler.addFadeOut((0.3), () -> {
            try {
                Main.changeRoot("leaderboard");
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
