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
     * PROPOSED LEVEL DESIGN CONCEPT
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
    
    /**
     * A constructor for the Levels class.
     * On initialization, it sets all the variables to point at the current instances.
     * 
     * @param game the currentWorld instance, which in this case is the Game
     * @param c the celestial object instance
     * @param collider the collider object instance
     * @param ib the increase button instance
     * @param db the decrease button instance
     * 
     * @since 1.0
     */
    public Levels(Game game, Celestial c, Collider collider, IncreaseButton ib, DecreaseButton db) {
        this.game = game;
        this.c = c;
        this.collider = collider;
        
        this.ib = ib;
        this.db = db;
        
        this.asteroidList = new CopyOnWriteArrayList<Asteroid>();
        
        this.draw();
    }
    
    /**
     * This method creats n number of asteroids on the screen, and adds the appropriate colliders for them.
     * Please note that the colliders are slightly smaller than the actual celstial object's size, to prevent
     * any misguided collision triggers in-game. (mainly to lower the sensitivity of the default Greenfoot Collision detection function)
     * 
     * @since 1.0
     */
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
    
    /**
     * This method runs at every frame, which is called by another class's act() function, a.k.a. the main loop.
     * It calculate the deltaTime between the time when level up happened previously and the current time.
     * Then, if that deltaTime exceeds three seconds, the method triggers the level up activity with increased difficulty.
     * In short, this method will spawn n number of asteroids based upon the level.
     * 
     * @since 1.0
     */
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
    
    /**
     * This static method determines if all the elements in the array are unanimously true or not.
     * 
     * @param values the array that contains a bunch of boolean values
     * 
     * @return whether all the values in the array indicate true or not
     * 
     * @since 1.0
     */
    private static boolean allTrue (CopyOnWriteArrayList<Boolean> values) {
        for (boolean value : values) {
            if (!value)
                return false;
        }
        return true;
    }
    
    /**
     * This static method will print out the deltaTime and the currentValue values on the console.
     * 
     * @param deltaTimeSecs delta time between the previous level change and the current time in seconds
     * @param currentLevel the numerical value of the current level
     * 
     * @since 1.0
     */
    private static void logState(int deltaTimeSecs, int currentLevel) {
        System.out.println("deltaTimeSecs: " + deltaTimeSecs);
        System.out.println("currentLevel: " + currentLevel);
        System.out.println();
    }
}
