public class ThreadGetModel extends Thread
{
    private Vehicle vehicle;
    public ThreadGetModel(Vehicle vehicle)
    {
        this.vehicle = vehicle;
    }
    public void run()
    {
        String [] model = vehicle.getAllModel();
        for(String i : model)
        {
            System.out.println("Model: " + i);
        }
    }
}
