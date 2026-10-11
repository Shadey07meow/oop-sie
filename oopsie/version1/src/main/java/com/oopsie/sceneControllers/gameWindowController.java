package com.oopsie.sceneControllers;

import java.io.IOException;

import com.oopsie.Main;
import com.oopsie.logic.gameplayLoop;
import com.oopsie.logic.gameQuestion;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;


public class gameWindowController {

    public static gameWindowController instance;

    

    private Button[] presentButtons = new Button[4];
    

    @FXML Label pointsLabel;
    @FXML VBox terminalVBox;
    @FXML TextArea questionTextArea;



    public void updateDisplay()
    {

        pointsLabel.setText("POINTS : " + gameplayLoop.getCurrentPoints());
        // If there are previous buttons in the scene, turn them into labels
        for (int i = 0; i < 4; i++)
        {
            if(presentButtons[i] != null)
            {
                Label newLabel = new Label(presentButtons[i].getText());
                int index = terminalVBox.getChildren().indexOf(presentButtons[i]);
                terminalVBox.getChildren().set(index, newLabel);
                presentButtons[i] = null;
                
            }
        }


        gameQuestion curQuestion = gameplayLoop.getSelectedQuestion();
        

        if (curQuestion == null)
        {
            // The question list has ended and is notw empty
            // This is where yuou finish the thing
            return;
        }
        questionTextArea.setText(curQuestion.getQuestionString());

        terminalVBox.getChildren().add(new Label("========================================="));
        terminalVBox.getChildren().add(new Label("Choices :"));
        for (int i = 0; i < 4; i++)
        {
            Button newButton = new Button("> " + curQuestion.getChoiceString(i));

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
                        gameplayLoop.wonLevel();
                        Label victoryLabel = new Label("> Correct Answer");
                        Label newLineLabel = new Label(" ");

                        terminalVBox.getChildren().add(victoryLabel);
                        terminalVBox.getChildren().add(newLineLabel);
                        updateDisplay();


                    } else
                    {
                        // Wrong answer logic
                    }

                }
            );

            presentButtons[i] = newButton;


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

