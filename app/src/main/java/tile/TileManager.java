package tile;

import main.GamePanel;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;


public class TileManager {
    
    GamePanel gp;
    public Tile[] tile;
    BufferedImage tileSheet_1; 
    BufferedImage tileSheet_2;
    public int mapTileNum[][];
    




    public TileManager(GamePanel gp){

        this.gp = gp;

        tile = new Tile[49];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/map.txt");

    }

    public Tile[] getTileSet(){
        return tile;
    }
    
  


    public void getTileImage(){
    	
    	

        try{
            tileSheet_1 = ImageIO.read(getClass().getResourceAsStream("/tiles/Tileset-Terrain2.png"));
            tileSheet_2 = ImageIO.read(getClass().getResourceAsStream("/tiles/Tileset_faulty.png"));

            tile[0] = new Tile();
            tile[0].image = tileSheet_1.getSubimage(576, 192, 32, 32); // GRASS TILE 1
            
            tile[1] = new Tile();
            tile[1].image = tileSheet_1.getSubimage(608, 192, 32, 32); // GRASS TILE 2

            tile[2] = new Tile();
            tile[2].image = tileSheet_1.getSubimage(576, 224, 32, 32); // GRASS TILE 3

            tile[3] = new Tile();
            tile[3].image = tileSheet_1.getSubimage(608, 224, 32, 32); // GRASS TILE 4

            tile[4] = new Tile();
            tile[4].image = tileSheet_1.getSubimage(640, 224, 32, 32); // GRASS TILE 5

            tile[5] = new Tile();
            tile[5].image = tileSheet_1.getSubimage(672, 224, 32, 32); // GRASS TILE 6

            tile[6] = new Tile();
            tile[6].image = tileSheet_1.getSubimage(704, 224, 32, 32); // GRASS TILE 7

            tile[7] = new Tile();
            tile[7].image = tileSheet_1.getSubimage(736, 224, 32, 32); // GRASS TILE 8

            tile[8] = new Tile();
            tile[8].image = tileSheet_1.getSubimage(32, 192, 32, 32); // LEFT WALL
            tile[8].collision = true;

            tile[9] = new Tile();
            tile[9].image = tileSheet_1.getSubimage(64, 192, 32, 32); // MIDDLE WALL
            tile[9].collision = true;

            tile[10] = new Tile();
            tile[10].image = tileSheet_1.getSubimage(96, 192, 32, 32); // RIGHT WALL
            tile[10].collision = true;

            tile[11] = new Tile();
            tile[11].image = tileSheet_2.getSubimage(0,0,32,32);

            tile[12] = new Tile();
            tile[12].image = tileSheet_2.getSubimage(32,0,32,32);

            tile[13] = new Tile();
            tile[13].image = tileSheet_2.getSubimage(64,0,32,32);

            tile[14] = new Tile();
            tile[14].image = tileSheet_2.getSubimage(96,0,32,32);

            tile[15] = new Tile();
            tile[15].image = tileSheet_2.getSubimage(128,0,32,32);

            tile[16] = new Tile();
            tile[16].image = tileSheet_2.getSubimage(160,0,32,32);

            tile[17] = new Tile();
            tile[17].image = tileSheet_2.getSubimage(192,0,32,32);

            tile[18] = new Tile();
            tile[18].image = tileSheet_2.getSubimage(224,0,32,32);

            tile[19] = new Tile();
            tile[19].image = tileSheet_2.getSubimage(256,0,32,32);

            tile[20] = new Tile();
            tile[20].image = tileSheet_2.getSubimage(320,0,32,32);

            tile[21] = new Tile();
            tile[21].image = tileSheet_2.getSubimage(352,0,32,32);

            tile[22] = new Tile();
            tile[22].image = tileSheet_2.getSubimage(384,0,32,32);

            tile[23] = new Tile();
            tile[23].image = tileSheet_2.getSubimage(416,0,32,32);

            tile[24] = new Tile();
            tile[24].image = tileSheet_2.getSubimage(448,0,32,32);

            tile[25] = new Tile();
            tile[25].image = tileSheet_2.getSubimage(480,0,32,32);

            tile[26] = new Tile();
            tile[26].image = tileSheet_2.getSubimage(512,0,32,32);

            tile[27] = new Tile();
            tile[27].image = tileSheet_2.getSubimage(544,0,32,32);

            tile[28] = new Tile();
            tile[28].image = tileSheet_2.getSubimage(576,0,32,32);

            tile[29] = new Tile();
            tile[29].image = tileSheet_2.getSubimage(608,0,32,32);

            tile[30] = new Tile();
            tile[30].image = tileSheet_2.getSubimage(0,32,32,32);

            tile[31] = new Tile();
            tile[31].image = tileSheet_2.getSubimage(32,32,32,32);

            tile[32] = new Tile();
            tile[32].image = tileSheet_2.getSubimage(64,32,32,32);

            tile[33] = new Tile();
            tile[33].image = tileSheet_2.getSubimage(96,32,32,32);

            tile[34] = new Tile();
            tile[34].image = tileSheet_2.getSubimage(128,32,32,32);

            tile[35] = new Tile();
            tile[35].image = tileSheet_2.getSubimage(160,32,32,32);

            tile[36] = new Tile();
            tile[36].image = tileSheet_2.getSubimage(192,32,32,32);

            tile[37] = new Tile();
            tile[37].image = tileSheet_2.getSubimage(224,32,32,32);

            tile[38] = new Tile();
            tile[38].image = tileSheet_2.getSubimage(256,32,32,32);

            tile[39] = new Tile();
            tile[39].image = tileSheet_2.getSubimage(320,32,32,32);

            tile[40] = new Tile();
            tile[40].image = tileSheet_2.getSubimage(352,32,32,32);

            tile[41] = new Tile();
            tile[41].image = tileSheet_2.getSubimage(384,32,32,32);

            tile[42] = new Tile();
            tile[42].image = tileSheet_2.getSubimage(416,32,32,32);

            tile[43] = new Tile();
            tile[43].image = tileSheet_2.getSubimage(448,32,32,32);

            tile[44] = new Tile();
            tile[44].image = tileSheet_2.getSubimage(480,32,32,32);

            tile[45] = new Tile();
            tile[45].image = tileSheet_2.getSubimage(512,32,32,32);

            tile[46] = new Tile();
            tile[46].image = tileSheet_2.getSubimage(544,32,32,32);

            tile[47] = new Tile();
            tile[47].image = tileSheet_2.getSubimage(576,32,32,32);

            tile[48] = new Tile();
            tile[48].image = tileSheet_2.getSubimage(608,32,32,32);

            





        }catch(IOException e){
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
                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
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