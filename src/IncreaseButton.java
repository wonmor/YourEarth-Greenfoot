import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This is a increase button, and when pressed, the Earth's mass increases step by step...
 * 
 * @author John Seong
 * @version 1.0
 */

public class IncreaseButton extends UIElement {
    public IncreaseButton(Celestial celestial, Game g, Collider collider) {
        super("increaseButton.png", true, 5, 5);
        
        super.setGameInstance(g);
        super.setCelestialInstance(celestial);
        super.setColliderInstance(collider);
    }
}
