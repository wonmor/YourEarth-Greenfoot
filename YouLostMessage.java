import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class YouLostMessage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class YouLostMessage extends UIElement {
    public YouLostMessage(Asteroid r) {
        super("youLostText.png", false, 2, 2);
        super.setAsteroidInstance(r);
    }
}
