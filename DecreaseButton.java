import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is a decrease button, and when pressed, the Earth's mass decreases step by step...
 * 
 * @author John Seong
 * @version 1.0
 */

public class DecreaseButton extends UIElement {
    public DecreaseButton(Asteroid r) {
        super("decreaseButton.png", true, 5, 5);
        super.setAsteroidInstance(r);
    }
}
