import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Earth here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Celestial extends Actor
{
    /**
     * Act - do whatever the Earth wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    GreenfootImage image;
    
    // The decreased the scale factor is, the sheer size will increase
    public int scaleFactor = 30;
    
    public Celestial() {
        setImage(new GreenfootImage("earth.png"));
        
        image = getImage();
        image.scale(image.getWidth() / this.scaleFactor, image.getHeight() / this.scaleFactor);
        
        setImage(image);
    }
    
    public int getWidth() {
        image = getImage();
        return image.getWidth();
    }
    
    public int getHeight() {
        image = getImage();
        return image.getHeight();
    }
    
    public void act() {}
}
