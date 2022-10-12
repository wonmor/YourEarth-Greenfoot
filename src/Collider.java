import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is a collider class suited for the use on the celestial object.
 * It basically covers the rectangular area within the celestial sphere,
 * providing accurate collision detection support.
 * 
 * @author John Seong
 * @version 1.0
 */
public class Collider extends GameObject {
    public int scaleFactor = 0;
    
    /**
     * A constructor for the Collider class. On intialization, draw the sprite image.
     * 
     * @since 1.0
     */
    public Collider() {
        this.draw();
    }
    
    /**
     * Draw the sprite image on the display.
     * Math.abs ensure that the width and the height never become negative values
     * as it happens when the slope of the trajectory is changed drastically.
     * 
     * @since 1.0
     */
    public void draw() {
        GreenfootImage image = new GreenfootImage(Math.abs(Constants.sunDefaultWidth - 15 - this.scaleFactor), Math.abs(Constants.sunDefaultHeight - 15 - this.scaleFactor));
        
        this.setImage(image);
    }
}
