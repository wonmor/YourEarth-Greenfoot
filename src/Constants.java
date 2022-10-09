import java.util.Map;
import java.util.HashMap;

/**
 * Write a description of class Constants here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Constants  
{
    public static final int screenWidth = 600;
    public static final int screenHeight = 600;
    
    public static final int sunDefaultWidth = 40;
    public static final int sunDefaultHeight = 40;
    
    public static final int containerWidth = 500;
    public static final int containerHeight = 30;

    public static final double gravitationalConstant = 6.67408 * Math.pow(10, -11);
    public static final double massOfTheSunKg = 1.98855 * Math.pow(10, 30);
    
    public static final double earthSunDistanceMeters = 1.496 * Math.pow(10, 11);
    public static final double earthAngularVelocityMetersPerSecond = 1.990986 *  Math.pow(10, -7);
    
    public static final double pixelsInOneEarthSunDistancePerPixel = 80;
    public static final double scaleFactor = earthSunDistanceMeters / pixelsInOneEarthSunDistancePerPixel;

    public static final double numberOfCalculationsPerFrame = 1000;
    public static final double deltaT = 3600 * 24 / Constants.numberOfCalculationsPerFrame;
}
