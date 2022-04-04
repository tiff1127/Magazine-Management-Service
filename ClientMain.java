import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author Tiffany Wong
 */
public class ClientMain {
    //shared pre-coded list of available supplements
    public static ArrayList<Supplement> suppList = new ArrayList<Supplement>(Arrays.asList(new Supplement("Health",7.5),new Supplement("Cosmetics",5.5),
                                                                    new Supplement("Apparel",2.0),new Supplement("Music",1.0),
                                                                    new Supplement("Motorcycle",5.0),new Supplement("Miniature",5.5)));
    
    /**
     * Prints all the Paying Customer in the ArrayList
     * @param arrCust - The ArrayList to print all the PayingCustomer
     * @return true if there are paying customer, false if there are no available paying customer
     */
    public static boolean custPrint(ArrayList<PayingCustomer> arrCust){
        //false if the ArrayList is empty
        if(arrCust.size()==0){
            System.out.println("No customers in this session");
            return false;
        }
        
        int n = 0;
        
        //go thru every single item in the ArrayList and prints it
        for(PayingCustomer cust:arrCust){
            n++;
            System.out.print("[" + n + "]");
            System.out.println(cust);
        }
        return true;
    }
    
    /**
     * Method to print main menu to user
     */
    public static void printMenu(){
        System.out.println("\t\tXXX Magazine Management Service\t\t");
        System.out.println("============================================================");
        System.out.println("a. Suscribe to a new supplement to ur magazine");
        System.out.println("b. Join as a new customer");
        System.out.println("c. View all magazine weekly email");
        System.out.println("d. View all payment monthly email for this month");
        System.out.println("e. Remove customer"); 
        System.out.println("f. Exit this session(WARNING! All data will be deleted)");
        System.out.println("============================================================");
    }
    
    /**
     * Method to return the PayingCustomer at index input by user
     * @param arrCust - ArrayList of all existing PayingCustomer
     * @return The details of PayingCustomer according to user's input
     *         0 indicates error/exit
     */
    public static int choosePayingCust(ArrayList<PayingCustomer> arrCust){
        Scanner input = new Scanner(System.in);
        
        if(arrCust.size()==0){
            System.out.println("No customer available");
            return 0;
        }
        //prompts user to choose paying customer name
        System.out.println("Please choose your name(number that is out of range will redirect you to the main menu):");
        int counter = 1;
        
        //prints out all the existing paying customer name
        for(PayingCustomer payCust : arrCust){
            System.out.print("[" + counter + "] ");
            System.out.println(payCust);
            counter++;
        }

        
        //gets input
        System.out.println("Your selection(Select the name that is paying for you) : ");
        int custSelect = input.nextInt();
        
        //If user input is not in the range of ArrayList
        if(custSelect>=counter || custSelect<1){
            System.out.println("Returning to main menu..");
            custSelect = 0;
        }
        return custSelect;
    }
    
    /**
     * Gets the Supplement that the user wants to add into their existing subscription
     * Supplement
     * @param payCustTemp - The Customer(either AssociateCustomer or PayingCustomer) to add the Supplement in
     * @return The Supplement that is to be added to the existing list of subscription
     */
    public static Supplement subSupp(Customer payCustTemp){
        Scanner input = new Scanner(System.in);
        
        //prints the currently subscirbed Supplements of the Customer
        System.out.println("You're in " + payCustTemp.getName() + "'s Magazine Settings : ");
        System.out.println("Current suscribed supplements : ");
        payCustTemp.getMag().printAllSupp();
        System.out.println("====================================");
        
        System.out.println("Available supplements to suscribe :");
        Iterator<Supplement> suppItr = payCustTemp.getMag().getSuppItr(1);

        //makes a copy of the predefined available Supplement ArrayList
        ArrayList<Supplement> notSubSupp = new ArrayList<Supplement>(suppList);
        
        //removes all existing subscribed supplements from the predefined arraylist
        if(!(suppItr == null)){
            
            //iterates if there are subscribed supplements in the current customer
            while(suppItr.hasNext()){
                Supplement suppHolder = suppItr.next();
                
                //removes the supplement from the arraylist if it's already subscribed by the customer
                if(notSubSupp.contains(suppHolder)){
                    notSubSupp.remove(suppHolder);
                }
            }
        }
        
        //if all the supplements are currently subscribed
        if(notSubSupp.size()==0){
            System.out.println("No More Supplements to suscribe");
        }
        
        //else let the user choose the supplement(s) they want to add into their list
        else{
            
            //outputs the available list of supplements to subscribe
            for(int i = 0;i < notSubSupp.size();i++){
                System.out.println("[" + (i+1) + "] " + notSubSupp.get(i).getSuppName());
            }
            System.out.println("====================================");
            
            //gets user input
            System.out.println("Supplement to choose :");
            int suppSelect = input.nextInt();
            
            //if index out of range
            if(suppSelect < 1 || suppSelect > notSubSupp.size()){
                return null;
            }
            
            //returns the supplement at the index on the arraylist where the user chosen
            return notSubSupp.get(suppSelect-1);
                
        }
        
        return null;
    }
    
