import java.io.Serializable;
import java.util.Arrays;

public class Bike implements Vehicle
    {
        private String brand_bike;

        public String getBrand()
        {
            return brand_bike;
        }

        public void setBrand(String newbrand_bike)
        {
            brand_bike = newbrand_bike;
        }
        private class Model implements Serializable, Cloneable
        {
            String name_model = null;
            double price = Double.NaN;
            Model prev = null;
            Model next = null;

            public Model(String newmodel_bike, double newprice_bike)
            {
                name_model = newmodel_bike;
                price = newprice_bike;
            }

            public String getNameModel()
            {
                return name_model;
            }

            public void setNameModel(String _name)
            {
                name_model = _name;
            }

            public double getPriceModel()
            {
                return price;
            }

            public void setPriceModel(double _price)
            {
                price = _price;
            }

            public Model() {
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
        private int size = 0;
        private Model head = new Model();
        private transient long lastModified;

        {
            head.next = head;
            head.prev = head;
            lastModified = System.currentTimeMillis();
            //lastModified = head.las
        }

        public void setModel(String old, String newm) throws NoSuchModelNameException, DuplicateModelNameException
        {
            Model q = head.next;
            while (q != head)
            {
                if(q.getNameModel().equals(newm))
                    throw new DuplicateModelNameException("Такая модель уже существует");
                else
                    q = q.next;
            }
            Model p = head.next;
            while (p.next!=head && !p.getNameModel().equals(old))
            {
                p = p.next;
            }
            if(p == head)
                throw new NoSuchModelNameException("Такой модели не существует");
            p.setNameModel(newm);
            lastModified = System.currentTimeMillis();
        }

        public String[] getAllModel()
        {
            int i = 0;
            String[] arr = new String[getLength()];
            Model p = head.next;
            while(p != head)
            {
                arr[i] = p.getNameModel();
                p = p.next;
                i++;
            }
            return arr;
        }

        public double getPrice(String namemodel) throws NoSuchModelNameException
        {
            Model p = head.next;
            while(p != head && !p.getNameModel().equals(namemodel))
            {
                p = p.next;
            }
            if(p == head)
                throw new NoSuchModelNameException("Такой модели не существует");
            return p.getPriceModel();
        }

        public void setPrice(String namemodell, double newpricee) throws NoSuchModelNameException
        {
            Model p = head.next;
            if(newpricee <= 0)
                throw new ModelPriceOutOfBoundsException("Цена не может быть отрицательной и равной нулю");
            else
            {
                while (p!=head && !p.getNameModel().equals(namemodell))
                {
                    p = p.next;
                }
                if(p == head)
                    throw new NoSuchModelNameException("Такой модели не существует");
                p.setPriceModel(newpricee);
                lastModified = System.currentTimeMillis();
            }
        }

        public double[] getAllPrice()
        {
            int i = 0;
            double[] allprice = new double[getLength()];
            Model p = head.next;
            while (p!=head)
            {
                allprice[i] = p.getPriceModel();
                p = p.next;
                i++;
            }
            return allprice;
        }

        public void addModel(String name, double cost) throws DuplicateModelNameException
        {
            Model q = head.next;
            if(cost <= 0)
                throw new ModelPriceOutOfBoundsException("Цена не может быть отрицательной и равной нулю");
            else
            {
                while(q != head)
                {
                    if(q.getNameModel().equals(name))
                        throw new DuplicateModelNameException("Такая модель уже существует");
                    else
                        q = q.next;
                }
            }
            Model p = new Model(name, cost);
            p.next = head;
            p.prev = head.prev;
            head.prev.next = p;
            head.prev = p;
            size++;
            lastModified = System.currentTimeMillis();
        }

        public void removeModel(String name) throws NoSuchModelNameException
        {
            Model p = head.next;
            while(p != head && !p.getNameModel().equals(name))
            {
                p = p.next;
            }
            if(p == head)
                throw new NoSuchModelNameException("Такой модели не существует");
            p.next.prev = p.prev;
            p.prev.next = p.next;
            size--;
            lastModified = System.currentTimeMillis();
        }

        public int getLength()
        {
            return size;
        }

        public Bike(String _brandBike, int s) throws DuplicateModelNameException
        {
            setBrand(_brandBike);
            for(int i = 0; i < s; i++)
            {
                addModel("model" + i, i + 100);
            }
        }

        public String toString()
        {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Brand: ");
            stringBuffer.append(brand_bike + "\n");
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
            if(obj == null)
                return false;
            if(this == obj)
                return true;
            if(obj instanceof Bike)
            {
                Bike bike = (Bike)obj;

                if(!bike.getBrand().equals(getBrand()))
                    return false;
                Model p = head.next;
                Model q = bike.head.next;

                while(p != head && q != bike.head)
                {
                    if(!q.name_model.equals(p.name_model) || q.price != p.price)
                        return false;
                    q = q.next;
                    p = p.next;
                }
                if (p == head && q == bike.head)
                {
                    return true;
                }

            }
            return false;
        }

        public int hashCode()
        {
            int result = brand_bike == null ? 0 : brand_bike.hashCode();
            result = 31 * result + Arrays.hashCode(getAllModel());
            result = 31 * result + Arrays.hashCode(getAllPrice());
            return result;
        }

        public Object clone() throws CloneNotSupportedException
        {

            try
            {
                Bike bike = (Bike) super.clone();
                bike.head = (Model)head.clone();
                Model p = head.next;
                Model q = bike.head;

                while(p != head)
                {
                    q.next = (Model)p.clone();
                    q.next.prev = q;
                    p = p.next;
                    q = q.next;
                }
                q.next = bike.head;
                bike.head.prev = q;

                return bike;
            }

            catch (CloneNotSupportedException e)
            {
                return null;
            }
        }
    }


