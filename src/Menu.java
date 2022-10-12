import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is a class that contains all the menu elements that appear on launch.
 * 
 * @author John Seong
 * @version 1.0
 */

public class Menu extends World {
    private Logo logo;
    private PlayButton playButton;
    private Celestial celestial;
    
    private GreenfootImage background;
    private GreenfootSound backgroundMusic;

    /**
     * Create a new world with 600x400 cells with a cell size of 1x1 pixels.
     * 
     * @since 1.0
     */
    public Menu() {    
        super(Constants.screenWidth, Constants.screenHeight, 1); 
        
        this.prepare();
    }
    
    /**
     * Adds all the objects and UI elements that appear on screen, whicn include the celestial, randomly generated stars, asteroid and its trace, etc.
     * 
     * @since 1.0
     */
    private void prepare() {
        background = getBackground(); // Create Image

        background.setColor(Color.BLACK); // Add Background color
        background.fillRect(0, 0, getWidth(), getHeight()); // Fill image with color
        
        backgroundMusic = new GreenfootSound("1939.mp3"); // 1939 is my original song that I actually released on Spotify!
        
        backgroundMusic.playLoop();
        backgroundMusic.setVolume(25);

        for (int i = 0; i <= 100; i++) {
            Star s = new Star(Color.WHITE);
            this.addObject(s, Game.getRandomNumber(0, getWidth()), Game.getRandomNumber(0, getHeight()));
        }
        
        this.celestial = new Celestial();
        this.addObject(this.celestial, Constants.screenWidth / 2, Constants.screenHeight / 3);
        
        this.logo = new Logo();
        this.addObject(this.logo, Constants.screenWidth / 2, Constants.screenHeight / 2);
        
        this.playButton = new PlayButton();
        this.addObject(this.playButton, Constants.screenWidth / 2, Constants.screenHeight - Constants.screenHeight / 3);
    }
}