    /**
     * Selection A in main menu(Subscribing to a new supplement)
     * @param arrCust - Array of PayingCustomer
     * @see #choosePayingCust(ArrayList<PayingCustomer> arrCust)
     * @see #subSupp(Customer payCustTemp)
     * @see PayingCustomer#printAllAssoCust()
     */
    public static void menuA(ArrayList<PayingCustomer> arrCust){      
        Scanner input = new Scanner(System.in);
        
        //gets input from user
        int index = choosePayingCust(arrCust);
        
        //exits operation if user decides to quit
        if(index == 0){
            return;
        }
        
        //Assigns PayingCustomer to a temporary holder at user 
        //indicated index
        PayingCustomer custHolder = arrCust.get(index-1);
        
        //if the PayingCustomer does not contain AssociateCustomers
        if(custHolder.getNumAssoCust()==0){
            
            //Ask customer on which supplement they want to subscribe
            Supplement suppAdd = subSupp(custHolder);
            
            //if supplement is chosen by user
            if(!(suppAdd==null)){
                //adds the supplement to the customer's arraylist subscription
                custHolder.getMag().addSupp(suppAdd);
                System.out.println("Added sucessfully");
            }
            //if it's null
            else{
                //prints error
                System.out.println("Index out of range");
            }
        }
        
        //if there are AssociateCustomer(s) under the PayingCustomer
        else{
            //outputs all the paying customer at [0] and also all the relative
            //associate customers that goes under the paying customer
            System.out.println("List of customers under \"" + custHolder.getName() + "\" enter a number that is not in range to quit");
            System.out.println("[0] " + custHolder.getName());
            custHolder.printAllAssoCust();
            
            //user selects which customer they need to work on
            System.out.println("Your selection :");
            int custSelect = input.nextInt();
            
            //If user enters number that is not in range, goes back to main menu
            if(custSelect>custHolder.getNumAssoCust()||custSelect<0){
                System.out.println("Going back to main menu...");
            }
            
            //if user chooses the paying customer itself
            else if(custSelect == 0){
                Supplement suppAdd = subSupp(custHolder);
                if(!(suppAdd==null)){
                    custHolder.getMag().addSupp(suppAdd);
                    System.out.println("Added sucessfully");
                }
                else{
                System.out.println("Index out of range");
                }
            }
            
            //if user chooses the associate customer
            else{
                //gets the iterator that will point to the associatecustomer
                //on the next next() call
                Iterator<AssociateCustomer> assoItr = custHolder.getAssoCustItr(custSelect);
                AssociateCustomer assoHolder = assoItr.next();
                
                //goes into the supplement choosing method
                Supplement suppAdd = subSupp(assoHolder);
                
                //adds if user chosen something
                if(!(suppAdd==null)){
                    assoHolder.getMag().addSupp(suppAdd);
                    System.out.println("Added sucessfully");
                }
                
                //does nothing if number is not valid
                else{
                    System.out.println("Index out of range");
                }
            }
        }
    }
    
