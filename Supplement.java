
import java.util.Objects;

/**
 *
 * @author Tiffany Wong
 */
public class Supplement {
    /**Name of Supplement*/
    private String suppName;
    /**Price of the Supplement*/
    private double suppPrice;
    
    /**
     * Default constructor
     */
    public Supplement(){
        
    }
    /**
     * Constructor with object of the same type : Supplement
     * @param supplement - object of type Supplement
     */
    public Supplement(Supplement supplement){
        this.suppName = supplement.suppName;
        this.suppPrice = supplement.suppPrice;
    }
    
    /**
     * Constructor with name of Supplement and price of Supplement as parameters
     * @param suppName - Name of Supplement
     * @param suppPrice - Price of Supplement
     */
    public Supplement(String suppName,double suppPrice){
        this.suppName = suppName;
        this.suppPrice = suppPrice;
    }
    
    /**
     * Sets Supplement name
     * @param suppName - Name of Supplement
     */
    public void setSuppName(String suppName){
        this.suppName = suppName;
    }
    
    /**
     * Sets Supplement price
     * @param suppPrice - Price of Supplement
     */
    public void setSuppPrice(double suppPrice){
        this.suppPrice = suppPrice;
    }
    
    /**
     * Gets Supplement name
     * @return The supplement name of the current object
     */
    public String getSuppName(){
        return suppName;
    }
    
    /**
     * Gets Supplement Price
     * @return The price of the current Supplement
     */
    public double getSuppPrice(){
        return suppPrice;
    }
    
    /**
     * This method is to be used in the .contains() method of ArrayList
     * to compare two objects of Supplement if they contains the same information
     * @param supp - Another object to be compared
     * @return true if both of the supplement names are equal, 
     *         false if there is an error or it's no equal
     * @see ClientMain
     */
    @Override
    public boolean equals(Object obj) {
        //if it's the same object from the same address
        if (this == obj) {
            return true;
        }
        
        //if object is null
        if (obj == null) {
            return false;
        }
        
        //check if the object is an instance of Supplement
        if(!(obj instanceof Supplement)){
            return false;
        }
        
        //type casting the object to Supplement
        Supplement supp = (Supplement) obj;
        
        //null safe checker, that replaces equals()
        if (!Objects.equals(this.suppName, supp.suppName)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.suppName);
        return hash;
    }
    
    
    /**
     * Overrides the toString() method at "java.lang.String"
     * @return this.getName() the name of the PaymentCustomer object
     *                        that is calling this method
     */
    @Override
    public String toString(){
        return suppName + "\t\t" + suppPrice;
    }
}
