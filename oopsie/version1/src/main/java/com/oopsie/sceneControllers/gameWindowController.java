package com.oopsie.sceneControllers;

import java.util.ArrayList;
import java.util.Random;

import com.oopsie.Main;
import com.oopsie.animations.ButtonAnimations;
import com.oopsie.audio.AudioManager;
import com.oopsie.logic.gameQuestion;
import com.oopsie.logic.gameplayLoop;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


public class gameWindowController {

    public static gameWindowController instance;

    

    private ArrayList<Button> presentButtons = new ArrayList<>();
    private gameQuestion selectedQuestion = null;
    private ActionEvent selectedQuesionActionEvent = null;
    



    private float scaleMultiplier = 1.2f;
    private Dimension2D oopSize = new Dimension2D(128 * scaleMultiplier, 128 * scaleMultiplier);
    private double oopShowDurationTime = 1f;

    private boolean hasLost = false;
    private static int corrrectAnswer;

    // Images of OOP
    public enum OOPState
    {
        Happy("OOP/OOP Happy.png"),
        QuestionMark("OOP/OOP Question Mark.png"),
        Sad("OOP/OOP Sad.png"),
        Loading("OOP/OOP Loading.gif"),
        A("OOP/A.png"),
        B("OOP/B.png"),
        C("OOP/C.png"),
        D("OOP/D.png");

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

    @FXML Pane dialoguePane;
    @FXML ImageView supervisorDisplay;
    @FXML TextArea supervisorLines;

    // OOP Region
    ImageView OOPImage;
    @FXML Button exceptionHandlerButton;
    @FXML Button debugButton;
    @FXML Button recompileButton;
    
    @FXML 
    public void initialize()
    {
        instance = this;

        OOPImage = new ImageView();
        OOPImage.setFitWidth(oopSize.getWidth());
        OOPImage.setFitHeight(oopSize.getHeight());
        dialoguePane.setVisible(false);
        OOPImage.setPreserveRatio(true);

        ButtonAnimations.addHoverSoundToTerminalButton(exceptionHandlerButton);
        ButtonAnimations.addHoverSoundToTerminalButton(debugButton);
        ButtonAnimations.addHoverSoundToTerminalButton(recompileButton);

        exceptionHandlerButton.pressedProperty().addListener((obs, wasPressed, isPressed)  ->
            {
                if (!isPressed)
                    return;
                if(gameplayLoop.getCurPlayerStats().getHasExceptionHandler() && !hasLost && selectedQuestion != null)
                {
                    AudioManager.playEffect(AudioManager.Effect.PressedButtonTerminal);
                } else
                {
                    AudioManager.playEffect(AudioManager.Effect.FailPressedButtonTerminal);
                }
            }
        );

        recompileButton.pressedProperty().addListener((obs, wasPressed, isPressed) ->
            {
                if (!isPressed)
                    return;
                    if(gameplayLoop.getCurPlayerStats().getHasRecompile() && hasLost)
                    {
                        AudioManager.playEffect(AudioManager.Effect.PressedButtonTerminal);
                    } else
                    {
                        AudioManager.playEffect(AudioManager.Effect.FailPressedButtonTerminal);
                    }
                
                    
            }
        );

        debugButton.pressedProperty().addListener((obs, wasPressed, isPressed) ->
            {
                if (!isPressed)
                    return;
                if(gameplayLoop.getCurPlayerStats().getHasDebug() && !hasLost && selectedQuestion != null)
                {
                    AudioManager.playEffect(AudioManager.Effect.PressedButtonTerminal);
                } else
                {
                    AudioManager.playEffect(AudioManager.Effect.FailPressedButtonTerminal);
                }
            }
        );
        

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
        selectedQuestion = null;
    
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
            ButtonAnimations.addButtonSoundsTerminal(newButton);
            newButton.setUserData(i);
            newButton.setPrefWidth(100000);
            newButton.setAlignment(Pos.CENTER_LEFT);

            newButton.setOnAction(e -> 
                {
                    selectedQuesionActionEvent = e;
                    showSelectedQuestion(selectedQuesionActionEvent);
                }

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
        gameQuestion newQuestion = selectedQuestions[(int)choiceBtnSource.getUserData()];
        selectedQuestion = newQuestion;
        questionTextArea.setText(newQuestion.getQuestionString());
        terminalVBox.getChildren().add(new Label("\n========================================="));
        terminalVBox.getChildren().add(new Label("Choices:"));
        corrrectAnswer = newQuestion.getCorrectAnswer();


        // Creates 4 new buttons
        for(int j= 0; j < 4; j++)
        {
            Button newButton2 = new Button("> " + newQuestion.getChoiceString(j));
            ButtonAnimations.addButtonSoundsTerminal(newButton2);

            // Attach a method to each of the buttons that assigns it a value, then goes to the game loop and chekcs if its the correct answer
            newButton2.setUserData(j);
            newButton2.setPrefWidth(100000);
            newButton2.setAlignment(Pos.CENTER_LEFT);


            newButton2.setOnAction(z -> checkAnswer(z, newQuestion));
            terminalVBox.getChildren().add(newButton2);
            terminalScrollbox.setVvalue(1.0);
        }

        terminalScrollbox.setVvalue(1.0);

    }

    public void checkAnswer(ActionEvent z, gameQuestion chosenQuestion)
    {
        Button btnSource = (Button)z.getSource();
        int index = (int)btnSource.getUserData();
        boolean checkAnswer = gameplayLoop.checkIfCorrectAnswer(index, chosenQuestion);
        if (checkAnswer)
        {
            AudioManager.playEffect(AudioManager.Effect.CorrectAnswer);
            choseRight();
        } else
        {
            AudioManager.playEffect(AudioManager.Effect.WrongAnswer);
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
                System.out.println("The scene for game win is invalid");
            }
    
        }
        

    }

