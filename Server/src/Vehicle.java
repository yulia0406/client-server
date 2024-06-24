import java.io.Serializable;

public interface Vehicle extends Serializable, Cloneable
{
    String getBrand();

    void setBrand(String newbrand);

    void setModel(String old, String newm) throws NoSuchModelNameException, DuplicateModelNameException;

    String[] getAllModel();

    double getPrice(String namemodel) throws NoSuchModelNameException;

    void setPrice(String namemodell, double newpricee) throws NoSuchModelNameException, ModelPriceOutOfBoundsException;

    double[] getAllPrice();

    void addModel(String name, double cost) throws DuplicateModelNameException, ModelPriceOutOfBoundsException;

    void removeModel(String name) throws NoSuchModelNameException;

    int getLength();

}
