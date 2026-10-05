package com.oopsie;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class sceneManager {

    private static int maxWindowWidth = 1280, maxWindowHeight = 720;

    public static void init(Stage stage)
    {
        StackPane screenHolder = new StackPane();
        screenHolder.setMinSize(maxWindowWidth, maxWindowHeight);
        screenHolder.setPrefSize(maxWindowWidth, maxWindowHeight);
        screenHolder.setMaxSize(maxWindowWidth, maxWindowHeight);

        // stage.setResizable(false);

        // Will work on this later (resizing)
    }

}
