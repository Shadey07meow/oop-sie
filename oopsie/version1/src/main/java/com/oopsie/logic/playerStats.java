
package com.oopsie.logic;

import com.oopsie.logic.supervisors.Supervisor;

public class playerStats {

    private String name;
    private Supervisor selectedDSupervisor = null;

    private int points;

    private boolean hasExceptionHandler = true;
    private boolean hasRecompile = true;
    private boolean hasDebug = true;

    public void addPoints(int val) {
        this.points += val;
    }

    public int getPoints() {
        return this.points;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setHasExceptionHandler(boolean v) {
        this.hasExceptionHandler = v;
    }

    public void setHasRecompile(boolean v) {
        this.hasRecompile = v;
    }

    public void setHasDebug(boolean v) {
        this.hasDebug = v;
    }

    public void setSupervisor(Supervisor v) {
        this.selectedDSupervisor = v;
    }

    public Supervisor getSupervisor() {
        return this.selectedDSupervisor;
    }

    public boolean getHasExceptionHandler() {
        return this.hasExceptionHandler;
    }

    public boolean getHasRecompile() {
        return this.hasRecompile;
    }

    public boolean getHasDebug() {
        return this.hasDebug;
    }

    public boolean hasLifelines() {
        return this.hasRecompile;
    }

    public String getUsedLifelines()
    {
        String used = "";

        if (!hasDebug)
            used = used.concat("> Debug \n");
    
        if (!hasRecompile)
            used = used.concat("> Recompile \n");
        
        if (!hasExceptionHandler)
            used = used.concat("> Execution Handler \n");
        
        return used;
    }

    public double getCompletionPercentage()
    {
        return Math.round((points/10) / 40);
    }
}