    public void choseWrong() 
    {
        // Chose the wrong answer

        // Check if there are lifelines left
        System.out.println("You chose wrong");
        hasLost = true;



        if (!gameplayLoop.getCurPlayerStats().hasLifelines())
        {
            try {
                Main.changeRoot("gameLoss");
                
            } catch (Exception e) {
                System.out.println("The scene for game over is invalid");
            }
        } else
        {
            terminalButtonsToLabels();
            Random rand = new Random();
            boolean val = rand.nextBoolean();

            if(val)
                showOOP(OOPState.Sad);
            else
                showOOP(OOPState.QuestionMark);
            // showOOP(OOPState.QuestionMark);

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
        if(gameplayLoop.getCurPlayerStats().getHasExceptionHandler() && !hasLost && selectedQuestion != null)
        {
            // Should Display OOP on the Bottom Right Console
            showOOP(OOPState.Loading);

            PauseTransition pause = new PauseTransition(Duration.seconds(oopShowDurationTime));
            pause.setOnFinished(e -> {
                // showSelectedQuestion(selectedQuesionActionEvent);
                // OOP Will Say the answer in his little console
                
                switch(corrrectAnswer)
                {
                    case 0:
                        showOOP(OOPState.A);
                        break;
                    case 1:
                        showOOP(OOPState.B);
                        break;
                    case 2:
                        showOOP(OOPState.C);
                        break;
                        
                    case 3:
                        showOOP(OOPState.D);
                        break;
                }

                
            }
            );
            pause.play();
            gameplayLoop.getCurPlayerStats().setHasExceptionHandler(false);
            System.out.println(Boolean.toString(gameplayLoop.getCurPlayerStats().getHasExceptionHandler()));
            exceptionHandlerButton.setDisable(true);
            hasLost = false;

        }
    }
    
    @FXML 
    public void doRecompile()
    {

        
        if(gameplayLoop.getCurPlayerStats().getHasRecompile() && hasLost)
        {
            // Should Display OOP on the Bottom Right Console
            showOOP(OOPState.Loading);

            PauseTransition pause = new PauseTransition(Duration.seconds(oopShowDurationTime));
            pause.setOnFinished(e -> {
                showSelectedQuestion(selectedQuesionActionEvent);
                // OOP Will Say the answer in his little console
                toggleOOP(false);
            }
            );
            pause.play();
            gameplayLoop.getCurPlayerStats().setHasRecompile(false);
            recompileButton.setDisable(true);
            
            hasLost = false;
        }

        
    }

    @FXML 
    public void doDebug()
    {

        if(gameplayLoop.getCurPlayerStats().getHasDebug() && !hasLost && selectedQuestion != null)
        {
            // Show dialogue pane

   

            dialoguePane.setVisible(true);


            String resultString = "";

            // Integer to string converter
            switch (gameplayLoop.getCurPlayerStats().getSupervisor().getAnswer(selectedQuestion))
            {
                case 0:
                    resultString = "A";
                    showOOP(OOPState.A);
                    break;
                case 1:
                    resultString = "B";
                    showOOP(OOPState.B);
                    break;
                case 2:
                    resultString = "C";
                    showOOP(OOPState.C);
                    break;
                case 3:
                    resultString = "D";
                    showOOP(OOPState.D);
                    break;
                default:
                    break;
            }


            // Supervisor tells you the answer
            supervisorLines.setText(gameplayLoop.getCurPlayerStats().getSupervisor().getLine() + " " + resultString);

            gameplayLoop.getCurPlayerStats().setHasDebug(false);
            hasLost = false;
            debugButton.setDisable(true);

        }
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

    @FXML 
    public void toggleDialoguePane()
    {
        dialoguePane.setVisible(!dialoguePane.isVisible());
    }

    public void showOOP(OOPState state)
    {
        toggleOOP(true);
        Image curImage = new Image(getClass().getResourceAsStream("/com/oopsie/images/" + state.getLocation()));
        OOPImage.setImage(curImage);  
    }

}    


