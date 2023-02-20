/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package version_two;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Maxth
 */
public class ImageEditor {
    //Fields
    private String pathToImage;
    private BufferedImage image;
    
    
    //Constructor
    public ImageEditor(String pathToImage) {
        this.pathToImage = pathToImage;
        
        try {
            this.image = ImageIO.read(new File(pathToImage));
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    

    //Methods
    private void cordChecker(int x, int y) {
        if(x > this.image.getWidth() || y > this.image.getHeight()) {
            System.out.println("ERROR Invalid Cords");
            System.exit(1);
        }
    }
    
    
    //Returns RGB of pixel
    public int getPixelRGBValue(int x, int y) {
        this.cordChecker(x, y);
        
        return this.image.getRGB(x, y);
    }
    
    public int getPixelRedValue(int x, int y) {
        this.cordChecker(x, y);

        int RGB = this.getPixelRGBValue(x, y);
        Color color = new Color(RGB, true);
        
        return color.getRed();
    }
    
    public int getPixelGreenValue(int x, int y) {
        this.cordChecker(x, y);
        
        int RGB = this.getPixelRGBValue(x, y);
        Color color = new Color(RGB, true);

        return color.getGreen();
    }
    
    public int getPixelBlueValue(int x, int y) {
        this.cordChecker(x, y);
        
        int RGB = this.getPixelRGBValue(x, y);
        Color color = new Color(RGB, true);

        return color.getBlue();
    }
    
    public int getPixelAlphaValue(int x, int y) {
        this.cordChecker(x, y);
        
        int RGB = this.getPixelRGBValue(x, y);
        Color color = new Color(RGB, true);

        return color.getAlpha();
    }
    
    public void setPixelRGBValue(int x, int y, int RGB) {
        this.cordChecker(x, y);
        
        this.image.setRGB(x, y, RGB);
    }
    
    public void setPixelRedValue(int x, int y, int red) {
        this.cordChecker(x, y);
        
        Color pixelColor = new Color(this.image.getRGB(x, y));
        Color newColor = new Color(red, pixelColor.getGreen(), pixelColor.getBlue(), pixelColor.getAlpha());
        
        this.image.setRGB(x, y, newColor.getRGB());
    }
    
    public void setPixelGreenValue(int x, int y, int green) {
        this.cordChecker(x, y);
        
        Color pixelColor = new Color(this.image.getRGB(x, y), true);
        Color newColor = new Color(pixelColor.getRGB(), green, pixelColor.getBlue(), pixelColor.getAlpha());
        
        this.image.setRGB(x, y, newColor.getRGB());
    }
    public void setPixelBlueValue(int x, int y, int blue) {
        this.cordChecker(x, y);
        
        Color pixelColor = new Color(this.image.getRGB(x, y), true);
        Color newColor = new Color(pixelColor.getRed(), pixelColor.getGreen(), blue, pixelColor.getAlpha());
        
        this.image.setRGB(x, y, newColor.getRGB());
    }
    
    
    public void setPixelAlphaValue(int x, int y, int alpha) {
        this.cordChecker(x, y);
        
        Color pixelColor = new Color(this.image.getRGB(x, y), true);
        Color newColor = new Color(pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue(), alpha);
        
        this.image.setRGB(x, y, newColor.getRGB());
    }
    
    public int getImageWidth() {
        return this.image.getWidth();
    }
    public int getImageHeight() {
        return this.image.getHeight();
    }
     
    public boolean saveImage() {
        try {
            ImageIO.write(image, "png", new File("./output.png"));
        } catch(Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
    

    //Main Method
    public static void main(String[] args) {
        ImageEditor imageEditor = new ImageEditor("./flower.png");
        
        int height = imageEditor.getImageHeight();
        int width = imageEditor.getImageWidth();
        
        for(int i = 0; i < height; i+=8) {
            for(int j = 0; j < width; j+=8) {
                imageEditor.setPixelRGBValue(j, i, Color.GREEN.getRGB());
            }
        }
        
        imageEditor.saveImage();
    }
}
