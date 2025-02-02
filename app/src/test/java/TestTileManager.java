import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.GamePanel;
import tile.Tile;
import tile.TileManager;

public class TestTileManager {
    
    private GamePanel gp;
    private TileManager tm;
    
    
    @BeforeEach
    public void createTileManger(){
        this.gp = new GamePanel();
        this.tm = new TileManager(gp);
    }

    @Test
    public void testgetTileImage(){
        for(int i = 0 ; i < tm.tile.length ; i++){
            assertTrue(tm.tile[i] != null && tm.tile[i] instanceof Tile);
            
        }
        
    } 

    @Test
    public void testLoadMap(){
        tm.loadMap("/maps/map.txt");
        assertTrue(tm.mapTileNum.length == 50 && tm.mapTileNum[0].length == 50);

        for(int i = 0 ; i < tm.mapTileNum.length ; i++){
            for(int j = 0 ; j < tm.mapTileNum[0].length ; j++){

                assertTrue(tm.mapTileNum[i][j] >= 0);
            }
        }   

    }




}
