package com.oopsie.sceneControllers;

import java.io.IOException;

import com.oopsie.Main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class helpScreenController {

    @FXML Button homeButton;

    @FXML
    public void initialize()
    {
        
    }

    
    @FXML
    private void switchToMain() throws IOException {
        Main.changeRoot("mainMenu");
    }
}