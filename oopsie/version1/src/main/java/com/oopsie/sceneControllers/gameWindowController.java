package com.oopsie.sceneControllers;

import java.io.IOException;
import java.util.ArrayList;

import com.oopsie.Main;
import com.oopsie.logic.gameplayLoop;
import com.oopsie.logic.gameQuestion;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;


public class gameWindowController {

    public static gameWindowController instance;

    

    private ArrayList<Button> presentButtons = new ArrayList<>();
    

    @FXML Label pointsLabel;
    @FXML VBox terminalVBox;
    @FXML TextArea questionTextArea;
    @FXML ScrollPane terminalScrollbox;


    public void updateDisplay()
    {

        pointsLabel.setText("POINTS : " + gameplayLoop.getCurrentPoints());
        // If there are previous buttons in the scene, turn them into labels
        terminalButtonsToLabels();



        // Get 4 choices
        
        gameplayLoop.selectQuestions();
        questionTextArea.setText("Choose Topic");

        terminalVBox.getChildren().add(new Label("\n========================================="));
        terminalVBox.getChildren().add(new Label("Choose a Topic:"));
        
        gameQuestion[] selectedQuestions = gameplayLoop.getSelectedQuestions();
        for (int i = 0; i < 4; i++)
        {
            // Turn pre-existing buttons into labels
            // Add buttons for the 

            Button newButton = new Button("> " + selectedQuestions[i].getTopic());
            newButton.setUserData(i);

            newButton.setOnAction(e -> 
                {
                    // Selected the button
                    
                    Button choiceBtnSource = (Button)e.getSource();
                    terminalButtonsToLabels();
                    gameQuestion selectedQuestion = selectedQuestions[(int)choiceBtnSource.getUserData()];
                    questionTextArea.setText(selectedQuestion.getQuestionString());
                    terminalVBox.getChildren().add(new Label("\n========================================="));
                    terminalVBox.getChildren().add(new Label("Choices:"));

                    // Creates 4 new buttons
                    for(int j= 0; j < 4; j++)
                    {
                        Button newButton2 = new Button("> " + selectedQuestion.getChoiceString(j));

                        // Attach a method to each of the buttons that assigns it a value, then goes to the game loop and chekcs if its the correct answer
                        newButton2.setUserData(j);

                        newButton2.setOnAction(z -> 
                            {
                                Button btnSource = (Button)z.getSource();
                                int index = (int)btnSource.getUserData();
                                boolean checkAnswer = gameplayLoop.checkIfCorrectAnswer(index, selectedQuestion);
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
                                    
                                    
                                    terminalScrollbox.setVvalue(1.0);
                                } else
                                {
                                    // Wrong answer logic
                                }
                                
                                terminalScrollbox.setVvalue(1.0);

                        });
                        terminalVBox.getChildren().add(newButton2);

                    }
                }
            );        
            // presentButtons.add(newButton);
            terminalVBox.getChildren().add(newButton);
            terminalScrollbox.setVvalue(1.0);
        }


        // Once user selects a choice, the other choices become labels
        // The program displays the question and 4 choices 



        // gameQuestion curQuestion = gameplayLoop.getSelectedQuestion();
        

        // if (curQuestion == null)
        // {
        //     // The question list has ended and is notw empty
        //     // This is where yuou finish the thing
        //     return;
        // }
        // questionTextArea.setText(curQuestion.getQuestionString());

        // terminalVBox.getChildren().add(new Label("========================================="));
        // terminalVBox.getChildren().add(new Label("Choices :"));
        // for (int i = 0; i < 4; i++)
        // {
        //     Button newButton = new Button("> " + curQuestion.getChoiceString(i));

        //     // Attach a method to each of the buttons that assigns it a value, then goes to the game loop and chekcs if its the correct answer
        //     newButton.setUserData(i);

        //     newButton.setOnAction(e -> 
        //         {
        //             Button btnSource = (Button)e.getSource();
        //             int index = (int)btnSource.getUserData();
        //             boolean checkAnswer = gameplayLoop.checkIfCorrectAnswer(index);
        //             if (checkAnswer)
        //             {
        //                 // correctAnswerLogic
        //                 questionTextArea.setText("Correct answer Yippe");
        //                 gameplayLoop.wonLevel();
        //                 Label victoryLabel = new Label("> Correct Answer");
        //                 Label newLineLabel = new Label(" ");

        //                 terminalVBox.getChildren().add(victoryLabel);
        //                 terminalVBox.getChildren().add(newLineLabel);
        //                 updateDisplay();


        //             } else
        //             {
        //                 // Wrong answer logic
        //             }

        //         }
        //     );

        //     presentButtons[i] = newButton;


        //     terminalVBox.getChildren().add(newButton);
        // }
    }

    public static void choiceButton(int val)
    {

    }

    public void terminalButtonsToLabels()
    {
        for (int i = 0; i < terminalVBox.getChildren().size(); i++)
        {
            if(terminalVBox.getChildren().get(i) instanceof  Button)
            {
                Button b = (Button)(terminalVBox.getChildren().get(i));
                Label l = new Label(b.getText());

                terminalVBox.getChildren().set(i, l);
            }
        }
    }

    @FXML 
    public void initialize()
    {
        instance = this;
        updateDisplay();
    }
     

}    

