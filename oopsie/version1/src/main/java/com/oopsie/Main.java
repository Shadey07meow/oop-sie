package com.oopsie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

        stage.setResizable(false);
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
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