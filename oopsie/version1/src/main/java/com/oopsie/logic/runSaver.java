package com.oopsie.logic;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.Path;

public class runSaver {

    static Path appLocation = Paths.get(System.getenv("APPDATA"), "OOPsie", "scores.txt");

  
    public static void resetStores() throws  IOException
    {
        Files.createDirectories(appLocation.getParent());

        String content = "";
        // Accept a stats
        // Write the tshit in the text file

        Files.writeString(
            appLocation, 
            content, 
            StandardCharsets.UTF_8, 
            StandardOpenOption.APPEND
        );
        

    }


    public static void saveRun(playerStats stats)  throws IOException
    {
        Files.createDirectories(appLocation.getParent());

        String content = stats.name + ";;;" + String.valueOf(stats.getPoints());
        // Accept a stats
        // Write the tshit in the text file
        try { 
            Files.writeString(
                appLocation, 
                content, 
                StandardCharsets.UTF_8, 
                StandardOpenOption.CREATE, StandardOpenOption.APPEND
            );
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }







}
