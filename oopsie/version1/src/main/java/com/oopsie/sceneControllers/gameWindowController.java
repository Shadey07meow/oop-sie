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

            // Attach a method to each of the buttons that assigns it a value, then goes to the game loop and chekcs if its the correct answer
            newButton.setUserData(i);

            newButton.setOnAction(e -> 
                {

                    Button btnSource = (Button)e.getSource();
                    int index = (int)btnSource.getUserData();

                    boolean checkAnswer = gameplayLoop.checkIfCorrectAnswer(index);

                    if (checkAnswer)
                    {
                        // correctAnswerLogic
                        questionTextArea.setText("Correct answer Yippe");

                    } else
                    {
                        // Wrong answer logic
                    }

                }
            );

            terminalVBox.getChildren().add(newButton);
        }
    }

    public static void choiceButton(int val)
    {

    }

    @FXML 
    public void initialize()
    {
        instance = this;
        updateDisplay();
    }
     

}    

