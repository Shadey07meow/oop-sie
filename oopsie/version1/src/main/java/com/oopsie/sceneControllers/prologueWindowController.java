package com.oopsie.sceneControllers;




import java.io.IOException;
import java.io.InputStream;

import com.oopsie.Main;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.List;

public class prologueWindowController {

    @FXML 
    private ImageView prologueImage;

    @FXML
    public void initialize() {
        List<Image> images = List.of(
            new Image(getClass().getResource("/com/oopsie/images/prologue/Image1.png").toExternalForm()),
            new Image(getClass().getResource("/com/oopsie/images/prologue/Image2.png").toExternalForm()),
            new Image(getClass().getResource("/com/oopsie/images/prologue/Image3.png").toExternalForm()),
            new Image(getClass().getResource("/com/oopsie/images/prologue/Image4.png").toExternalForm())
        );
        
        int curImage = 0;
        prologueImage.setImage(images.get(0));
        
        SequentialTransition show = new SequentialTransition();
        
        for (int i = 0; i < images.size(); i++) {
            prologueImage.setOpacity(0);
            // Hold the current photo
            if (i < images.size() - 1) {
                final Image next = images.get(i + 1);

                FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), prologueImage);
                fadeOut.setFromValue(1);
                fadeOut.setToValue(0);

                PauseTransition swap = new PauseTransition(Duration.seconds(3));
                
                FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), prologueImage);
                fadeIn.setFromValue(0);
                fadeIn.setToValue(1);
                fadeOut.setOnFinished(e -> prologueImage.setImage(next));



                show.getChildren().addAll(fadeIn, swap, fadeOut);
            }
        }

        show.setOnFinished(e ->{
            try {
                Main.changeRoot("mainMenu");
            } catch (Exception z) {
                // TODO: handle exception
            }
        });

        show.play();

    }
}