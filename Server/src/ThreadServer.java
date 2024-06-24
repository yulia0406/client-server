import java.net.Socket;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ThreadServer implements Runnable
{
    private Socket socket;
    public ThreadServer(Socket s){
        this.socket = s;
    }

    public void run()
    {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            Vehicle[] transports = (Vehicle[]) objectInputStream.readObject();
            objectOutputStream.writeDouble(Transports.getAverage(transports));
            objectOutputStream.flush();
            socket.close();
        }
        catch (IOException|ClassNotFoundException|NoSuchModelNameException e)
        {
            e.printStackTrace();
        }
    }
}
