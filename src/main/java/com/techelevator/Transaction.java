package com.techelevator;

import java.util.Scanner;

public class Transaction {

    private double balance;
    private boolean transactionOver;



    Scanner userInput = new Scanner(System.in);


    public Transaction() {
        this.balance = 0.0;
        this.transactionOver = false;
    }
    public void feedMoney(double amount) {

        if (amount > 0.0) {
            balance += amount;
        }
        else {
            System.out.println("Please insert a valid amount");
        }

    }


    public String getChange(){ //Returns change through the use of modulus
        double quarter = 0.25;
        double dimes = 0.10;
        double nickels = 0.05;

        double numOfQuarters = this.balance / quarter;
        this.balance %= quarter;
        double numOfDimes = this.balance / dimes;
        this.balance %= dimes;
        double numOfNickels = this.balance / nickels;
        this.balance %= nickels;

        return "Number of Quarters: " + Math.floor(numOfQuarters) + " Number of Dimes : " + Math.floor(numOfDimes) + " Number of Nickels: " + Math.ceil(numOfNickels);

    }

//    public void getChange() {
////        double change = Math.round(getBalance());
////        int quarters = 0;
////        int dimes = 0;
////        int nickels = 0;
////
////        while(change != 0.0){
////            if(change >= 0.25){
////                change -= 0.25;
////                quarters++;
////
////            }
////            else if(change < 0.25 && change > 0.05){
////                change -= 0.1;
////                dimes++;
////
////            }
////            else if(change <= 0.05){
////                change -= 0.05;
////                nickels++;
////
////            }
////        }
//
//        //Calculates the change owed to the customer in coins (nickels, dimes, quarters).
//        //System.out.println("Quarters = " + quarters + "Dimes = " + dimes + "Nickels = " + nickels);
//
//    }


    public void finishTransaction() {
        reduceBalance(getBalance());
    }

    public void reduceBalance(double amount) {
        balance -= amount;
    }

    public String purchaseMenu() {
        System.out.println("(1) Feed Money"); // Calls the
        System.out.println("(2) Select Duck");
        System.out.println("(3) Finish Transaction");
        return userInput.nextLine();

        // Handles the purchase process (1 money feeding, 2 selecting ducks, finishing transaction).
    }

    public double getBalance() {
        return balance;
    }

    public boolean isTransactionOver() {
        return transactionOver;
    }
    public void transactionIsOver(){
        transactionOver = true;
    }
}
