package com.techelevator;

import java.util.Locale;

public class Duck {
    private String name = "";
    private int quantity = 5;
    private double price = 0.0;
    private String type = "";

    public Duck(String name, double price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = 5;

    }

    public String getName() { //GETTERS
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public String displayDuck(String slot) {
        String status = isSoldOut() ? "SOLD OUT" : "Quantity: " + quantity;
        return (slot + " | " + name + " | $" + String.format("%.2f", price) + " | " + status);
    }


    public boolean isSoldOut() {
        return quantity == 0;
    }

    public String selectDuck(Duck duck, double balance) {
        // double price = 0.0;

        //Inventory.get(slot).getPrice THis is what we will use to set price to the duck price from the duck class.

        if(isSoldOut()) {
            return "Duck is Sold out.";
        } else if (balance < duck.price) {
            return "Not enough money to purchase Duck.";
        } else{
            reduceQuantity();
            return purchaseMessage(duck);
        }

    }

    public void reduceQuantity() { //LOWERS Duck quantity by one when a duck is purchased
        if(quantity > 0) {
            quantity--;
        }
    }

    public String purchaseMessage(Duck duck) { //RETURNS a message depending on what type of duck was purchased
        switch (duck.type) {
            case "Party Duck":
                return("Yippee, Dippy, Splash!");
            //break;
            case "Sporty Duck":
                return("Go, Team, Go");
            // break;
            case "Clown Duck":
                return("Wakka, Wakka, Whee!");
            //  break;
            case "Sickly Duck":
                return("Snuffle, Shuffle, Quack Off!");

            default:
                break;
        }

        return "Thank you for your purchase, enjoy your Duck!";
    }




}
