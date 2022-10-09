import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is a parent class of all the elements of the game that appear on-screen.
 * 
 * @author John Seong 
 * @version 1.0
 */

public class GameObject extends Actor {
    private long startTime;
    
    public GameObject() {
        super();
    }
    
    protected String getName() {
        return this.getClass().getSimpleName();
    }
    
    protected void startFadeOutTimer() {
        this.startTime = System.currentTimeMillis();
    }
    
    protected void runFadeOut(Game game, Class cls) {
        long currentTime = System.currentTimeMillis();
        
        int deltaTimeMillis = (int)(currentTime - this.startTime);
        int deltaTimeSecs = deltaTimeMillis / 1000;
        
        if (deltaTimeSecs >= 3) {
            game.removeObjects(game.getObjects(cls));
        }  
    }
}
