package com.oopsie.animations;

import javafx.util.Duration;

import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ButtonAnimations {

    public static void addHoverAnimation(Node curNode) {
        if (curNode == null) return;

        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(150), curNode);
        scaleUp.setToX(1.1); 
        scaleUp.setToY(1.1); 

        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(150), curNode);
        scaleDown.setToX(1.0); 
        scaleDown.setToY(1.0); 

        
        curNode.hoverProperty().addListener((observable, oldValue, isHovered) -> {
            if (isHovered) {
                scaleDown.stop();
                scaleUp.playFromStart();
            } else {
                scaleUp.stop();
                scaleDown.playFromStart();
            }
        });
    }

    
    public static void addHoverImage(Button button, Image normalImage, Image hoverImage) {
        ImageView buttonImageView = (ImageView) button.getGraphic();

        if (buttonImageView != null) {
            button.hoverProperty().addListener((obs, oldValue, isHovered) -> {
                if (isHovered) {
                    buttonImageView.setImage(hoverImage);
                } else {
                    buttonImageView.setImage(normalImage);
                }
            });
        }
    }

}
