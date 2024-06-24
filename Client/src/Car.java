import java.io.Serializable;
import java.util.Arrays;

public class Car implements Vehicle
{
    private String brand;

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String newbrand)
    {
        brand = newbrand;
    }

    private class Model  implements Serializable, Cloneable
    {
        private String model;
        private double price;

        Model(String newmodel, double newprice) {
            model = newmodel;
            price = newprice;
        }

        public String getNameModel()
        {
            return model;
        }

        public void setNameModel(String _name)
        {
            model = _name;
        }

        public double getPriceModel()
        {
            return price;
        }

        public void setPriceModel(double _price)
        {
            price = _price;
        }

        public Object clone()
        {
            Model model =null;
            try
            {
                model = (Model)super.clone();
            }
            catch(CloneNotSupportedException exception)
            {
                return model;
            }
            return model;
        }
    }

    private Model[] models;

    public void setModel(String old, String newm) throws NoSuchModelNameException, DuplicateModelNameException
    {
        int i = 0;

        while (i<models.length)
        {
            if(models[i].getNameModel().equals(newm))
                throw new DuplicateModelNameException("Такая модель уже существует");
            i++;
        }
        i=0;
        while (i<models.length && !models[i].getNameModel().equals(old))
        {
            i++;
        }
        if(i == models.length)
        {
            throw new NoSuchModelNameException("Такой модели не существует");
        }

        models[i].setNameModel(newm);
    }

    public String[] getAllModel()
    {
        String[] arr = new String[models.length];
        for(int i = 0; i< arr.length; i++)
        {
            arr[i] = models[i].getNameModel();
        }
        return arr;
    }

    public double getPrice(String namemodel) throws NoSuchModelNameException
    {
        double answer = 0;
        int i = 0;
        while (i < getLength() && !models[i].getNameModel().equals(namemodel))
        {
            i++;
        }
        if(i == getLength())
            throw new NoSuchModelNameException("Такой модели не существует");

        answer = models[i].getPriceModel();
        return answer;
    }

    public void setPrice(String namemodell, double newpricee) throws NoSuchModelNameException
    {
        int i = 0;
        if(newpricee <= 0)
            throw new ModelPriceOutOfBoundsException("Цена не может быть отрицательной и равной нулю");
        else
        {
            while(i < getLength() && !models[i].getNameModel().equals(namemodell))
            {
                i++;
            }

            if(i == getLength())
                throw new NoSuchModelNameException("Такой модели не существует");

            models[i].setPriceModel(newpricee);
        }

    }

    public double[] getAllPrice()
    {
        double[] allprice = new double[models.length];
        for(int i = 0; i< allprice.length; i++)
        {
            allprice[i] = models[i].getPriceModel();
        }
        return allprice;
    }

    public void addModel(String name, double cost) throws DuplicateModelNameException
    {
        int i = 0;
        if(cost <= 0)
            throw new ModelPriceOutOfBoundsException("Цена не может быть отрицательной и равной нулю");
        else
        {
            while (i < getLength())
            {
                if(models[i].getNameModel().equals(name))
                    throw new DuplicateModelNameException("Такая модель уже существует");
                i++;
            }
            Model[] model = Arrays.copyOf(models,models.length+1);
            model[models.length] = new Model(name, cost);
            models = model;
        }
    }

    public void removeModel(String name) throws NoSuchModelNameException
    {
        int _i = 0;
        int i = 0;
        while (i < getLength() && !models[i].getNameModel().equals(name))
        {
            i++;
        }
            if(i == getLength())
                throw new NoSuchModelNameException("Такой модели не существует");
            _i = i;
            System.arraycopy(models, _i + 1, models, _i,getLength() - _i - 1);
            models = Arrays.copyOf(models, getLength() - 1);

    }

    public int getLength()
    {
        int l = models.length;
        return l;
    }

    public Car(String _brand, int size)
    {
        brand = _brand;
        models = new Model[size];
        for(int i = 0; i < models.length; i++)
        {
            models[i] = new Model("model" + i, i + 100);
        }
    }

    public Car(String brand)
    {
        this.brand = brand;
        models = new Model[0];
    }

    public String toString()
    {
        StringBuffer stringBuffer = new StringBuffer(); //создаем объект класса StringBuffer
        stringBuffer.append("Brand: "); //Метод append() добавляет подстроку в конец StringBuffer
        stringBuffer.append(brand + "\n");
        for(int i = 0; i < getLength(); i++)
        {
            stringBuffer.append("Model: ");
            stringBuffer.append(getAllModel()[i] + "\n");
            stringBuffer.append("Price: ");
            stringBuffer.append(getAllPrice()[i] + "\n");
        }
        return stringBuffer.toString();
    }

    public boolean equals(Object obj)
    {
        if(obj == null) //проверяем, что объект не пустой
            return false;
        if(this == obj) //проверяем, указывают ли ссылки на один и тот же объект(объект равен самому себе)
            return true;
        if(obj instanceof Car)//создан ли объект на основе класса Car
        {
            Car car = (Car)obj; //приводим к типу Car

            if(!car.getBrand().equals(getBrand()))
                return false;
            for(int i = 0; i < getLength(); i++)
            {
                if(!car.getAllModel()[i].equals(getAllModel()[i]) || car.getAllPrice()[i] != getAllPrice()[i])
                    return false;
            }
            return true;
        }
        return false;
    }


    public int hashCode()
    {
        int result = brand == null ? 0 : brand.hashCode();
        result = 31 * result + (models != null ? Arrays.hashCode(getAllModel()) : 0);
        result = 31 * result + (models != null ? Arrays.hashCode(getAllPrice()) : 0);
        return result;
    }

    public Object clone() throws CloneNotSupportedException
    {

        try
        {
            Car car = (Car) super.clone(); //super вызывает метод базового класса(подкласс переопределяет базовый класс)
            car.models = models.clone(); //клонируется массив размерности массива models
            for (int i = 0; i < getLength(); i++)
            {
                car.models[i] = (Model)models[i].clone();//клонируется содержимое каждого элемента массива
            }
            return car;
        }

        catch (CloneNotSupportedException e)
        {
            return null;
        }
    }



}
