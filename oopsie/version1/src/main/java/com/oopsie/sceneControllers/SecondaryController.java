package com.oopsie.sceneControllers;

import java.io.IOException;

import com.oopsie.Main;

import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        Main.changeRoot("primary");
    }
}