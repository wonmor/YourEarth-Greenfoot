import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Trajectory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Star extends Actor
{
    /**
     * Act - do whatever the Trajectory wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Star(Color c) {
        GreenfootImage image = new GreenfootImage(Constants.sunDefaultWidth, Constants.sunDefaultHeight);
        
        image.setColor(c);
        
        image.drawOval(0, 0, 3, 3);
        image.fillOval(0, 0, 3, 3);
        
        this.setImage(image);
    }
    
    public void act() {}
}
