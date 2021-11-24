package com.company;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Random;

public class Main {

    private static HashMap<String, Integer> storage = new HashMap<String, Integer>();
    private static String[] prizes = {"teddy", "goldfish", "keyring", "sweets", "toy car", "rubber duck"};

    public static String choice() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Would you like to check a raffle ticket or purchase one. (One ticket per person)");
        String choice = "";
        boolean fail = true;
        try {
            while (fail) {
                String userChoice = bufferedReader.readLine();
                switch (userChoice) {
                    case "purchase":
                        file();
                        fail = false;
                        break;
                    case "check":
                        fail = false;
                        check(storage);
                        break;
                    default:
                        System.out.println("That was not one of the options. PLease enter 'check' or 'purchase'");
                }
            }
        } catch (Exception e) {
            System.out.println("There has been an error. PLease try again.");

        }
        return choice;

    }

    public static boolean prime(int number) {
        boolean prime = true;
        for (int i = 2; i < number / 2; i++) {
            if (number % i == 0) {
                prime = false;
                break;
            }
        }
        return prime;
    }

    public static HashMap<String, Integer> file() {
        Random random = new Random();
        boolean repeat = true;
        while (repeat) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("What is your name?");
            try {
                String userName = bufferedReader.readLine();
                int randomNumber = random.nextInt(500
                );
                PrintWriter file = new PrintWriter("Storage file");
                file.println(userName);
                file.println(randomNumber);
                file.close();
                storage.put(userName, randomNumber);
                System.out.println("Your raffle ticket number is " + randomNumber);
                System.out.println("Would you like to go again");
                String again = bufferedReader.readLine();
                if (!again.equals("yes")) {
                    repeat = false;
                }
            } catch (Exception e) {
                System.out.println("Sorry");
            }
        }
        System.out.println(storage);
        return storage;
    }

    public static void check(HashMap list) {
        Random random = new Random();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("What name is the ticket under");
        try {
            String userName = bufferedReader.readLine();
            if (storage.containsKey(userName)) {
                if (prime(storage.get(userName))) {
                    System.out.println("CONGRATULATIONS!!! You have won. Your raffle number " + storage.get(userName) + " was the one which won.");
                    System.out.println("Your prize is a " + prizes[random.nextInt(prizes.length)]);
                }else{
                    System.out.println("Sorry but your ticket hasn't won. Maybe try again");
                }
            }
        } catch (Exception e) {
            System.out.println("I'm sorry there has been an error" + e);
        }
    }

    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        boolean repeating = true;
        while (repeating) {
            choice();
            System.out.println("Would you like another service");
            try {
                String again = bufferedReader.readLine();
                if(again.equals("no")){
                    repeating = false;
                }
            } catch (Exception e) {
                System.out.println("There has been an error " + e);
            }
        }

    }
}
