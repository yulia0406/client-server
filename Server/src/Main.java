import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main
{
    public static void main(String[] args) throws NoSuchModelNameException, IOException
    {
        Socket socket = null;
        ObjectInputStream objectInputStream;
        ObjectOutputStream objectOutputStream;
        ServerSocket serverSocket = null;

        try
        {
            serverSocket = new ServerSocket(3350);
            /*
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");
            socket = serverSocket.accept();
            System.out.println("Client accepted");
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            Vehicle[] vehicles = (Vehicle[]) objectInputStream.readObject();
            objectOutputStream.writeDouble(Transports.getAverage(vehicles));
            objectOutputStream.flush();
            objectOutputStream.close();
            objectInputStream.close();
            socket.close();
            */
            //*
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");
            socket = serverSocket.accept();
            Runnable r = new ThreadServer(socket);
            Thread thread = new Thread(r);
            thread.start();


        }

        //catch (ClassNotFoundException e)
        //{
          //  e.printStackTrace();
       // }

        catch (IOException e){
            e.printStackTrace();
        }
    }
}
