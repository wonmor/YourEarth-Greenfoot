import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
public class TryAgainButton extends UIElement {
    /**
     * A constructor for the TryAgainButton class.
     * It selects the image and sends it to the parent class.
     * 
     * @param r the current Asteroid instance
     * 
     * @since 1.0
     */
    public TryAgainButton(Asteroid r) {
        super("tryAgainButton.png", true, 4, 4);
        super.setAsteroidInstance(r);
    }
}
