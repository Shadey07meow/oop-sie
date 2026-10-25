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
            new String[]{"C", "Java"}, 
            0.1f,
            new Image(getClass().getResourceAsStream("/com/oopsie/images/supervisorScreen/Supervisor1.png"))
        ));
        
        supervisorList.add(new Supervisor(
            "Cecil",
            new String[]{"C", "Java"}, 
            0.1f,
            new Image(getClass().getResourceAsStream("/com/oopsie/images/supervisorScreen/Supervisor2.png"))

            
        ));
        
        
        supervisorList.add(new Supervisor(
            "Jerome",
            new String[]{"C", "Java"}, 
            0.1f,
            new Image(getClass().getResourceAsStream("/com/oopsie/images/supervisorScreen/Supervisor3.png"))

        ));
        
        supervisorList.add(new Supervisor(
            "Friday",
            new String[]{"C", "Java"}, 
            0.1f,
            new Image(getClass().getResourceAsStream("/com/oopsie/images/supervisorScreen/Supervisor4.png"))

        ));

        supervisorList.add(new Supervisor(
            "Ultron",
            new String[]{"C", "Java"}, 
            0.1f,
            new Image(getClass().getResourceAsStream("/com/oopsie/images/supervisorScreen/Supervisor5.png"))

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
