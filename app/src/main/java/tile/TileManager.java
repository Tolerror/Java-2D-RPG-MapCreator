package tile;

import main.GamePanel;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.BufferedReader;


public class TileManager {
    
    GamePanel gp;
    //public Tile[] tile;
    BufferedImage tileSheet_1; 
    BufferedImage tileSheet_2;
    public int mapTileNum[][];
    public ArrayList<Tile> tile;

    //default tile sizes
    static int tileWidth = 32;
    static int tileHeight = 32;

    




    public TileManager(GamePanel gp){

        this.gp = gp;

        //tile = new Tile[49];
        tile = new ArrayList<>();
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/map.txt");

    }

    public Tile[] getTileSet(){
        //Tile[] tileArr = new Tile[tile.size()];
        Tile[] tileArr = tile.toArray(new Tile[0]);
        return tileArr;
    }
    
  


    public void getTileImage(){
    	
    	

        try{
            tileSheet_1 = ImageIO.read(getClass().getResourceAsStream("/tiles/Tileset-Terrain2.png"));
            tileSheet_2 = ImageIO.read(getClass().getResourceAsStream("/tiles/Tileset_faulty.png"));

            tileSheetFormatter(tileSheet_1);
            




        }catch(IOException e){
            e.printStackTrace();
        }

    }

    private void tileSheetFormatter(BufferedImage tileSheet){
        int imageCnt = 0;
        try{
                int rows = tileSheet.getHeight()/tileHeight;
                int cols = tileSheet.getWidth()/tileWidth;

                for(int tileCol = 0 ; tileCol < cols ; tileCol++){
                    for(int tileRow = 0 ; tileRow < rows ; tileRow++){

                        //testing purposes
                        if(imageCnt == 50) return;
                        
                        int x = tileCol*tileWidth;
                        int y = tileRow*tileHeight;

                        Tile initTile = new Tile();
                        initTile.image = tileSheet.getSubimage(x, y, tileWidth, tileHeight);
                        tile.add(initTile); 

                         imageCnt++;

                    }
                }

        }catch(Error e){
            e.printStackTrace();
        }


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