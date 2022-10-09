import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This is a centralized class that handles all the UI elements on the game screen.
 * It effectively utilizes Java's core functionality: Polymorphism.
 * 
 * Not only that, by creating two methods that accept different types of parameters but share the same name,
 * the program uses Method Overriding design pattern as well.
 * 
 * A guard clause is used instead of complex, nested if statements for syntactic sugar (better readability).
 * 
 * @author John Seong 
 * @version 1.0
 */

public class UIElement extends GameObject {
    private GreenfootImage image;
    
    private CopyOnWriteArrayList<Asteroid> asteroidList;
    
    private Game game = null;
    private Celestial celestial = null;
    private Collider collider = null;
    
    private String imageName;
    
    private boolean onClickEvent;
    
    protected UIElement(String imageName, boolean onClickEvent, int scaleFactorX, int scaleFactorY) {
        this.imageName = imageName;
        this.onClickEvent = onClickEvent;
        
        this.asteroidList = new CopyOnWriteArrayList<Asteroid>();
        
        this.draw(imageName, scaleFactorX, scaleFactorY);
    }
    
    /*
     * Below are the setters
     * that will be used globally
     * throughout the program.
     */
    
    public void setGameInstance(Game game) {
        this.game = game;
    }
    
    public void setAsteroidInstance(CopyOnWriteArrayList<Asteroid> asteroidList) {
        this.asteroidList.addAll(asteroidList);
    }
    
    // Method overriding...
    public void setAsteroidInstance(Asteroid r) {
        this.asteroidList.add(r);
    }
    
    public void setCelestialInstance(Celestial celestial) {
        this.celestial = celestial;
    }
    
    public void setColliderInstance(Collider collider) {
        this.collider = collider;
    }
    
    public void draw(String imageName, int scaleFactorX, int scaleFactorY) {
        setImage(new GreenfootImage(imageName));
        
        image = getImage();
        image.scale(image.getWidth() / scaleFactorX, image.getHeight() / scaleFactorY);
        
        setImage(image);
    }
    
    public void act() {
        boolean clicked = this.onClickEvent && Greenfoot.mouseClicked(this);
    
        
        switch (this.imageName) {
            case "decreaseButton.png":
                if (clicked) {
                    this.draw("decreaseButtonSelected.png", 5, 5);
                    
                    // A guard clause...
                    if (this.celestial == null && this.collider == null) { return; }
                    
                    if (this.asteroidList.isEmpty()) { return; }
                    
                    for (Asteroid r : this.asteroidList) {
                        r.changeSizeOfSun(0.7);
                    }
                    
                    this.celestial.scaleFactor++;
                    this.collider.scaleFactor += 3;
                    
                    this.celestial.draw();
                    this.collider.draw();
                } else {
                    this.draw("decreaseButton.png", 5, 5);
                }
                break;
            case "increaseButton.png":
                if (clicked) {
                    this.draw("increaseButtonSelected.png", 5, 5);
                    
                    // A guard clause...
                    if (this.celestial == null && this.collider == null) { return; }
                    
                    for (Asteroid r : this.asteroidList) {
                        r.changeSizeOfSun(1.3);
                    }
                    
                    this.celestial.scaleFactor--;
                    this.collider.scaleFactor -= 3;
                    
                    this.celestial.draw();
                    this.collider.draw();
                } else {
                    this.draw("increaseButton.png", 5, 5);
                }
                break;
            case "tryAgainButton.png":
                if (clicked) {
                    this.draw("tryAgainButtonSelected.png", 4, 4);
                    
                    for (Asteroid r : this.asteroidList) {
                        r.isPlaying = true;
                        r.setToInitConditions();
                    }
                    
                    Greenfoot.setWorld(new Game());
                } else {
                    this.draw("tryAgainButton.png", 4, 4);
                }
                break;
            case "playButton.png":
                if (clicked) {
                    this.draw("playButtonSelected.png", 4, 4);
                    
                    Greenfoot.setWorld(new Game());
                } else {
                    this.draw("playButton.png", 4, 4);
                }
                break;
            default:
                if (this.game == null) { return; }
                
                // Prevent the UI element overlap dysfunction error...
                this.game.addUIElements();
        }
    }
}
