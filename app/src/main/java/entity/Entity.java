package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    
    public int worldX, worldY;
    public double speed;



    public BufferedImage idleup1, idleup2, idleup3, idleup4, idledown1, idledown2, idledown3, idledown4, idleleft1, idleleft2, idleleft3, idleleft4, idleright1, idleright2, idleright3, idleright4,
    up1, up2, up3, up4, down1, down2, down3, down4, runleft1, runleft2, runleft3, runleft4, runright1, runright2, runright3, runright4, walkleft1, walkleft2, walkleft3, walkleft4, walkright1, walkright2, walkright3, walkright4;
    public String direction, lastHorizontalDirection, motionState;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public boolean collisionOn = false;
}

