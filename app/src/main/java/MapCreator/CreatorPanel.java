package MapCreator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import tile.Tile;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreatorPanel extends JPanel{

    private int tileSize;
    private int mouseCol;
    private int mouseRow;
    private int col;
    private int row;
    private Tile[][] map;
    private double zoomFactor = 1.0;
    private Tile[] tileImages;
    private Tile selectedTile = null;



    CreatorPanel(int row, int col, int tileSize, Tile[] tileImages){

            this.tileSize = tileSize;
            this.row = row;
            this.col = col;
            this.tileImages = tileImages;
            
            map = new Tile[row][col];

            //Side by size grid layout
            this.setLayout(new GridLayout(1,2));

            //Grid Panel
            JPanel gridPanel = new JPanel(){

                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
    
                    int tileSize = getCurrentTileSize();
                    for (int r = 0; r < row; r++) {
                        for (int c = 0; c < col; c++) {
                            Tile tile = map[r][c];
                            if (tile != null) {
                                g.drawImage(tile.image, 
                                    (int) (c * tileSize * zoomFactor), 
                                    (int) (r * tileSize * zoomFactor), 
                                    tileSize, 
                                    tileSize, 
                                    null);
                            } else {
                                g.setColor(Color.WHITE);
                                g.fillRect((int) (c * tileSize * zoomFactor), 
                                    (int) (r * tileSize * zoomFactor), 
                                    tileSize, 
                                    tileSize);
                            }
                            g.setColor(Color.GRAY);
                            g.drawRect((int) (c * tileSize * zoomFactor), 
                                (int) (r * tileSize * zoomFactor), 
                                tileSize, 
                                tileSize);
                        }
                    }
                }

                @Override
                public Dimension getPreferredSize(){
                return new Dimension((int)(tileSize*col*zoomFactor), (int)(tileSize*row*zoomFactor));
                }

            };



            
            gridPanel.setBackground(Color.LIGHT_GRAY);
            gridPanel.setDoubleBuffered(true);
            gridPanel.addMouseListener(new MouseListener(){

            
                @Override
                public void mouseClicked(MouseEvent e) {
                  
                  int mouseX = (int) (e.getX() / zoomFactor);
                  int mouseY = (int) (e.getY() / zoomFactor);

        
                   mouseCol = calculateCol(mouseX);
                   mouseRow = calculateRow(mouseY);

                  System.out.println("Mouse clicked at (" + mouseCol + "," + mouseRow + ")");

                  if(selectedTile != null){
                      editTile(mouseCol, mouseRow);
                      gridPanel.repaint();
                  }

                }



                @Override
                public void mousePressed(MouseEvent e) {
                    
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                
                }

                @Override
                public void mouseExited(MouseEvent e) {
            
                }

            
            }); 

            gridPanel.addMouseWheelListener(new MouseWheelListener() {

                @Override
                public void mouseWheelMoved(MouseWheelEvent e) {

                   if (e.getWheelRotation() < 0){
                        zoomIn();
                   }else{
                        zoomOut();
                   }
                   gridPanel.revalidate();
                   gridPanel.repaint();

                }
                
            });

            JScrollPane gridScrollPane = new JScrollPane(gridPanel);
            gridScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            gridScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


            //Side Panel with tile images
            JPanel tilePanel = new JPanel();
            tilePanel.setLayout(new BoxLayout(tilePanel, BoxLayout.Y_AXIS));

            for (Tile tileImage : tileImages){
                JLabel label = new JLabel(new ImageIcon(tileImage.image)); //load each bufferedImage onto a label
                label.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
                tilePanel.add(label);

                label.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e){
                    selectedTile = tileImage;           //store selected tile
                    updateTilePanelSelection(tilePanel, label);
                    System.out.println("Tile Selected: " + selectedTile);
                    }
                    
                @Override
                public void mousePressed(MouseEvent e) {
                    // TODO Auto-generated method stub
                   
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    // TODO Auto-generated method stub
                   
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // TODO Auto-generated method stub
                    
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // TODO Auto-generated method stub
                   
                }
                });
            }

            JButton saveButton = new JButton("Save Map");
            //saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            saveButton.addActionListener(e -> saveMapToFileWithDialog());
            tilePanel.add(saveButton);


            JScrollPane tileScrollPane = new JScrollPane(tilePanel);
            tileScrollPane.setPreferredSize(new Dimension(100,0));
            tileScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            tileScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


            //Split pane to separate grid and tile panel
            JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gridScrollPane, tileScrollPane);
            splitPane.setDividerLocation(800);

            this.add(splitPane);
    }


        private void zoomIn(){
            zoomFactor *= 1.1;
            revalidate();
            repaint();
        }

        private void zoomOut(){
            zoomFactor /= 1.1;
            revalidate();
            repaint();

        }


        public void editTile(int col, int row){
            if (col >= 0 && col < this.col && row >= 0 && row < this.row) {
               // map[row][col] = 1 - map[row][col]; // Toggle tile between 0 and 1
               map[row][col] = selectedTile;
            }
        }

      
        private int getCurrentTileSize() {
            return (int) (tileSize*zoomFactor);
        }


        public int calculateCol(int mouseX) {
            int tileSize = getCurrentTileSize();
            return (int) Math.floor(mouseX / tileSize);
        }
        
        public int calculateRow(int mouseY) {
            int tileSize = getCurrentTileSize();
            return (int) Math.floor(mouseY / tileSize);
        }

        private void updateTilePanelSelection(JPanel tilePanel, JLabel selectedLabel){
            for(Component component : tilePanel.getComponents()){
                if(component instanceof JLabel label){
                    if(label == selectedLabel){
                        label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    }else{
                        label.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                    }
                }
                tilePanel.repaint();
            }
        }




        // Method to show the "Save As" dialog and save the map
        private void saveMapToFileWithDialog() {
            // Create a JFileChooser instance
            JFileChooser fileChooser = new JFileChooser();

            // Set the default file name
            fileChooser.setSelectedFile(new File("map.txt"));

            // Show the Save As dialog
            int returnValue = fileChooser.showSaveDialog(this);

            // If the user selects a file, save the map
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                saveMapToFile(selectedFile);
            }
        }

        private void saveMapToFile(File file) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    for (int r = 0; r < row; r++) {
                        for (int c = 0; c < col; c++) {
                            // Write the tile value (or some identifier) to the file
                            if (map[r][c] != null) {
                                writer.write("1 "); // Assuming non-null tiles are '1'
                            } else {
                                writer.write("0 "); // Empty spaces are '0'
                            }
                        }
                        writer.newLine();
                    }
                    JOptionPane.showMessageDialog(this, "Map saved successfully!", "Save", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Error saving map: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
        }




        private int getTileIndex(Tile tile) {
            for (int i = 0; i < tileImages.length; i++) {
                if (tileImages[i] == tile) {
                    return i;
                }
            }
            return -1; // Default if the tile is not found
        }

        

        
        

   
       
}

