package com.by;

/** Hello world! */
public final class App {
  /** */
  private App() { }

  /** @param args args */
  public static void main(String[] args) {
    String a = "bob";
    a.replace('b', 'p');
    if (a.equals("pop")) {
      System.out.println(a);
    }
    System.out.println("Hello World!");
  }
}
