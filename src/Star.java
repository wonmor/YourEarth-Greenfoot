import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is a class that defines the object that composes the trajectory of the asteroid
 * AND the background decors (the randomized stars in the background).
 * 
 * @author John Seong
 * @version 1.0
 */
public class Star extends GameObject {
    /**
     * A constructor for the Star class.
     * On initialization, it sets the image sprite and displays it on the main window.
     * The colour of the star is randomized based upon the available options.
     * 
     * @param c the colour that the star is going to be
     * 
     * @since 1.0
     */
    public Star(Color c) {
        GreenfootImage image = new GreenfootImage(Constants.sunDefaultWidth, Constants.sunDefaultHeight);
        
        image.setColor(c);
        
        image.drawOval(0, 0, 3, 3);
        image.fillOval(0, 0, 3, 3);
        
        this.setImage(image);
    }
}
