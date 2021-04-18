package com.company;

import java.util.*;

class display{
    static Scanner sc=new Scanner(System.in);

    // clearscreen function used to clear the console after every search
    public static void clearScreen() {
        try{
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        } catch(Exception e){}
    }

    // sleep function used to make the thread to pause for particular time and is used where ever required
    public static void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.print("Handled");
        }
    }

    public static void sleep1(){
         try {
                Thread.sleep(4000);
            } catch (InterruptedException e){
                System.out.print("Handled");
            }
        }

        public static void homepage(){
            int choice;
            System.out.println("\n************Employee Management Portal*************\n\n");
            System.out.println("=======================Welcome======================\n");
            System.out.print("1. View Employee Details\n2. View Salary Details\n3. Exit\nPress Any Key to Continue.....\nYour choice : ");
            choice=sc.nextInt();
            switch(choice){
                case 1:
                    getempdetails();
                    break;
                case 2:
                    getsalary();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nChoose from the above numbers only.....\n");
                    sleep();
                    clearScreen();
            }
        }

        public static void getempdetails(){
            clearScreen();
            System.out.println("\n************Employee Management Portal*************\n\n");
            Main main1=new Main();
            main1.loaddata(false);// calling loaddata function which is in the main.java file
            sleep1();
        }

        public static void getsalary(){
            clearScreen();
            System.out.println("\n************Employee Management Portal*************\n\n");
            Main main1=new Main();
            main1.loaddata(true);// calling loaddata function which is in the main.java file
            sleep1();
        }

        //main function
        public static void main(String args[]){
            int choice;
            while(true){
                clearScreen();
                homepage();
            }
        }
    }