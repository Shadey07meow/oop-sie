package com.oopsie.sceneControllers;

import java.io.IOException;
import com.oopsie.Main;


import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        Main.changeRoot("secondary");
    }
}
