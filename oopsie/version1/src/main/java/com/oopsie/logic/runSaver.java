
package com.oopsie.logic;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class runSaver {

    private static final Path appLocation = Paths.get(
        System.getenv("APPDATA"), "OOPsie", "scores.txt"
    );

    public static void resetStores() throws IOException {

        Files.createDirectories(appLocation.getParent());

        Files.writeString(
            appLocation,
            "",
            StandardCharsets.UTF_8,
            StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING
        );
    }

    public static void saveRun(playerStats stats) throws IOException {

        Files.createDirectories(appLocation.getParent());

        String name = stats.getName().replace(";;;", "");
        
        String content = name + ";;;" + stats.getPoints()
                + System.lineSeparator();

        Files.writeString(
            appLocation,
            content,
            StandardCharsets.UTF_8,
            StandardOpenOption.CREATE,
            StandardOpenOption.APPEND
        );
    }

    public static List<playerStats> getAllScores() throws IOException {

        List<playerStats> scores = new ArrayList<>();

        if (!Files.exists(appLocation)) {
            return scores;
        }

        List<String> lines = Files.readAllLines(
            appLocation,
            StandardCharsets.UTF_8
        );

        for (String line : lines) {

            String[] data = line.split(";;;", 2);

            if (data.length != 2) {
                continue;
            }

            try {

                playerStats stats = new playerStats();

                stats.setName(data[0]);
                stats.addPoints(Integer.parseInt(data[1]));

                scores.add(stats);

            } catch (NumberFormatException e) {

                System.out.println("Invalid score entry: " + line);

            }
        }

        scores.sort(
            Comparator.comparingInt(playerStats::getPoints).reversed()
        );

        return scores;
    }
}
