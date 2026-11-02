
package com.oopsie.sceneControllers;

import java.io.IOException;
import java.util.Map;
import java.util.WeakHashMap;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

public class settingsOverlayManager {

    private static final Map<Scene, StackPane> containers =
            new WeakHashMap<>();

    public static void attach(Scene scene) {

        // Prevent attaching the overlay more than once
        if (containers.containsKey(scene)) {
            return;
        }

        try {
            // Get the original screen
            Parent originalRoot = scene.getRoot();

            // Create a container for both screens
            StackPane container = new StackPane();

            container.getChildren().add(originalRoot);

            // Load the settings FXML
            FXMLLoader loader = new FXMLLoader(
                settingsOverlayManager.class.getResource(
                    "/com/oopsie/settingsScreen.fxml"
                )
            );

            Parent settings = loader.load();

            // Create the overlay background
            StackPane overlay = new StackPane();

            overlay.setStyle(
                "-fx-background-color: rgba(0, 0, 0, 0.55);"
            );

            overlay.setAlignment(Pos.CENTER);
            overlay.getChildren().add(settings);

            // Hide the overlay initially
            overlay.setVisible(false);
            overlay.setManaged(false);

            container.getChildren().add(overlay);

            // Replace the scene root
            scene.setRoot(container);

            containers.put(scene, overlay);

            // Listen for ESC anywhere in this scene
            scene.addEventFilter(
                KeyEvent.KEY_PRESSED,
                event -> {

                    if (event.getCode() == KeyCode.ESCAPE) {

                        overlay.setVisible(!overlay.isVisible());
                        overlay.setManaged(overlay.isVisible());

                        event.consume();
                    }
                }
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
