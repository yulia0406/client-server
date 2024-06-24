public class RunnableGetPrice implements Runnable
{
    private TransportSynchronizer transportSynchronizer;
    public RunnableGetPrice(TransportSynchronizer transportSynchronizer)
    {
        this.transportSynchronizer = transportSynchronizer;
    }
    public void run()
    {
        while (transportSynchronizer.canPrintPrice())
        {
            try {
                    transportSynchronizer.printPrice();
                 }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
