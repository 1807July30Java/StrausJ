package com.revature.calc;

public class Calculator {

    //Take two ints and add
    public <T extends Number> double add(T a, T b) {
        return a.doubleValue() + b.doubleValue();
    }

    public <T extends Number> double subtract(T a, T b) {
        return a.doubleValue() - b.doubleValue();
    }

    public <T extends Number> double multiply(T a, T b) {
        return a.doubleValue() * b.doubleValue();
    }

    public <T extends Number> double divide(T a, T b) {
        return a.doubleValue() / b.doubleValue();
    }
}
