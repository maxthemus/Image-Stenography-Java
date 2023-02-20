/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package version_two;

import java.util.ArrayList;

/**
 *
 * @author max
 */
public class Message {
    //Fields
    private final String message;
    private final ArrayList<Integer> bits;
    
    
    //Constructor
    public Message(String message) {
        this.message = message;
        this.bits = this.messageToBits(message);
    }
    
    public Message(ArrayList<Integer> bits) {
        this.bits = bits;
        this.message = this.bitsToMessage(bits);
    }

    //Methods
    private String bitsToMessage(ArrayList<Integer> bits) {        
        String tempString = "";
        
        for(int i =  0; i < bits.size(); i+=8) {
            String byteString = "";
            
            for(int j = i; j < (i+8); j++) {
                byteString = byteString + bits.get(j).toString();
            }
            int charInt = Integer.valueOf(byteString, 2);
            tempString = (char)charInt + tempString;
        }        
        
        return tempString;
    } 
    
    private ArrayList<Integer> messageToBits(String message) {
        ArrayList<Integer> bitArray = new ArrayList<>(message.length() * 8);
        
        for(int i = 0; i < message.length(); i++) {
            int value = Integer.valueOf(message.charAt(i));
            int[] byteArray = new int[8];
            
            for(int j = 0; j < 8; j++) {
                int remainder = value % 2;
                
                byteArray[7-j] = remainder;
                bitArray.add(0, remainder);
                value /= 2;
            }
        }
        
        //Adding null terminator
        for(int i = 0; i < 8; i++) {
            bitArray.add(0);
        }
        
        return bitArray;
    }
    
    public ArrayList<Integer> getBits() {
        return this.bits;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public String toString() {
        return this.bits.toString();
    }
    

    //Main Method
    public static void main(String[] args) {
        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(0);
        ints.add(1);
        ints.add(1);
        ints.add(0);
        ints.add(0);
        ints.add(0);
        ints.add(0);
        ints.add(1);
//        
//        ints.add(1);
//        ints.add(0);
//        ints.add(1);
//        ints.add(0);
//        ints.add(1);
//        ints.add(0);
//        ints.add(1);
//        ints.add(1);
//        String tempString = "a";
        Message msg = new Message(ints);
        
        System.out.println(msg.getMessage());
    }

    
}
