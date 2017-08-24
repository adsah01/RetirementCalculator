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
    public static int totalAssets;
    public static int nrOfMonths;
    public static int receivedInterest;

    public static void main(String[] args) {
        // Bygg den recursivt den där den räknar år för år - when interestBearingCapital = goalz lifeVacation is true
        setUpConditions();
        calculateGoalAmount();

        if(possibleConditions()){
            assetCount();
        }

        else {
            System.out.println("you guys want to much salary!");
        }


        System.out.println(receivedInterest);
        System.out.println(nrOfMonths);
        

    }

    public static int assetCount(){
        nrOfMonths+=1;
        for(People p: people){
            p.setAssets(p.getAssets() + p.getInvoiceAmount() - p.getSalary());
            if(nrOfMonths % 12==0){
                calculateInterest(p);
            }
        }
        for(People p: people){
            totalAssets += p.getAssets();
        }
        System.out.println("Month: " + nrOfMonths + "   Assets: " + totalAssets);

        if(totalAssets >= goalz){
            return nrOfMonths;
        }
        totalAssets = 0;
        return assetCount();
    }

    public static void setUpConditions(){
        groupConditions();
        individualConditions();
    }

    public static boolean possibleConditions(){
        int salaryCost = 0;
        int invoiceAmount = 0;
        for(People p : people){
            salaryCost += (int) ((p.getSalary()/0.7)+1);
            invoiceAmount += p.getInvoiceAmount();
        }
        if(invoiceAmount > salaryCost){
            return true;
        }
        return false;
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
        wp.setSalary(salary());
        wp.setFuturePayout(futurePayOut());
        people.add(wp);
    }

    public static void nonWorkingConditions(){
        People nwp = new NonWorkingPerson();
        nwp.setName(name);
        nwp.setAge(age());
        nwp.setSalary(salary());
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

    public static int salary(){
        return (int)((Integer.parseInt(JOptionPane.showInputDialog(null, "What does " + name + " want for salary?"))/0.7)+1);
    }

    private static void calculateGoalAmount() {
        for(People p : people){
            goalz += p.getFuturePayout()/0.7;
        }
        goalz = (int) (goalz*12/0.06);
    }

    public static void calculateInterest(People p){
        receivedInterest += p.getAssets()*0.06;
        p.setAssets((int) (p.getAssets()*interest));
    }

    /*
    public static int taxesAndCompanyLoan(){

    }*/
}
