package com.oopsie.animations;


import com.oopsie.Main;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class FadeHandler {
    
    // Class that handles all the fading logic

    public static void addFadeOut(double duration, Runnable onFinished)
    {
        Duration properDur = Duration.seconds(duration);
        Scene scene = Main.getStage().getScene();
        Parent root = scene.getRoot();

        Region fadeRegion = new Region();
        fadeRegion.setBackground(new Background(
                new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        fadeRegion.setOpacity(0);                       // start transparent

        // Size it to the scene (not the stage, which includes the title bar and borders)
        fadeRegion.prefWidthProperty().bind(scene.widthProperty());
        fadeRegion.prefHeightProperty().bind(scene.heightProperty());

        // Add to the root
        if (root instanceof Pane) {
            ((Pane) root).getChildren().add(fadeRegion);
        } else if (root instanceof Group) {
            ((Group) root).getChildren().add(fadeRegion);
        } else {
            System.out.println("Root type not supported: " + root.getClass());
            return;
        }

        fadeRegion.setMouseTransparent(true);

            // Animate opacity 0 -> 1
        FadeTransition ft = new FadeTransition(properDur, fadeRegion);
        ft.setInterpolator(Interpolator.EASE_OUT);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
        ft.setOnFinished(eh ->
            {
                Main.hideOverlayLayer(fadeRegion);
                if(onFinished != null)
                    onFinished.run();
            }
        );
    }

    public static void addFadeIn(double duration)
    {
        addFadeIn(duration, null);
    }

    public static void addFadeIn(double duration, Runnable onFinished)
    {
        Duration properDur = Duration.seconds(duration);
        Scene scene = Main.getStage().getScene();
        Parent root = scene.getRoot();

        Region fadeRegion = new Region();
        fadeRegion.setBackground(new Background(
                new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        fadeRegion.setOpacity(1);                       // start transparent

        // Size it to the scene (not the stage, which includes the title bar and borders)
        fadeRegion.prefWidthProperty().bind(scene.widthProperty());
        fadeRegion.prefHeightProperty().bind(scene.heightProperty());
        fadeRegion.setMouseTransparent(true);

        // Add to the root
        if (root instanceof Pane) {
            ((Pane) root).getChildren().add(fadeRegion);
        } else if (root instanceof Group) {
            ((Group) root).getChildren().add(fadeRegion);
        } else {
            System.out.println("Root type not supported: " + root.getClass());
            return;
        }

            // Animate opacity 0 -> 1
        FadeTransition ft = new FadeTransition(properDur, fadeRegion);
        ft.setInterpolator(Interpolator.EASE_IN);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.play();
        ft.setOnFinished(eh ->
            {
                Main.hideOverlayLayer(fadeRegion);
                if(onFinished != null)
                    onFinished.run();
            }
        );
    }
}
