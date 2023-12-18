/**
 * The {@code ViewCustomers} class provides functionality to view and modify customer information
 * stored in an XML file. It includes methods for reading, printing, and modifying customer data.
 *
 * <p>This class uses Java's XML and DOM parsing libraries to interact with XML documents.
 * It includes methods for viewing a customer report, building an XML report, and modifying
 * customer data based on user input. The main entry point is the {@code startCustomerList}
 * method, which initializes the application and manages user interactions.</p>
 *
 * <p>The class also contains utility methods for validating user input, saving XML documents,
 * and modifying customer data. Exceptions during input validation and XML processing are
 * caught and handled, providing a robust user experience.</p>
 *
 * <p>Version: 1.0</p>
 * <p>Author: Million Eyassu</p>
 * <p>Since: 12/14/2023</p>
 *
 * @see CCustomers
 * @see CModifiedCustomerData
 * @see ModifyCustomers
 * @see #startCustomerList()
 * @see #validateMainMenuOption()
 * @see #buildCustomerReport(List)
 * @see #readAndPrintCustomerReport(String)
 * @see #validateExitOption(Scanner)
 * @see #ReadIntegerFromUser()
 * @see #saveDocument(Document, String)
 * @see #modifyCustomerData(Document, CModifiedCustomerData[])
 * @see #modifyCustomerList()
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ViewCustomers {
    /**
     * Main method to start the program.
     *
     * @param args Command-line arguments (not used in this application).
     */
	
	/**
	 * Initializes and manages the customer list application, allowing users to interact with
	 * a list of CCustomers objects. Users can view customer information or create a modified
	 * customers file. The program runs in a loop until the user chooses to exit.
	 *
	 * This method creates a list of CCustomers objects with example information and presents
	 * a menu with options to view customers or create a modified customers file. The user's
	 * input is validated, and corresponding actions are performed. The program continues to
	 * run until the user chooses to exit. Exception handling is in place to handle input
	 * errors and unexpected exceptions, providing a robust user experience.
	 *
	 * @see CCustomers
	 * @see ModifyCustomers
	 * @see #validateMainMenuOption()
	 * @see #buildCustomerReport(List)
	 * @see #readAndPrintCustomerReport(String)
	 * @see #validateExitOption(Scanner)
	 * @since 1.0
	 */
	
    public static void startCustomerList() {
    	
		  // Creating 10 CCustomers objects with real-looking information
        CCustomers customer1 = new CCustomers(1, "ACME Hauling", "Commercial", "123 Main St", "Springfield", "IL", "62701");
        CCustomers customer2 = new CCustomers(2, "AJ Newtown", "Residential", "456 Oak St", "Rivertown", "CA", "90210");
        CCustomers customer3 = new CCustomers(3, "Foster Burgers", "Commercial", "789 Pine St", "Lakeside", "NY", "10001");
        CCustomers customer4 = new CCustomers(4, "Emily's Bakery", "Residential", "101 Elm St", "Mountainview", "CO", "80302");
        CCustomers customer5 = new CCustomers(5, "Smith Paving", "Commercial", "202 Cedar St", "Harbortown", "FL", "33101");
        CCustomers customer6 = new CCustomers(6, "Dave's Painting Service", "Residential", "303 Birch St", "Valleyville", "TX", "75201");

        // Creating a list and adding CCustomers instances to it
        List<CCustomers> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer3);
        customerList.add(customer4);
        customerList.add(customer5);
        customerList.add(customer6);
 
        
        
        // Create Scanner object to take user input
        Scanner scanner = new Scanner(System.in);

        // Display menu options
        System.out.println("Choose an option:");
        System.out.println("1. View Customers");
        System.out.println("2. Create Modified Customers File");
        
        // Create loop to until user inputs correct input
        String exitCheck ="";
        
     // Specify the path to your XML file
        String filePath = "customers.xml";

        // Loop until the user wants to exit the program
        do {
            try {
                // Validate user input for main menu options
                int userChoice = validateMainMenuOption();

                switch (userChoice) {
                    case 1:
                        System.out.println("Viewing Customers...");
                        buildCustomerReport(customerList);

                        System.out.println("\n");

                        // Read and print the contents of the XML file
                        readAndPrintCustomerReport(filePath);
                        break;
                    case 2:
                        System.out.println("Creating Modified Customers File...");

                        // calls function from ModifyCustomers class to modify customer list 
                        ModifyCustomers.modifyCustomerList();

                        // Add logic to create modified customers file
                        break;
                }

                System.out.println("\n");

                // Ask the user if they want to exit
                exitCheck = validateExitOption(scanner);

                // Step 5 enhancement: Handle input mismatch exception, printing error details and stack trace                
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                // Clear the invalid input
                scanner.nextLine();
                
                // Step 5 enhancement: Handle unexpected exceptions, printing error details and stack trace                
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace(); // print the stack trace for debugging
            }

        } while (!exitCheck.equalsIgnoreCase("yes"));

        // Close the scanner
        scanner.close();
    }
	
    
    /**
     * Reads an integer value from the user through the console input.
     *
     * This method prompts the user to enter a line of text, reads the input, and attempts to
     * convert the input to an integer. If the conversion is successful, the integer value is
     * returned; otherwise, an exception is caught, and the error details are printed to the console.
     *
     * @return The integer value entered by the user. If an error occurs during input or conversion,
     *         the method returns 0, and the error details are printed to the console.
     * @since 1.0
     */
	private static int ReadIntegerFromUser( )
	{			  

		int intValue = 0;

		try
		{
			String strBuffer = "";	

			// Input stream
			BufferedReader burInput = new BufferedReader( new InputStreamReader( System.in ) ) ;

			// Read a line from the user
			strBuffer = burInput.readLine( );
			
			// Convert from string to integer
			intValue = Integer.parseInt( strBuffer );
		}
		
		 // Step 5 enhancement: Handle exceptions during integer input, printing error details and stack trace
		catch( Exception excError )
		{
			System.out.println( excError.toString( ) );
		}
		
		// Return integer value
		return intValue;
	}

	
	/**
	 * Prompts the user to enter a choice for the main menu, validates the input, and returns
	 * the validated menu option. The method continues prompting until a valid choice (1 or 2)
	 * is entered by the user.
	 *
	 * This method utilizes the ReadIntegerFromUser() method to read the user's input and ensures
	 * that the input is either 1 or 2. If an invalid choice is entered, the user is informed
	 * and prompted to enter a valid choice. InputMismatchException is caught to handle cases
	 * where a non-integer value is entered, and the user is prompted to enter a number. Any other
	 * unexpected errors during input validation are caught and handled, allowing the user to try again.
	 *
	 * @return The validated main menu option, which is either 1 or 2.
	 * @since 1.0
	 */
	
	private static int validateMainMenuOption() {
	    int choice = 0;
	    boolean isValid = false;

	    do {
	        try {
	            System.out.print("Enter your choice (1 or 2): ");
	            choice = ReadIntegerFromUser();
	            
	            // If the choice is 1 or 2, set isValid to true
	            if (choice == 1 || choice == 2) {
	                isValid = true;
	            } else {
	                System.out.println("Invalid choice. Please choose 1 or 2.");
	            }
	            
	            // Step 5 enhancement: Handle exceptions during menu option validation, printing error details and stack trace
	        } catch (InputMismatchException e) {
	            // Handle input mismatch exception
	            System.out.println("Invalid input. Please enter a number.");
	            
	            // Clear the invalid input from the scanner
	            new Scanner(System.in).nextLine();
	        }
	    } while (!isValid);

	    return choice;
	}

	
	/**
	 * Prompts the user with a yes/no option to exit, validates the input, and returns the
	 * validated exit choice. The method continues prompting until a valid choice ('yes' or 'no')
	 * is entered by the user.
	 *
	 * This method uses the provided Scanner to read the user's input and ensures that the input
	 * is either "yes" or "no" (case-insensitive). If an invalid choice is entered, the user is
	 * informed and prompted to enter a valid choice. Any unexpected errors during input
	 * validation are caught and handled, allowing the user to try again.
	 *
	 * @param scanner The Scanner object used to read user input.
	 * @return The validated exit choice, which is either "yes" or "no".
	 * @since 1.0
	 */
	private static String validateExitOption(Scanner scanner) {
	    String exitChoice = "";
	    boolean isValid = false;

	    do {
	        try {
	            System.out.println("Do you want to exit? (yes/no)");
	            exitChoice = scanner.nextLine().toLowerCase();

	            if (exitChoice.equals("yes") || exitChoice.equals("no")) {
	                isValid = true;
	            } else {
	                System.out.println("Invalid choice. Please enter 'yes' or 'no'.");
	            }
	            
	            // Step 5 enhancement: Handle exceptions during exit option validation, printing error details and stack trace
	        } catch (Exception e) {
	            // Handle other exceptions (if any)
	            System.out.println("An unexpected error occurred: " + e.getMessage());
	            
	            // Clear the invalid input from the scanner
	            scanner.nextLine();
	        }
	    } while (!isValid);

	    return exitChoice;
	}
	
	
	/**
	 * Builds an XML customer report document based on the provided list of customer objects
	 * and saves it to a file named "customers.xml". Each customer in the list is represented
	 * as a "Customer" element in the XML document with attributes for ID, name, type, address,
	 * city, state, and zip code.
	 *
	 * This method creates a new XML document using DocumentBuilder, generates "Customer" elements
	 * for each customer in the list, and sets the corresponding attributes and child elements.
	 * The resulting XML document is then transformed and saved to a file using a Transformer.
	 *
	 * @param customerList The list of customer objects used to build the XML report.
	 * @throws ParserConfigurationException If an error occurs during XML parser configuration.
	 *         The error details are logged to the standard error stream.
	 * @throws TransformerConfigurationException If an error occurs during XML transformer configuration.
	 *         The error details are logged to the standard error stream.
	 * @throws TransformerException If an error occurs during XML document transformation.
	 *         The error details are logged to the standard error stream.
	 *
	 * @since 1.0
	 */
    private static void buildCustomerReport(List<CCustomers> customerList) {
        try {
            // Create a new XML document using DocumentBuilder
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // Create the root element for Customers
            Element rootCustomersElement = doc.createElement("Customers");
            doc.appendChild(rootCustomersElement);

            // Loop through the customer list to create Customer elements
            for (CCustomers customer : customerList) {
                Element customerElement = doc.createElement("Customer");

                // Set ID attribute for each Customer element
                customerElement.setAttribute("ID", String.valueOf(customer.getID()));

                // Create and append child elements for each customer attribute
                Element nameElement = doc.createElement("name");
                nameElement.appendChild(doc.createTextNode(customer.getName()));
                customerElement.appendChild(nameElement);

                Element typeElement = doc.createElement("type");
                typeElement.appendChild(doc.createTextNode(customer.getType()));
                customerElement.appendChild(typeElement);

                Element addressElement = doc.createElement("address");
                addressElement.appendChild(doc.createTextNode(customer.getAddress()));
                customerElement.appendChild(addressElement);

                Element cityElement = doc.createElement("city");
                cityElement.appendChild(doc.createTextNode(customer.getCity()));
                customerElement.appendChild(cityElement);

                Element stateElement = doc.createElement("state");
                stateElement.appendChild(doc.createTextNode(customer.getState()));
                customerElement.appendChild(stateElement);

                Element zipElement = doc.createElement("zip");
                zipElement.appendChild(doc.createTextNode(customer.getZipcode()));
                customerElement.appendChild(zipElement);

                // Append the Customer element to the root element
                rootCustomersElement.appendChild(customerElement);
            }

            // Create a Transformer for outputting the XML document
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            // Save the XML document to a file ("customers.xml")
            StreamResult result = new StreamResult(new File("customers.xml"));
            transformer.transform(source, result);

            // Output the XML to the console
           // StreamResult consoleResult = new StreamResult(System.out);
          //  transformer.transform(source, consoleResult);
            
            // Step 5 enhancement: Handle parser configuration exception, printing error details and stack trace            
        } catch (ParserConfigurationException e) {
            // Handle parser configuration exception
            System.err.println("Error creating XML parser configuration: " + e.getMessage());
            e.printStackTrace();
            
         // Step 5 enhancement: Handle transformer configuration exception, printing error details and stack trace
        } catch (TransformerConfigurationException e) {
            // Handle transformer configuration exception
            System.err.println("Error creating XML transformer configuration: " + e.getMessage());
            e.printStackTrace();
            
         // Step 5 enhancement: Handle transformer exception, printing error details and stack trace
        } catch (TransformerException e) {
            // Handle transformer exception
            System.err.println("Error transforming XML document: " + e.getMessage());
            e.printStackTrace();
        }
    }

    
    /**
     * Prints a formatted customer report based on the provided NodeList of customer nodes.
     * The customer information is extracted from the XML nodes and printed to the console.
     *
     * This method iterates over the given NodeList, extracts customer details such as ID, name,
     * type, address, city, state, and zip code, and prints a formatted report for each customer
     * to the console.
     *
     * @param customerNodeList The NodeList containing customer nodes from the XML document.
     * @throws Exception If an error occurs during the extraction or printing of customer information.
     *                   The error details are logged to the standard error stream.
     *
     * @since 1.0
     */
    private static void printCustomerReport(NodeList customerNodeList) {
        try {
            // prints out all child nodes in Node List, start at 1 because that's what the ID attribute in XML index starts at 
            for (int i = 1; i < customerNodeList.getLength(); i++) {
                Node customerNode = customerNodeList.item(i);

                if (customerNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element customerElement = (Element) customerNode;

                    String id = customerElement.getAttribute("ID");
                    String name = customerElement.getElementsByTagName("name").item(0).getTextContent();
                    String type = customerElement.getElementsByTagName("type").item(0).getTextContent();
                    String address = customerElement.getElementsByTagName("address").item(0).getTextContent();
                    String city = customerElement.getElementsByTagName("city").item(0).getTextContent();
                    String state = customerElement.getElementsByTagName("state").item(0).getTextContent();
                    String zip = customerElement.getElementsByTagName("zip").item(0).getTextContent();

                    // Format the address
                    String formattedAddress = address + "\n\t " + city + ", " + state + " " + zip;

                    System.out.println("Customer ID " + id);
                    System.out.println("Name:    " + name);
                    System.out.println("Type:    " + type);
                    System.out.println("Address: " + formattedAddress);

                    // System.out.println("\n");
                }
            }
            
            // Step 5 enhancement: Handle exceptions during customer report printing, printing error details and stack trace
        } catch (Exception e) {
            // Handle exceptions
            System.err.println("Error printing customer report: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Reads a customer report from an XML file specified by the given file path,
     * parses the document, and prints a formatted customer report to the console.
     *
     * This method uses the provided file path to read an XML file containing customer
     * information. It then parses the XML document, normalizes it, and extracts customer
     * data. Finally, it prints a formatted customer report to the console.
     *
     * @param filePath The file path to the XML file containing customer information.
     * @throws Exception If an error occurs during file reading, parsing, or printing.
     *                   The error details are logged to the standard error stream.
     *
     * @see #printCustomerReport(NodeList)
     * @since 1.0
     */
	private static void readAndPrintCustomerReport(String filePath) {
	    try {
	        File file = new File(filePath);
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(file);

	        doc.getDocumentElement().normalize();

	        NodeList customerNodeList = doc.getElementsByTagName("Customer");

	        System.out.println("Root Element: " + doc.getDocumentElement().getNodeName());

	        System.out.println("\n");

	        // Prints customer list
	        printCustomerReport(customerNodeList);

	        // Step 5 enhancement: Handle exceptions during customer report reading and printing, printing error details and stack trace
	    } catch (Exception e) {
	        // Handle exceptions
	        System.err.println("Error reading and printing customer report: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
    


}
