import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Transports
{
    public static double getAverage(Vehicle trans)
    {
        double [] array = trans.getAllPrice();
        double summ = 0;
        for (int i = 0; i < trans.getLength(); i++)
        {
            summ = summ + array[i];
        }

        return summ/trans.getLength();
    }

    public static double getAverage(Vehicle... trans) throws NoSuchModelNameException
    {
        double summ = 0;

        for (int i = 0; i < trans.length; i++)
        {
            String [] array = trans[i].getAllModel();
            for(int j = 0; j < trans[i].getLength(); j++)
            {
                summ = summ + trans[i].getPrice(array[j]);
            }

        }

        return summ/trans.length;
    }


    public static void printName(Vehicle trans)
    {
        String [] array = trans.getAllModel();
        for(int i = 0; i < trans.getLength(); i++)
        {
            System.out.println("Model: " + array[i]);
        }
    }

    public static void printPrice(Vehicle trans)
    {
        double [] array = trans.getAllPrice();
        for(int i = 0; i < trans.getLength(); i++)
        {
            System.out.println("Price: " + array[i]);
        }
    }

    public static void outputVehicle(Vehicle v, OutputStream out) throws IOException
    {
        DataOutputStream dataOutputStream = new DataOutputStream(out);
        byte[] bytes = v.getBrand().getBytes();
        dataOutputStream.writeInt(bytes.length);
        dataOutputStream.write(bytes);
        dataOutputStream.writeInt(v.getLength());

        String[] array = v.getAllModel();
        double[] array_price = v.getAllPrice();

        for(int i = 0; i < v.getLength(); i++)
        {
            bytes = array[i].getBytes();
            dataOutputStream.writeInt(bytes.length);
            dataOutputStream.write(bytes);
            dataOutputStream.writeDouble(array_price[i]);
        }

    }

    public static Vehicle inputVehicle(InputStream in) throws IOException, DuplicateModelNameException
    {
        Vehicle vehicle = null;
        DataInputStream dataInputStream = new DataInputStream(in);
        int len = dataInputStream.readInt();
        byte[] bytes = new byte[len];
        for(int i = 0; i < len; i++)
        {
            bytes[i] = dataInputStream.readByte();
        }
        String brand = new String(bytes);
        int size = dataInputStream.readInt();
        vehicle = new Car(brand);

        double price;
        for(int i = 0; i < size; i++)
        {
            len = dataInputStream.readInt();
            bytes = new byte[len];
            for(int j = 0; j < len; j++)
            {
                bytes[j] = dataInputStream.readByte();
            }
            String model = new String(bytes);
            price = dataInputStream.readDouble();

            vehicle.addModel(model, price);
        }
        return vehicle;
    }

    public static void writeVehicle(Vehicle v)
    {
        System.out.printf("\nBrand: %s\n", v.getBrand());
        String[] model = v.getAllModel();
        double[] price = v.getAllPrice();
        for(int i = 0; i < v.getLength(); i++)
        {
            System.out.printf("\nModel: %s\n ", model[i]);
            System.out.printf("Price: %f\n", price[i]);
        }

    }

    public static Vehicle readVehicle() throws IOException, DuplicateModelNameException
    {
        Scanner scanner = new Scanner(System.in);
        String brand = scanner.nextLine();
        int size = Integer.parseInt(scanner.nextLine());
        Vehicle vehicle = new Car(brand, size);

        String model;
        double price;
        for(int i = 0; i < size; i++)
        {
            model = scanner.nextLine();
            price = Double.parseDouble(scanner.nextLine());
            vehicle.addModel(model, price);
        }
        return vehicle;
    }

    public static Vehicle createTransports(String brand, int size_model, Vehicle vehicle)
    {
        try
        {
            Class ts = vehicle.getClass();
            Constructor constructor = ts.getConstructor(String.class, int.class);
            Vehicle vh = (Vehicle) constructor.newInstance(brand, size_model);
            return vh;
        }
        catch (NoSuchMethodException e)
        {
            System.out.println(e);
            return null;
        }
        catch (InstantiationException e)
        {
            System.out.println(e);
            return null;
        }

        catch (IllegalAccessException e)
        {
            System.out.println(e);
            return null;
        }

        catch (InvocationTargetException e)
        {
            System.out.println(e);
            return null;
        }
    }



}
