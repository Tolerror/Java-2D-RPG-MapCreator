package MapCreator;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main{

    public JFrame window;

    public Main(){

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Map Creator");
        window.setVisible(true);


        ConfigPanel specPanel = new ConfigPanel(this);
        window.add(specPanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }

    public void removePanel(JPanel panel){
        window.remove(panel);
        window.revalidate();
        window.repaint();

    }



    public void addPanel(JPanel panel){
        window.add(panel);
        window.revalidate();
        window.repaint();
        window.pack();
       
    }


    public static void main(String[] args){
      Main mainMenu = new Main();

    }













}