    /**
     * Selection B in main menu(Adding a new customer)
     * @param arrCust - Array of PayingCustomer
     * @see PayingCustomer#getPayInfo(ArrayList<Supplement> suppList)
     * @see AssociateCustomer#getAssoInfo(ArrayList<Supplement> suppList)
     */
    public static void menuB(ArrayList<PayingCustomer> arrCust){
        Scanner input = new Scanner(System.in);
        
        //gets type of array user wants to add
        System.out.println("[1] Paying Customer");
        System.out.println("[2] Associate Customer");
        System.out.print("Type of customer to add(any number to exit to main menu) : ");
        int custSelect = input.nextInt();
        //consuming newLine character that is not caught by nextInt();
        input.nextLine();
        
        //if user chooses to add PayingCustomer
        if(custSelect==1){
            //creates a temporary PayingCustomer
            PayingCustomer payHold = new PayingCustomer();
            //calls the method in PayingCustomer to get info of the customer
            payHold.getPayInfo(suppList);
            //adds into the arrCust arraylist
            arrCust.add(payHold);
        }
        
        //if user chooses to add AssociateCustomer
        else if(custSelect==2){
            AssociateCustomer assoHold = new AssociateCustomer();
            System.out.println("Choose your paying customer(other numbers will redirect you to the main menu");
            
            //let user choose which PayingCustomer they are under
            for(int i = 0;i<arrCust.size();i++){
                System.out.println("[" + (i+1) + "] " + arrCust.get(i));
            }

            System.out.println("Your Selection : ");
            int choosePay = input.nextInt();
            input.nextLine();

            //check for user input
            if(choosePay<1||choosePay>arrCust.size()){
                System.out.println("Going back to main menu....");
            }
            //if user choose a valid PayingCustomer
            else{
                //asks user for input
                assoHold.getAssoInfo(suppList);
                //adds to the PayingCustomer's AssociateCustomer ArrayList
                arrCust.get(choosePay-1).addPaidCust(assoHold);
            }
            
        }
        
        //if not 1 or 2, performs nothing
        else{
            System.out.println("Going to main menu...");
        }
        
                        
    }
    
    /**
     * Selection C in main menu(Printing all weekly email)
     * @param arrCust - Array of PayingCustomer 
     * @see Customer#printReadyEmail()
     */
    public static void menuC(ArrayList<PayingCustomer> arrCust){
        //iterates through every PayingCustomer in the array
        for(PayingCustomer printCust :  arrCust){
            printCust.printReadyEmail();
            int assoCount = printCust.getNumAssoCust();
            
            //if there are associate customer in the paying customer
            if(assoCount>0){
                /*gets iterator that will point to the first entity at the 
                first index of the array on the next next() call*/
                Iterator<AssociateCustomer> assoItr = printCust.getAssoCustItr(1);
                
                //prints all the associate customer's email
                for(int i = 0;i<assoCount;i++){
                    assoItr.next().printReadyEmail();
                }
            }
        }
    }
    
