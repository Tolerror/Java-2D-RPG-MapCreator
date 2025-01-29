package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import main.KeyHandler;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;
import javax.imageio.ImageIO;


public class Player extends Entity {
    
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;

    public BufferedImage spriteSheetidleLeft, spriteSheetidleRight, spriteSheetRunLeft, spriteSheetRunRight, spriteSheetWalkLeft, spriteSheetWalkRight;
    int spriteHeight = 32;
    int spriteWidth = 32;
    double speedConst = 0.8;


    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 -(gp.tileSize/2);
        screenY = gp.screenHeight/2 -(gp.tileSize/2);

        //CURRENT COLLISIONBOX SETTINGS
        //16 pixels from the sides
        //48 pixels down
        //32 pixel width on block
        //14 pixel height on block
        solidArea = new Rectangle(16, 48, 32, 14); //size of collision box on player (x, y, width, height) 

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){

        worldX = gp.tileSize*23;
        worldY = gp.tileSize*21;
        speed = 4;
        direction = "right";
        motionState = "idle";
        
    }


    

    public void getPlayerImage(){


        try{
            // up1 = ImageIO.read(getClass().getResourceAsStream("/player/CATSPRITESHEET_Gray.png"));
            spriteSheetidleLeft = ImageIO.read(getClass().getResourceAsStream("/player/Dude_Monster_Idleleft_4.png"));
            spriteSheetidleRight = ImageIO.read(getClass().getResourceAsStream("/player/Dude_Monster_Idleright_4.png"));

            spriteSheetRunLeft = ImageIO.read(getClass().getResourceAsStream("/player/Dude_Monster_Runleft_6.png"));
            spriteSheetRunRight = ImageIO.read(getClass().getResourceAsStream("/player/Dude_Monster_Runright_6.png"));

            spriteSheetWalkLeft = ImageIO.read(getClass().getResourceAsStream("/player/Dude_Monster_WalkLeft_6.png"));
            spriteSheetWalkRight = ImageIO.read(getClass().getResourceAsStream("/player/Dude_Monster_WalkRight_6.png"));
            

            // spriteSheetrun = ImageIO.read(getClass().getResourceAsStream("/player/CATSPRITESHEET_Gray.png"));
            
        


        idleleft1 = spriteSheetidleLeft.getSubimage(0,0, spriteWidth,spriteHeight);
        idleleft2 = spriteSheetidleLeft.getSubimage(32, 0, spriteWidth, spriteHeight);
        idleleft3 = spriteSheetidleLeft.getSubimage(64,0, spriteWidth,spriteHeight);
        idleleft4 = spriteSheetidleLeft.getSubimage(96,0, spriteWidth,spriteHeight);

        idleright1 = spriteSheetidleRight.getSubimage(0, 0, spriteWidth, spriteHeight);
        idleright2 = spriteSheetidleRight.getSubimage(32, 0, spriteWidth, spriteHeight);
        idleright3 = spriteSheetidleRight.getSubimage(64, 0, spriteWidth, spriteHeight);
        idleright4 = spriteSheetidleRight.getSubimage(96, 0, spriteWidth, spriteHeight);

        walkright1 = spriteSheetWalkRight.getSubimage(0, 0, spriteWidth, spriteHeight);
        walkright2 = spriteSheetWalkRight.getSubimage(32, 0, spriteWidth, spriteHeight);
        walkright3 = spriteSheetWalkRight.getSubimage(64, 0, spriteWidth, spriteHeight);
        walkright4 = spriteSheetWalkRight.getSubimage(96, 0, spriteWidth, spriteHeight);

        walkleft1 = spriteSheetWalkLeft.getSubimage(0, 0, spriteWidth, spriteHeight);
        walkleft2 = spriteSheetWalkLeft.getSubimage(32, 0, spriteWidth, spriteHeight);
        walkleft3 = spriteSheetWalkLeft.getSubimage(64, 0, spriteWidth, spriteHeight);
        walkleft4 = spriteSheetWalkLeft.getSubimage(96, 0, spriteWidth, spriteHeight);

    
        // down1 = spriteSheet.getSubimage(0,160,spriteWidth,spriteHeight);
        // down2 = spriteSheet.getSubimage(32, 160, spriteWidth, spriteHeight);
        // down3 = spriteSheet.getSubimage(64,160,spriteWidth,spriteHeight);
        // down4 = spriteSheet.getSubimage(96,160,spriteWidth,spriteHeight);

        runleft1 = spriteSheetRunLeft.getSubimage(0, 0, spriteWidth, spriteHeight);
        runleft2 = spriteSheetRunLeft.getSubimage(32, 0, spriteWidth, spriteHeight);
        runleft3 = spriteSheetRunLeft.getSubimage(64, 0, spriteWidth, spriteHeight);
        runleft4 = spriteSheetRunLeft.getSubimage(96, 0, spriteWidth, spriteHeight);

        runright1 = spriteSheetRunRight.getSubimage(0, 0, spriteWidth, spriteHeight);
        runright2 = spriteSheetRunRight.getSubimage(32, 0, spriteWidth, spriteHeight);
        runright3 = spriteSheetRunRight.getSubimage(64, 0, spriteWidth, spriteHeight);
        runright4 = spriteSheetRunRight.getSubimage(96, 0, spriteWidth, spriteHeight);

        // up1 = spriteSheet.getSubimage(0, 352, spriteWidth, spriteHeight);
        // up2 = spriteSheet.getSubimage(32, 352, spriteWidth, spriteHeight);
        // up3 = spriteSheet.getSubimage(64, 352, spriteWidth, spriteHeight);
        // up4 = spriteSheet.getSubimage(96, 352, spriteWidth, spriteHeight);

        }catch(IOException e){
            e.printStackTrace();
        }

        

    }

    



    public void update(){


        double speed = 4;

        if (keyH.shiftPressed == true){ // increased speed when shift is held
            speed = 8;
        }

        if (keyH.doubleKeyPress()){
            speed *= speedConst;
        }



        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || motionState == "idle"){

        
            if (keyH.upPressed == true){
                direction = "up";
                
                if (keyH.shiftPressed == true){
                    direction = "upRun";
                }
            }

            if (keyH.downPressed == true){
                direction = "down";

                if (keyH.shiftPressed == true){
                    direction = "downRun";
                }
            }

            if (keyH.leftPressed == true){
                direction = "left";

                if (keyH.shiftPressed == true){
                    direction = "leftRun";
                }
            }

            if (keyH.rightPressed == true){
                direction = "right";

                if (keyH.shiftPressed == true){
                    direction = "rightRun";
                }
            }

            // Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //if collision is false, player cant move
            if(collisionOn == false){
                switch(direction){
                    case "up":
                        worldY -= speed; //  
                        break;
                    
                    case "upRun":
                        worldY -= speed; //  
                        break;

                    case "down":
                        worldY += speed;
                        break;

                    case "downRun":
                        worldY += speed;
                        break;
                    

                    case "left":
                        worldX -= speed;
                        break;

                    case "leftRun":
                        worldX -= speed;
                        break;

                    case "right":
                        worldX += speed;
                        break;

                    case "rightRun":
                        worldX += speed;
                        break;
                }
            }

            //SPRITE UPDATE LOOP
            spriteCounter++;
            if(spriteCounter > 10){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 3;
                }
                else if(spriteNum == 3){
                    spriteNum = 4;
                }
                else if(spriteNum == 4){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        

    }




    public void draw(Graphics2D g2){
        
    
        BufferedImage image = null;

        switch(direction){

            case "up":
            motionState = "moving";

                if (spriteNum == 1){
                image = lastHorizontalDirection == "left" ? walkleft1 : walkright1;
                }
                if (spriteNum == 2){
                    image = lastHorizontalDirection == "left" ? walkleft2 : walkright2;
                }
                if (spriteNum == 3){
                    image = lastHorizontalDirection == "left" ? walkleft3 : walkright3;
                }
                if (spriteNum == 4){
                    image = lastHorizontalDirection == "left" ? walkleft4 : walkright4;
                }
            

            direction = "idle";
            break;

            case "upRun":
            motionState = "moving";

                if (spriteNum == 1){
                image = lastHorizontalDirection == "left" ? runleft1 : runright1;
                }
                if (spriteNum == 2){
                    image = lastHorizontalDirection == "left" ? runleft2 : runright2;
                }
                if (spriteNum == 3){
                    image = lastHorizontalDirection == "left" ? runleft3 : runright3;
                }
                if (spriteNum == 4){
                    image = lastHorizontalDirection == "left" ? runleft4 : runright4;
                }
            

            direction = "idle";
            break;


            case "down":
            motionState = "moving";

                if (spriteNum == 1){
                    image = lastHorizontalDirection == "left" ? walkleft1 : walkright1;
                }
                if (spriteNum == 2){
                    image = lastHorizontalDirection == "left" ? walkleft2 : walkright2;
                }
                if (spriteNum == 3){
                    image = lastHorizontalDirection == "left" ? walkleft3 : walkright3;
                }
                if (spriteNum == 4){
                    image = lastHorizontalDirection == "left" ? walkleft4 : walkright4;
                }

            direction = "idle";
            break;


            case "downRun":
            motionState = "moving";

                if (spriteNum == 1){
                image = lastHorizontalDirection == "left" ? runleft1 : runright1;
                }
                if (spriteNum == 2){
                    image = lastHorizontalDirection == "left" ? runleft2 : runright2;
                }
                if (spriteNum == 3){
                    image = lastHorizontalDirection == "left" ? runleft3 : runright3;
                }
                if (spriteNum == 4){
                    image = lastHorizontalDirection == "left" ? runleft4 : runright4;
                }
            

            direction = "idle";
            break;

            case "right":
            motionState = "moving";
            lastHorizontalDirection = "right";

                if (spriteNum == 1){
                    image = walkright1;
                }
                if (spriteNum == 2){
                    image = walkright2;
                }
                if (spriteNum == 3){
                    image = walkright3;
                }
                if (spriteNum == 4){
                    image = walkright4;
                }

            direction = "idle";
            break;


            case "rightRun":
            motionState = "moving";
            lastHorizontalDirection = "right";

                if (spriteNum == 1){
                    image = runright1;
                }
                if (spriteNum == 2){
                    image = runright2;
                }
                if (spriteNum == 3){
                    image = runright3;
                }
                if (spriteNum == 4){
                    image = runright4;
                }

            direction = "idle";
            break;

            case "left":
            motionState = "moving";
            lastHorizontalDirection = "left";

                if (spriteNum == 1){
                    image = walkleft1;
                }
                if (spriteNum == 2){
                    image = walkleft2;
                }
                if (spriteNum == 3){
                    image = walkleft3;
                }
                if (spriteNum == 4){
                    image = walkleft4;
                }

            direction = "idle";
            break;

            case "leftRun":
            motionState = "moving";
            lastHorizontalDirection = "left";

                if (spriteNum == 1){
                    image = runleft1;
                }
                if (spriteNum == 2){
                    image = runleft2;
                }
                if (spriteNum == 3){
                    image = runleft3;
                }
                if (spriteNum == 4){
                    image = runleft4;
                }

            direction = "idle";
            break;

            
            default:
            motionState = "idle";

                if (spriteNum == 1){
                    image = lastHorizontalDirection == "left" ? idleleft1 : idleright1;
                }
                if (spriteNum == 2){
                    image = lastHorizontalDirection == "left" ? idleleft2 : idleright2;
                }
                if (spriteNum == 3){
                    image = lastHorizontalDirection == "left" ? idleleft3 : idleright3;
                }
                if (spriteNum == 4){
                    image = lastHorizontalDirection == "left" ? idleleft4 : idleright4;
                }

            break;


        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }


}