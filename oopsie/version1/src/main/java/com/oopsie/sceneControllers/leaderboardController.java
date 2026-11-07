
package com.oopsie.sceneControllers;

import java.io.IOException;

import com.oopsie.Main;
import com.oopsie.animations.ButtonAnimations;
import com.oopsie.animations.FadeHandler;
import com.oopsie.audio.AudioManager;
import com.oopsie.logic.gameplayLoop;
import com.oopsie.logic.playerStats;
import com.oopsie.logic.runSaver;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

public class leaderboardController {

    private Image exitNormal;
    private Image exitPressed;

    @FXML
    private Button quitButton;

    @FXML
    private TableView<playerStats> leaderboardTable;

    @FXML
    private TableColumn<playerStats, String> nameColumn;

    @FXML
    private TableColumn<playerStats, Integer> scoreColumn;

    @FXML
    public void initialize() {

        AudioManager.playMusic(AudioManager.Music.Leaderboard);

        exitNormal = new Image(getClass().getResourceAsStream(
            "/com/oopsie/images/buttons/ExitButton.png"
        ));

        exitPressed = new Image(getClass().getResourceAsStream(
            "/com/oopsie/images/buttons/ExitButtonPressed.png"
        ));

        ButtonAnimations.addHoverImage(quitButton, exitNormal, exitPressed);
        ButtonAnimations.addHoverAnimation(quitButton);

        nameColumn.setCellValueFactory(
            new PropertyValueFactory<>("name")
        );

        scoreColumn.setCellValueFactory(
            new PropertyValueFactory<>("points")
        );

        loadLeaderboard();
    }

    private void loadLeaderboard() {

        try {

            ObservableList<playerStats> scores =
                FXCollections.observableArrayList(
                    runSaver.getAllScores()
                );

            leaderboardTable.setItems(scores);

        } catch (IOException e) {

            System.err.println("Failed to load leaderboard!");
            e.printStackTrace();

        }
    }

    @FXML
    private void switchToCredits() throws IOException {
        Main.changeRoot("creditScreen");
    }

    @FXML
    private void switchToHelp() throws IOException {
        Main.changeRoot("helpScreen");
    }

    @FXML
    private void switchToGame() throws IOException {
        gameplayLoop.setIsRunning(true);
        Main.changeRoot("nameScreen");
    }

    @FXML
    private void switchToPrimary() throws IOException {
        Main.changeRoot("mainMenu");
    }

    @FXML
    private void switchToTitle() throws IOException {
                FadeHandler.addFadeOut((0.3), () -> {
            try {
                Main.changeRoot("mainMenu");
                FadeHandler.addFadeIn(0.3);
                
            } catch (Exception e) {
                // TODO: handle exception
            }
        });
    }

    @FXML
    private void quitGame() {
        Platform.exit();
        System.exit(0);
    }
}
