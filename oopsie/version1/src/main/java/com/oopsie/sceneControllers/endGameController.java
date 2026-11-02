package com.oopsie.sceneControllers;

import java.io.IOException;


import com.oopsie.Main;
import com.oopsie.logic.gameplayLoop;

import javafx.fxml.FXML;

public class endGameController {

    @FXML
    private void switchToMain() throws IOException {
        Main.changeRoot("mainMenu");
    }

        @FXML
    private void retryGame() throws IOException {
        gameplayLoop.setIsRunning(true);
        Main.changeRoot("nameScreen");
    }
}