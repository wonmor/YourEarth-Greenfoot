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
    
    /**
     * A constructor for the Scoreboard class.
     * On initialization, it sets the game instance variable
     * as well as starting the fadeOutTimer, for calculating deltaT.
     * 
     * @param game the game instance
     * 
     * @since 1.0
     */
    public Scoreboard(Game game) {
        this.game = game;
        
        super.startFadeOutTimer();
    }
    
    /**
     * Prints out the score on the window.
     * whetherFinal determines whether the game is paused due to asteroid being out of bounds or not.
     * 
     * @param score the raw string that contains the level that the user has achieved during the game.
     * @param whetherFinal determines whether the user has failed to meet the requirements for this game to continue levelling up: all the asteroid being in bounds.
     * 
     * @since 1.0
     */
    public void draw(String score, boolean whetherFinal) {
        GreenfootImage img = new GreenfootImage(500, 100);
        
        this.whetherFinal = whetherFinal;
        
        img.setColor(Color.WHITE);
        img.fill();
        img.setColor(Color.BLACK);
        
        img.setFont(new Font("Arial", false, false, !whetherFinal ? 100 : 50));
        img.drawString(!whetherFinal ? ("  " + score) : ("      MAX " + score), 10, !whetherFinal ? 90 : 70);
        
        setImage(img);
        
        this.game.addObject(this, Constants.screenWidth / 2, Constants.screenHeight / 5);
    }
    
    /**
     * This method runs every frame, and it determines whether the user has failed the game or not;
     * if not, the score message will disappear after 3 seconds upon displaying;
     * else, it will stay there permanently, displaying the top score user has accomplished.
     * 
     * @since 1.0
     */
    public void act() {
        if (!this.whetherFinal) { super.runFadeOut(this.game, this.getClass()); }
    }
}