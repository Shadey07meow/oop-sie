package com.oopsie.sceneControllers;

import java.io.IOException;


import com.oopsie.Main;

import javafx.fxml.FXML;

public class endGameController {

    @FXML
    private void switchToMain() throws IOException {
        Main.changeRoot("mainMenu");
    }
}