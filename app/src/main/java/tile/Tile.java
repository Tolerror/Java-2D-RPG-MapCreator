package tile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Tile{

    public BufferedImage image;
    public boolean collision = false;
    public String filePath;
    public int arrayIndex;



    public void loadProperties(int x, int y, String filepath) {
        // Define the path to the properties file based on the tile's x and y
        File fileProperties = new File(filepath, arrayIndex + "_tile_" + x + "_" + y + ".properties");

        // Use 'try-with-resources' to automatically close the FileInputStream
        try (FileInputStream inProp = new FileInputStream(fileProperties)) {
            Properties tileProperties = new Properties();
            // Load the properties file
            tileProperties.load(inProp);

            // Set tile attributes from properties file
            if (tileProperties.containsKey("collision")) {
                this.collision = Boolean.parseBoolean(tileProperties.getProperty("collision"));
            }
            if (tileProperties.containsKey("filePath")) {
                this.filePath = tileProperties.getProperty("filePath");
            }
            if (tileProperties.containsKey("arrayIndex")){
                this.arrayIndex = Integer.parseInt(tileProperties.getProperty("arrayIndex"));
        
            }


        } catch (IOException e) {
            System.err.println("Error loading properties for tile (" + arrayIndex +"_" + x + ", " + y + "): " + e.getMessage());
        }
    }








}