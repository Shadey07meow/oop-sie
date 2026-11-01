package com.oopsie.sceneControllers;

import java.io.IOException;
import java.util.ArrayList;

import com.oopsie.Main;
import com.oopsie.animations.ButtonAnimations;
import com.oopsie.logic.gameplayLoop;
import com.oopsie.logic.gameQuestion;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Dimension2D;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;


public class gameWindowController {

    public static gameWindowController instance;

    

    private ArrayList<Button> presentButtons = new ArrayList<>();




    private Dimension2D oopSize = new Dimension2D(512, 512);
    private enum OOPState
    {
        Happy(""),
        QuestionMark(""),
        Sad("");

        private String imageLocation;
        OOPState(String imageLocation)
        {
            this.imageLocation = imageLocation;
        }

        public String getLocation()
        {
            return imageLocation;
        }
    }
    

    @FXML Label pointsLabel;
    @FXML Label diffLabel;
    @FXML Label questionNumber;
    @FXML VBox terminalVBox;
    @FXML TextArea questionTextArea;
    @FXML ScrollPane terminalScrollbox;
    @FXML Pane OOPHolder;


    public void updateDisplay()
    {
        pointsLabel.setText("POINTS : " + gameplayLoop.getCurrentPoints());
        // If there are previous buttons in the scene, turn them into labels
        terminalButtonsToLabels();
        
    
        // Get 4 choices
        
        gameplayLoop.selectQuestions();
        questionNumber.setText("Q"+gameplayLoop.getCurrentLevel());
        diffLabel.setText(gameplayLoop.getDifficultyString());
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
            newButton.setPrefWidth(100000);
            newButton.setAlignment(Pos.CENTER_LEFT);

            ButtonAnimations.addSoundToButton(newButton);
            newButton.setOnAction(e -> 
                showSelectedQuestion(e)
            );        
            // presentButtons.add(newButton);
            terminalVBox.getChildren().add(newButton);
            Platform.runLater(() -> terminalScrollbox.setVvalue(1.0));

        }

    }

    public void showSelectedQuestion(ActionEvent e)
    {
        // Selected the button
        gameQuestion[] selectedQuestions = gameplayLoop.getSelectedQuestions();

        
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
            ButtonAnimations.addSoundToButton(newButton2);

            // Attach a method to each of the buttons that assigns it a value, then goes to the game loop and chekcs if its the correct answer
            newButton2.setUserData(j);
            newButton2.setPrefWidth(100000);
            newButton2.setAlignment(Pos.CENTER_LEFT);


            newButton2.setOnAction(z -> checkAnswer(z, selectedQuestion));
            terminalVBox.getChildren().add(newButton2);
            terminalScrollbox.setVvalue(1.0);
        }
    }

    public void checkAnswer(ActionEvent z, gameQuestion selectedQuestion)
    {
        Button btnSource = (Button)z.getSource();
        int index = (int)btnSource.getUserData();
        boolean checkAnswer = gameplayLoop.checkIfCorrectAnswer(index, selectedQuestion);
        if (checkAnswer)
        {
            choseRight();
        } else
        {
            choseWrong();
        }

    }


    public void choseRight()
    {


        // Chose the correct answer
        questionTextArea.setText("Correct answer Yippe");
        gameplayLoop.wonLevel();
        Label victoryLabel = new Label("> Correct Answer");
        Label newLineLabel = new Label(" ");

        terminalVBox.getChildren().add(victoryLabel);
        terminalVBox.getChildren().add(newLineLabel);
        terminalScrollbox.setVvalue(1.0);

        updateDisplay();

        if(gameplayLoop.getCurrentLevel() > 40)
        {
            try {
                Main.changeRoot("gameWin");
                
            } catch (Exception e) {
                System.out.println("The scene for game over is invalid");
            }
    
        }
        

    }

    public void choseWrong() 
    {
        // Chose the wrong answer

        // Check if there are lifelines left
        System.out.println("You chose wrong");

        if (!gameplayLoop.getCurPlayerStats().hasLifelines())
        {
            try {
                Main.changeRoot("gameOver");
                
            } catch (Exception e) {
                System.out.println("The scene for game over is invalid");
            }
        } else
        {
            terminalButtonsToLabels();
            Label wrongAnswerLabel = new Label("=================\n> Wrong answer");
            Label chooseLabel = new Label("> Choose a Lifeline");
            terminalVBox.getChildren().add(wrongAnswerLabel);
            terminalVBox.getChildren().add(chooseLabel);
        }

    }

    public void terminalButtonsToLabels()
    {
        for (int i = 0; i < terminalVBox.getChildren().size(); i++)
        {
            if(terminalVBox.getChildren().get(i) instanceof  Button)
            {
                Button b = (Button)(terminalVBox.getChildren().get(i));
                Label l = new Label(" " + b.getText());

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

    @FXML 
    public void doExceptionHandler()
    {
        // Should Display OOP on the Bottom Right Console
        // OOP Will say some snarky remark
        // OOP Will Say the answer in his little console


        gameplayLoop.getCurPlayerStats().setHasExceptionHandler(false);
        System.out.println(Boolean.toString(gameplayLoop.getCurPlayerStats().getHasExceptionHandler()));
    }
    
    @FXML 
    public void doRecompile()
    {
        gameplayLoop.getCurPlayerStats().setHasRecompile(false);
    }

    @FXML 
    public void doDebug()
    {

        gameplayLoop.getCurPlayerStats().setHasDebug(false);
    }


    public void showOOP(int OOPState)
    {

    }

}    


