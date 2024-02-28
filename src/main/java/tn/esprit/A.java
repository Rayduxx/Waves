package tn.esprit;

public class A {
    private static A instance ;
    private A(){

    }
    public static A getInstance(){
       if (instance == null)
           instance = new A();

       return instance;
    }


}
