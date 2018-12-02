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
public class Exercise2 {

    public static void main(String[] args) {

        Factory f = new Factory();

        f.threadFactory(3);

    }
}

class Factory {

    public void threadFactory(int n) {

        for (int i = 1; i <= n; i++) {

            MyThread temp = new MyThread(i);

            temp.start();
            try {
                temp.join(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class MyThread extends Thread {

    int i;

    public MyThread(int s) {
        i = s;
    }

    @Override
    public void run() {
        int count = 0;
        while (count < 100) {
            count++;
            System.out.println("Thread #" + i + " count is: " + count);
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
