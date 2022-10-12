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
    
    /**
     * A constructor for the UIElement class.
     * On initialization, it creates an empty asteroidList array,
     * as well as setting the sprite image that will be shown on the window.
     * 
     * @param imageName the name of the image that will be displayed
     * @param onClickEvent whether the UIElement is clickable or not
     * @param scaleFactorX the scale factor in the x-direction
     * @param scaleFactorY the scale factor in the y-direction
     * 
     * @since 1.0
     */
    protected UIElement(String imageName, boolean onClickEvent, int scaleFactorX, int scaleFactorY) {
        this.imageName = imageName;
        this.onClickEvent = onClickEvent;
        
        this.asteroidList = new CopyOnWriteArrayList<Asteroid>();
        
        this.draw(imageName, scaleFactorX, scaleFactorY);
    }
    
    /**
     * Below are the setters that will be used globally throughout the program,
     * allowing interaction between different classes.
     * 
     * @param game the currentWorld instance, in this case the Game
     * 
     * @since 1.0
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
    
    /**
     * This method displays the spite image on the display.
     * 
     * @param imageName the name of the image
     * @param scaleFactorX the scale factor in the X-direction
     * @param scaleFactorY the scale factor in the Y-direction
     * 
     * @since 1.0
     */
    public void draw(String imageName, int scaleFactorX, int scaleFactorY) {
        setImage(new GreenfootImage(imageName));
        
        image = getImage();
        image.scale(image.getWidth() / scaleFactorX, image.getHeight() / scaleFactorY);
        
        setImage(image);
    }
    
    /**
     * This method runs every frame, and it determines individualized custom behaviour
     * for each UIElement in the window. It utilizes the switch-case statement to seperate
     * the actions that each UIElement will trigger.
     * 
     * @since 1.0
     */
    
    @Override
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
                        r.changeSizeOfCelestial(0.7);
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
                        r.changeSizeOfCelestial(1.3);
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
        }
    }
}
