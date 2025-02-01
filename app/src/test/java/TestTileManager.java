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
            assertTrue(tm.tile[i] != null);
        }

    } 


}
