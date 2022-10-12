import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Celestial class stores the properties of the main planet (Earth),
 * including the scale factor between the raw value (in astronomical units) and
 * the game Earth-Asteroid distance.
 * 
 * @author John Seong 
 * @version 1.0
 */
public class Celestial extends GameObject {
    private GreenfootImage image;
    private Game game = null;
    
    public int scaleFactor = 30;
    
    /**
     * A constructor for the Celestial class. On intialization, draw the sprite image.
     * 
     * @since 1.0
     */
    public Celestial() {    
        this.draw();
    }
    
    /**
     * Points the game instance variable to the input variable.
     * 
     * @since 1.0
     */
    public void setGame(Game game) {
        this.game = game;
    }
    
    /**
     * Draw the sprite image on the display.
     * 
     * @since 1.0
     */
    public void draw() {
        setImage(new GreenfootImage("earth.png"));
        
        image = getImage();
        image.scale(image.getWidth() / this.scaleFactor, image.getHeight() / this.scaleFactor);
        
        setImage(image);
    }
    
    /**
     * This is an act() function that runs every frame.
     * Essentially serves as the main loop.
     * 
     * @since 1.0
     */
    public void act() {
        if (this.game == null) { return; }
        
        this.game.levels.run();
    }
    
    /**
     * Gets the width of the image sprite.
     * 
     * @return the width of the image
     * 
     * @since 1.0
     */
    public int getWidth() {
        image = getImage();
        return image.getWidth();
    }
    
    /**
     * Gets the height of the image sprite.
     * 
     * @return the height of the image
     * 
     * @since 1.0
     */
    public int getHeight() {
        image = getImage();
        return image.getHeight();
    }
    
    /**
     * Gets the x-coordinate of where the image is located.
     * 
     * @return the x-coordinate of the image
     * 
     * @since 1.0
     */
    public int getCoordX() {
        image = getImage();
        return this.getX() - image.getWidth() / 2;
    }
    
    /**
     * Gets the y-coordinate of where the image is located.
     * 
     * @return the y-coordinate of the image
     * 
     * @since 1.0
     */
    public int getCoordY() {
        image = getImage();
        return this.getX() - image.getHeight() / 2;
    }
}
