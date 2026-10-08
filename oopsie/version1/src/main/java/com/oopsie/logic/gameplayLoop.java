package com.oopsie.logic;

public class gameplayLoop {
    private static boolean isRunning = false;

    private static int currentLevel = -1;
    private static int currentPoints = 0;
    private static int pointIncrease = 10;

    private static gameQuestion[] maintenanceTierQuestions;
    private static gameQuestion[] technicianTierQuestions;
    private static gameQuestion[] systemsEngineerTierQuestions;
    private static gameQuestion[] adminTierQuestions;

    private static gameQuestion[] selectedQuestions = new gameQuestion[100];

 
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
        gameQuestion q1 = new gameQuestion("What is the capital of France?", "London", "fParis", "Berlin", "Rome", 1);
        gameQuestion q2 = new gameQuestion("Which planet is known as the Red Planet?", "Earth", "Mars", "Jupiter", "Saturn", 1);
        gameQuestion q3 = new gameQuestion("What is the largest ocean on Earth?", "Atlantic", "Pacific", "Indian", "Arctic", 1);
        gameQuestion q4 = new gameQuestion("How many bones are in an adult human body?", "106", "206", "306", "406", 1);
        gameQuestion q5 = new gameQuestion("Which element has the chemical symbol 'O'?", "Gold", "Oxygen", "Osmium", "Silver", 1);
        gameQuestion q6 = new gameQuestion("Who painted the Mona Lisa?", "Van Gogh", "Leonardo da Vinci", "Picasso", "Monet", 1);
        gameQuestion q7 = new gameQuestion("What is the hardest natural substance on Earth?", "Gold", "Diamond", "Iron", "Platinum", 1);
        gameQuestion q8 = new gameQuestion("Which country is home to the kangaroo?", "Africa", "Australia", "Peru", "India", 1);
        gameQuestion q9 = new gameQuestion("What is the freezing point of water in Celsius?", "100°C", "0°C", "-10°C", "32°C", 1);
        gameQuestion q10 = new gameQuestion("How many continents are there on Earth?", "5", "7", "6", "8", 1);

        selectedQuestions[0] = q1;
        selectedQuestions[1] = q2;
        selectedQuestions[2] = q3;
        selectedQuestions[3] = q4;
        selectedQuestions[4] = q5;
        selectedQuestions[5] = q6;
        selectedQuestions[6] = q7;
        selectedQuestions[7] = q8;
        selectedQuestions[8] = q9;
        selectedQuestions[9] = q10;
    }


    public static boolean checkIfCorrectAnswer(int value)
    {
        return selectedQuestions[currentLevel].isCorrectAnswer(value);
    }
    
    public static gameQuestion getSelectedQuestion()
    {
        if (currentLevel >= 0 && currentLevel < 100)
        {
            return selectedQuestions[currentLevel];
        } else
        {
            System.err.println("Selected level index is out of bounds");
            return null;
        }
    }

    public static int getCurrentPoints()
    {
        return currentPoints;
    }


    



}
