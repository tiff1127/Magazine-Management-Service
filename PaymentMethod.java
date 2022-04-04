/**
 *
 * @author Tiffany Wong
 */
public class PaymentMethod {
    /**Type of payment method, either Credit Card or Direct Debit*/
    private String type;
    /**Account Number if it's Direct Debit, card number if it's credit card*/
    private long accountNo;
    /**The name of the bank*/
    private String bank;
    
    /**
     * Default constructor
     */
    public PaymentMethod(){
        //does nth :D
    }
    
    /**
     * Constructor with another object of the same type
     * @param payMethod - PaymentMethod object
     */
    public PaymentMethod(PaymentMethod payMethod){
        this.type = payMethod.type;
        this.accountNo = payMethod.accountNo;
        this.bank = payMethod.bank;
    }
    
    /**
     * Constructor with type of payment, account number and bank name as parameters
     * @param type - type of payment
     * @param accountNo - account number/card number
     * @param bank - name of bank
     */
    public PaymentMethod(String type,long accountNo,String bank){
        this.type = type;
        this.accountNo = accountNo;
        this.bank = bank;
    }
    
    /**
     * Sets the payment type
     * @param type - payment type
     */
    public void setType(String type){
        this.type = type;
    }
    
    /**
     * Sets account number/card number
     * @param accountNo - account number or card number of type long
     */
    public void setAccount(long accountNo){
        this.accountNo = accountNo;
    }
    
    /**
     * Sets the name of the bank
     * @param bank - name of bank
     */
    public void setBank(String bank){
        this.bank = bank;
    }
    
    
}
