package com.oopsie.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class gameplayLoop {
    private static boolean isRunning = false;


    private playerStats curPlayer = null;

    private static int currentLevel = -1;
    private static int currentPoints = 0;
    private static int pointIncrease = 10;

    private static ArrayList<Integer> completedIndexes = new ArrayList<>();
    private static ArrayList<gameQuestion> choicesQuestions = new ArrayList<>();

    private static ArrayList<gameQuestion> easyQuestions = new ArrayList<>(); // MaintenanceTierQuestions
    private static ArrayList<gameQuestion> mediumQuestions = new ArrayList<>(); // Technician Tier Questions
    private static ArrayList<gameQuestion> hardQuestions = new ArrayList<>(); // System Engineer Tier Questions
    private static ArrayList<gameQuestion> difficultQuestions = new ArrayList<>(); // Admin Tier Questions

    private static gameQuestion[] selectedQuestions = new gameQuestion[4];

 
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
        currentLevel = 0;
    }

    private static void endgame()
    {
        currentPoints = 0;
        currentLevel = -1;
    }

    public static void init()
    {
        debugInit();
    }

    public static void wonLevel()
    {
        currentPoints += pointIncrease;
        currentLevel++;
    }


    public static void lostLevel()
    {
        // Something here
    }

    public static void debugInit()
    {
        String separator = " -- ";
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
                lineSeparated[0] = lineSeparated[0].replace("\\n", "\n");
                //easyQuestions.add(new gameQuestion(lineSeparated[0].replace("\\n", "\n"), lineSeparated[1], lineSeparated[2], lineSeparated[3], lineSeparated[4], Integer.parseInt(lineSeparated[5]), lineSeparated[6], lineSeparated[7]));
                if(lineSeparated[7].equals("Easy"))
                {
                    easyQuestions.add(new gameQuestion(lineSeparated[0], lineSeparated[1], lineSeparated[2], lineSeparated[3], lineSeparated[4], Integer.parseInt(lineSeparated[5]), lineSeparated[6], lineSeparated[7]));
                } else if (lineSeparated[7].equals("Medium"))
                {
                    mediumQuestions.add(new gameQuestion(lineSeparated[0], lineSeparated[1], lineSeparated[2], lineSeparated[3], lineSeparated[4], Integer.parseInt(lineSeparated[5]), lineSeparated[6], lineSeparated[7]));                    
                } else if (lineSeparated[7].equals( "Hard"))
                {
                    hardQuestions.add(new gameQuestion(lineSeparated[0], lineSeparated[1], lineSeparated[2], lineSeparated[3], lineSeparated[4], Integer.parseInt(lineSeparated[5]), lineSeparated[6], lineSeparated[7]));                    
                } else if (lineSeparated[7].equals("Difficult"))
                {
                    hardQuestions.add(new gameQuestion(lineSeparated[0], lineSeparated[1], lineSeparated[2], lineSeparated[3], lineSeparated[4], Integer.parseInt(lineSeparated[5]), lineSeparated[6], lineSeparated[7]));                    
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
            int val = rand.nextInt(easyQuestions.size());
            selectedQuestions[i] = easyQuestions.get(val);
            System.out.println(val);
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


    



}
