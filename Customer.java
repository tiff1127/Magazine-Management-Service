import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Tiffany Wong
 */
public abstract class Customer {
    /**id of customer*/
    private int id;
    /**name of customer*/
    private String name;
    /**email of customer*/
    private String email;
    /**Magazine of customer*/
    private Magazine mag;
    /**Array of interested supplements of customer*/
    private ArrayList<Supplement> instSupp;
    
    /**
     * Default constructor, initializes needed variables
     */
    public Customer(){
        this.mag = new Magazine();
        this.instSupp = new ArrayList<Supplement>();
    }
    
    /**
     * Constructor with id,name,email and Magazine as parameters
     * @param id - Unique id of Customer
     * @param name - Name of Customer
     * @param email - Email of Customer
     * @param mag - Magazine object of Customer
     */
    public Customer(int id,String name,String email,Magazine mag){
        this.id = id;
        this.name = name;
        this.email = email;
        this.mag = new Magazine(mag);
    }
    
    /**
     * Constructor with id,name,email,Magazine and ArrayList of Supplements as parameters
     * @param id - Unique id of Customer
     * @param name - Name of Customer
     * @param email - Email of Customer
     * @param mag - Magazine object of Customer
     * @param instSupp - ArrayList of interested supplements
     */
    public Customer(int id,String name,String email,Magazine mag,ArrayList<Supplement> instSupp){
        this.id = id;
        this.name = name;
        this.email = email;
        this.mag = mag;
        this.instSupp = new ArrayList<Supplement>(instSupp);
    }
    
    /**
     * Sets the id field in Customer
     * @param id 
     */
    public void setId(int id){
        this.id = id;
    }
    
    /**
     * Sets the name field in Customer
     * @param name - variable to set the name of the Customer
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * Sets the email field in Customer
     * @param email - variable to set the email of the Customer
     */
    public void setEmail(String email){
        this.email = email;
    }
    
    /**
     * Sets the Magazine field in Customer
     * @param mag - variable to set the Magazine of the Customer 
     */
    public void setMag(Magazine mag){
        this.mag = new Magazine(mag);
    }
    
    /**
     * Sets the interested supplement field in Customer
     * @param instSupp - ArrayList of interested supplements
     */
    public void setInstSupp(ArrayList<Supplement> instSupp){
        this.instSupp = new ArrayList<Supplement>(instSupp);
    }
    
    /**
     * Adds interested supplement to the current ones
     * @param instSupp - Single Supplement object to add to the interested supplements
     */
    public void addInstSupp(Supplement instSupp){
        this.instSupp.add(instSupp);
    }
    
    /**
     * Gets the id of the Customer
     * @return id of the Customer
     */
    public int getId(){
        return id;
    }
    
    /**
     * Gets the name of the Customer
     * @return name of the Customer
     */
    public String getName(){
        return name;
    }
    
    /**
     * Gets the email of the Customer
     * @return email of the Customer
     */
    public String getEmail(){
        return email;
    }
    
    /**
     * Gets the Magazine of the Customer
     * @return Magazine of the Customer
     */
    public Magazine getMag(){
        return mag;
    }
    
    /**
     * Prints the weekly email to Customer
     */
    public void printReadyEmail(){
        System.out.println("XXX Magazine <xxxmag@gmail.com>");
        System.out.println("To : <" + email + ">");
        
        System.out.println("\tHi " + name + ",");
        System.out.println("\t  Your Magazine is ready to view for the week.");
        System.out.println("\t  Currently suscribed Supplements :");
        mag.printAllSupp();
        
        System.out.println("================================================================");
        
    }
    
    /**
     * Gets the name,email,supplements to subscribe from user input
     * @param suppList - Predefined ArrayList of Supplement to choose from
     */
    public void getBasicInfo(ArrayList<Supplement> suppList){
        Scanner input = new Scanner(System.in);
        int selection;
        
        //gets name and email
        System.out.println("Name:");
        name = input.nextLine();
        System.out.println("Email:");
        email = input.nextLine();
        
        //copy to prevent tampering of the original arraylist
        ArrayList<Supplement> suppHolder = new ArrayList<Supplement>(suppList);
        
        
        do{
            //if error occurs while copying the ArrayList over
            if(suppHolder.size()==0){
                break;
            }
            
            //choose the supplements available(supplements that are chosen cant be choosed again)
            System.out.println("Select supplements to suscribe(enter 0 to exit) :");
            for(int i = 0;i<suppHolder.size();i++){
                System.out.print("[" + (i+1) + "] ");
                System.out.println(suppHolder.get(i).getSuppName());
            }
            
            System.out.println("Your selection :");
            selection = input.nextInt();
            //consumes newspace character that is not caught by the nextInt()
            input.nextLine();
            
            //invalid number handling
            if(selection<0||selection>suppHolder.size()){
                System.out.println("Number not accepted.");
                continue;
            }
            
            //0 to exit the selection loop
            else if(selection == 0){
                break;
            }
            
            //if it's a valid number
            else{
                //adds the supplement to the Magazine ArrayList of Supplements
                Supplement supp = suppHolder.get(selection-1);
                this.getMag().addSupp(supp);
                suppHolder.remove(selection-1);
            }
        }while(selection!=0);

        
    }
        
}
