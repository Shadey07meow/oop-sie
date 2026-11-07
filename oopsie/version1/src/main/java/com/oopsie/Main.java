
package com.oopsie;

import java.io.IOException;

import com.oopsie.animations.FadeHandler;
import com.oopsie.audio.AudioManager;
import com.oopsie.logic.gameplayLoop;
import com.oopsie.sceneControllers.gameWindowController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.util.Duration;

public class Main extends Application {

    private static Scene scene;

    private static StackPane rootContainer;
    private static StackPane screenLayer;
    private static StackPane settingsOverlay;

    private static double maxWindowWidth = 1280;
    private static double maxHeight = 720;
    private static Stage curStage = null;

    @Override
    public void start(Stage stage) throws IOException {

        // Container for the entire application
        rootContainer = new StackPane();

        // Container for the current screen
        screenLayer = new StackPane();

        // Dev
        screenLayer.getChildren().add(loadFXML("splashScreen"));
        // screenLayer.getChildren().add(loadFXML("cutscene"));

        // Load settings FXML
        FXMLLoader loader = new FXMLLoader(
            Main.class.getResource("/com/oopsie/settingsScreen.fxml")
        );

        Parent settingsContent = loader.load();

        // Create settings overlay
        settingsOverlay = new StackPane();

        settingsOverlay.setAlignment(Pos.CENTER);

        settingsOverlay.setStyle(
            "-fx-background-color: rgba(0, 0, 0, 0.55);"
        );

        settingsOverlay.getChildren().add(settingsContent);

        // Hide settings initially
        settingsOverlay.setVisible(false);
        settingsOverlay.setManaged(false);

        // Add both layers to the root
        rootContainer.getChildren().addAll(
            screenLayer,
            settingsOverlay
        );

        // Create scene
        scene = new Scene(
            rootContainer,
            maxWindowWidth,
            maxHeight
        );

        scene.getStylesheets().add(
            Main.class.getResource("/com/oopsie/styles/main-theme.css").toExternalForm()
        );
        // Global ESC listener
        scene.addEventFilter(
            KeyEvent.KEY_PRESSED,
            event -> {

                if (event.getCode() == KeyCode.ESCAPE) {

                    toggleSettings();

                    event.consume();
                }
            }
        );

        stage.setScene(scene);
        stage.show();

        gameplayLoop.init();

        // Window settings
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();

        stage.setMaxWidth(screen.getWidth());
        stage.setMaxHeight(screen.getHeight());

        stage.setResizable(false);


        stage.xProperty().addListener((obs, oldX, newX) -> {

            double x = Math.max(
                screen.getMinX(),
                Math.min(
                    newX.doubleValue(),
                    screen.getMaxX() - stage.getWidth()
                )
            );

            if (x != newX.doubleValue()) {
                stage.setX(x);
            }
        });

        stage.yProperty().addListener((obs, oldY, newY) -> {

            double y = Math.max(
                screen.getMinY(),
                Math.min(
                    newY.doubleValue(),
                    screen.getMaxY() - stage.getHeight()
                )
            );

            if (y != newY.doubleValue()) {
                stage.setY(y);
            }
        });


        stage.getIcons().add(
              new Image(Main.class.getResourceAsStream("/com/oopsie/images/screens/LogoScreen.png"))
        );

        stage.setTitle("OOP Terminal");
        curStage = stage;

        FadeHandler.addFadeIn(1);
    }

    // Change the current screen without removing the overlay
    public static void changeRoot(String fxml) 
    {
        try {
            screenLayer.getChildren().setAll(loadFXML(fxml));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    // Open or close settings
    public static void toggleSettings() {

        boolean isVisible = settingsOverlay.isVisible();

        settingsOverlay.setVisible(!isVisible);
        settingsOverlay.setManaged(!isVisible);
    }

    // Close settings specifically
    public static void closeSettings() {

        settingsOverlay.setVisible(false);
        settingsOverlay.setManaged(false);
    }

    // Check whether settings are open
    public static boolean isSettingsOpen() {

        return settingsOverlay.isVisible();
    }

    private static Parent loadFXML(String fxml) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(
            Main.class.getResource(fxml + ".fxml")
        );

        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Parent showOverlayLayer(String fxml) throws IOException
    {
        Parent node = loadFXML(fxml);
        rootContainer.getChildren().add(node);
        return node;
    }

    public static void hideOverlayLayer(Node node2) 
    {
        rootContainer.getChildren().remove(node2);
    }

    public static void  addNodeToRoot(Node node) throws IOException
    {
        rootContainer.getChildren().add(node);
    }
    
    public static Stage getStage()
    {
        return curStage;
    }
  
}
