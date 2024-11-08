package com.techelevator;

import com.sun.security.jgss.GSSUtil;
//import io.FileReader;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TheDuctron800 {
    private Map<String, Duck> inventory = new HashMap<>();

    public Map<String, Duck> getInventory() {
        return inventory;
    }

    public void setInventory(Map<String, Duck> inventory) {
        this.inventory = inventory;
    }

    public static void main (String [] args) {



        TheDuctron800 theDuctron800 = new TheDuctron800();
        //theDuctron800.setInventory(theDuctron800.loadInventory(testFile.getAbsolutePath()));
        //System.out.println(theDuctron800.getInventory());
        theDuctron800.run();
    }
    public void run(){
        File vendingFile = new File("vendingmachine.csv");
        File resources = new File("resources");
        //System.out.println(resources.getAbsolutePath());
        File logFile = new File(resources.getAbsolutePath(), "Log.txt");
        if (!resources.exists()) {
            resources.mkdirs(); // Create the directory if it doesn't exist



            try {
                if (logFile.createNewFile()) {
                    System.out.println("File created: " + logFile.getAbsolutePath());
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        try{
            loadInventory(vendingFile.getAbsolutePath());
        } catch (IOException e){
            System.out.println("Invalid file path found: " + e.getMessage());
        }
        MainMenu menu = new MainMenu();
        while(!menu.exitMenu){
            String option = menu.displayMenu();
            menu.handleOption(option, this.inventory, logFile);
        }



    }




    public void loadInventory(String fileName) throws IOException {
        File inventoryFile = new File(fileName);

        String inventoryName = "";
        double inventoryPrice = 0.0;
        String inventoryType = "";
        String inventorySlot = "";

        try(Scanner inputScanner = new Scanner(inventoryFile)) {

            while(inputScanner.hasNextLine()) {
                String line = inputScanner.nextLine();
                String [] splitInventory = line.split(",");

                inventorySlot = splitInventory[0];
                inventoryName = splitInventory[1];
                inventoryPrice = Double.parseDouble(splitInventory[2]);
                inventoryType = splitInventory[3];

                inventory.put(inventorySlot, new Duck(inventoryName, inventoryPrice, inventoryType));


            }

        }

    }
}
