package com.oopsie.sceneControllers;


import javafx.event.EventHandler;

import java.io.IOException;
import java.io.InputStream;

import com.oopsie.Main;
import com.oopsie.animations.ButtonAnimations;
import com.oopsie.audio.AudioManager;
import com.oopsie.logic.gameplayLoop;
import com.oopsie.logic.supervisors.Supervisor;
import com.oopsie.logic.supervisors.SupervisorManager;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class supervisorChooser {

    @FXML  StackPane superRoot;

    @FXML Group  superSlot1;
    @FXML Group  superSlot2;
    @FXML Group  superSlot3;

    @FXML ImageView superSlotImg1;
    @FXML ImageView superSlotImg2;
    @FXML ImageView superSlotImg3;

    @FXML Button acceptButton;

    @FXML Label s1_name;
    @FXML Label s1_role;
    
    @FXML Label s2_name;
    @FXML Label s2_role;

    @FXML Label s3_name;
    @FXML Label s3_role;
    

    private final EventHandler<MouseEvent> blocker = MouseEvent::consume;
    Supervisor[] selectedSupervisors = new Supervisor[3];

    @FXML
    public void initialize()
    {
        acceptButton.setVisible(false);
        ButtonAnimations.addHoverAnimation(superSlot1);
        ButtonAnimations.addHoverAnimation(superSlot2);
        ButtonAnimations.addHoverAnimation(superSlot3);

        selectedSupervisors[0] = SupervisorManager.get().randSelectSupervisors()[0];
        
        
        selectedSupervisors[1] = SupervisorManager.get().randSelectSupervisors()[1];
        selectedSupervisors[2] = SupervisorManager.get().randSelectSupervisors()[2];
        
        superSlotImg1.setImage(selectedSupervisors[0].getImage());
        s1_name.setText("Name: " + selectedSupervisors[0].getName());
        s1_role.setText("Role: " + selectedSupervisors[0].getRole());

        superSlotImg2.setImage(selectedSupervisors[1].getImage());
        s2_name.setText("Name: " + selectedSupervisors[1].getName());
        s2_role.setText("Role: " +selectedSupervisors[1].getRole());
        
        superSlotImg3.setImage(selectedSupervisors[2].getImage());
        s3_name.setText("Name: " + selectedSupervisors[2].getName());
        s3_role.setText("Role: " + selectedSupervisors[2].getRole());
        
    }

    @FXML 
    private void setSelect1() {
        gameplayLoop.getCurPlayerStats().setSupervisor(selectedSupervisors[0]);
        

        setSelected(0);
        activateButton();

    }

    
    @FXML 
    private void setSelect2() {
        gameplayLoop.getCurPlayerStats().setSupervisor(selectedSupervisors[1]);
        setSelected(1);
        activateButton();
    }

    
    @FXML 
    private void setSelect3() {
        gameplayLoop.getCurPlayerStats().setSupervisor(selectedSupervisors[2]);
        setSelected(2);
        activateButton();
    }

    @FXML
    private void switchToGame() throws IOException {
        Platform.runLater(() -> gameWindowController.get().showSupervisorAnswer());
        Main.hideOverlayLayer(gameWindowController.get().getSuperVisorScreen());
    }


    private void activateButton()
    {
        acceptButton.setVisible(true);
        AudioManager.playEffect(AudioManager.Effect.Talk);

    }

    private void setSelected(int v)
    {
        Group[] groups = {superSlot1, superSlot2, superSlot3};

        for(int i = 0; i < 3; i++)
        {
            if(i == v)
            {
                ScaleTransition smoothScaler = new ScaleTransition(Duration.millis(200), groups[i]);
                smoothScaler.setToX(1.15);
                smoothScaler.setToY(1.15);
                groups[i].addEventFilter(MouseEvent.MOUSE_ENTERED, blocker);
                groups[i].addEventFilter(MouseEvent.MOUSE_EXITED, blocker);
                smoothScaler.play();
            } else
            {
                ScaleTransition smoothScaler = new ScaleTransition(Duration.millis(300), groups[i]);
                Group curGroup = groups[i]; 
                smoothScaler.setToX(1.0);
                smoothScaler.setToY(1.0);
                curGroup.removeEventFilter(MouseEvent.MOUSE_ENTERED, blocker);
                curGroup.removeEventFilter(MouseEvent.MOUSE_EXITED, blocker);
                
                smoothScaler.play();
                smoothScaler.setOnFinished(e ->
                    {
                        forceMouseReset(curGroup);
                    }
                );

            }
        }
    }

    private void forceMouseReset(Group targetGroup) {
        MouseEvent fakeEntryEvent = new MouseEvent(
            MouseEvent.MOUSE_EXITED, 
            0, 0, 0, 0, null, 0, 
            false, false, false, false, false, false, false, false, false, false, null
        );
        
        Event.fireEvent(targetGroup, fakeEntryEvent);
    }
} 