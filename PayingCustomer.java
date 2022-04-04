import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Tiffany Wong
 */
public class PayingCustomer extends Customer{
    /**Array to store all the AssociateCustomer(s) that 
     * the PayingCustomer is paying for */
    private ArrayList<AssociateCustomer> paidCust;
    /**The PaymentMethod of PayingCustomer*/
    private PaymentMethod payMethod;
    
    /**
     * 
     * Constructor without parameters
     * @see Customer#constructor()
     * 
     */
    public PayingCustomer(){
        //calls the empty constructor at abstract class
        super();
        
        //initializes all objects
        this.paidCust = new ArrayList<AssociateCustomer>();
        this.payMethod = new PaymentMethod();
    }
    
    /**
     * 
     * Constructor with name, email, Magazine and
     * PaymentMethod as parameters,(No AssociateCustomer)
     * @see Customer#constructor(int id,String name,String email,Magazine mag)
     * 
     */
    public PayingCustomer(int id,String name,String email,Magazine mag,PaymentMethod payMethod){
        //calls constructor with parameters at abstract class
        super(id,name,email,mag);
        
        //initializes ArrayList
        this.paidCust = new ArrayList<AssociateCustomer>();
        
        //initializes PayMethod with the parameter
        this.payMethod = new PaymentMethod(payMethod);
    }
    
    /**
     * 
     * Constructor with name, email, Magazine and
     * PaymentMethod as parameters, and ArrayList of AssociateCustomer
     * @see Customer#constructor(int id,String name,String email,Magazine mag)
     * 
     */
    public PayingCustomer(int id,String name,String email,Magazine mag,ArrayList<AssociateCustomer> paidCust,PaymentMethod payMethod){
        //calls constructor with parameters at abstract class
        super(id,name,email,mag);
        
        //initializes objects with the parameters
        this.paidCust = new ArrayList<AssociateCustomer>(paidCust);
        this.payMethod = new PaymentMethod(payMethod);
    }
    
    /**
     * Gets the number of associate customer that is under
     * this PayingCustomer
     * @return the size of AssociateCustomer's ArrayList of PayingCustomer
     */
    public int getNumAssoCust(){
        return paidCust.size();
    }
    
    /**
     * Sets the AssociateCustomer array by getting ArrayList
     * of AssociateCustomer
     * @param paidCust ArrayList of AssociateCustomer to add 
     *                 under the PayingCustomer
     */
    public void setPaidCust(ArrayList<AssociateCustomer> paidCust){
        this.paidCust = new ArrayList<AssociateCustomer>(paidCust);
    }
    
    /**
     * Adds a new AssociateCustomer to the PayingCustomer
     * @param cust AssociateCustomer to be added under the
     *             PayingCustomer
     */
    public void addPaidCust(AssociateCustomer cust){
        this.paidCust.add(cust);
    }
    
    /**
     * Sets the PaymentMethod of the PaymentCustomer
     * @param payMethod PaymentMethod object to set
     */
    public void setPayMethod(PaymentMethod payMethod){
        this.payMethod = new PaymentMethod(payMethod);
    }
    
    /**
     * Removes the AssociateCustomer from the paidCust ArrayList
     * at index i-1
     * @param i The position of the entity in the ArrayList
     *          that will be removed
     */
    public void removeAssoCust(int i){
        int position = i-1;
        paidCust.remove(position);
    }
    
    /**
     * Outputs all the names of AssociateCustomer that
     * is under this PayingCustomer, uses getAssoItr to
     * loop through the ArrayList
     * @see #getAssoItr(int i)
     */
    public void printAllAssoCust(){
        //gets the iterator to point at the first element
        Iterator<AssociateCustomer> iter = getAssoCustItr(1);
        int counter = 1;
        
        //Does not print if error occurs/ArrayList is empty
        if(iter == null){
            System.out.println();
        }
        
        else{
            //loops through the whole ArrayList
            while(iter.hasNext()){
                //prints out all the AssociateCustomer under the PayingCustomer
                AssociateCustomer temp = iter.next();
                System.out.println("\t   [" + counter + "]" + temp.getName());
                counter++;
            }
        }
    }
    
    /**
     * Gets the Iterator of AssociateCustomer ArrayList at index i-1
     * @param i The position of the AssociateCustomer at the user's view
     * @return iter The Iterator at index i-1
     * @return null Returns when there is an error
     */
    public Iterator<AssociateCustomer> getAssoCustItr(int i){
        int counter = 0;
        
        //Minus 1 to the number,(user's view starts from 1
        //but index starts from 0)
        i = i-1;
        
        //if user's input goes out of the ArrayList's size exit the method
        if(i >= paidCust.size()){
            return null;
        }
        
        //Gets the iterator of the ArrayList
        Iterator<AssociateCustomer> iter = paidCust.iterator();
        
        //Searches through the ArrayList until the index i is reached
        //and returns the iterator
        while(iter.hasNext()){
            if(counter == i){
                return iter;
            }
            iter.next();
            counter ++;
        }
        
        return null;
    }

