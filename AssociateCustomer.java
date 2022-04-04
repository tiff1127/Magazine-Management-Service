import java.util.ArrayList;

/**
 *
 * @author Boomie
 */
public class AssociateCustomer extends Customer{
    /**
     * Default constructor
     * @see Customer#constructor()
     */
    public AssociateCustomer(){
        super();
    }
    
    /**
     * Constructor with id,name,email and Magazine as parameters
     * @param id - id of AssociateCustomer
     * @param name - name of AssociateCustomer
     * @param email - email of AssociateCustomer
     * @param mag - Magazine of AssociateCustomer
     * @see Customer#constructor(int id,String name,String email,Magazine mag)
     */
    public AssociateCustomer(int id,String name,String email,Magazine mag){
        super(id,name,email,mag);
    
    }
    
    /**
     * Gets the basic information for a AssociateCustomer
     * @param suppList - Predefined array of available Supplement list
     * @see Customer#getBasicInfo(ArrayList<Supplement> suppList)
     */
    public void getAssoInfo(ArrayList<Supplement> suppList){
        
        super.getBasicInfo(suppList);

    }
    
    /**
     * Overrides the toString() method at "java.lang.String"
     * @return this.getName() the name of the AssociateCustomer object
     *                        that is calling this method
     */
    @Override
    public String toString() {
    	return this.getName();
    }
}
