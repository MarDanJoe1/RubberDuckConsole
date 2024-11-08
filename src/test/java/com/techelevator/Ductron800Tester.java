package com.techelevator;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class Ductron800Tester {

//    @Test
//    public void load_inventory_reads_from_csv_and_creates_inventory_successfully(){
//        TheDuctron800 testTron = new TheDuctron800();
//
//        Map<String, Duck> testInventory = new HashMap<>();
//
//
//
//
//        Assertions.assertAll();
//    }

    @Test
    public void option_2_transaction_successfully_feed_money_to_ductron800(){

    }

    @Test
    public void option_2_transaction_successfully_select_duck_from_ductron800(){

    }

    @Test
    public void option_2_transaction_successfully_finish_transaction(){

    }



    @Test
    public void option_3_should_successfully_exit_the_menu(){
        TheDuctron800 testTron = new TheDuctron800();
        String nothing = "";

        Map<String, Duck> testInventory = new HashMap<>();
        File uselessFile = new File(nothing);

        MainMenu menu = new MainMenu();
        menu.handleOption("3", testInventory, uselessFile);

        //boolean menuExited = true;


        Assertions.assertTrue(menu.exitMenu);
    }

}