    /**
     * Selection E in main menu(Removing a customer)
     * @param arrCust - Array of PayingCustomer 
     * @see #choosePayingCust(ArrayList<PayingCustomer> arrCust)
     * @see PayingCustomer#printAllAssoCust()
     */
    public static void menuE(ArrayList<PayingCustomer> arrCust){
        Scanner input = new Scanner(System.in);
        //user chooses the paying customer first
        int index = choosePayingCust(arrCust);
        
        //if nothing is chosen, nothing is performed
        if(index == 0){
            return;
        }
        
        //gets the paying customer, put its into a holder
        PayingCustomer custHolder = arrCust.get(index-1);
        
        //if the paying customer does not have any associate customer
        if(custHolder.getNumAssoCust()==0){
            //prompts again to confirm the deletion
            System.out.println("Do you really want to delete this customer?(Other numbers will redirect you to the main menu)");
            System.out.println("[1] Yes");
            System.out.println("[2] No");
            int selection = input.nextInt();
            input.nextLine();
            
            //if user confirms to delete
            if(selection==1){
                arrCust.remove(index-1);
                System.out.println("Removed successfully");
            }
            //if user does not want to delete
            else{
                System.out.println("Going back to main menu..");
            }
        }
        
        //if there are associate customers
        else{
            //prints all associate customer and the paying customer
            System.out.println("List of customers under \"" + custHolder.getName() + "\" enter a number that is not in range to quit");
            System.out.println("[0] " + custHolder.getName());
            custHolder.printAllAssoCust();
            
            //getting input
            System.out.println("Your selection :");
            int custSelect = input.nextInt();
            
            //if user chooses an index that is not in range
            if(custSelect>custHolder.getNumAssoCust()||custSelect<0){
                System.out.println("Going back to main menu...");
            }
            
            //if user chooses to delete the paying customer
            else if(custSelect == 0){
                //prompts again to double confirm the deletion
                System.out.println("Do you really want to delete this customer?(Other numbers will redirect you to the main menu)");
                System.out.println("[1] Yes");
                System.out.println("[2] No");
                int selection = input.nextInt();
                input.nextLine();
                
                //deletes if they confirm
                if(selection==1){
                    arrCust.remove(index-1);
                    System.out.println("Removed successfully");
                }
                
                //does not delete if they denies the transaction
                else{
                    System.out.println("Going back to main menu..");
                }
            }
            
            //when user chooses the associate customer
            else{
                //prompts to confirm
                System.out.println("Do you really want to delete this customer?(Other numbers will redirect you to the main menu)");
                System.out.println("[1] Yes");
                System.out.println("[2] No");
                int selection = input.nextInt();
                input.nextLine();
                
                //if user confirms to delete
                if(selection==1){
                    custHolder.removeAssoCust(custSelect);
                    System.out.println("Removed successfully");
                }
                //if user denies
                else{
                    System.out.println("Going back to main menu..");
                }
            }
        }
    }
    
    
    public static void main(String[]args){
        //Predefined supplements
        
   
        //A magazine with all the predefined supplements (a)
        Magazine mag = new Magazine();
        mag.addSupp(suppList.get(1));
        mag.addSupp(suppList.get(4));
        mag.addSupp(suppList.get(2));
        mag.addSupp(suppList.get(0));
        

        //populate customer info and also the supplement available(hard-coded) (b)
        ArrayList<PayingCustomer> arrCust = new ArrayList<PayingCustomer>();
        
        ArrayList<AssociateCustomer> assoTemp = new ArrayList<AssociateCustomer>();
        assoTemp.add(new AssociateCustomer(10000000,"DJ","DJ@hotmail.com",new Magazine()));
        
        ArrayList<Supplement> suppTemp = new ArrayList<Supplement> ();
        suppTemp.add(suppList.get(3));
        
        
        arrCust.add(new PayingCustomer(20000000,"Eric","eric@yahoo.com",mag,assoTemp,new PaymentMethod("Card",(long)23426576,"OCBC")));
        arrCust.add(new PayingCustomer(20000001,"Audry","aud75@outlook.com",new Magazine(suppTemp),new ArrayList<AssociateCustomer>(),new PaymentMethod("Bank",(long)34326543,"UOB")));
        

        assoTemp.clear();

        suppTemp.add(suppList.get(1));
        suppTemp.add(suppList.get(2));
        
        
        
        assoTemp.add(new AssociateCustomer(10000001,"Dhivya","Dhiv453@hotmail.com",new Magazine(suppTemp)));      
        
        suppTemp.clear();
        
        suppTemp.add(suppList.get(0));
        suppTemp.add(suppList.get(4));
        suppTemp.add(suppList.get(3));
                
        assoTemp.add(new AssociateCustomer(10000002,"Esme","EK1924@outlook.com",new Magazine(suppTemp)));
        
        arrCust.add(new PayingCustomer(20000002,"Hui Ling","HL3234@hotmail.com",new Magazine(suppTemp),assoTemp,new PaymentMethod("Card",(long)75675534,"POSB")));
        
        suppTemp.clear();
        suppTemp.add(suppList.get(5));
        suppTemp.add(suppList.get(1));
        
        arrCust.add(new PayingCustomer(20000003,"Damyan","damyan@gmail.com",new Magazine(suppTemp),new ArrayList<AssociateCustomer>(),new PaymentMethod("Card",(long)23453453,"OCBC")));
        
        arrCust.add(new PayingCustomer(20000004,"Woody","woodz@rocketmail.com",new Magazine(),new ArrayList<AssociateCustomer>(),new PaymentMethod("Card",(long)878422,"POSB")));
        //end of hard-coded 
               
        
        Scanner input = new Scanner(System.in);        
        
        //loops as long as user does not choose to exit
        loop:
        do{   
            char selection_char;
            //prints menu and ask user for input
            printMenu();
            System.out.println("Your selection : ");
            String selection = input.nextLine().toLowerCase();     //Ensures all input is in lower case
            if(selection.equals("")||selection.equals(null)){
                System.out.println("Please enter a letter!");
                continue;
            }
            else{
                selection_char = selection.charAt(0);
            }
            
            switch(selection_char){
                //Subscribing to a new supplement
                case 'a' :
                    menuA(arrCust);
                break;
                
                //adding a new customer to the service
                case 'b':
                    menuB(arrCust);
                break;
                
                //prints weekly email
                case 'c':
                    menuC(arrCust);
                break;
                
                //prints monthly email
                case 'd':
                    //got error 
                    for(PayingCustomer printCust : arrCust){
                        printCust.printPaymentEmail();
                    }
                break;
                
                //removing a customer from the service
                case 'e':
                    menuE(arrCust);
                break;
                
                case 'f':
                    break loop;
                
                //if any other letter is entered
                default:
                    //shows error
                    System.out.println("Invalid letter");
                break;
                    
                        
            }

        }while(true);
        
        //exits the program
        System.out.println("Thank you for using XXX magazine service :D");
       
    }
}
