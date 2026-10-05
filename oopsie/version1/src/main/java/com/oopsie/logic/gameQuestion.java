package com.oopsie.logic;

public class gameQuestion {
    private String questionString = "";
    private int correctAnswer;
    private String[] choicesString = new String[4];
    

    public gameQuestion(String questionString, String q1, String q2, String q3, String q4, int correctAnswer)
    {
        this.questionString = questionString;
        choicesString[0] = q1.trim();
        choicesString[1] = q2.trim();
        choicesString[2] = q2.trim();
        choicesString[3] = q4.trim();
        this.correctAnswer = correctAnswer;
    }

    public gameQuestion(String questionString, String[] choices, int correctAnswer) throws Exception
    {
        if (choices.length != 4)
            throw new RuntimeException("Input array has less than 4 strings");
        this.questionString = questionString;
        this.choicesString[0] = choices[0].trim();
        this.choicesString[1] = choices[1].trim();
        this.choicesString[2] = choices[2].trim();
        this.choicesString[3] = choices[3].trim();
        this.correctAnswer = correctAnswer;
    }

    public boolean isCorrectAnswer(int val)
    {
        // Function to check for the correct answer
        return (correctAnswer == val);
    }

    public String getQuestionString()
    {
        return questionString;
    }

    public String getChoiceString(int val)
    {
        if (val >= 4)
            throw new RuntimeException("Input index exceeds choices size");
        return choicesString[val];
    }
}
