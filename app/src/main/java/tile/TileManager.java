package tile;

import main.GamePanel;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;


public class TileManager {
    
    GamePanel gp;
    //public Tile[] tile;
    BufferedImage tileSheet_1; 
    BufferedImage tileSheet_2;
    public int mapTileNum[][];
    public ArrayList<Tile> tile;
    String fileDirProperties = "C:\\Users\\tolga\\onedrive\\dokument\\github\\2d-java-rpg\\app\\src\\main\\resources\\tilesproperties";
    String fileDir = "C:\\Users\\tolga\\onedrive\\dokument\\github\\2d-java-rpg\\app\\src\\main\\resources\\tiles";
    int imageCnt = 0;
    
    //default tile sizes
    static int tileWidth = 32;
    static int tileHeight = 32;

    




    public TileManager(GamePanel gp){

        this.gp = gp;

        //tile = new Tile[49];
        tile = new ArrayList<>();
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/map4.txt");

    }

    public Tile[] getTileSet(){
        //Tile[] tileArr = new Tile[tile.size()];
        Tile[] tileArr = tile.toArray(new Tile[0]);
        return tileArr;
    }
    
  


    public void getTileImage(){
    	
    	

        try{
            tileSheet_1 = ImageIO.read(getClass().getResourceAsStream("/tilesheets/Tileset-Terrain2.png"));
            tileSheet_2 = ImageIO.read(getClass().getResourceAsStream("/tilesheets/Tileset_faulty.png"));

            tileSheetFormatter(tileSheet_1);
            tileSheetFormatter(tileSheet_2);
            
        }catch(IOException e){
            e.printStackTrace();
        }

    }


    private void tileSheetFormatter(BufferedImage tileSheet){
      

        try{
                int rows = tileSheet.getHeight()/tileHeight;
                int cols = tileSheet.getWidth()/tileWidth;
                

                for(int tileCol = 0 ; tileCol < cols ; tileCol++){
                    for(int tileRow = 0 ; tileRow < rows ; tileRow++){

                        
                        
                        int x = tileCol*tileWidth;
                        int y = tileRow*tileHeight;
                        
                        Tile initTile = new Tile();
                        initTile.image = tileSheet.getSubimage(x, y, tileWidth, tileHeight);
                        if(isTileFullyTransparent(initTile.image)){
                            continue; //skip to next tile
                        }


                        File fileProperties = new File(fileDirProperties, this.imageCnt + "_tile_" + x + "_" + y + ".properties");
                        File fileTile = new File(fileDir, this.imageCnt + "_tile_" + x + "_" + y + ".png");
                        initTile.arrayIndex = this.imageCnt;

                        
                        

                        
                        // Save properties file
                        if(!fileProperties.exists()){ //skip if it already exists

                            Properties tileProperties = new Properties();
                            tileProperties.setProperty("collision", String.valueOf(initTile.collision));
                            tileProperties.setProperty("arrayIndex", String.valueOf(initTile.arrayIndex));
                            

                            try(FileOutputStream outProp = new FileOutputStream(fileProperties)){
                                tileProperties.store(outProp, "Tile Properties");
                            }catch(IOException e){
                                e.printStackTrace();
                            }
                        }


                        // Save .png file
                        if(!fileTile.exists()){ //skip if it already exists
                            
                            try {
                                ImageIO.write(initTile.image, "PNG", fileTile);
                            } catch (IOException e) {
                                System.err.println("Error saving image: " + e.getMessage());
                            }
                        }
                      
                        this.imageCnt++;

                        if(fileProperties.exists() && fileTile.exists()){

                            initTile.loadProperties(x,y,"src/main/resources/tilesproperties");
                            tile.add(initTile); 
                        }
                    }
                }
       

        }catch(Error e){
            e.printStackTrace();
        }


    }

    private boolean isTileFullyTransparent(BufferedImage image){

        //if image is invalid ignore it
        if(image == null || image.getWidth() != tileWidth || image.getHeight() != tileHeight){
            return false;
        }

        for(int x = 0 ; x < tileWidth ; x++){
            for(int y = 0 ; y < tileHeight ; y++){
                int pixel = image.getRGB(x,y);

                /*
                 * ARGB color model (32 bit integer color representation).
                 * Each part (A, R, G, B) are 8 bits.
                 * Last 8 bits rep A, Alpha. If A = 0 -> fully transparent 
                 */
                int alpha = (pixel >> 24) & 0xFF; //Copies alpha part
                
                if(alpha != 0){
                    return false;
                }
            }
        }
        
        return true;
    }


    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldCol){

                String line = br.readLine();

                while (col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close(); // After we're done reading, close the buffer

        }catch(Exception e){

        }
    }


    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;
 

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            
            int tileNum = mapTileNum[worldCol][worldRow];

            //player position in world
            int worldX = worldCol*gp.tileSize;
            int worldY = worldRow*gp.tileSize;

            //screen border positions for drawing distance
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            //to not draw tiles that are not visible by player
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                    g2.drawImage(tile.get(tileNum).image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;
           

            if (worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }

        // g2.drawImage(tile[8].image, 0, 0, gp.tileSize, gp.tileSize, null);
        // g2.drawImage(tile[9].image, gp.tileSize, gp.tileSize, gp.tileSize, gp.tileSize, null);
        // g2.drawImage(tile[10].image, gp.tileSize*2, gp.tileSize, gp.tileSize, gp.tileSize, null);
        // g2.drawImage(tile[3].image, gp.tileSize*3, 0, gp.tileSize, gp.tileSize, null);
        // g2.drawImage(tile[4].image, gp.tileSize*4, 0, gp.tileSize, gp.tileSize, null);
        // g2.drawImage(tile[5].image, gp.tileSize*5, 0, gp.tileSize, gp.tileSize, null);


    }




}