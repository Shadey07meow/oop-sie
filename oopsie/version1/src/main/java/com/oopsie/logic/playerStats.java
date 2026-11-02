package com.oopsie.logic;

import com.oopsie.logic.supervisors.Supervisor;



public class playerStats {
    String name;
    Supervisor selectedDSupervisor = null;

    int points;
    boolean hasExceptionHandler = true;
    boolean hasRecompile = true;
    boolean hasDebug = true;

    public void setHasExceptionHandler(boolean v)
    {
        this.hasExceptionHandler = v;
    }

    public void setHasRecompile(boolean v)
    {
        this.hasRecompile = v;
    }
    
    public void setHasDebug(boolean v)
    {
        this.hasDebug = v;
    }
    
    public void setSupervisor(Supervisor v)
    {   
        this.selectedDSupervisor = v;
    }

    public Supervisor getSupervisor()
    {   
        return this.selectedDSupervisor;
    }
    
    public boolean getHasExceptionHandler()
    {
        return this.hasExceptionHandler;
    }

    public boolean getHasRecompile()
    {
        return this.hasRecompile;
    }
    
    public boolean getHasDebug()
    {
        return this.hasDebug;
    }

    public boolean hasLifelines()
    {
        return (this.hasRecompile);
    }
    
    public void setName(String name)
    {
        this.name = name;
    } 

    public String getName() {return this.name;}
}
