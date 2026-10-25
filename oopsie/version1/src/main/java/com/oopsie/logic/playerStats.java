package com.oopsie.logic;

public class playerStats {
    String name;
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
        System.out.println(Boolean.toString(this.hasDebug));
        System.out.println(Boolean.toString(this.hasExceptionHandler));
        System.out.println(Boolean.toString(this.hasRecompile));
        return (this.hasDebug || this.hasExceptionHandler || this.hasRecompile);
    }
    

}
