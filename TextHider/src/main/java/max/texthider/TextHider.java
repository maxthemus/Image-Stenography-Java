 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package max.texthider;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author max
 */
public class TextHider {

    //Method transforms String into integer array representing the bits
    public static ArrayList<Integer> getBitsFromString(String text) {
        ArrayList<Integer> bitArray = new ArrayList<>(text.length() * 8);
        
        for(int i = 0; i < text.length(); i++) {
            int tempNumber = (int)text.charAt(i);
            
            
            int count = 0;
            int[] byteArray = new int[8];
            while(tempNumber > 0) {
                int remainder = tempNumber % 2;
                byteArray[count++] = remainder;
                
                tempNumber /= 2;
            }
            
            for(int j = count; j < 8; j++) {
                byteArray[j] = 0;
            }
            
            for(int j = 7; j >= 0; j--) {
                bitArray.add(byteArray[j]);
            }
        }
        
        return bitArray;
    }
    
    public static String decodeBitArray (ArrayList<Integer> bitArray) {
        String tempString = "";
        
        
        for(int i = bitArray.size()-1; i > 0; i-=8) {
            String binaryString = "";
            
            for(int j = i; j > i-8; j--) {
                System.out.println(j + " : " + bitArray.get(j));
                binaryString += String.valueOf(bitArray.get(j));
            }
            int currentInteger = Integer.parseInt(binaryString, 2);
            System.out.println(binaryString);
            tempString = (char)currentInteger + tempString;
        }
        
        System.out.println(tempString);
        
        return tempString;
    }
    
    public static String getStringFromImage(String path, int length) {
        String tempString = "";
        ArrayList<Integer> bits = new ArrayList<>(length * 8);
        
        try {
            File imageFile = new File(path);
            int count = 0;
            
            BufferedImage bufferImage = ImageIO.read(imageFile);
            
            int height = bufferImage.getHeight();
            int width = bufferImage.getWidth();
            
            for(int i = 0; i < width; i+=8) {
                for(int j = 0; j < height; j+=8) {
                    if(count < length * 8) {
                        int rgb = bufferImage.getRGB(i, j);
                        Color col = new Color(rgb, true);                        
                        
                        int red = col.getRed();
                        int statusBit = red & (1 << 0);
                        
                        bits.add(statusBit);
                        count++;                        
                    }
                }
            }
            
        } catch(Exception e) {
            System.out.println(e);
        }
        
        System.out.println(bits.toString());
        decodeBitArray(bits);
        
        return tempString;
    }
    
    
    
    
    public static void main(String[] args) {
        
        
        String message = "d";
        ArrayList<Integer> bits = getBitsFromString(message);
        System.out.println(bits);
        
//        //Checking if bit array is valid
//        if(bits.size() != (message.length() * 8)) {
//            System.out.println("Error In Creating bit array");
//            System.exit(1);
//        } else {
//            System.out.println("Bit array Finshed!");  
//        }
//        
//        
//        try {
//            File imageFile = new File("./flower.png");
//            int index = 0;
//            
//            BufferedImage bufferImage = ImageIO.read(imageFile);
//            BufferedImage imageOut = ImageIO.read(imageFile);
//            
//            int height = bufferImage.getHeight();
//            int width = bufferImage.getWidth();
//            
//            for(int i = 0; i < width; i+=8) {
//                for(int j = 0; j < height; j+=8) {
//                    
//                    int rgb = bufferImage.getRGB(i, j);
//                    
//                    //Get value in index
//                    //Left shift 24
//                    //Insert value
//                    if(index < bits.size()) {                        
//                        int alpha = (rgb >> 24) & 0xff;
//                   
//                        int red = (rgb >> 16) & 0xff;
//                        red = 0;
//                        red = red | (bits.get(index++));
//                        
//                        
//                        int green = (rgb >> 8) & 0xff;
//                        int blue = (rgb >> 0) & 0xff;
//                        Color newColor = new Color(red, green, blue, alpha);
//                        
//                        
//                        imageOut.setRGB(i, j, newColor.getRGB()); 
//                        
//                        int checker = imageOut.getRGB(i, j);
//                        Color color = new Color(checker);
//                    }
//                }
//            }
//            
//            File outputFile =new File("test.png");
//            ImageIO.write(imageOut, "png", outputFile);
//        } catch(Exception e) {
//            System.out.println(e);
//        }
        
        System.out.println("Finshed inserting Text into image");
        getStringFromImage("./flower.png", message.length());
    }
    
    public static void printBits(int number) {
        if(number == 0) {
            System.out.println("0");
        } else {
            StringBuilder string = new StringBuilder();
            while(number > 0) {
                int remainder = number % 2;
                string.append(remainder);
                number /= 2;
            }
            string = string.reverse();
            System.out.println(string);
        }    
    }
}
