package com.oopsie.logic.supervisors;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;
 

public class SupervisorManager {
    
    public static SupervisorManager instance;
    public ArrayList<Supervisor> supervisorList = new ArrayList<>();
    private Random rand = new Random();
    
    public SupervisorManager()
    {
        generateSupervisors();
    }

    

    public static SupervisorManager get()
    {
        if (instance == null)
        {
            instance = new SupervisorManager();
        }        

        return instance;
    }

    private  void generateSupervisors()
    {
        supervisorList.add(new Supervisor(
            "Bob",
            "Head Programmer",
            new String[]{"C", "Java"}, 
            0.2f,
            new Image(getClass().getResourceAsStream("/com/oopsie/images/supervisorScreen/Supervisor1.png")),
            "*Grumbling*"
        ));
        
        supervisorList.add(new Supervisor(
            "William",
            "Theory Technician",
            new String[]{"Module 1 - Introduction to Paradigms"}, 
            0.5f,
            new Image(getClass().getResourceAsStream("/com/oopsie/images/supervisorScreen/Supervisor2.png")), 
            "Uh…"
        ));
        
        
        supervisorList.add(new Supervisor(
            "Greg",
            "Junior Programmer",
            new String[]{"Module 2 - Procedural Programming", "Module 3 - Functional Programming"}, 
            0.1f,
            new Image(getClass().getResourceAsStream("/com/oopsie/images/supervisorScreen/Supervisor3.png")),
            "What's happening now, recruit?"
        ));
        
        supervisorList.add(new Supervisor(
            "Craig",
            "Honorary Game Developer",
            new String[]{"Module 4 - OOP Programming", "Module 5 - Imperative vs Declarative Programming", "Module 6 - Event-driven Programming"}, 
            0.1f,
            new Image(getClass().getResourceAsStream("/com/oopsie/images/supervisorScreen/Supervisor4.png")),
            "Let’s see here…"

        ));

        supervisorList.add(new Supervisor(
            "Jarvis",
            "Lead Senior API Developer",
            new String[]{"Module 2 - Procedural Programming", "Module 3 - Functional Programming", "Module 4 - OOP Programming", "Module 5 - Imperative vs Declarative Programming", "Module 6 - Event-driven Programming"},
            0.1f,
            new Image(getClass().getResourceAsStream("/com/oopsie/images/supervisorScreen/Supervisor5.png")),
            "No worries, recruit! I’ve got your back."
        ));
    }

    public Supervisor[] randSelectSupervisors()
    {
        Supervisor[] tempSupervisorArray = new Supervisor[3];
        int supervisorCount = 0;

        while(supervisorCount < 3)
        {
            Supervisor selectedSupervisor = null;

            // while (selectedSupervisor == null) {
            selectedSupervisor = supervisorList.get(rand.nextInt(supervisorList.size()));

            //     for(int i = 0; i < 3; i++)
            //     {
            //         if (tempSupervisorArray[i] == selectedSupervisor);
            //             selectedSupervisor = null;
            //             break;
            //     }
            // }
            
            tempSupervisorArray[supervisorCount] = selectedSupervisor;
            supervisorCount++;
        }

        return tempSupervisorArray;
        

    }
}
