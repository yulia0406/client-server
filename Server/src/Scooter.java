import java.util.HashMap;
public class Scooter implements Vehicle
{
    private String brand;
    private HashMap<String, Double> models;

    public Scooter(String brand)
    {
        this.brand = brand;
        models = new HashMap<>();
    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String newbrand)
    {
        brand = newbrand;
    }

    public void setModel(String old, String newm) throws NoSuchModelNameException, DuplicateModelNameException
    {
        if(models.containsKey(newm))
            throw new DuplicateModelNameException("Такая модель уже существует");
        if(!models.containsKey(old))
            throw new NoSuchModelNameException("Такой модели не существует");
        models.put(newm, models.get(old));
        models.remove(old);
    }

    public String[] getAllModel()
    {
        String[] arr = new String[models.size()];
        int i = 0;
        for(String key : models.keySet())
        {
            arr[i] = key;
            i++;
        }
        return arr;
    }

    public double getPrice(String namemodel) throws NoSuchModelNameException
    {
        if(!models.containsKey(namemodel))
            throw new NoSuchModelNameException("Такой модели не существует");
        return models.get(namemodel);
    }

    public void setPrice(String namemodell, double newpricee) throws NoSuchModelNameException
    {
        int i = 0;
        if(newpricee <= 0)
            throw new ModelPriceOutOfBoundsException("Цена не может быть отрицательной и равной нулю");
        else
        {
            if(!models.containsKey(namemodell))
                throw new NoSuchModelNameException("Такой модели не существует");
            models.put(namemodell, newpricee);
        }

    }

    public double[] getAllPrice()
    {
        double[] allprice = new double[models.size()];
        int i = 0;
        for(double p : models.values())
        {
            allprice[i] = p;
            i++;
        }
        return allprice;
    }

    public void addModel(String name, double cost) throws DuplicateModelNameException
    {
        if(cost <= 0)
            throw new ModelPriceOutOfBoundsException("Цена не может быть отрицательной и равной нулю");
        else
        {
                if(models.containsKey(name))
                    throw new DuplicateModelNameException("Такая модель уже существует");
          models.put(name, cost);
        }
    }

    public void removeModel(String name) throws NoSuchModelNameException
    {
        if(!models.containsKey(name))
            throw new NoSuchModelNameException("Такой модели не существует");
        models.remove(name);
    }

    public int getLength()
    {
        int l = models.size();
        return l;
    }

    public Scooter(String _brand, int size)
    {
        brand = _brand;
        models = new HashMap<>();
        for(int i = 0; i < size; i++)
        {
            models.put("model" + i, i + 100.0);
        }
    }
}
