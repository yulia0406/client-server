public class RunnableGetModel implements Runnable
{
    private TransportSynchronizer transportSynchronizer;
    public RunnableGetModel(TransportSynchronizer transportSynchronizer)
    {
        this.transportSynchronizer = transportSynchronizer;
    }
    public void run()
    {
        while (transportSynchronizer.canPrintModel())
        {
            try {
                transportSynchronizer.printModel();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
