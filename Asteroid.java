import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.time.Instant;
import java.time.Duration;
import java.time.temporal.ChronoUnit;  

/**
 * Write a description of class Rocket here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Asteroid extends Actor
{   
    private double massOfTheSunKg = Constants.massOfTheSunKg;
    
    private double distance = 0.0;
    private double distanceSecondDeriv = 0.0;
    
    private double angle = 0.0;
    private double angleSecondDeriv = 0.0;
    
    private Celestial c;
    private MyWorld w;
    private YouLostMessage lm;
    
    private GreenfootImage image;
    
    public Asteroid(MyWorld w, Celestial c) {
        this.w = w;
        this.c = c;
        
        setToInitConditions();
        updatePosition(Constants.deltaT);
        
        int[] coords = getCartesianCoords(this.distance, this.angle);
        setLocation(coords[0], coords[1]);
        
        setImage(new GreenfootImage("asteroid.png"));
        
        image = getImage();
        image.scale(image.getWidth() / 10, image.getHeight() / 10);
    }
    
    private void setToInitConditions() {
        this.distance = Constants.earthSunDistanceMeters;
        this.distanceSecondDeriv = 0.00;
        
        this.angle = Math.PI / 6 * MyWorld.getRandomNumber(2, 10);
        this.angleSecondDeriv = Constants.earthAngularVelocityMetersPerSecond * 1.5;
    }
    
    public static double calculateDistanceAcceleration(double distance, double angleSecondDeriv, double massOfTheSunKg) {
        return distance * Math.pow(angleSecondDeriv, 2) - (Constants.gravitationalConstant * massOfTheSunKg) / Math.pow(distance, 2);
    }
    
    public static double calculateAngleAcceleration(double distanceSecondDeriv, double angleSecondDeriv, double distance) {
        return -2.0 * distanceSecondDeriv * angleSecondDeriv / distance;
    }
    
    // Predicting the derivative graph using Euler's Method...
    public static double performEulersMethod(double currentValue, double deltaT, double derivative) {
        return currentValue + deltaT * derivative; // y = ax + b form
    }
    
    public void changeSizeOfSun(double solarMassMultiplier) {
        this.massOfTheSunKg *= solarMassMultiplier;
        this.c.scaleFactor *= solarMassMultiplier;
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
        
        System.out.println("x: " + x);
        System.out.println("y: " + y);
        
        int[] coords = new int[]{x, y};
        
        return coords;
    }
    
    public void act() {
        for (int i = 0; i < Constants.numberOfCalculationsPerFrame; i++) {
            updatePosition(Constants.deltaT);
        }
        
        int[] coords = getCartesianCoords(this.distance / Constants.scaleFactor, this.angle);
        
        if (coords[0] <= Constants.screenWidth && coords[1] <= Constants.screenHeight) {
            Star s = new Star(Color.RED);
            this.w.addObject(s, coords[0], coords[1]);
            
            setLocation(coords[0] - image.getWidth() / 2, coords[1] - image.getHeight() / 2);
        } else {
            lm = new YouLostMessage(this);
            w.addObject(lm, Constants.screenWidth / 2, Constants.screenHeight / 2);
        }
    }
}
