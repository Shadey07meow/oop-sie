package com.oopsie.sceneControllers;


import com.oopsie.Main;
import com.oopsie.animations.FadeHandler;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class cutsceneController {
    @FXML ImageView oopsieImg;
    @FXML Label speakerLabel;
    @FXML StackPane rootNode;
    @FXML TextArea dialoguePane;

    int currentDialogue = 0;

    @FXML 
    public void initialize()
    {
        currentDialogue = 0;
        rootNode.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            doDialogue();
        });
        speakerLabel.setText("Narrator");
        dialoguePane.setEditable(false);
    }

    public void doDialogue()
    {
        currentDialogue += 1;
        System.out.println("Hello there");
        switch (currentDialogue) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            default:
                FadeHandler.addFadeOut((1), () -> {
                    try {
                        Main.changeRoot("nameScreen");
                        FadeHandler.addFadeIn(1);
                        
                    } catch (Exception e) {
                        // TODO: handle exception
                    }});

                 break;
                
        }
    }
}



