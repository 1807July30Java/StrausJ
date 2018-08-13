package com.revature.main;

import com.revature.calc.*;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        Scanner kb = new Scanner(System.in);
        boolean run = true;

        while(run) {
            System.out.print("Welcome to Calculate\nWe can do the following:\n\tAdd - a\n\tSubtract - s\n\tDivide - d\n\tMultiply - m\n");
            System.out.print("Please enter an operation (a, s, d, m, exit): ");
            String opp = kb.nextLine();
            if (opp.equals("exit")) {
                System.out.println("Goodbye!");
                run = false;
                kb.close();
                return;
            }
            System.out.print("Please enter your first number: ");
            String num1 = kb.nextLine();
            System.out.print("Please enter your second number: ");
            String num2 = kb.nextLine();
            Number numa = null;
            Number numb = null;
            //Attempt to parse out numbers
            try {
                numa = NumberFormat.getInstance().parse(num1);
                numb = NumberFormat.getInstance().parse(num2);

            } catch (ParseException e) {
                System.out.print("Those weren't Numbers!");
            }

            try {
                switch (opp.toLowerCase()) {
                    case "a":
                        System.out.println(calc.add(numa, numb));
                        break;
                    case "s":
                        System.out.println(calc.subtract(numa, numb));
                        break;
                    case "d":
                        System.out.println(calc.divide(numa, numb));
                        break;
                    case "m":
                        System.out.println(calc.multiply(numa, numb));
                        break;
                    default:
                        System.out.println("That's not an operation...");
                        break;
                }
            } catch (NullPointerException e) {
                System.out.println(" Please try again");
            }
        }
        kb.close();
    }
}
