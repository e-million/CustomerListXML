/**
 * The {@code ModifyCustomers} class provides functionality to modify customer data in an XML document.
 * It includes methods for updating phone numbers, contact names, and email addresses for each customer
 * based on a predefined array of CModifiedCustomerData objects. The modified data is applied to the
 * existing XML document, and the updated document is saved to a new XML file named "customer_modified.xml".
 *
 * <p>This class serves as a utility for modifying customer data in XML format. It includes methods to
 * load an existing XML document, apply modifications using the `modifyCustomerData` method, and save
 * the modified document using the `saveDocument` method. Any exceptions that occur during the modification
 * or saving process are caught, and the stack trace is printed to the standard error stream.</p>
 *
 * <p>Version: 1.0</p>
 * <p>Author: Million Eyassu</p>
 * <p>Since: 12/14/2023</p>
 *
 * @see CModifiedCustomerData
 * @see #modifyCustomerData(Document, CModifiedCustomerData[])
 * @see #saveDocument(Document, String)
 * @since 1.0
 */

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;


public class ModifyCustomers {
	/**
	 * Modifies the customer list by updating phone numbers, contact names, and email addresses
	 * for each customer based on a predefined array of CModifiedCustomerData objects. The
	 * modified data is applied to the existing XML document, and the updated document is saved
	 * to a new XML file named "customer_modified.xml".
	 *
	 * This method initializes an array of CModifiedCustomerData objects containing modified
	 * phone numbers, contact names, and email addresses. It then loads the existing XML document,
	 * applies the modifications using the `modifyCustomerData` method, and saves the modified
	 * document to a new XML file. Any exceptions that occur during the modification or saving
	 * process are caught, and the stack trace is printed to the standard error stream.
	 *
	 * @see CModifiedCustomerData
	 * @see #modifyCustomerData(Document, CModifiedCustomerData[])
	 * @see #saveDocument(Document, String)
	 * @since 1.0
	 */    

    public static void modifyCustomerList() {
    	
    	CModifiedCustomerData[] modifiedCustomerDataArray = {
    		    new CModifiedCustomerData("555-111-1111", "John Doe", "ACME_Hauling@gmail.com"),
    		    new CModifiedCustomerData("444-222-2222", "Jane Smith", "AJ_Newtown@gmail.com"),
    		    new CModifiedCustomerData("321-333-3333", "Bob Johnson", "Foster_Burgers@gmail.com"),
    		    new CModifiedCustomerData("512-444-4444", "Emily Jones", "Emilys_Bakery@gmail.com"),
    		    new CModifiedCustomerData("515-555-5555", "Sam Smith", "Smith_Paving@gmail.com"),
    		    new CModifiedCustomerData("518-675-6236", "Dave Wilson", "Daves_Painting_Service@gmail.com")
    		};
    	
    	
        try {
            // Load the existing XML document
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File("customers.xml"));

            // Modify the XML content
            modifyCustomerData(doc, modifiedCustomerDataArray);

            // Save the modified document to customers_updated.xml
            saveDocument(doc, "customer_modified.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    }
    
    
    /**
     * Modifies customer data in the provided XML document based on the given array of
     * CModifiedCustomerData objects. Adds or updates elements for phone number, contact name,
     * and email address for each "Customer" element in the XML document.
     *
     * This method iterates over the "Customers" elements in the XML document, adds or updates
     * elements for phone number, contact name, and email address based on the provided
     * CModifiedCustomerData array. Proper indentation is set for readability. Any exceptions
     * that occur during the modification process are caught and logged to the standard error stream.
     *
     * @param doc The XML Document to be modified.
     * @param modifiedCustomerDataArray An array of CModifiedCustomerData objects containing
     *                                  the modified customer data.
     * @since 1.0
     */
    private static void modifyCustomerData(Document doc, CModifiedCustomerData[] modifiedCustomerDataArray) {
        try {
            // Get the root element
            Element rootElement = doc.getDocumentElement();

            // Get a list of all "Customers" elements
            NodeList customersList = rootElement.getElementsByTagName("Customer");

            // Iterate over each Customers element
            for (int i = 0; i < customersList.getLength(); i++) {
                Node customersNode = customersList.item(i);

                if (customersNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element customersElement = (Element) customersNode;

                    // Create new elements for Customer Phone, Contact Name, and Customer Email Address
                    Element phoneElement = doc.createElement("phone");
                    phoneElement.appendChild(doc.createTextNode(modifiedCustomerDataArray[i].getPhoneNumber()));
                    customersElement.appendChild(phoneElement);
                    // Set the indentation level for Phone
                    customersElement.insertBefore(doc.createTextNode("    "), phoneElement);

                    Element contactNameElement = doc.createElement("contactName");
                    contactNameElement.appendChild(doc.createTextNode(modifiedCustomerDataArray[i].getContactName()));
                    customersElement.appendChild(contactNameElement);
                    // Set the indentation level for ContactName
                    customersElement.insertBefore(doc.createTextNode("\n        "), contactNameElement);

                    Element emailAddressElement = doc.createElement("email");
                    emailAddressElement.appendChild(doc.createTextNode(modifiedCustomerDataArray[i].getEmailAddress()));
                    customersElement.appendChild(emailAddressElement);
                    // Set the indentation level for EmailAddress
                    customersElement.insertBefore(doc.createTextNode("\n        "), emailAddressElement);

                    // lines up Customer node end tag
                    customersElement.appendChild(doc.createTextNode("\n    "));
                }
            }
        } 
        // Step 5 enhancement: Handle exceptions during customer data modification, printing error details and stack trace
        catch (Exception e) {
            // Handle exceptions
            System.err.println("Error modifying customer data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    
    /**
     * Saves the provided XML document to a specified file with proper indentation.
     *
     * This method uses a Transformer to save the given Document to an XML file specified by
     * the provided fileName. The document is saved with proper indentation for readability.
     * The method also prints a confirmation message to the console after successful saving.
     *
     * @param doc The XML Document to be saved.
     * @param fileName The name of the file to which the Document should be saved.
     * @throws TransformerException If an error occurs during XML document transformation.
     *         The error details are logged to the standard error stream.
     *
     * @since 1.0
     */
    private static void saveDocument(Document doc, String fileName) throws TransformerException {
        try {
            // Save the document to a new XML file with proper indentation
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            // Create a new file
            File outputFile = new File(fileName);
            StreamResult result = new StreamResult(outputFile);
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);

            System.out.println("Modified XML saved to " + fileName + "\n");

            // Print to console
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

         // Step 5 enhancement: Handle transformer configuration exception, printing error details and stack trace
        } catch (TransformerConfigurationException e) {
            // Handle transformer configuration exception
            System.err.println("Error creating XML transformer configuration: " + e.getMessage());
            throw new TransformerException(e.getMessage(), e.getCause());
            
         // Step 5 enhancement: Handle transformer exception, printing error details and stack trace
        } catch (TransformerException e) {
            // Handle transformer exception
            System.err.println("Error transforming XML document: " + e.getMessage());
            throw e;
        }
    }
    
    
    
}


