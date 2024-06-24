public class TransportSynchronizer
{
    private Vehicle v;
    private volatile int current = 0;
    private Object lock = new Object();
    private boolean set = false;

    public TransportSynchronizer(Vehicle v) {
        this.v = v;
    }

    public double printPrice() throws InterruptedException {
        double val;
        synchronized(lock) {
            double [] p = v.getAllPrice();
            if (!canPrintPrice()) throw new InterruptedException();
            while (set)
                lock.wait();
            val = p[current];
            System.out.println("Print price: " + val);
            set = true;
            lock.notifyAll();
        }
        return val;
    }

    public void printModel() throws InterruptedException {
        synchronized(lock) {
            String [] s = v.getAllModel();
            if (!canPrintModel()) throw new InterruptedException();
            while (!set)
                lock.wait();
            System.out.println("Print model: " + s[current++]);
            set = false;
            lock.notifyAll();
        }
    }

    public boolean canPrintModel() {
        return current < v.getLength();
    }

    public boolean canPrintPrice() {
        return (!set && current < v.getLength()) || (set && current < v.getLength() - 1);
    }
}
