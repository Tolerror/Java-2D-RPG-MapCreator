package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp){
		this.gp = gp;
	}
	
	
	
	public void checkTile(Entity entity){
		
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction){
            case "up":
                entityTopRow = (int)(entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];

                if(gp.tileM.tile.get(tileNum1).collision == true || gp.tileM.tile.get(tileNum2).collision == true){
                    entity.collisionOn = true;
                }
            break;
            
            case "upRun":
                entityTopRow = (int)(entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];

                if(gp.tileM.tile.get(tileNum1).collision == true || gp.tileM.tile.get(tileNum2).collision == true){
                    entity.collisionOn = true;
                }
            break;

            case "down":
                entityBottomRow = (int)(entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                if(gp.tileM.tile.get(tileNum1).collision == true || gp.tileM.tile.get(tileNum2).collision == true){
                    entity.collisionOn = true;
                }
                break;
                
            case "downRun":
                entityBottomRow = (int)(entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                if(gp.tileM.tile.get(tileNum1).collision == true || gp.tileM.tile.get(tileNum2).collision == true){
                    entity.collisionOn = true;
                }
                break;

            case "left":
                entityLeftCol = (int)(entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];

                if(gp.tileM.tile.get(tileNum1).collision == true || gp.tileM.tile.get(tileNum2).collision == true){
                    entity.collisionOn = true;
                }
                break;
                
            case "leftRun":
                entityLeftCol = (int)(entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];

                if(gp.tileM.tile.get(tileNum1).collision == true || gp.tileM.tile.get(tileNum2).collision == true){
                    entity.collisionOn = true;
                }
                break;

            case "right":
                entityRightCol = (int)(entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                if(gp.tileM.tile.get(tileNum1).collision == true || gp.tileM.tile.get(tileNum2).collision == true){
                    entity.collisionOn = true;
                }
                break;
                
            case "rightRun":
                entityRightCol = (int)(entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                if(gp.tileM.tile.get(tileNum1).collision == true || gp.tileM.tile.get(tileNum2).collision == true){
                    entity.collisionOn = true;
                }
                break;


        }
	}
}
