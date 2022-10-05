import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Collider here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Collider extends GameObject
{
    /**
     * Act - do whatever the Collider wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public int scaleFactor = 0;
    
    public Collider() {
        this.draw();
    }
    
    public void draw() {
        GreenfootImage image = new GreenfootImage(Constants.sunDefaultWidth - 15 - this.scaleFactor, Constants.sunDefaultHeight - 15 - this.scaleFactor);
        
        this.setImage(image);
    }
}
