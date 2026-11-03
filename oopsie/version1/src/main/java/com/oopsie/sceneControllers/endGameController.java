package com.oopsie.sceneControllers;

import java.io.IOException;


import com.oopsie.Main;
import com.oopsie.logic.gameplayLoop;

import javafx.fxml.FXML;

public class endGameController {

    @FXML
    private void switchToMainWin() throws IOException {
        Main.changeRoot("mainMenu");
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