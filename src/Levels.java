import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.ArrayList;

/**
 * This class handles both the transition between the levels as well as
 * the difficulty settings for individual stages of this game.
 * 
 * Basically, as time passes by, the number of asteroids that will fly into
 * Earth orbit will increase.
 * 
 * @author John Seong
 * @version 1.0
 */
public class Levels extends Actor {
    /**
     * Act - do whatever the Levels wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public int currentLevel = 1;
    
    // When the game is started, do:
    private long startTime = System.currentTimeMillis();
        
    public CopyOnWriteArrayList<Asteroid> asteroidList;
    
    private Celestial c;
    private Game game;
    private Collider collider;
    
    private IncreaseButton ib;
    private DecreaseButton db;
    
    public Levels(Game game, Celestial c, Collider collider, IncreaseButton ib, DecreaseButton db) {
        this.game = game;
        this.c = c;
        this.collider = collider;
        
        this.ib = ib;
        this.db = db;
        
        this.asteroidList = new CopyOnWriteArrayList<Asteroid>();
        
        this.change();
    }
    
    public void change() {
        for (int i = 0; i < this.currentLevel; i++) {
            Asteroid currentAsteroid = new Asteroid(this.game, this.c, this.collider);
            
            this.asteroidList.add(currentAsteroid);
            
            // Update the asteroidList variable in the UI classes upon every new asteroid render...
            this.ib.setAsteroidInstance(this.asteroidList);
            this.db.setAsteroidInstance(this.asteroidList);
            
            this.game.addObject(currentAsteroid, 50, 50);
        }
    }
    
    public void run() {
        long currentTime = System.currentTimeMillis();
        
        int deltaTimeMillis = (int)(currentTime - this.startTime);
        int deltaTimeSecs = deltaTimeMillis / 1000;
        
        for (Asteroid asteroid : asteroidList) {
            if (deltaTimeSecs >= 15 && asteroid.isPlaying) {
                this.change();
                this.currentLevel++;
                
                this.startTime = currentTime;
            }
        }
    }
}
