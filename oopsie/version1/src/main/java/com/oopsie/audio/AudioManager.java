
package com.oopsie.audio;

import java.io.File;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioManager {

    private static MediaPlayer playingMusic;

    private static double musicVolume = 1.0;
    private static double soundVolume = 1.0;

    public enum Music {

        MainMenuMusic("music/LongingForAIR.mp3", 0),
        Leaderboard("music/Leaderboard.mp3", 0),
        GameOverMusic("null", 0),
        GameWinMusic("null", 0);

        private String path;
        private double volumeOffset;

        Music(String path, double volOffset) {
            this.path = path;
            this.volumeOffset = volOffset;
        }

        public String getPath() {
            return this.path;
        }

        public double getVolumeOffset() {
            return this.volumeOffset;
        }
    }

    public enum Effect {

        HoverButton("effects/mouse_hover.wav", 0),
        HoverButtonTerminal("effects/UI/buttonHover.mp3", 0),
        PressedButtonTerminal("effects/UI/buttonClickTerminal.mp3", 0),
        FailPressedButtonTerminal("effects/UI/failButtonClickTerminal.mp3", 0),
        CorrectAnswer("effects/playerFeedback/correctAnswer.mp3", 0),
        WrongAnswer("effects/playerFeedback/wrongAnswer.mp3", 0),
        Crash("effects/BSOD.mp3", 0),
        Win("effects/Startup.mp3", 0),
        Talk("effects/DialoguePop.mp3", 0),
        SelectButton("effects/confirmMenu.mp3", 0),
        SelectButton2("effects/UI/buttonClickLesser.mp3", 0),
        CorrectChoice("null", 0);

        private String path;
        private double volumeOffset;

        Effect(String path, double volOffset) {
            this.path = path;
            this.volumeOffset = volOffset;
        }

        public String getPath() {
            return this.path;
        }

        public double getVolumeOffset() {
            return this.volumeOffset;
        }
    }

    // PLAY MUSIC
    public static void playMusic(Music music) {

        stopMusic();

        File file = new File(relativeToAbsolutePath(music.getPath()));

        if (!file.exists()) {
            System.out.println("File: " + file.getPath() + " does not exist");
            return;
        }

        playingMusic = new MediaPlayer(
            new Media(file.toURI().toString())
        );

        playingMusic.setCycleCount(MediaPlayer.INDEFINITE);

        updateMusicVolume(music);

        playingMusic.setOnError(() ->
            System.err.println("Music error: " + playingMusic.getError())
        );

        playingMusic.play();
    }

    // UPDATE MUSIC VOLUME
    private static void updateMusicVolume(Music music) {

        if (playingMusic != null) {
            double volume = musicVolume + music.getVolumeOffset();

            volume = Math.max(0, Math.min(1, volume));

            playingMusic.setVolume(volume);
        }
    }

    // SET MUSIC VOLUME
    public static void setMusicVolume(double volume) {

        musicVolume = Math.max(0, Math.min(1, volume));

        if (playingMusic != null) {

            // Update the currently playing music
            playingMusic.setVolume(musicVolume);
        }
    }

    // GET MUSIC VOLUME
    public static double getMusicVolume() {
        return musicVolume;
    }

    // SET SOUND EFFECT VOLUME
    public static void setSoundVolume(double volume) {

        soundVolume = Math.max(0, Math.min(1, volume));
    }

    // GET SOUND EFFECT VOLUME
    public static double getSoundVolume() {
        return soundVolume;
    }

    // STOP MUSIC
    public static void stopMusic() {

        if (playingMusic != null) {

            playingMusic.stop();
            playingMusic.dispose();

            playingMusic = null;
        }
    }

    public static void pauseMusic() {

        if (playingMusic != null) {
            playingMusic.pause();
        }
    }

    public static void resumeMusic() {

        if (playingMusic != null) {
            playingMusic.play();
        }
    }

    // PLAY SOUND EFFECT
    public static void playEffect(Effect fx) {

        File file = new File(relativeToAbsolutePath(fx.getPath()));

        if (!file.exists()) {
            System.out.println("Effect file does not exist: " + file.getPath());
            return;
        }

        AudioClip clip = new AudioClip(file.toURI().toString());

        double volume = soundVolume + fx.getVolumeOffset();

        volume = Math.max(0, Math.min(1, volume));

        clip.setVolume(volume);
        clip.play();
    }

    // FILE PATH
    private static String relativeToAbsolutePath(String string) {

        return "oopsie/version1/target/classes/com/oopsie/soundResources/" + string;
    }
}
