/**
 * CCustomers class represents a customer with various attributes such as ID, name,
 * type, address, city, state, and zipcode. It provides constructors, getters, and setters
 * for these attributes.
 * 
 * @version 1.0
 * @since 2023-11-18
 */
public class CCustomers {
    private int intID;
    private String strName;
    private String strType;
    private String strAddress;
    private String strCity;
    private String strState;
    private String strZip;
    
    private String strCustomerPhoneNumber; 
    private String strContactName; 
    private String strEmailAddress; 


    /**
     * Default constructor that initializes the attributes to default values.
     */
    public CCustomers() {
        this.intID = 0;
        this.strName = "";
        this.strType = "";
        this.strAddress = "";
        this.strCity = "";
        this.strState = "";
        this.strZip = "";
    }

    /**
     * Parameterized constructor that sets the attributes based on provided values.
     * 
     * @param intId    The ID of the customer.
     * @param strName  The name of the customer.
     * @param strType  The type of the customer.
     * @param strAddress The address of the customer.
     * @param strCity  The city of the customer.
     * @param strState The state of the customer.
     * @param strZip   The zipcode of the customer.
     */
    public CCustomers(int intId, String strName, String strType, String strAddress, String strCity, String strState, String strZip) {
        this.intID = intId;
        this.strName = strName;
        this.strType = strType;
        this.strAddress = strAddress;
        this.strCity = strCity;
        this.strState = strState;
        this.strZip = strZip;
    }

    /**
     * Gets the ID of the customer.
     *
     * @return The ID of the customer.
     */
    public int getID() {
        return intID;
    }

    /**
     * Sets the ID of the customer.
     *
     * @param intId The new ID of the customer.
     */
    public void setID(int intId) {
        intID = intId;
    }

    /**
     * Gets the name of the customer.
     *
     * @return The name of the customer.
     */
    public String getName() {
        return strName;
    }

    /**
     * Sets the name of the customer.
     *
     * @param strName The new name of the customer.
     */
    public void setName(String strName) {
        this.strName = strName;
    }

    /**
     * Gets the type of the customer.
     *
     * @return The type of the customer.
     */
    public String getType() {
        return strType;
    }

    /**
     * Sets the type of the customer.
     *
     * @param strType The new type of the customer.
     */
    public void setType(String strType) {
        this.strType = strType;
    }

    /**
     * Gets the address of the customer.
     *
     * @return The address of the customer.
     */
    public String getAddress() {
        return strAddress;
    }

    /**
     * Sets the address of the customer.
     *
     * @param strAddress The new address of the customer.
     */
    public void setAddress(String strAddress) {
        this.strAddress = strAddress;
    }

    /**
     * Gets the city of the customer.
     *
     * @return The city of the customer.
     */
    public String getCity() {
        return strCity;
    }

    /**
     * Sets the city of the customer.
     *
     * @param strCity The new city of the customer.
     */
    public void setCity(String strCity) {
        this.strCity = strCity;
    }

    /**
     * Gets the state of the customer.
     *
     * @return The state of the customer.
     */
    public String getState() {
        return strState;
    }

    /**
     * Sets the state of the customer.
     *
     * @param strState The new state of the customer.
     */
    public void setState(String strState) {
        this.strState = strState;
    }

    /**
     * Gets the ZIP code of the customer.
     *
     * @return The ZIP code of the customer.
     */
    public String getZipcode() {
        return strZip;
    }

    /**
     * Sets the ZIP code of the customer.
     *
     * @param strZip The new ZIP code of the customer.
     */
    public void setZipcode(String strZip) {
        this.strZip = strZip;
    }

}
