import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.concurrent.CopyOnWriteArrayList;

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
    public int currentLevel = 1;
    
    /*
     * LEVEL DESIGN CONCEPT
     * 
     * LEVEL 1: DECREASE THE SPHERE OF CONTAINMENT
     * LEVEL 2: SAME AS LEVEL 1 BUT EVEN FURTHER
     * LEVEL 3: EVEN FURTHER
     * LEVEL 4: LEVEL 1 SPHERE BUT TWO ASTEROIDS
     * LEVEL 5: DECREASE
     * LEVEL 6: DECREASE EVEN FURTHER
     * LEVEL 7: CONGRATS MESSAGE
     * 
     * MAKE MISSILE APPROACHING SCENARIO?
     */
    
    private long startTime = System.currentTimeMillis();
        
    public CopyOnWriteArrayList<Asteroid> asteroidList;
    
    private Celestial c;
    private Game game;
    private Collider collider;
    
    private Scoreboard scoreboard;
    
    private IncreaseButton ib;
    private DecreaseButton db;
    
    public Levels(Game game, Celestial c, Collider collider, IncreaseButton ib, DecreaseButton db) {
        this.game = game;
        this.c = c;
        this.collider = collider;
        
        this.ib = ib;
        this.db = db;
        
        this.asteroidList = new CopyOnWriteArrayList<Asteroid>();
        
        this.draw();
    }
    
    public void draw() {
        Asteroid currentAsteroid = new Asteroid(this.game, this.c, this.collider);
        currentAsteroid.setLevelsInstance(this);
        
        this.asteroidList.add(currentAsteroid);
        
        // Update the asteroidList variable in the UI classes upon every new asteroid render...
        this.ib.setAsteroidInstance(this.asteroidList);
        this.db.setAsteroidInstance(this.asteroidList);
        
        this.game.addObject(currentAsteroid, 50, 50);
        
        this.scoreboard = new Scoreboard(this.game);
        this.scoreboard.draw("LEVEL " + this.currentLevel, false);
    }
    
    public void run() {
        long currentTime = System.currentTimeMillis();
        
        int deltaTimeMillis = (int)(currentTime - this.startTime);
        int deltaTimeSecs = deltaTimeMillis / 1000;
        
        CopyOnWriteArrayList<Boolean> isLevelUp = new CopyOnWriteArrayList<Boolean>();
        
        this.logState(deltaTimeSecs, currentLevel);
        
        for (Asteroid asteroid : asteroidList) {
            if (deltaTimeSecs >= 15 && asteroid.isPlaying) {
                isLevelUp.add(true);
                
                this.startTime = currentTime;
            } else { isLevelUp.add(false); }
        }
        
        if (this.allTrue(isLevelUp)) {
            this.currentLevel++;
            this.draw();
        }
    }
    
    private static boolean allTrue (CopyOnWriteArrayList<Boolean> values) {
        for (boolean value : values) {
            if (!value)
                return false;
        }
        return true;
    }
    
    private static void logState(int deltaTimeSecs, int currentLevel) {
        System.out.println("deltaTimeSecs: " + deltaTimeSecs);
        System.out.println("currentLevel: " + currentLevel);
        System.out.println();
    }
}
