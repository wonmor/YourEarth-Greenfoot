import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This is a decrease button, and when pressed, the Earth's mass decreases step by step...
 * 
 * @author John Seong
 * @version 1.0
 */

public class DecreaseButton extends UIElement {
    public DecreaseButton(Celestial celestial, Game g, Collider collider) {
        super("decreaseButton.png", true, 5, 5);
        
        super.setGameInstance(g);
        super.setCelestialInstance(celestial);
        super.setColliderInstance(collider);
    }
}
