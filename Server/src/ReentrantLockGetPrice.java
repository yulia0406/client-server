import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockGetPrice implements Runnable
{
    private Vehicle vehicle;
    private ReentrantLock reentrantLock;
    public ReentrantLockGetPrice(Vehicle vehicle, ReentrantLock reentrantLock)
    {
        this.vehicle = vehicle;
        this.reentrantLock = reentrantLock;
    }
    public void run()
    {
        reentrantLock.lock();

            try {
                double [] price = vehicle.getAllPrice();
                for(double i : price)
                {
                    System.out.println("Price: " + i);
                }
            }
            finally
            {
                reentrantLock.unlock();
            }
    }
}
