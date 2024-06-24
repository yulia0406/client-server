public class ThreadGetPrice extends Thread
{
    private Vehicle vehicle;
    public ThreadGetPrice(Vehicle vehicle)
    {
        this.vehicle = vehicle;
    }
    public void run()
    {
        double [] price = vehicle.getAllPrice();
        for(double i : price)
        {
            System.out.println("Price: " + i);
        }
    }
}
