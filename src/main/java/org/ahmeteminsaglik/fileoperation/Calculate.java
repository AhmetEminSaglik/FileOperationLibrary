package org.ahmeteminsaglik.fileoperation;

public class Calculate {
    public Calculate() {
        System.out.println("constructor is invoked");
    }

    public int sum(int a, int b) {
        return a + b;
    }

    public int minus(int a, int b) {
        return a - b;
    }

    public int divide(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        return a / b;

    }

    public int multiply(int a, int b) {
        return a * b;
    }


}
