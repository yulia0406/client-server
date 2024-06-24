import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;

public class ReadingFromFile implements Runnable
{
    private Vehicle vehicle;
    private String nameFile;
    private ArrayBlockingQueue<Vehicle> arrayBlockingQueue;

    public ReadingFromFile(String nameFile, ArrayBlockingQueue<Vehicle> arrayBlockingQueue)
    {
        this.nameFile = nameFile;
        this.arrayBlockingQueue = arrayBlockingQueue;
    }

    public void run()
    {
        try {
            FileReader fileReader = new FileReader(nameFile);
            BufferedReader in = new BufferedReader(fileReader);
            String brand = in.readLine();
            vehicle = new Car(brand);
            arrayBlockingQueue.add(vehicle);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
