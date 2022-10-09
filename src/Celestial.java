import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Celestial class stores the properties of the main planet (Earth),
 * including the scale factor between the raw value (in astronomical units) and
 * the game Earth-Asteroid distance.
 * 
 * @author John Seong 
 * @version 1.0
 */
public class Celestial extends GameObject
{
    /**
     * Act - do whatever the Earth wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    private GreenfootImage image;
    private Game game = null;
    
    public int scaleFactor = 30;
    
    public Celestial() {    
        this.draw();
    }
    
    public void setGame(Game game) {
        this.game = game;
    }
    
    public void draw() {
        setImage(new GreenfootImage("earth.png"));
        
        image = getImage();
        image.scale(image.getWidth() / this.scaleFactor, image.getHeight() / this.scaleFactor);
        
        setImage(image);
    }
    
    public void act() {
        if (this.game == null) { return; }
        
        this.game.levels.run();
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
