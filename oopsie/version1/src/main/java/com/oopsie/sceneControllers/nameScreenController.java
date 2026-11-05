package com.oopsie.sceneControllers;


import java.io.IOException;
import java.io.InputStream;

import com.oopsie.Main;
import com.oopsie.animations.ButtonAnimations;
import com.oopsie.logic.gameplayLoop;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class nameScreenController {


    @FXML TextField playerName;
    @FXML Button confirmButton;

    private int nameSize = 10;

    @FXML
    public void initialize()
    {
        confirmButton.setVisible(false);
        playerName.setOnKeyTyped(eh -> 
            {
                if(playerName.getText().equals(""))
                {
                    confirmButton.setVisible(false);
                } else
                {
                    confirmButton.setVisible(true);   
                }
                
                playerName.setTextFormatter(new TextFormatter<String>(change ->
                    change.getControlNewText().length() <= nameSize ? change : null
                ));
            }
        );
    }

    @FXML
    private void switchToChooseSupervisor() throws IOException {
        gameplayLoop.getCurPlayerStats().setName(playerName.getText().trim());
        Main.changeRoot("gameWindow");
    }
}