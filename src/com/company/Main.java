package com.company;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.util.ArrayList;

public class Main {

    public static int goalz;
    public static boolean lifeVacation;
    public static int noOfPeopleThatWantRetirement;
    public static String name;
    public static ArrayList<People> people = new ArrayList<>();
    public static double interest = 1.06;

    public static void main(String[] args) {
        // Bygg den recursivt den där den räknar år för år - when interestBearingCapital = goalz lifeVacation is true
        setUpConditions();
        calculateGoalAmount();

        //Bygg rekursiv grej
        

    }

    public static void assetCount(){
        if(lifeVacation){
            System.out.println("sweet");
        }
    }

    public static void setUpConditions(){
        groupConditions();
        individualConditions();
    }

    public static void groupConditions(){
        noOfPeopleThatWantRetirement = Integer.parseInt(JOptionPane.showInputDialog(null, "How many people want this vacation?"));
    }


    public static void individualConditions(){
        for(int i = 0; i < noOfPeopleThatWantRetirement; i++){
            name = JOptionPane.showInputDialog(null, "What is the " + (i+1) + " persons name?");
            if(workOrNot() == JOptionPane.YES_OPTION){
                workingConditions();
                continue;
            }
            nonWorkingConditions();
        }

    }

    public static int workOrNot(){
        return JOptionPane.showConfirmDialog(null,"Will they be freelancing?", "Please select", JOptionPane.YES_NO_OPTION);
    }

    public static void workingConditions(){
        People wp = new People();
        wp.setName(name);
        wp.setAge(age());
        wp.setAssets(seed());
        wp.setInvoiceAmount(income());
        wp.setFuturePayout(futurePayOut());
        people.add(wp);
    }

    public static void nonWorkingConditions(){
        People nwp = new NonWorkingPerson();
        nwp.setName(name);
        nwp.setAge(age());
        nwp.setFuturePayout(futurePayOut());
        people.add(nwp);
    }

    public static int age(){
        return Integer.parseInt(JOptionPane.showInputDialog(null, "How old is " + name +"?"));
    }

    public static int seed(){
        return Integer.parseInt(JOptionPane.showInputDialog(null, "What is " + name + "s seed?"));
    }

    public static int income(){
        return Integer.parseInt(JOptionPane.showInputDialog(null, "How much do " + name + " invoice each month? (based on 11 months of working)"));
    }

    public static int futurePayOut(){
        return Integer.parseInt(JOptionPane.showInputDialog(null, "What does " + name + " want for future monthly payout?"));
    }

    private static void calculateGoalAmount() {
        for(People p : people){
            goalz += p.getFuturePayout();
        }
        goalz = (int) (goalz*12/0.06);
    }

    /*
    public static int interest(){

    }

    public static int taxes(){

    }*/
}
