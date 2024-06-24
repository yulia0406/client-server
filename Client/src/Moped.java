import java.util.Arrays;
import java.util.LinkedList;

public class Moped implements Vehicle
{
    private String brand;
    private LinkedList<Model> models;

    public Moped(String brand)
    {
        this.brand = brand;
        models = new LinkedList<>();
    }

    private class Model
    {
        private String model;
        private double price;

        Model(String newmodel, double newprice) {
            model = newmodel;
            price = newprice;
        }

        public String getNameModel() {
            return model;
        }

        public void setNameModel(String _name) {
            model = _name;
        }

        public double getPriceModel() {
            return price;
        }

        public void setPriceModel(double _price) {
            price = _price;
        }
    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String newbrand)
    {
        brand = newbrand;
    }

    public String[] getAllModel()
    {
        String[] arr = new String[models.size()];
        for(int i = 0; i< models.size(); i++)
        {
            arr[i] = models.get(i).getNameModel();
        }
        return arr;
    }

    public void setModel(String old, String newm) throws NoSuchModelNameException, DuplicateModelNameException
    {
        if(Arrays.asList(getAllModel()).contains(newm))
            throw new DuplicateModelNameException("Такая модель уже существует");
        if(!Arrays.asList(getAllModel()).contains(old))
            throw new NoSuchModelNameException("Такой модели не существует");
        models.get(Arrays.asList(getAllModel()).indexOf(old)).setNameModel(newm);
    }

    public double getPrice(String namemodel) throws NoSuchModelNameException
    {
        if(!Arrays.asList(getAllModel()).contains(namemodel))
            throw new NoSuchModelNameException("Такой модели не существует");
        return models.get(Arrays.asList(getAllModel()).indexOf(namemodel)).getPriceModel();
    }

    public void setPrice(String namemodell, double newpricee) throws NoSuchModelNameException
    {
        int i = 0;
        if(newpricee <= 0)
            throw new ModelPriceOutOfBoundsException("Цена не может быть отрицательной и равной нулю");
        else
        {
            if(!Arrays.asList(getAllModel()).contains(namemodell))
                throw new NoSuchModelNameException("Такой модели не существует");
            models.get(Arrays.asList(getAllModel()).indexOf(namemodell)).setPriceModel(newpricee);
        }
    }

    public double[] getAllPrice()
    {
        double[] allprice = new double[models.size()];
        for (int i = 0;i < models.size();i++)
        {
            allprice[i] = models.get(i).getPriceModel();
        }
        return allprice;
    }

    public void addModel(String name, double cost) throws DuplicateModelNameException
    {
        if(cost <= 0)
            throw new ModelPriceOutOfBoundsException("Цена не может быть отрицательной и равной нулю");
        else
        {
            if(Arrays.asList(getAllModel()).contains(name))
                throw new DuplicateModelNameException("Такая модель уже существует");
            models.add(new Model(name,cost));
        }
    }

    public void removeModel(String name) throws NoSuchModelNameException
    {
        if(!Arrays.asList(getAllModel()).contains(name))
            throw new NoSuchModelNameException("Такой модели не существует");
        models.remove(Arrays.asList(getAllModel()).indexOf(name));
    }

    public int getLength()
    {
        int l = models.size();
        return l;
    }

    public Moped(String _brand, int size)
    {
        brand = _brand;
        models = new LinkedList<>();
        for(int i = 0; i < size; i++)
        {
            models.addLast(new Model("model" + i, i + 100.0));
        }
    }
}
