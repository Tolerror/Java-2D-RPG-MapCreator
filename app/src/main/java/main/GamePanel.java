package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import entity.Player;
import tile.TileManager;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 32; // 32x32 tile 
    final int scale = 2;

    public final int tileSize = originalTileSize*scale; // 64x64 tile size (upscaled)
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize*maxWorldCol;
	public final int worldHeight = tileSize*maxWorldRow;


    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    KeyHandler keyH = new KeyHandler();
    public Player player = new Player(this, keyH);

    
    //public gamepanel for MapCreator to access. GamePanel should be private!
    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // to enhance performance
        this.addKeyListener(keyH); // panel recognizes key presses
        this.setFocusable(true);
    }


    public void startGameThread(){
        gameThread = new Thread(this); // pass this GamePanel to Thread
        gameThread.start();

    }





    public void update(){

        player.update();
       
    }




    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g; // cast to Graphics2D for more functionality

        tileM.draw(g2); // Tiles to be drawn before player, for player to overlap

        player.draw(g2);

        g2.dispose();

    }




    @Override
    public void run() {
		  
        double drawInterval = 1000000000/FPS; // amount of nanoseconds to draw screen FPS times per second
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        long timer = 0;
        int drawCount = 0;
        
        while(gameThread != null) {
            
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
            if(delta >= 1) {

                update();
                repaint(); // linked to paintComponent

                delta--;
                drawCount++;
            }
            
            if(timer >= 1000000000) { // print fps status every second
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
            
        }
        
    }
   




}
   