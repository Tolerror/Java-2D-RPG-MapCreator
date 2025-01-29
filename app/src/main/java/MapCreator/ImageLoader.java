package MapCreator;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class ImageLoader {


    BufferedImage tileSheet;
    List<BufferedImage> loadedImages = new ArrayList<>();
    



    public List<BufferedImage> loadTileImages(String path) {
      
        int posX, posY = 0;

        try {
            tileSheet = ImageIO.read(getClass().getResourceAsStream(path));

                int sheetWidth = tileSheet.getWidth();
                int sheetHeight = tileSheet.getHeight();
                
                
                for (posY = 0 ; posY < sheetHeight-32 ; posY =+ 32){ //-32 since there is no image at (image_Width + 32 , image_Height + 32)
                    for (posX = 0 ; posX < sheetWidth-32 ; posX =+ 32){
        
                        loadedImages.add(tileSheet.getSubimage(posX, posY, 32, 32));
                        // tileImages[i] = tileSheet.getSubimage(posX, posY, 32, 32);
                        // i++;
                    }
                        
                }
                
            }catch(IOException e){
                e.printStackTrace();
            }

            return loadedImages;
           

    //         File[] imageFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));

    //         if (imageFiles != null) {
    //             for (File file : imageFiles) {
    //                 try (InputStream is = ImageLoader.class.getResourceAsStream(path + "/" + file.getName())) {
    //                     if (is != null) {
    //                         images.add(ImageIO.read(is));
    //                     } else {
    //                         System.err.println("Image not found: " + file.getName());
    //                     }
    //                 } catch (IOException e) {
    //                     e.printStackTrace();
    //                 }
    //             }
    //         } else {
    //             System.err.println("No image files found in directory: " + path);
    //         }
    //     } catch (URISyntaxException e) {
    //         e.printStackTrace();
    //     }

    //     return images.toArray(new Image[0]);
    }
}
