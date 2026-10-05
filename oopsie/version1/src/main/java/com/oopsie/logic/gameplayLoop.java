package com.oopsie.logic;

public class gameplayLoop {
    private static boolean isRunning = false;

    private static int currentLevel = -1;

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

    private static void startGame()
    {
        currentLevel = 0;
    }

    private static void endgame()
    {
        currentLevel = -1;
    }

    public static void init()
    {
        gameQuestion q1 = new gameQuestion("This is test question 1", "Answer 1", "Answer 2", "Answer 3", "Answer 4", 1);
        gameQuestion q2 = new gameQuestion("This is test question 2", "Answer 1", "Answer 2", "Answer 3", "Answer 4", 1);
        gameQuestion q3 = new gameQuestion("This is test question 3", "Answer 1", "Answer 2", "Answer 3", "Answer 4", 1);

        selectedQuestions[0] = q1;
        selectedQuestions[1] = q2;
        selectedQuestions[2] = q3;
    }

    public static void wonLevel()
    {
        currentLevel++;
    }


    public static void lostLevel()
    {
        // Something here
    }


    



}
