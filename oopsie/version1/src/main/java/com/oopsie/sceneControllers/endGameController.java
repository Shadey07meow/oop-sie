package com.oopsie.sceneControllers;

import java.io.IOException;

import com.oopsie.Main;
import com.oopsie.logic.gameplayLoop;
import com.oopsie.logic.runSaver;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class endGameController {

    @FXML 
    private TextArea lifelinesUsedLabel;

    @FXML 
    private Label percentTextWin;


    @FXML 
    private Label pointsLabel;

    @FXML
    private  void initialize()
    {
        try {
            runSaver.saveRun(gameplayLoop.getCurPlayerStats());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        percentTextWin.setText(gameplayLoop.getCurPlayerStats().getCompletionPercentage() + "% Completed");

        pointsLabel.setText("Points: " + gameplayLoop.getCurPlayerStats().getPoints());
        lifelinesUsedLabel.setText(gameplayLoop.getCurPlayerStats().getUsedLifelines());
    }

    @FXML
    private void switchToMainWin() throws IOException {
        Main.changeRoot("mainMenu");
        
        // AudioManager.playFX();

    }

    
    @FXML
    private void switchToMainLoss() throws IOException {
        Main.changeRoot("mainMenu");
    }


        @FXML
    private void retryGameLoss() throws IOException {
        gameplayLoop.setIsRunning(true);
        Main.changeRoot("nameScreen");
    }
}