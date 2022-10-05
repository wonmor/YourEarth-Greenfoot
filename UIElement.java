import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is a centralized class that handles all the UI elements on the game screen...
 * 
 * @author John Seong 
 * @version 1.0
 */

public class UIElement extends GameObject {
    private GreenfootImage image;
    private Asteroid r = null;
    
    private String imageName;
    
    private boolean onClickEvent;
    
    protected UIElement(String imageName, boolean onClickEvent, int scaleFactorX, int scaleFactorY) {
        this.imageName = imageName;
        this.onClickEvent = onClickEvent;
        
        this.draw(imageName, scaleFactorX, scaleFactorY);
    }
    
    public void setAsteroidInstance(Asteroid r) {
        this.r = r;
    }
    
    public void draw(String imageName, int scaleFactorX, int scaleFactorY) {
        setImage(new GreenfootImage(imageName));
        
        image = getImage();
        image.scale(image.getWidth() / scaleFactorX, image.getHeight() / scaleFactorY);
        
        setImage(image);
    }
    
    public void act() {
        boolean clicked = this.onClickEvent && Greenfoot.mouseClicked(this);
        
        // A guard clause to implement a singleton design pattern...
        if (this.r == null && this.imageName != "playButton.png") { return; }
        
        switch (this.imageName) {
            case "decreaseButton.png":
                if (clicked) {
                    this.r.changeSizeOfSun(0.7);
                    this.draw("decreaseButtonSelected.png", 5, 5);
                } else {
                    this.draw("decreaseButton.png", 5, 5);
                }
                break;
            case "increaseButton.png":
                if (clicked) {
                    this.r.changeSizeOfSun(1.3);
                    this.draw("increaseButtonSelected.png", 5, 5);
                } else {
                    this.draw("increaseButton.png", 5, 5);
                }
                break;
            case "tryAgainButton.png":
                if (clicked) {
                    this.draw("tryAgainButtonSelected.png", 4, 4);
                    
                    this.r.isPlaying = true;
                    this.r.setToInitConditions();
                    
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
