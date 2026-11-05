package com.oopsie.sceneControllers;

import java.io.IOException;

import com.oopsie.Main;
import com.oopsie.logic.gameplayLoop;
import com.oopsie.logic.runSaver;

import javafx.fxml.FXML;

public class endGameController {

    @FXML
    private void switchToMainWin() throws IOException {
        Main.changeRoot("mainMenu");
        runSaver.saveRun(gameplayLoop.getCurPlayerStats());

    }

    
    @FXML
    private void switchToMainLoss() throws IOException {
        Main.changeRoot("mainMenu");
        runSaver.saveRun(gameplayLoop.getCurPlayerStats());
    }


        @FXML
    private void retryGameLoss() throws IOException {
        gameplayLoop.setIsRunning(true);
        runSaver.saveRun(gameplayLoop.getCurPlayerStats());
        Main.changeRoot("nameScreen");
    }
}