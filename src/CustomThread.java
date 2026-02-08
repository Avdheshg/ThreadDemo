
public class CustomThread extends Thread {
    @Override
    public void run()
    {
        for (int i = 0; i < 10; i++) {
            System.out.print(" c1 ");

            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
