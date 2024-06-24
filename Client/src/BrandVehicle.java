public class BrandVehicle implements Runnable
{
    private Vehicle vehicle;
    public BrandVehicle(Vehicle vehicle)
    {
        this.vehicle = vehicle;
    }
    public void run()
    {
        System.out.println("Brand: " + vehicle.getBrand());
    }
}
