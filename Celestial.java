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

    private GreenfootImage image;
    
    public int scaleFactor = 30;
    
    public Celestial() {
        this.scaleImage();
    }
    
    public void scaleImage() {
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
    
    public int getCoordX() {
        image = getImage();
        return this.getX() - image.getWidth() / 2;
    }
    
    public int getCoordY() {
        image = getImage();
        return this.getX() - image.getHeight() / 2;
    }
}