    /**
     * Gets all the information needed to create a PayingCustomer entity
     * @param suppList The hard-coded fixed supplement list that is available
     * @see Customer#getBasicInfo(ArrayList<Supplement> suppList)
     */
    public void getPayInfo(ArrayList<Supplement> suppList){
        //inherits the method from abstract class
        //gets the name,email and supplements to suscribe to
        super.getBasicInfo(suppList);
        
        Scanner input = new Scanner(System.in);
        int selection;
        
        //loops until user gets valid input selection
        do{
            //gets payment method
            System.out.println("Ur preferred payment method:");
            System.out.println("[1] Credit Card");
            System.out.println("[2] Direct Debit");
            System.out.println("Your selection :");
            selection = input.nextInt();
            input.nextLine();
            
            if(selection == 1){
                payMethod.setType("Credit Card");
                System.out.println("Enter your card number :");
                payMethod.setAccount(input.nextLong());
                //consumes newline that is not captured by nexInt()
                input.nextLine();
                System.out.println("Enter your bank name :");
                payMethod.setType(input.nextLine());
            }
            else if(selection == 2){
                payMethod.setType("Direct Debit");
                System.out.println("Enter your account number :");
                payMethod.setAccount(input.nextInt());
                //consumes newline that is not captured by nexInt()
                input.nextLine();
                System.out.println("Enter your bank name :");
                payMethod.setType(input.nextLine());
            }
            else{
                System.out.println("Invalid selection of payment method");
            }
        }while(selection!=1&&selection!=2);
        
        System.out.println("Operation done");
    }
    
    /**
     * Prints the monthly email for the PayingCustomer entity
     * that will also includes every AssociateCustomer under it
     * @see getAssoCustItr(int i)
     * @see Magazine#getSuppItr(int) getSuppItr
     * @bug "NoSuchElementException" will occur at some entities
     */
    public void printPaymentEmail(){
        //base price of magazine
    	double totalPrice = this.getMag().getMagPrice();
        
        //header of email
        System.out.println("XXX Magazine <xxxmag@gmail.com>");
        System.out.println("To : <" + this.getEmail() + ">");
        
        System.out.println("\tHi " + this.getName() + ",");
        System.out.println("\t  Your Payment Bill is ready to view.");
        System.out.println("\t  Currently suscribed Supplements :");
        System.out.println("\t\t Name \t\t Price");
        int suppCount = this.getMag().getSuppCount();
        
        //prints all supplement of PayingCustomer if applicable
        if(suppCount > 0) {
            Iterator<Supplement> suppItr = this.getMag().getSuppItr(1);
            for(int x = 0;x < suppCount;x++) {
        	Supplement suppHolder = suppItr.next();
        	System.out.println("\t\t " + suppHolder);
                totalPrice += suppHolder.getSuppPrice();
            }
        }
        
        System.out.println();
        System.out.println("\t\t\t People you are paying for : ");
        int assoCount = this.getNumAssoCust();
        
        //Prints all information of AssociateCustomer if applicable
        //including their suscribed supplements
        if(assoCount>0){
            
            /*gets iterator that will point to the first entity at the 
            first index of the array on the next next() call*/
            Iterator<AssociateCustomer> assoItr = this.getAssoCustItr(1);
            
            for(int i = 0;i<assoCount;i++){
                //gets the AssociateCustomer from ArrayList
                AssociateCustomer assoHolder = assoItr.next();
                
                //prints details and suscribed supplements from the AssociateCustomer
                System.out.println("\t\t\t   " + assoHolder);
                totalPrice += assoHolder.getMag().getMagPrice();
                suppCount = assoHolder.getMag().getSuppCount();
                System.out.println("\t\t\t Suscribed supplements :");
                
                //iterates through the Supplement array inside the AssociateCustomer if applicable
                if(suppCount > 0) {
                                       
                    //gets the Iterator where the next .next() call of the Iterator will be pointed
                    //to the first index of the array
                    Iterator<Supplement> suppItr = assoHolder.getMag().getSuppItr(1);
                    
                    //prints all supplements and price
                    for(int x = 0;x < suppCount;x++) {
                        Supplement suppHolder = suppItr.next();
                        System.out.println("\t\t\t " + suppItr.next());
                        totalPrice += suppHolder.getSuppPrice();
                    }
                }
                else{
                    System.out.println("\t\t\t NIL");
                }
            }
        }
        
        else {
        	System.out.println("\t\t\t NIL");
        }
        
        //prints the total price needed to pay
        System.out.println("Total Price : $" + totalPrice);
        
        System.out.println("================================================================");
    }
    
    /**
     * Overrides the toString() method at "java.lang.String"
     * @return this.getName() the name of the PaymentCustomer object
     *                        that is calling this method
     */
    @Override
    public String toString(){
        return this.getName();  //override to string at asso cust
    }
    
}
