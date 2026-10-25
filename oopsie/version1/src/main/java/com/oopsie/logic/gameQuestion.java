package com.oopsie.logic;

public class gameQuestion {
    private String questionString = "";
    private int correctAnswer;
    private String[] choicesString = new String[4];
    private String topicString;
    private String difficultyString;
    

    public gameQuestion(String questionString, String q1, String q2, String q3, String q4, int correctAnswer, String topicString, String dificultString)
    {
        this.questionString = questionString;
        choicesString[0] = q1.trim();
        choicesString[1] = q2.trim();
        choicesString[2] = q3.trim();
        choicesString[3] = q4.trim();
        this.correctAnswer = correctAnswer;
        this.topicString = topicString;
        this.difficultyString = dificultString;
    }

    public gameQuestion(String questionString, String[] choices, int correctAnswer, String topic, String difficulty) throws Exception
    {
        if (choices.length != 4)
            throw new RuntimeException("Input array has less than 4 strings");
        this.questionString = questionString;
        this.choicesString[0] = choices[0].trim();
        this.choicesString[1] = choices[1].trim();
        this.choicesString[2] = choices[2].trim();
        this.choicesString[3] = choices[3].trim();
        this.correctAnswer = correctAnswer;
        this.difficultyString= difficulty;
        this.topicString = topic;
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

    public String getDifficulty()
    {
        return this.difficultyString;
    }

    public String getTopic()
    {
        return this.topicString;
    }
}
