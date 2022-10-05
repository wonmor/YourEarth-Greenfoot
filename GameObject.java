import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameObject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameObject extends Actor {
    public GameObject() {
        super();
    }
    
    protected String getName() {
        return this.getClass().getSimpleName();
    }
}
