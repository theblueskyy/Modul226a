package business;

import java.io.IOException;
import java.util.Properties;

import presentation.App;


public class PropertiesManager 
{

    public static Properties mConfig = new Properties();

    static
    {
        try 
        {
            mConfig.load(App.class.getResourceAsStream("resources/config.properties"));
        } 
        catch (IOException ex) 
        {
            System.out.println("Could not find config.properties");
        }
    }

    public static int getTileWidth()
    {
        return Integer.parseInt(mConfig.get("TILE_WIDTH").toString());
    }
    
    public static int getTileHeight()
    {
        return Integer.parseInt(mConfig.get("TILE_WIDTH").toString());
    }
    
    public static String getBackgroundColor()
    {
        return mConfig.getProperty("SQUARE_BACKGROUD_COLOR");
    }

    public static String getMineColor() 
    {
        return mConfig.getProperty("MINED_BACKGROUD_COLOR");
    }
}

