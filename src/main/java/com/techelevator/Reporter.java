package com.techelevator;

import jdk.jshell.spi.ExecutionControl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;

public class Reporter {


    public String generateLog (LocalDateTime time, String action, double amount, double newBalance) {
    //Logs transactions with local date, action, amount, and new balance.
        String log = time.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + " " + action +  " $" + amount + " $" + newBalance;
        return log;
    }

    public void writeSalesReport(Map<String, Duck> inventory) {
        // Purpose: Writes the sales report to a file with total sales for each duck and overall revenue. Occurs when user inputs (4) in main menu as a hidden feature
        File resources = new File("resources");
        String salesLine = "";
        //System.out.println(resources.getAbsolutePath());
        File logFile = new File(resources.getAbsolutePath(), "Sales.csv"); //Initial creation of hidden sales report function
        if (!resources.exists()) {
            resources.mkdirs(); // Create the directory if it doesn't exist
        }

        try {
            if (logFile.createNewFile()) {
                System.out.println("File created: " + logFile.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        for (Map.Entry<String, Duck> duck : inventory.entrySet()) {
             salesLine = duck.getValue().getName() + "," + (5 - duck.getValue().getQuantity()); //FORMAT FOR SALES REPORT
            try (PrintWriter writer = new PrintWriter(new FileWriter(logFile, true))) {
                writer.println(salesLine);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }



    }


}
