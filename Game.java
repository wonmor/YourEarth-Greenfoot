import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Game extends World {

    /**
     * Constructor for objects of class MyWorld.
     * 
     */

    private Asteroid r;
    private Celestial c;
    private ContainerText ct;

    private IncreaseButton ib;
    private DecreaseButton db;

    public Game() {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(Constants.screenWidth, Constants.screenHeight, 1);
        
        this.prepare();
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
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

        this.addUIElements();
    }
    
    private void addUIElements() {
        this.c = new Celestial();
        this.addObject(this.c, getWidth() / 2, getHeight() / 2);

        this.r = new Asteroid(this, this.c);
        this.addObject(this.r, 50, 50);

        this.ct = new ContainerText(this.r);
        this.addObject(this.ct, getWidth() / 2 + 10, getHeight() - getHeight() / 20);

        this.ib = new IncreaseButton(this.r);
        this.addObject(this.ib, getWidth() / 2 + 20, getHeight() - getHeight() / 8);

        this.db = new DecreaseButton(this.r);
        this.addObject(this.db, getWidth() / 2 - 20, getHeight() - getHeight() / 8);
    }
}
