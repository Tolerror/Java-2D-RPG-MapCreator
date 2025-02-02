package MapCreator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.GamePanel;
import tile.Tile;
import tile.TileManager;


public class ConfigPanel extends JPanel{
    

    private Main main;
    private Tile[] tileImages;
    private int imCnt = 0;

    ConfigPanel(Main main){

        this.main = main;
        this.setBackground(Color.white);
        this.setLayout(new GridLayout(4,2,20, 20));

        JLabel label1 = new JLabel("Rows:");
        JTextField textField1 = new JTextField(20);

        JLabel label2 = new JLabel("Cols:");
        JTextField textField2 = new JTextField(20);

        JLabel label3 = new JLabel("Tile Size:   (default 32x32)");
        JTextField textField3 = new JTextField(20);

        JButton confirmButton = new JButton("Confirm");

        
        this.add(label1);
        this.add(textField1);
        this.add(label2);
        this.add(textField2);
        this.add(label3);
        this.add(textField3);
        this.add(confirmButton);

        confirmButton.addActionListener(e -> {
            int fieldValue1  = Integer.parseInt(textField1.getText());
            int fieldValue2 = Integer.parseInt(textField2.getText());
            int fieldValue3 = Integer.parseInt(textField3.getText());

            System.out.println("Values confirmed. Rows:" + fieldValue1 + " Cols:" + fieldValue2 + " Tile Size:" + fieldValue3);
            createMapCreator(fieldValue1, fieldValue2, fieldValue3);

        });


    }
    
    


    public void createMapCreator(int rows, int cols, int tileSize){
        
        GamePanel gp = new GamePanel();
        //Load tile images from main TileManager
        TileManager tileM = new TileManager(gp);
        tileM.getTileImage();
        tileImages = new Tile[tileM.getTileSet().length]; //create Tile[] size of main Tile[] from TileManager
        tileImages = tileM.getTileSet();
        System.out.println("The 2nd tile: " + tileImages[1]);
        
        // ImageLoader imageLoader = new ImageLoader();
        // this.tileimages = imageLoader.loadTileImages("/tiles/tiles.png"); //Load images from resources directory

        for (int i = 0 ; i < tileImages.length ; i++)
        if (tileImages[i] != null){
            imCnt++;
        }
        System.out.println(imCnt + " Images loaded successfully");

        main.removePanel((JPanel) this);
        CreatorPanel createPanel = new CreatorPanel(rows, cols, tileSize, tileImages);
        main.addPanel((JPanel) createPanel);

    }

   

    
}
