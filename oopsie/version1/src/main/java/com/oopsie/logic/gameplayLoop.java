package com.oopsie.logic;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

import com.oopsie.audio.AudioManager;

public class gameplayLoop {
    private static boolean isRunning = false;


    private static playerStats curPlayer = null;

    private static int currentLevel = 0;
    private static int currentPoints = 0;
    private static int pointIncrease = 10;
    private static difficulty currentDifficulty;


    private static ArrayList<Integer> completedIndexes = new ArrayList<>();
    private static ArrayList<gameQuestion> choicesQuestions = new ArrayList<>();

    private static ArrayList<gameQuestion> easyQuestions = new ArrayList<>(); // MaintenanceTierQuestions
    private static ArrayList<gameQuestion> mediumQuestions = new ArrayList<>(); // Technician Tier Questions
    private static ArrayList<gameQuestion> hardQuestions = new ArrayList<>(); // System Engineer Tier Questions
    private static ArrayList<gameQuestion> difficultQuestions = new ArrayList<>(); // Admin Tier Questions

    private static gameQuestion[] selectedQuestions = new gameQuestion[4];

    private static boolean wonRun = false;

    public enum difficulty
    {
        EASY,
        MEDIUM,
        HARD,
        DIFFICULT,
        
    }
 
    public static void setIsRunning(boolean val)
    {
        isRunning = val;

        if(isRunning)
        {
            startGame();
        } else
        {
            endgame();
        }
    }


    public static boolean getIsRunning()
    {
        return isRunning;
    }

    private static void startGame()
    {
        currentPoints = 0;
        curPlayer = new playerStats();
        currentLevel = 1;
        
        checkDifficulty();

    }

    private static void endgame()
    {
        
        currentPoints = 0;
        currentLevel = -1;
    }

    public static void init()
    {
        curPlayer = new playerStats();

        readFile();
    }

    public static void wonLevel()
    {
        currentPoints += pointIncrease;
        curPlayer.addPoints(pointIncrease);
        currentLevel++;

        if(currentLevel > 40)
        {
            wonRun = true;
            AudioManager.playEffect(AudioManager.Effect.Win);

        }
        checkDifficulty();
    }

    public static void checkDifficulty()
    {
        if(currentLevel <= 10)
        {
            currentDifficulty = difficulty.EASY;
            return;
        } else if(currentLevel <= 20)
        {
            currentDifficulty = difficulty.MEDIUM;
            return;
        } else if(currentLevel <= 30)
        {
            currentDifficulty = difficulty.HARD;
            return;
        } else if(currentLevel <= 40)
        {
            currentDifficulty = difficulty.DIFFICULT;
            return;
        }
    }


    public static void lostLevel()
    {
        // Something here
    }

    public static void readFile()
    {
        String separator = ";;;";
        boolean readFirst = false;

        easyQuestions.clear();
        mediumQuestions.clear();
        hardQuestions.clear();
        difficultQuestions.clear();

        try (InputStream in = gameplayLoop.class.getResourceAsStream("/com/oopsie/gameQuestions.csv"))
        {
            if (in == null)
            {
                throw new IllegalStateException("gameQuestions.csv not found on classpath");
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8)))
            {
                String line;

                while ((line = br.readLine()) != null)
                {
                    if (!readFirst)
                    {
                        readFirst = true;
                        continue;
                    }

                    if (line.trim().isEmpty())
                    {
                        continue;
                    }

                    String[] parts = line.split(separator);

                    if (parts.length < 8)
                    {
                        System.err.println("Skipping malformed question line: " + line);
                        continue;
                    }

                    for (int i = 0; i <= 4; i++)
                    {
                        parts[i] = parts[i].replace("\\n", "\n  ");
                    }

                    gameQuestion q = new gameQuestion(
                        parts[0], parts[1], parts[2], parts[3], parts[4],
                        Integer.parseInt(parts[5].trim()) - 1,
                        parts[6], parts[7]
                    );

                    switch (parts[7].trim())
                    {
                        case "Easy":      easyQuestions.add(q);      break;
                        case "Medium":    mediumQuestions.add(q);    break;
                        case "Hard":      hardQuestions.add(q);      break;
                        case "Difficult": difficultQuestions.add(q); break;
                        default:
                            System.err.println("Unknown difficulty: " + parts[7]);
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void addToCompletedIndexes(int val)
    {
        completedIndexes.add(val);
    }

    public static void resetCompletedIndexes()
    {
        completedIndexes.clear();
    }



    public static boolean checkIfCorrectAnswer(int value, gameQuestion question)
    {
        return question.isCorrectAnswer(value);
    }

    public static void selectQuestions()
    {
        Random rand = new Random();

        for(int i = 0; i < 4; i++)
        {   
            switch (currentDifficulty) {
                case EASY:{
                    int val = rand.nextInt(easyQuestions.size());
                    selectedQuestions[i] = easyQuestions.get(val);
                    System.out.println(val);
                        break;
                    }
                case MEDIUM:{
                    int val = rand.nextInt(mediumQuestions.size());
                    selectedQuestions[i] = mediumQuestions.get(val);
                    System.out.println(val);
                        break;
                    }
                case HARD:{
                    int val = rand.nextInt(hardQuestions.size());
                    selectedQuestions[i] = hardQuestions.get(val);
                    System.out.println(val);
                        break;
                    }
                case DIFFICULT:{
                    int val = rand.nextInt(difficultQuestions.size());
                    selectedQuestions[i] = difficultQuestions.get(val);
                    System.out.println(val);
                        break;
                    }
                default:
                    break;
            }
        }
    }
    
    public static gameQuestion[] getSelectedQuestions()
    {
        return selectedQuestions;
    }

    public static int getCurrentPoints()
    {
        return currentPoints;
    }

    public static int getCurrentLevel() {
        return currentLevel;
    }

    public static String getDifficultyString()
    {
        switch (currentDifficulty) {
                case EASY:{
                        return "EASY";
                    }
                case MEDIUM:{
                        return "MEDIUM";
                    }
                case HARD:{
                        return "HARD";
                    }
                case DIFFICULT:{
                        return "DIFFICULT";
                    }
                default:
                    return "null";
            }

    }

    public static playerStats getCurPlayerStats()
    {
        return curPlayer;
    }

    public static boolean hasWonRun()
    {
        return wonRun;
    }
    



}