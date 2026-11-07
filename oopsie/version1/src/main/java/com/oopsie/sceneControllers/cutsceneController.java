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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class cutsceneController {
    @FXML Pane oop;
    @FXML Label speakerLabel;
    @FXML StackPane rootNode;
    @FXML TextArea dialoguePane;

    int currentDialogue = 0;

    @FXML 
    public void initialize()
    {
        oop.setOpacity(0);
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
                speakerLabel.setText("Narrator");
                dialoguePane.setText("CONGRATULATIONS ! You have been hired as a maintenance worker for O.O.P or Overcomplicated Operating Program.");
        
                break;
            case 1:
                speakerLabel.setText("Narrator");
                dialoguePane.setText("You see, we’ve had a bit of a mess up with our daily work. A little OOPSIE, if you will. Haha. You will be tasked with helping with the more… menial work.");
        
                break;
            case 2:
                speakerLabel.setText("You");
                dialoguePane.setText("Oh, that’s f—");
                break;
            case 3:
                oop.setOpacity(1);
                speakerLabel.setText("OOPSIE");
                dialoguePane.setText("HELLO, NEW RECRUIT !!");
                break;
            case 4:
                speakerLabel.setText("Narrator");
                dialoguePane.setText("And that would be OOP. Your new co-worker, if you will.");
        
                break;
            case 5:
                speakerLabel.setText("HR");
                dialoguePane.setText("Welcome to the company, new hire! Here’s your ID.");
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



