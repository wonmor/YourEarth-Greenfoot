import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is a centralized class that handles all the UI elements on the game screen...
 * 
 * @author John Seong 
 * @version 1.0
 */

public class UIElement extends Actor {
    GreenfootImage image;
    Asteroid r;
    
    String imageName;
    
    boolean onClickEvent;
    
    public UIElement(Asteroid r, String imageName, boolean onClickEvent, int scaleFactor) {
        this.r = r;
        this.imageName = imageName;
        this.onClickEvent = onClickEvent;
        
        this.draw(r, imageName, scaleFactor);
    }
    
    public void draw(Asteroid r, String imageName, int scaleFactor) {
        setImage(new GreenfootImage(imageName));
        
        image = getImage();
        image.scale(image.getWidth() / scaleFactor, image.getHeight() / scaleFactor);
        
        setImage(image);
    }
    
    public void act() {
        boolean clicked = this.onClickEvent && Greenfoot.mouseClicked(this);
        
        switch (this.imageName) {
            case "decreaseButton.png":
                if (clicked) {
                    r.changeSizeOfSun(0.7);
                    this.draw(r, "decreaseButtonSelected.png", 5);
                } else {
                    this.draw(r, "decreaseButton.png", 5);
                }
                break;
            case "increaseButton.png":
                if (clicked) {
                    r.changeSizeOfSun(1.3);
                    this.draw(r, "increaseButtonSelected.png", 5);
                } else {
                    this.draw(r, "increaseButton.png", 5);
                }
                break;
        }
    }
}
