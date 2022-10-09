import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is a class of an UI element that displays the current level of the user.
 * It does not extend the UIElement class, however, due to drastically differing behaviour
 * from other on-screen elements.
 * 
 * @author John Seong
 * @version 1.0
 */
public class Scoreboard extends GameObject {
    private Game game;
    private boolean whetherFinal;
    
    public Scoreboard(Game game) {
        this.game = game;
        
        super.startFadeOutTimer();
    }
    
    public void draw(String score, boolean whetherFinal) {
        GreenfootImage img = new GreenfootImage(500, 100);
        
        this.whetherFinal = whetherFinal;
        
        img.setColor(Color.WHITE);
        img.fill();
        img.setColor(Color.BLACK);
        
        img.setFont(new Font("Arial", false, false, !whetherFinal ? 100 : 50));
        img.drawString(!whetherFinal ? ("  " + score) : ("      TOP: " + score), 10, !whetherFinal ? 90 : 70);
        
        setImage(img);
        
        this.game.addObject(this, Constants.screenWidth / 2, Constants.screenHeight / 5);
    }
    
    public void act() {
        if (!this.whetherFinal) { super.runFadeOut(this.game, this.getClass()); }
    }
}