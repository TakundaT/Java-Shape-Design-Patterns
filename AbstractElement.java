public abstract class AbstractElement{
    protected static int numberofInstances = 0;

    public AbstractElement(){
        numberofInstances++;
    }
    public static int getNumOfInstances(){
        return numberofInstances;
    }
}