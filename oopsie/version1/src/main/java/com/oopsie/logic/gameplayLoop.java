package com.oopsie.logic;

import java.io.BufferedReader;
import java.io.FileReader;
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
        System.out.println("I am being called");
        boolean readFirst = false;

        try (BufferedReader br = new BufferedReader(new FileReader("oopsie/version1/src/main/java/com/oopsie/gameQuestions.csv")))
        {
            
            String line;
            
            while((line = br.readLine()) != null)
            {
                if (!readFirst) {
                    readFirst = true;
                    continue;
                }

                String[] lineSeparated = line.split(separator);
                lineSeparated[0] = lineSeparated[0].replace("\\n", "\n  ");
                lineSeparated[1] = lineSeparated[1].replace("\\n", "\n  ");
                lineSeparated[2] = lineSeparated[2].replace("\\n", "\n  ");
                lineSeparated[3] = lineSeparated[3].replace("\\n", "\n  ");
                lineSeparated[4] = lineSeparated[4].replace("\\n", "\n  ");
                //easyQuestions.add(new gameQuestion(lineSeparated[0].replace("\\n", "\n"), lineSeparated[1], lineSeparated[2], lineSeparated[3], lineSeparated[4], Integer.parseInt(lineSeparated[5]), lineSeparated[6], lineSeparated[7]));
                if(lineSeparated[7].equals("Easy"))
                {
                    easyQuestions.add(new gameQuestion(lineSeparated[0], lineSeparated[1], lineSeparated[2], lineSeparated[3], lineSeparated[4], Integer.parseInt(lineSeparated[5]) - 1, lineSeparated[6], lineSeparated[7]));
                } else if (lineSeparated[7].equals("Medium"))
                {
                    mediumQuestions.add(new gameQuestion(lineSeparated[0], lineSeparated[1], lineSeparated[2], lineSeparated[3], lineSeparated[4], Integer.parseInt(lineSeparated[5]) - 1, lineSeparated[6], lineSeparated[7]));                    
                } else if (lineSeparated[7].equals("Hard"))
                {
                    hardQuestions.add(new gameQuestion(lineSeparated[0], lineSeparated[1], lineSeparated[2], lineSeparated[3], lineSeparated[4], Integer.parseInt(lineSeparated[5]) - 1, lineSeparated[6], lineSeparated[7]));                    
                } else if (lineSeparated[7].equals("Difficult"))
                {
                    difficultQuestions.add(new gameQuestion(lineSeparated[0], lineSeparated[1], lineSeparated[2], lineSeparated[3], lineSeparated[4], Integer.parseInt(lineSeparated[5]) - 1, lineSeparated[6], lineSeparated[7]));                    
                }     
            }
        } catch (Exception exception)
        {

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

        for(int i = 0; i < 4; i++)
        {   
            Random rand = new Random();
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
