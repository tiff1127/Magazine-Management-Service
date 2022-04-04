import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Tiffany Wong
 */
public class Magazine{
    /**Fixed amount of price for the main part of the Magazine*/
    private final double magPrice = 5;
    /**An ArrayList of subscribed supplements under the Magazine*/
    private ArrayList<Supplement> magSupp;
    
    /**
     * Default constructor
     */
    public Magazine(){
        //Initialize and empty ArrayList of Supplement
        magSupp = new ArrayList<Supplement>();
    }
    
    /**
     * Constructor that takes one Supplement as a parameter
     * @param supp - One Supplement that is to added to the subscribed Supplements
     */
    public Magazine(Supplement supp){
        magSupp = new ArrayList<Supplement>();
        magSupp.add(supp);
    }
    
    /**
     * Constructor that takes an ArrayList of Supplement to add into the Magazine
     * @param magSupp - ArrayList of Supplement
     */
    public Magazine(ArrayList<Supplement> magSupp){
        this.magSupp = new ArrayList<Supplement>(magSupp);
    }
    
    /**
     * Default constructor that takes an object of the same type
     * @param mag - Object of type Magazine
     */
    public Magazine(Magazine mag){
        this.magSupp = new ArrayList<Supplement>(mag.magSupp);
    }
    
    /**
     * Sets the subscribed Supplement of the Magazine
     * @param magSupp - An array of Supplement to be added to the object
     */
    public void setSupp(ArrayList<Supplement> magSupp){
        this.magSupp = new ArrayList<Supplement>(magSupp);
    }
    
    /**
     * Adds an additional subscribed Supplement to the existing ones
     * @param supp - Supplement to add into the ArrayList
     */
    public void addSupp(Supplement supp){
        this.magSupp.add(supp);
    }
    
    /**
     * Gets the number of currently subscribed Supplements
     * @return the size of the ArrayList
     */
    public int getSuppCount(){
        return magSupp.size();
    }
    
    /**
     * Gets the price of the main part of the Magazine
     * @return Price of the main part of the Magazine
     */
    public double getMagPrice(){
        return magPrice;
    }
    
    /**
     * Gets the Iterator of the Supplement ArrayList at index i-1
     * @param i number of index for the supplements at user's view,
     *          minus one for the program's view of index
     * @return Iterator of the ArrayList that will point to the index i-1
     *         when .next() is called on the Iterator
     */
    public Iterator<Supplement> getSuppItr(int i){
        int counter = 0;
        i = i-1;
        
        //if the requested number does not exist
        if(i >= magSupp.size()){
            return null;
        }

        //gets the iterator from the arraylist
        Iterator<Supplement> iter = magSupp.iterator();
        
        //iterate throughout the whole arraylist
        while(iter.hasNext()){
            //if the iterator has reached the index number that is wanted
            if(counter == i){
                return iter;
            }
            //if not continue to iterate
            iter.next();
            counter ++;
        }
        
        return null;
    }
            
    /**
     * Prints all the name of the currently subscribed Supplement in the current Magazine
     * 
     */
    public void printAllSupp(){
        //gets the iterator to point at the first element
        Iterator<Supplement> iter = getSuppItr(1);
        int counter = 1;
        
        //if error occured/no subsribed
        if(iter == null){
            System.out.println("\t   NIL");
        }
        
        //Loop through the entire ArrayList and prints all the content
        else{
            while(iter.hasNext()){
                Supplement temp = iter.next();
                System.out.println("\t   [" + counter + "]" + temp.getSuppName());
                counter++;
            }
        }
    }    
    
}
