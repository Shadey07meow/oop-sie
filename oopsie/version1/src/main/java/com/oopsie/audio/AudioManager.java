package com.oopsie.audio;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AudioManager {
    private static MediaPlayer playingMusic;
    private static float musicVolume = 1.0f;

    


    public enum Music{
        // Contains all the music files for the game starting from the classes path
        MainMenuMusic("music/LongingForAIR.mp3", 0),
        GameOverMusic("null", 0),
        GameWinMusic("null", 0);

        private String path;
        private double volumeOffset;

        Music(String path, double volOffset)
        {
            this.path = path;
            this.volumeOffset = volOffset;
        }

        public String getPath()
        {
            return this.path;
        }

        public double getVolumeOffset()
        {
            return this.volumeOffset;
        }
    }

    public enum Effect{
        // Contains all the music files for the game starting from the classes path
        HoverButton("effects/mouse_hover.wav", 0),
        SelectButton("null", 0),
        CorrectChoice("null", 0);

        private String path;
        private double volumeOffset;


        Effect(String path, double volOffset)
        {
            this.path = path;
            this.volumeOffset = volOffset;
        }

        public String getPath()
        {
            return this.path;
        }

        public double getVolumeOffset()
        {
            return this.volumeOffset;
        }
    }


    // Actual Sound Functions
    public static void playMusic(Music music)
    {
        stopMusic();

        File file = new File(relativeToAbsolutePath(music.getPath()));

        if(!file.exists())
        {
            System.out.println("File: " + file.getPath() + " does not exist");
            return;
        }

        playingMusic = new MediaPlayer(new Media(file.toURI().toString()));
        playingMusic.setCycleCount(MediaPlayer.INDEFINITE);       // loop forever
        playingMusic.setVolume(musicVolume + music.getVolumeOffset());
        playingMusic.setOnError(() -> System.err.println("Music error: " + playingMusic.getError()));
        playingMusic.play();
    }

    public static void stopMusic()
    {
        if (playingMusic != null) {
            playingMusic.stop();
            playingMusic.dispose();                               
            playingMusic= null;
        }
    }

    public static void pauseMusic()  { if (playingMusic != null) playingMusic.pause(); }
    public static void resumeMusic() { if (playingMusic != null) playingMusic.play(); }

    public static void playEffect(Effect fx)
    {
        File file = new File(relativeToAbsolutePath(fx.getPath()));
        // System.out.println("URI for JavaFX: " + file.toURI() + " File Exists: " + file.exists());

        if(!file.exists())
        {
            System.out.println("File:  does not exist");

            return;
        }

        AudioClip clip = new AudioClip(file.toURI().toString());

        clip.setVolume(musicVolume + fx.getVolumeOffset());
        clip.play();
    }

    private static String relativeToAbsolutePath(String string)
    {
        return "oopsie/version1/target/classes/com/oopsie/soundResources/" + string;

    }
}

