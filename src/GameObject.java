import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is a parent class of all the elements of the game that appear on-screen.
 * 
 * @author John Seong 
 * @version 1.0
 */

public class GameObject extends Actor {
    private long startTime;
    
    /**
     * Calls the superclass' constructor, which includes the act() method.
     * 
     * @since 1.0
     */
    public GameObject() {
        super();
    }
    
    /**
     * Retrieves the name of the child class.
     * 
     * @return name of the child class
     * 
     * @since 1.0
     */
    protected String getName() {
        return this.getClass().getSimpleName();
    }
    
    /**
     * Starts counting the timer for fade out animation.
     * 
     * @since 1.0
     */
    protected void startFadeOutTimer() {
        this.startTime = System.currentTimeMillis();
    }
    
    /**
     * This method dictates the behaviour of a fade out animation.
     * Essentially, it starts by calculating the deltaTime
     * and if it exceeds 3 seconds then hide the object.
     * 
     * @param game the instance of the current world, which is Game.
     * @param cls the class that represent an UI element, which will go through the fade out process.
     * 
     * @since 1.0
     */
    protected void runFadeOut(Game game, Class cls) {
        long currentTime = System.currentTimeMillis();
        
        int deltaTimeMillis = (int)(currentTime - this.startTime);
        int deltaTimeSecs = deltaTimeMillis / 1000;
        
        if (deltaTimeSecs >= 3) {
            game.removeObjects(game.getObjects(cls));
        }  
    }
}
