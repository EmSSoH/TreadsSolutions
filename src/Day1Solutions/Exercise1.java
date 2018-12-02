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
 * @author magn2265
 */

public class Exercise1
{
    public static void main(String[] args) throws InterruptedException
    {

        Runnable r = new Runnable() {
            @Override
            public void run(){
            System.out.println("Fron inside the thread");
            }
        };
        
        
        Thread t1 = new Thread(() ->
        {
            int count = 0;
            while(count < 1e9){
             count++;
            System.out.println("thread 1 count is: " + count);
            }
        });
        
        Thread t2 = new Thread (() ->
        {
            try
            {
            System.out.println("thread 2 count is: 1");
            Thread.sleep(1000);
            System.out.println("thread 2 count is: 2"); 
            Thread.sleep(1000);
            System.out.println("thread 2 count is: 3"); 
            Thread.sleep(1000);
            System.out.println("thread 2 count is: 4"); 
            Thread.sleep(1000);
            System.out.println("thread 2 count is: 5");
            } catch (InterruptedException ex)
            {
                Logger.getLogger(Exercise1.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Thread t3 = new Thread (() ->
        {
            int count = 9;
            
            while(!Thread.currentThread().isInterrupted())
            {
                count++;
                System.out.println("thread 3 count is: "+ count);
                 try
                {
                    Thread.sleep(3000);
                } catch (InterruptedException ex)
                {
                    System.out.println("interrupted in sleep");
                    Thread.currentThread().interrupt();
                } 
            }
            System.out.println("t3 end loop");
        });
        
        
        t1.start();
        t2.start();
        t3.start();
        Thread.sleep(10000);
        t3.interrupt();
        t3.join();
        t2.join();
        t1.join();
        System.out.println("ENDING the program...");
    }
    
    static class myRun implements Runnable
    {
        private boolean run = true;
        private int Counter; 
        public myRun(int counter, boolean run){
            this.Counter = counter;
            this.run = run;
        }
        @Override
        public void run(){
            
        }
    }
}
