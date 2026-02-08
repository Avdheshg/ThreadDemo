//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
/*

    1. Get the current Thread of Main
    2. print it's class name
    3. Print all the state of this thread: Id, name, priority, state, threadGroup, isAlive
    4. What does sout(currThread) prints? - Check the Thread class toString()
    5. Create a new Child Thread which prints something at 1s interval

    6. Run the child thread
    7. How we would get to know which thread(parent or child) is running what
        Add some sout to main thread and child Th that will tell us which thread is executed when
        Diff bw start() and run()
    8. Create a Thread using Runnable Interface
    9. Till now, how many thread V have created till now, and how to distinguish each thread from other
        WHat are the different ways to creating a Thread and which is better

*/

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        Thread currThread = Thread.currentThread();
//        System.out.println(currThread.getClass().getName());

        // printThreadState(currThread);
//        System.out.println(currThread);

        // Child Th2
        CustomThread customThread = new CustomThread();
        customThread.start();

        // Child Th3 - using Runnable Interface
        Runnable childThread2 = () -> {
            for (int i = 0; i < 15; i++)
            {
                System.out.print(" c2 ");

                try
                {
                    TimeUnit.SECONDS.sleep(2);
                }
                catch (InterruptedException ex)
                {
                    ex.printStackTrace();
                }
            }
        };

        Thread myThread = new Thread(childThread2);
        myThread.start();


        for (int i = 0; i < 5; i++) {
            System.out.print("P ");
            try
            {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }


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


