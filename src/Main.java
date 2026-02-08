//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
/*

    === 4. Interacting and Manipulating a running thread   ===
    1. Make the main thread print something after 1 sec - print before and after completion of this thread
    2. Make a child thread to print 10 dots each after .5s - print before and after completion of this thread
    3. Start both the threads while updating the user for each action

    4. Interrupt the child thread from the main thread after sleeping the main thread for 2s

    5. In the second scenario of interrupting the child thread - stop the child thread if it exceeds 2s
        Print the state of the child TD in the try(running) and catch(interrupted state) of the exception
        While the child TD is running(4s), make sure to check the state of the Child TD from the main TH

*/

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        System.out.println("Inside the main thread");

        Thread mainThread = Thread.currentThread();
        System.out.println(mainThread.getName() + " thread is sleeping for 1s");
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException ex)
        {
            ex.printStackTrace();
        }

        Runnable childThreadRunnable = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " will take 10 dots to run");
            for (int i = 0; i < 10; i++)
            {
                System.out.print(" . ");
                try {
                    Thread.sleep(500);
                    System.out.println("A. State = " + Thread.currentThread().getState());
                } catch (Exception ex) {
                    System.out.println("Oops, child Thread interrupted!");
                    System.out.println("A1. State = " + Thread.currentThread().getState());
                    return;
                }
            }
            System.out.println("\n" + threadName + " completed!");
        };

        Thread childThread = new Thread(childThreadRunnable);
        System.out.println("Starting: " + childThread.getName());
        childThread.start();

        System.out.println("Main thread would continue here");

        // interrupting the child Thread after 2s sleep of main Thread
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
//        childThread.interrupt();

        // stopping the child TH after 2 sec
        long now = System.currentTimeMillis();
        while (childThread.isAlive())
        {
            System.out.println("\n Waiting for child thread to complete");
            try {
                Thread.sleep(1000);
                System.out.println("B. State = " + childThread.getState());

                if (System.currentTimeMillis() - now > 2000)
                    childThread.interrupt();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        System.out.println("C. State = " + childThread.getState());

    }

    private static void printThreadState(Thread thread)
    {
        System.out.println("================================");
        System.out.println("threadId: " + thread.threadId());
        System.out.println("name: " + thread.getName());
        System.out.println("priority: " + thread.getPriority());
        System.out.println("state: " + thread.getState());
        System.out.println("threadGroup: " + thread.getThreadGroup());
        System.out.println("isAlive: " + thread.isAlive());
        System.out.println("================================");
    }
}


