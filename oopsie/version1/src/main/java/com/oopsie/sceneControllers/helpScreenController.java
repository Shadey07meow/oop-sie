package com.oopsie.sceneControllers;

import java.io.IOException;
import java.io.InputStream;

import com.oopsie.Main;
import com.oopsie.animations.FadeHandler;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class helpScreenController {

    @FXML 
    private Button smallExitButton;

    private Image homeButtonNormal;
    private Image homeButtonPressed;

    @FXML
    public void initialize() {
        homeButtonNormal = loadImage("/com/oopsie/images/buttons/smallExitButton.png");
        homeButtonPressed = loadImage("/com/oopsie/images/buttons/smallExitButtonPressed.png");

        if (smallExitButton != null) {
            setupButtonHover(smallExitButton, homeButtonNormal, homeButtonPressed);
        }
    }

    private Image loadImage(String path) {
        InputStream stream = getClass().getResourceAsStream(path);
        if (stream == null) {
            System.err.println("Resource path not found: " + path);
            return null;
        }
        return new Image(stream);
    }

    private void setupButtonHover(Button button, Image normalImage, Image hoverImage) {
        addHoverAnimation(button);

        ImageView buttonImageView = (ImageView) button.getGraphic();

        if (buttonImageView != null && normalImage != null && hoverImage != null) {
            button.hoverProperty().addListener((obs, oldValue, isHovered) -> {
                if (isHovered) {
                    buttonImageView.setImage(hoverImage);
                } else {
                    buttonImageView.setImage(normalImage);
                }
            });
        }
    }

    private void addHoverAnimation(Button curBut) {
        if (curBut == null) return;

        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(150), curBut);
        scaleUp.setToX(1.1); 
        scaleUp.setToY(1.1); 

        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(150), curBut);
        scaleDown.setToX(1.0); 
        scaleDown.setToY(1.0); 

        curBut.hoverProperty().addListener((observable, oldValue, isHovered) -> {
            if (isHovered) {
                scaleDown.stop();
                scaleUp.playFromStart();
            } else {
                scaleUp.stop();
                scaleDown.playFromStart();
            }
        });
    }

     @FXML
    private void switchToMain() throws IOException {
                FadeHandler.addFadeOut((0.3), () -> {
            try {
                Main.changeRoot("mainMenu");
                FadeHandler.addFadeIn(0.3);
                
            } catch (Exception e) {
                // TODO: handle exception
            }
        });
    }
}