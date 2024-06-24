import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main
{
    public static void main(String[] args)
    {
        Socket socket = null;
        ObjectInputStream objectInputStream;
        ObjectOutputStream objectOutputStream;
        Vehicle car1 = new Car("Lada", 3);
        Vehicle car2 = new Car("Cherry", 3);
        Vehicle car3 = new Car("Kia", 3);
        Vehicle[] cars = new Vehicle[] {car1, car2, car3};

        try
        {
            socket = new Socket("localhost", 3350);
            System.out.println("Connected");
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            objectOutputStream.writeObject(cars);
            objectOutputStream.flush();
            System.out.println("Average: " + objectInputStream.readDouble());
            objectOutputStream.close();
            objectInputStream.close();
            socket.close();
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
}
