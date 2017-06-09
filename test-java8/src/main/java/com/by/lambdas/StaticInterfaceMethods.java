package com.by.lambdas;

interface Greeter {
    static Greeter newInstance(String greeted) {
        return new Greeter() {
            public String greet() {
                return "Hello, " + greeted;
            }
        };
    }

    String greet();
}

public class StaticInterfaceMethods {
    public static void main(String[] args) {
        Greeter worldGreeter = Greeter.newInstance("World");
        System.out.println(worldGreeter.greet());
    }
}
