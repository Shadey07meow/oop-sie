package com.oopsie;

import java.io.File;
import java.io.IOException;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import com.oopsie.logic.*;

/**
 * JavaFX App
 */
public class Main extends Application {

    private static Scene scene;
    private static double  maxWindowWidth = 1280;
    private static double  maxHeight = 720;



    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("mainMenu"), maxWindowWidth, maxHeight);
        
        stage.setScene(scene);
        stage.show();
        gameplayLoop.init();

        double ratio = (float)(maxWindowWidth) / (float)(maxHeight);

        double decorationValWidth = stage.getWidth() - scene.getWidth() ;
        double decorationValHeight = stage.getHeight() - scene.getHeight();

        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        
        stage.setMaxWidth(screen.getWidth());
        stage.setMaxHeight(screen.getHeight());

        stage.setResizable(false);
        stage.xProperty().addListener((obs, oldX, newX) -> {
            double x = Math.max(screen.getMinX(),
            Math.min(newX.doubleValue(), screen.getMaxX() - stage.getWidth()));
            if (x != newX.doubleValue()) stage.setX(x);
        });

        stage.yProperty().addListener((obs, oldY, newY) -> {
            double y = Math.max(screen.getMinY(),
            Math.min(newY.doubleValue(), screen.getMaxY() - stage.getHeight()));
            if (y != newY.doubleValue()) stage.setY(y);
        });

        // File file = new File("oopsie/version1/target/classes/com/oopsie/soundResources/effects/mouse_hover.wav");
        // System.out.println("URI for JavaFX: " + file.toURI() + " File Exists: " + file.exists());

    }



    public static void changeRoot(String fxml) throws IOException
    {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}