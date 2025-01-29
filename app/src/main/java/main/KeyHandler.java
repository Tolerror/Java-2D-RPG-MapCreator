package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public boolean upPressed, downPressed, rightPressed, leftPressed, shiftPressed;
    public int keyCount = 0;

    @Override
    public void keyTyped(KeyEvent e) {
    }



    
    public boolean doubleKeyPress(){
        boolean press = false;

        if (upPressed && rightPressed || upPressed && leftPressed || downPressed && rightPressed || downPressed && leftPressed){
            press = true;
        }

        return press;
    }



    @Override
    public void keyPressed(KeyEvent e) {

       int code = e.getKeyCode(); //  code for pressed key

    
        // Key cases for movement
        switch (code){ 

        case (KeyEvent.VK_W):
        upPressed = true;
    
        break;

        case (KeyEvent.VK_S):
        downPressed = true;
        
        break;

        case (KeyEvent.VK_A):
        leftPressed = true;
        
        break;

        case (KeyEvent.VK_D):
        rightPressed = true;
        
        break;

        case (KeyEvent.VK_SHIFT):
        shiftPressed = true;
       
        break;

        
       }
       
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();


        // Key cases for movement
        switch (code){ 

        case (KeyEvent.VK_W):
        upPressed = false;
        
        break;

        case (KeyEvent.VK_S):
        downPressed = false;
        
        break;

        case (KeyEvent.VK_A):
        leftPressed = false;
       
        break;

        case (KeyEvent.VK_D):
        rightPressed = false;
       
        break;

        case (KeyEvent.VK_SHIFT):
        shiftPressed = false;
        
        break;

        
       }

    }
    










}
