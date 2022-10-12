import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This is a centralized class that handles all the UI elements on the game screen.
 * It effectively utilizes Java's core functionality: Polymorphism.
 * 
 * Not only that, by creating two methods that accept different types of parameters but share the same name,
 * the program uses Method Overriding design pattern as well.
 * 
 * A guard clause is used instead of complex, nested if statements for syntactic sugar (better readability).
 * 
 * @author John Seong 
 * @version 1.0
 */
public class IncreaseButton extends UIElement {
    /**
     * A constructor for the IncreaseButton class.
     * It sets the variables to point instances (e.g. celestial, current world, and the collider).
     * 
     * @param celestial the Earth object located on the centre of the screen
     * @param g the current world instance, which in this case is Game
     * @param collider the collider object that detects asteroid-Earth collision
     * 
     * @since 1.0
     */
    public IncreaseButton(Celestial celestial, Game g, Collider collider) {
        super("increaseButton.png", true, 5, 5);
        
        super.setGameInstance(g);
        super.setCelestialInstance(celestial);
        super.setColliderInstance(collider);
    }
}
