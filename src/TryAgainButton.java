import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TryAgainButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TryAgainButton extends UIElement {
    public TryAgainButton(Asteroid r) {
        super("tryAgainButton.png", true, 4, 4);
        super.setAsteroidInstance(r);
    }
}
