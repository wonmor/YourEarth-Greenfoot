import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Asteroid class contains the core functionality of this game,
 * which includes its realistic and accurate modelling of planetary motion.
 * 
 * The equation itself was derived by using the Lagrangian (kinetic energy minus potential energy
 * in a two-body system).
 * 
 * Here is a really nice video on YouTube that explains why Lagrangian Mechanics is superior to its
 * Newtonian Equivalent in some situations:
 * https://www.youtube.com/watch?v=KpLno70oYHE
 * 
 * Newton's famous Universal Gravitational law (Gmm/r^2 with the sign flipped to negative) 
 * was used to calculate the potential energy.
 * 
 * CHECK OUT A VIDEO I FILMED ABOUT THIS:
 * https://www.youtube.com/watch?v=AnD9-1YCGdg
 * 
 * Basic understanding of differential calculus was required to derive these equations, although they weren't 
 * particularly too hard, luckily enough. Was just perfect for me who has been taking Grade 12 Calculus and Vectors
 * and took the AP Calculus BC exam last year.
 * 
 * In this case, I used the numerical differentation method that I learned in AP Cal. (tangent approximation method
 * or in other words the "Euler's method" of getting the derivative) to get the velocity from the position-time system.
 * 
 * Last but not at least, the last AP Cal. knowledge that was useful was the polar coordinates.
 * This actually appears in Sal Khan's Pre-Calculus course as well; it basically expresses the existing Cartesian coordinates
 * in terms of the angle and the radius between the point and the origin (0, 0).
 * 
 * All calculations were done in terms of polar coordinates and converted at the last minute upon splashing them onto canvas.
 * This was done by utilizing Trigonmetry that I learned in Gr. 10 and 11 Math.
 * 
 * @author John Seong
 * @version 1.0
 */

public class Asteroid extends GameObject {   
    private double massOfTheSunKg = Constants.massOfTheSunKg;
    
    private double distance = 0.0;
    private double distanceSecondDeriv = 0.0;
    
    private double angle = 0.0;
    private double angleSecondDeriv = 0.0;
    
    private Color[] colorArray = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN};
    
    private Color traceColor;
    
    public boolean isPlaying;
    
    private Celestial c;
    private Collider celestialCollider;
    private Game w;
    private YouLostMessage lm;
    private TryAgainButton tb;
    
    private Levels lvls = null;
    
    private Scoreboard scoreboard;
    
    private GreenfootImage image;
    
    public Asteroid(Game w, Celestial c, Collider celestialCollider) {
        this.w = w;
        this.c = c;
        this.celestialCollider = celestialCollider;
        
        this.isPlaying = true;
        
        this.setToInitConditions();
        this.updatePosition(Constants.deltaT);
        
        int[] coords = this.getCartesianCoords(this.distance, this.angle);
        
        this.setLocation(coords[0], coords[1]);
        this.setImage(new GreenfootImage("asteroid.png"));
        
        this.image = getImage();
        this.image.scale(image.getWidth() / 10, image.getHeight() / 10);
        
        this.traceColor = this.colorArray[Game.getRandomNumber(0, this.colorArray.length - 1)];
    }
    
    public void setLevelsInstance(Levels lvls) {
        this.lvls = lvls;
    }
    
    public void setToInitConditions() {
        this.distance = Constants.earthSunDistanceMeters;
        this.distanceSecondDeriv = 0.00;
        
        this.angle = Math.PI / 6 * Game.getRandomNumber(2, 10);
        this.angleSecondDeriv = Constants.earthAngularVelocityMetersPerSecond * Game.getRandomNumber(1.1, 1.35);
    }
    
    private double calculateDistanceAcceleration(double distance, double angleSecondDeriv, double massOfTheSunKg) {
        return distance * Math.pow(angleSecondDeriv, 2) - (Constants.gravitationalConstant * massOfTheSunKg) / Math.pow(distance, 2);
    }
    
    private double calculateAngleAcceleration(double distanceSecondDeriv, double angleSecondDeriv, double distance) {
        return -2.0 * distanceSecondDeriv * angleSecondDeriv / distance;
    }
    
    // Predicting the derivative graph using Euler's Method...
    private double performEulersMethod(double currentValue, double deltaT, double derivative) {
        return currentValue + deltaT * derivative; // y = ax + b form
    }
    
    public void changeSizeOfSun(double solarMassMultiplier) {
        // A guard clause...
        if (solarMassMultiplier == 1) { return; }
        
        this.massOfTheSunKg *= solarMassMultiplier;
    }
    
    private void updatePosition(double deltaT) {
        double distanceAcceleration = calculateDistanceAcceleration(this.distance, this.angleSecondDeriv, this.massOfTheSunKg);
        
        this.distanceSecondDeriv = performEulersMethod(this.distanceSecondDeriv, deltaT, distanceAcceleration);
        this.distance = performEulersMethod(this.distance, deltaT, this.distanceSecondDeriv);
        
        double angleAcceleration = calculateAngleAcceleration(this.distanceSecondDeriv, this.angleSecondDeriv, this.distance);
        
        this.angleSecondDeriv = performEulersMethod(this.angleSecondDeriv, deltaT, angleAcceleration);
        this.angle = performEulersMethod(this.angle, deltaT, this.angleSecondDeriv);
        
        if (this.angle > 2 * Math.PI) {
            this.angle = this.angle % (2 * Math.PI);
        }
    }
    
    // Convert polar coordinates to the cartesian equivalent...
   private int[] getCartesianCoords(double distance, double angle) {
        int x = (int)Math.round(Math.cos(angle) * distance + Constants.screenWidth / 2 + 35);
        int y = (int)Math.round(Math.sin(angle * -1) * distance + Constants.screenHeight / 2 + 35);
        
        Game.logCoords(x, y);
        
        int[] coords = new int[]{x, y};
        
        return coords;
    }
    
    public void act() {
        // Don't render after the failure message appeared...
        if (this.isPlaying) {            
            for (int i = 0; i < Constants.numberOfCalculationsPerFrame; i++) {
                updatePosition(Constants.deltaT);
            }
        }
        
        int[] coords = getCartesianCoords(this.distance / Constants.scaleFactor, this.angle);
        
        boolean xCoordInBound = coords[0] <= Constants.screenWidth && coords[0] > 0;
        boolean yCoordInBound = coords[1] <= Constants.screenHeight && coords[1] > 0;
        
        
        boolean isCrash = this.isTouching(this.celestialCollider.getClass());

        if (xCoordInBound && yCoordInBound && !isCrash) {
            this.isPlaying = true;
            
            Star s = new Star(Color.RED);
            this.w.addObject(s, coords[0], coords[1]);
            
            setLocation(coords[0] - image.getWidth() / 2, coords[1] - image.getHeight() / 2);
        } else {
            this.isPlaying = false;
            
            this.w.removeObjects(this.w.getObjects(Asteroid.class));
            
            this.lm = new YouLostMessage(this);
            this.w.addObject(this.lm, Constants.screenWidth / 2, Constants.screenHeight / 2);
            
            this.tb = new TryAgainButton(this);
            this.w.addObject(this.tb, Constants.screenWidth / 2, Constants.screenHeight / 2 + 50);
            
            if (this.lvls == null) { return; }
            
            this.scoreboard = new Scoreboard(this.w);
            this.scoreboard.draw("LEVEL " + this.lvls.currentLevel, true);
        }
    }
}
