import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the class that has all the properties and methods for the "game" world to be intialized.
 * 
 * @author John Seong
 * @version 1.0
 */

public class Game extends World {
    private Celestial c;
    
    private Collider collider;
    
    public Levels levels = null;
    
    private ContainerText ct;
    private IncreaseButton ib;
    private DecreaseButton db;
    
    private Scoreboard scoreboard;

    public Game() {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(Constants.screenWidth, Constants.screenHeight, 1);
        
        this.prepare();
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    
    public static double getRandomNumber(double min, double max) {
        return (double) ((Math.random() * (max - min)) + min);
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare() {
        GreenfootImage background = getBackground(); // Create Image

        background.setColor(Color.BLACK); // Add Background color
        background.fillRect(0, 0, getWidth(), getHeight()); // Fill image with color

        for (int i = 0; i <= 100; i++) {
            Star s = new Star(Color.WHITE);
            this.addObject(s, getRandomNumber(0, getWidth()), getRandomNumber(0, getHeight()));
        }
        
        this.c = new Celestial();
        this.c.setGame(this);
        
        this.addObject(this.c, getWidth() / 2, getHeight() / 2);
        
        this.collider = new Collider();
        this.addObject(this.collider, getWidth() / 2, getHeight() / 2);
        
        this.addUIElements();
        
        this.levels = new Levels(this, this.c, this.collider, this.ib, this.db);
        
        this.scoreboard = new Scoreboard(this);
        this.scoreboard.draw("LEVEL " + this.levels.currentLevel, false);
        
        this.ib.setAsteroidInstance(this.levels.asteroidList);
        this.db.setAsteroidInstance(this.levels.asteroidList);
    }
    
    public void addUIElements() {
        this.ct = new ContainerText();
        this.addObject(this.ct, getWidth() / 2 + 10, getHeight() - getHeight() / 20);

        this.ib = new IncreaseButton(this.c, this, this.collider);
        this.addObject(this.ib, getWidth() / 2 + 20, getHeight() - getHeight() / 8);

        this.db = new DecreaseButton(this.c, this, this.collider);
        this.addObject(this.db, getWidth() / 2 - 20, getHeight() - getHeight() / 8);
    }
    
    public static void logCoords(int x, int y) {
        System.out.println("x: " + x);
        System.out.println("y: " + y);
        System.out.println();
    }
}
