import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Leveles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Levels extends Actor
{
    /**
     * Act - do whatever the Leveles wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public int currentLevel = 1;
    
    public Asteroid r;
    
    private Celestial c;
    private Game game;
    private Collider collider;
    
    public Levels(Game game, Celestial c, Collider collider) {
        this.game = game;
        this.c = c;
        this.collider = collider;
        
        this.change();
    }
    
    public void change() {
        for (int i = 0; i < 1; i++) {
            this.r = new Asteroid(this.game, this.c, this.collider);
            this.game.addObject(this.r, 50, 50);
        }
    }
}
