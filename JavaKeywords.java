//
//  Name:   Pham, Vinh
//  Project: 1
//  Due: 9/26/2022
//  Course: cs-2400-02-f22
//  
//  Description:
/*           Reads a file of java keywords and adds them to an array bag.
             when running JavaKeywords.java the user will enter in words.
             The program will return what is or is not a java keyword.

             It will run some other code to test methods in array bag after displaying
             the user input.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class JavaKeywords {
    
    public static void main(String[] args) {
        BagInterface<String> bag = new ArrayBag<>();
        try {
            Scanner scan = new Scanner(new File("./javakeywords.txt"));
            while (scan.hasNext()) {
                bag.add(scan.nextLine());
            }
            scan.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("An error occurred. Could not read file.");
        }

        System.out.println("Java Keywords by V. Pham\n");
        System.out.println(bag.getCurrentSize() + " Java keywords loaded.\n");

        for(String str : args)
            System.out.println((bag.contains(str) ? str + " is a kewword" : str + " is not a keyword"));

        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~Testing Other Methods~~~~~~~~~~~~~~~");
        System.out.println();

        //Testing other methods
        //Creating new ArrayBag and filling it with strings
        BagInterface<String> testBag = new ArrayBag<>();
        String[] str1 = {"A", "B", "C", "D", "E", "a string", "test", "test2", "A", "A", "A", "A#", "L", "P"};
        System.out.println("Filling testBag with Strings\n");
        testAdd(testBag, str1);

        //Testing Remove Method
        System.out.println("Testing remove(). Removing 2 Unspecified Items from the Bag");
        System.out.println(testBag.remove() + " " + testBag.remove());
        // Testing toArray() in displayBag
        displayBag(testBag);
        System.out.println();

        //Testing getFrequencyOf Method
        System.out.println("Testing getFrequencyOf()");
        System.out.println("The number of 'A' in Test Bag is " + testBag.getFrequencyOf("A"));
        displayBag(testBag);
        System.out.println();

        //Testing Remove Method with anEntry
        System.out.println("Removing a specific item in Test Bag");
        testRemove(testBag, "a string");
        System.out.println();
    
        //Testing clear method
        System.out.println("Removing All Items from Test Bag");
        testBag.clear();
        displayBag(testBag);
        System.out.println();

    }

    private static void testAdd(BagInterface<String> aBag, String[] content) {
        for(String str : content)
            aBag.add(str);
        
        System.out.println(aBag.getCurrentSize() + " Items currently in Test Bag\n");
        displayBag(aBag);
        System.out.println();
    }

    private static void testRemove(BagInterface<String> aBag, String entry) {
        System.out.print("Removing '" + entry + "' from the bag: ");
        aBag.remove(entry);

        System.out.println();
        displayBag(aBag);
    }

    private static void displayBag(BagInterface<String> aBag) {
        System.out.println("The bag contains " + aBag.getCurrentSize() + " string(s), as follows:");

        // Testing toArray() here
        Object[] bagArray = aBag.toArray();

        for(Object obj : bagArray) {
            System.out.print(obj + " ");
        }
        System.out.println();
    }
}
