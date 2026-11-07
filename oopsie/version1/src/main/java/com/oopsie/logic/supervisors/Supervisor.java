package com.oopsie.logic.supervisors;


import java.util.Random;

import com.oopsie.logic.gameQuestion;

import javafx.scene.image.Image;

public class Supervisor {
    private String name;
    private String role;
    private String[] proficientTopics;
    private float baseCorrectChance = 0.25f;
    private Image image;
    private String pickedLine = "";
    
    public Supervisor(String name, String role, String[] proficientTopics, float baseCorrectChance, Image image, String pickedLine)
    {
        this.name = name;
        this.role = role;
        this.proficientTopics = proficientTopics;
        this.baseCorrectChance = baseCorrectChance;
        this.image = image;
        this.pickedLine = pickedLine;
    }

    public int getAnswer(gameQuestion question)
    {
        float chance = getCorrectChance(question.getTopic());

        Random rand = new Random();
        float rolled = rand.nextFloat();


        if(rolled <= chance)
        {
            return question.getCorrectAnswer();
        }else
        {
            boolean gottenRandomAnswer = false;
            int gottenAnswer = 0;
            while(!gottenRandomAnswer)
            {
                gottenAnswer = rand.nextInt(4);
                if(gottenAnswer != question.getCorrectAnswer())
                    gottenRandomAnswer = true;
            }
            return gottenAnswer;
        }
    }



    public float getCorrectChance(String topic)
    {

        String keyString = topic.toLowerCase().trim(); 
        for(int i = 0; i < proficientTopics.length; i++)
        {
            if (keyString.equals(proficientTopics[i]))
            {
                return 1.0f;
            }
        }

        return this.baseCorrectChance;
    }

    public boolean checkName(String targetName)
    {
        return targetName.toLowerCase().trim().equals(this.name);
    }

    public Image getImage()
    {
        return this.image;
    }

    public String getLine()
    {
        return this.pickedLine;
    }

    public String getName()
    {
        return this.name;
    }

    public String getRole()
    {
        return this.role;
    }
}
