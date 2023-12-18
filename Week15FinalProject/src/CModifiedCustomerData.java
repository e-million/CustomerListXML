/**
 * CModifiedCustomerData class represents modified customer data with fields for
 * customer phone, contact name, and email address. It provides a constructor
 * and getters/setters for these fields.
 *
 * @version 1.0
 * @since 2023-12-14
 */
public class CModifiedCustomerData {

    /** The customer's phone number. */
    private String strCustomerPhone;

    /** The contact name associated with the customer. */
    private String strContactName;

    /** The email address associated with the customer. */
    private String strEmailAddress;

    
    /**
     * Constructs a CModifiedCustomerData object with default values for phone number,
     * contact name, and email address.
     */
    public CModifiedCustomerData() {
    	
    	strCustomerPhone = ""; 
    	strContactName = ""; 
    	strEmailAddress = ""; 
    }
    
    
    /**
     * Constructs a CModifiedCustomerData object with the specified customer phone,
     * contact name, and email address.
     *
     * @param strCustomerPhone The customer's phone number.
     * @param strContactName   The contact name associated with the customer.
     * @param strEmailAddress  The email address associated with the customer.
     */
    public CModifiedCustomerData(String strCustomerPhone, String strContactName, String strEmailAddress) {
        this.strCustomerPhone = strCustomerPhone;
        this.strContactName = strContactName;
        this.strEmailAddress = strEmailAddress;
    }

    /**
     * Gets the customer's phone number.
     *
     * @return The customer's phone number.
     */
    public String getPhoneNumber() {
        return strCustomerPhone;
    }

    /**
     * Sets the customer's phone number.
     *
     * @param strCustomerPhone The new customer's phone number.
     */
    public void setCustomerPhone(String strCustomerPhone) {
        this.strCustomerPhone = strCustomerPhone;
    }

    /**
     * Gets the contact name associated with the customer.
     *
     * @return The contact name associated with the customer.
     */
    public String getContactName() {
        return strContactName;
    }

    /**
     * Sets the contact name associated with the customer.
     *
     * @param strContactName The new contact name.
     */
    public void setContactName(String strContactName) {
        this.strContactName = strContactName;
    }

    /**
     * Gets the email address associated with the customer.
     *
     * @return The email address associated with the customer.
     */
    public String getEmailAddress() {
        return strEmailAddress;
    }

    /**
     * Sets the email address associated with the customer.
     *
     * @param strEmailAddress The new email address.
     */
    public void setEmailAddress(String strEmailAddress) {
        this.strEmailAddress = strEmailAddress;
    }
}
