import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class UIElement here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UIElement extends Actor
{
    GreenfootImage image;
    Asteroid r;
    
    String imageName;
    boolean onClickEvent;
    
    public UIElement(Asteroid r, String imageName, boolean onClickEvent, int scaleFactor) {
        this.r = r;
        this.imageName = imageName;
        this.onClickEvent = onClickEvent;
        
        setImage(new GreenfootImage(imageName));
        
        image = getImage();
        image.scale(image.getWidth() / scaleFactor, image.getHeight() / scaleFactor);
        
        setImage(image);
    }
    
    public void act()
    {
        if (this.onClickEvent && Greenfoot.mouseClicked(this)) {
            switch (this.imageName) {
                case "decreaseButton.png":
                    r.changeSizeOfSun(0.9);
                    break;
                case "increaseButton.png":
                    r.changeSizeOfSun(1.1);
                    break;
            }
        }
    }
}
