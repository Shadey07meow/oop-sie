package com.oopsie.sceneControllers;

import java.io.IOException;

import com.oopsie.Main;
import com.oopsie.logic.gameplayLoop;
import com.oopsie.logic.gameQuestion;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;


public class gameWindowController {

    public static gameWindowController instance;

    @FXML VBox terminalVBox;
    @FXML TextArea questionTextArea;



    public void updateDisplay()
    {
        gameQuestion curQuestion = gameplayLoop.getSelectedQuestion();
        questionTextArea.setText(curQuestion.getQuestionString());

        for (int i = 0; i < 4; i++)
        {
            Button newButton = new Button(curQuestion.getChoiceString(i));
            terminalVBox.getChildren().add(newButton);
        }
    }

    @FXML 
    public void initialize()
    {
        instance = this;
        updateDisplay();
    }
     

}    

