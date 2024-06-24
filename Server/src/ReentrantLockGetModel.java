import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockGetModel implements Runnable
{
    private Vehicle vehicle;
    private ReentrantLock reentrantLock;
    public ReentrantLockGetModel(Vehicle vehicle, ReentrantLock reentrantLock)
    {
        this.vehicle = vehicle;
        this.reentrantLock = reentrantLock;
    }
    public void run()
    {
        reentrantLock.lock();

        try {
            String [] model = vehicle.getAllModel();
            for(String i : model)
            {
                System.out.println("Model: " + i);
            }
        }
        finally
        {
            reentrantLock.unlock();
        }
    }
}
