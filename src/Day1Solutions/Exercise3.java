/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Day1Solutions;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Magnus West Madsen
 */
public class Exercise3 {

    public static void main(String[] args) {
        Even e = new Even();

        Thread t1 = new Thread(()
                -> {
            System.out.println(e.next());
        });

        Thread t2 = new Thread(()
                -> {
            System.out.println(e.next());
        });
        
        Thread t3 = new Thread(()
                -> {
            System.out.println(e.next());
        });

        t1.start();
        t2.start();
        t3.start();
        
        try {
            
            t3.join();
            t2.join();
            t1.join();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Exercise3.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    static class Even {

        private int n = 0;

        public synchronized int next() {
            n++;
            n++;
            return n;
        }
    }
}
/* 

I dont get uneven numbers but i do get even numbers in the wrong order. 


*/