/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package version_two;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Maxth
 */
public class Stenographer {
    //Fields
    
    //Constructor
    
    //Methods
    public static Message extractText(ImageEditor image) {
        //Logic
        //1- Loop through image
        //2- read in 8 bits a a time Till byte with all 0's
        //3- create Message and return
        ArrayList<Integer> bitArray = new ArrayList<>(); //For message Bits
        
        int count = 0;
        ArrayList<Integer> byteArray = new ArrayList<>(8);
        
        for(int i = 0; i < image.getImageHeight(); i++) {
            for(int j = 0; j < image.getImageWidth(); j++) {
                //Extracting bit from pixel
                Color pixelColor = new Color(image.getPixelRGBValue(j, i), true);
                int alphaValue = pixelColor.getAlpha();
                
                int bit = alphaValue & 1;
                byteArray.add(bit);
                
                //Reseting the count back to 0
                if(count >= 7) {
                    //Now we want to look at the byte array we have created
                    
                    //Looping through to see if all 0's
                    boolean foundBit = false;
                    for(int f = 0; f < 8; f++) {
                        if(byteArray.get(f) == 1) {
                            foundBit = true;
                            break;
                        }
                    }
                    
                    if(foundBit) {
                        //Then we save byteArray to bit Array
                        for(int f = 0; f < 8; f++) {
                            bitArray.add(byteArray.get(f));
                        }
                    } else {
                        //We terminate the String because we have found all the bits
                        break; //Terminate for loops
                    }
                    
                    byteArray = new ArrayList<>(8); //Reseting byte array
                    count = 0; //Reseting count
                } else {
                    count++; //Incrementing count
                }
            }
        }        
        Message message = new Message(bitArray);
        return message;
    }
    
    public static void insertText(Message message, ImageEditor image) {
        //Testing purposes (Setting background to green
//        for(int i = 0; i < image.getImageHeight(); i++) {
//            for(int j = 0; j < image.getImageWidth(); j++) {
//                image.setPixelRGBValue(j, i, Color.GREEN.getRGB());
//            }
//        }
        //TESTING END
        
        ArrayList<Integer> bits = message.getBits();
        int count = 0;
        
        for(int i = 0; i < image.getImageHeight(); i++) {
            for(int j = 0; j < image.getImageWidth(); j++) {
                if(count < bits.size()) {
                    int updatedRGB = Stenographer.insertBitIntoRGB(bits.get(count), image.getPixelRGBValue(j, i));                    
                    
                    //Insert new RGB into image
                    image.setPixelRGBValue(j, i, updatedRGB);                    
                } else {
                    break;
                }
                
                count++;
            }
        }        
        image.saveImage();
    }
    
    public static int insertBitIntoRGB(int bitValue, int RGB) {
        Color pixelColor = new Color(RGB, true);
        int alpha = pixelColor.getAlpha();
        
        //Now we want to insert the bit value into the first bit of the alpha
        if(bitValue == 1) {
            //Set bit
//            alpha = alpha | 1;
            alpha = 255;
        } else {
            //Clear bit
//            alpha = alpha & ~(1);
            alpha = 0;
        }
        
        Color newColor = new Color(pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getGreen(), alpha);
//        Color newColor = new Color(1, 1, 1, alpha);

        return newColor.getRGB();
    }
        
    //Main Method
    public static void main(String[] args) {
        String tempString = "This is a";
        Message msg = new Message(tempString);
        ImageEditor image = new ImageEditor("./flower.png");
        
        Stenographer.insertText(msg, image);
        Message message = Stenographer.extractText(image);
        
        System.out.println(message.getMessage());
    }
}
