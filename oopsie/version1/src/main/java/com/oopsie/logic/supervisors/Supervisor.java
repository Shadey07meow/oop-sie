package com.oopsie.logic.supervisors;

import javafx.scene.image.Image;

public class Supervisor {
    private String name;
    private String[] proficientTopics;
    private float baseCorrectChance = 0.25f;
    private Image image;
    
    public Supervisor(String name, String[] proficientTopics, float baseCorrectChance, Image image)
    {
        this.name = name;
        this.proficientTopics = proficientTopics;
        this.baseCorrectChance = baseCorrectChance;
        this.image = image;
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
}
