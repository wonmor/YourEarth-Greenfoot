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

    Asteroid r;
    Celestial c;
    ContainerText ct;

    IncreaseButton ib;
    DecreaseButton db;

    public Game()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(Constants.screenWidth, Constants.screenHeight, 1);
        
        prepare();
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        GreenfootImage background = getBackground(); // Create Image

        background.setColor(Color.BLACK); // Add Background color
        background.fillRect(0, 0, getWidth(), getHeight()); // Fill image with color

        for (int i = 0; i <= 100; i++) {
            Star s = new Star(Color.WHITE);
            this.addObject(s, getRandomNumber(0, getWidth()), getRandomNumber(0, getHeight()));
        }

        c = new Celestial();
        this.addObject(c, getWidth() / 2, getHeight() / 2);

        r = new Asteroid(this, c);
        this.addObject(r, 50, 50);

        ct = new ContainerText(r);
        this.addObject(ct, getWidth() / 2 + 10, getHeight() - getHeight() / 20);

        ib = new IncreaseButton(r);
        this.addObject(ib, getWidth() / 2 + 20, getHeight() - getHeight() / 8);

        db = new DecreaseButton(r);
        this.addObject(db, getWidth() / 2 - 20, getHeight() - getHeight() / 8);
    }
}
