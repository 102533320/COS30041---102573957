/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mclib;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Matthew Coulter
 */

public abstract class OptionSelector {
    private ArrayList<Object> _options;
    
    public OptionSelector(ArrayList<Object> options){
        this._options = options;
    }
    private void printOptions(){
        int i = 1;
        for (Object option : _options){
            System.out.println(String.format("%d. %s",i,option));
            i++;
        }
    }
    private int getMin(){
        return 1;
    }
    private int getMax(){
        return _options.size();
    }
    public abstract void handle(Object value); // override this to define what happens on selection of the item
    
    public void prompt(){
        this.printOptions();
        int selection = getIntInput();
        Object objSelection = _options.get(selection - 1);
        this.handle(objSelection);
    }
    
    // prompts the user to select a number inbetween 2 values, validated of course.
    private int getIntInput(){
        int min = this.getMin();
        int max = this.getMax();
        
        System.out.println(String.format("\nPlease select an option %d-%d",min,max));
        Scanner inputScanner = new Scanner(System.in);
        String input = inputScanner.nextLine();
        try{
            int number = Integer.parseInt(input);
            if (number >= min && number <= max){
                return number; // ends here
            }
            System.out.println(String.format("\n%s is not in range of %d and %d",input,min,max));
        }
        catch (NumberFormatException ex){
            System.out.println(String.format("\n%s is not a valid number. Please try again.",input));
        }
        return this.getIntInput();
    }
}
