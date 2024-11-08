package com.techelevator;

import java.io.*;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;

public class MainMenu  {
    Scanner userInput = new Scanner(System.in);

    boolean exitMenu = false;
    public MainMenu () {

    }

    public String displayMenu() {
        System.out.println("(1) Display Ducks for sale");
        System.out.println("(2) Purchase");
        System.out.println("(3) Exit");
        return userInput.nextLine();

    }
    public void handleOption(String option, Map<String, Duck> inventory, File log) {
        int numberOfSales = 0;
        Reporter reporter = new Reporter();
//nventory.get (SLOT NUMBER)

        if(option.equals("1")){
            for(Map.Entry<String, Duck> current: inventory.entrySet()){
                System.out.println(current.getValue().displayDuck(current.getKey()));
            }
            System.out.println();
        }

        if(option.equals("2")){
            Transaction transaction = new Transaction();

            LocalDateTime time = null;



            while(!transaction.isTransactionOver()) {
                String transactionOption = transaction.purchaseMenu();
                if (transactionOption.equals("1")) { //: FEED MONEY OPTION AFTER OPTION 1 IS SELECTED ON THE PURCHASE MENU THEN RETURNS TO TRANSACTION MENU
                    System.out.println("Enter whole dollar bills (ex: 1.00, 5.00, 10.00): ");
                    String amountInput = userInput.nextLine();
                    double amount = Double.parseDouble(amountInput);
                    try(Scanner myInput = new Scanner(log); PrintWriter writer = new PrintWriter(new FileWriter(log, true))){
                        transaction.feedMoney(amount);

                        writer.println(reporter.generateLog(time.now(), "FEED MONEY", amount, transaction.getBalance()));



                    } catch (IOException e) {
                        e.getMessage();
                    }

                }
                if (transactionOption.equals("2")) { //SELECT DUCK OPTION: AFTER OPTION 2 IS SELECTED ON THE PURCHASE MENU
                    System.out.println("Please select a slot: ");
                    String slotInput = userInput.nextLine();
                    Duck selectedDuck = inventory.get(slotInput);
                    String result = selectedDuck.selectDuck(selectedDuck, transaction.getBalance()); //USER INPUT DETERMINES WHICH DUCK IS SELECTED BY SLOT INPUT
                    transaction.reduceBalance(selectedDuck.getPrice());

                    try(Scanner myInput = new Scanner(log); PrintWriter writer = new PrintWriter(new FileWriter(log, true))){

                        writer.println(reporter.generateLog(time.now(), slotInput + " " + selectedDuck.getName(), selectedDuck.getPrice(), transaction.getBalance()));

                    } catch (IOException e) {
                        e.getMessage();
                    }
                    System.out.println(selectedDuck.getName() + " " + selectedDuck.getPrice() + " Here's your change: " + transaction.getBalance());
                    System.out.println(result); //IF IS NOT SOLD OUT IT WILL RETURN THE DUCK MESSAGE FOR THE CLASS OF DUCK

                }
                if (transactionOption.equals("3")) { //Finish Transaction option returns change and message: AFTER OPTION 3 IS SELECTED ON THE PURCHASE MENU


                    try(Scanner myInput = new Scanner(log); PrintWriter writer = new PrintWriter(new FileWriter(log, true))){


                        writer.println(reporter.generateLog(time.now(), "GIVE CHANGE", transaction.getBalance(), 0.0));



                    } catch (IOException e) {
                        e.getMessage();
                    }
                    System.out.println(transaction.getChange());
                    transaction.finishTransaction();
                    transaction.transactionIsOver();
                }
            }

        }

        if(option.equals("3")){
            exitMenu = true;
        }

        if(option.equals("4")){
            reporter.writeSalesReport(inventory);
        }

    }




}
