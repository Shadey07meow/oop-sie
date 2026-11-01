package com.oopsie.sceneControllers;

import javafx.scene.image.Image;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.oopsie.Main;
import com.oopsie.animations.ButtonAnimations;
import com.oopsie.logic.gameplayLoop;
import com.oopsie.logic.gameQuestion;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Dimension2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;


public class gameWindowController {

    public static gameWindowController instance;

    

    private ArrayList<Button> presentButtons = new ArrayList<>();



    private float scaleMultiplier = 1.2f;
    private Dimension2D oopSize = new Dimension2D(128 * scaleMultiplier, 128 * scaleMultiplier);
    private double oopShowDurationTime = 1f;

    // Images of OOP
    public enum OOPState
    {
        Happy("OOP/OOP Happy.png"),
        QuestionMark("OOP/OOP Question.png"),
        Sad("OOP/OOP Sad.png"),
        Loading("OOP/OOP Loading.gif");

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
    Node OOPHolderParent;
    Label CorrectAnswerLabel;

    // OOP Region
    ImageView OOPImage;
    
    @FXML 
    public void initialize()
    {
        instance = this;

        OOPImage = new ImageView();
        OOPImage.setFitWidth(oopSize.getWidth());
        OOPImage.setFitHeight(oopSize.getHeight());

        OOPImage.setPreserveRatio(true);

        

        // Set OOP Image Here

        OOPHolder.getChildren().add(OOPImage);
        OOPHolderParent = OOPHolder.getParent();
        
        toggleOOP(false);
        // showOOP(OOPState.Happy);


        updateDisplay();
    }

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

        terminalScrollbox.setVvalue(1.0);

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

        showOOP(OOPState.Happy);
        PauseTransition pause = new PauseTransition(Duration.seconds(oopShowDurationTime));
        pause.setOnFinished(y -> 
            {
                toggleOOP(false);            
                terminalVBox.getChildren().add(victoryLabel);
                terminalVBox.getChildren().add(newLineLabel);
                terminalScrollbox.setVvalue(1.0);

                updateDisplay();
            });
        pause.play();

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
            Random rand = new Random();
            if (rand.nextBoolean())
            {
                showOOP(OOPState.Sad);
            } else
            {
                showOOP(OOPState.QuestionMark);
            }
            PauseTransition pause = new PauseTransition(Duration.seconds(oopShowDurationTime));
            pause.setOnFinished(y -> 
                {
                    toggleOOP(false);            
                    terminalScrollbox.setVvalue(1.0);
                });
                
                
                Label wrongAnswerLabel = new Label("=================\n> Wrong answer");
                Label chooseLabel = new Label("> Choose a Lifeline");
                terminalVBox.getChildren().add(wrongAnswerLabel);
                terminalVBox.getChildren().add(chooseLabel);
                pause.play();
            }

        terminalScrollbox.setVvalue(1.0);


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
    public void doExceptionHandler()
    {
        // Should Display OOP on the Bottom Right Console
        showOOP(OOPState.Loading);

        // OOP Will Say the answer in his little console
        PauseTransition pause = new PauseTransition(new Duration(oopShowDurationTime));
        
        

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



    public void toggleOOP()
    {
        // Toggles OOP on or off
        toggleOOP(OOPHolder.isVisible());
    }

    public void toggleOOP(boolean value)
    {
        // Toggles OOP to the selected value
        OOPHolderParent.setVisible(value);
        System.out.println(OOPHolderParent.isVisible());
        OOPHolder.setMouseTransparent(!value);
        OOPHolderParent.setMouseTransparent(!value);
    }

    public void showOOP(OOPState state)
    {
        toggleOOP(true);
        Image curImage = new Image(getClass().getResourceAsStream("/com/oopsie/images/" + state.getLocation()));
        OOPImage.setImage(curImage);  
    }

}    


