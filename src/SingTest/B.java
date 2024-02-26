/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SingTest;

/**
 *
 * @author macbook
 */
public class B {

    private int x;
    private static B instance; 
            
    private B(){
        System.out.println("Je suis B");
    }
    
    
    public static B getInstance(){
        if (instance == null)
            instance = new B();
        return instance;
    }
    

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
    
    
    
    
}
