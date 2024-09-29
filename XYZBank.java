import java.text.DecimalFormat;
import java.util.*;

// Main class for XYZBank
public class XYZBank {
    private static List<Customer> customers; // Static list of customer objects

    // A constructor for XYZBank
    public XYZBank() {
        customers = new ArrayList<>();// Initialise the customer list as an empty ArrayList
    }

    // Method. To register a particular customer by having them on the customer list.
    public void registerCustomer(Customer customer) {
        customers.add(customer);// Including the provided customer object in the list of customers
    }
    // A Method for updating customers information such as income and loan records
    public void updateCustomerInformation(String customerID, double income, Loan newLoanRecord, Loan removeLoanRecord) {
        Customer customer = findCustomer(customerID); // Finding a customer with a given customer ID
        if (customer == null) { // If the customer is not found
            System.out.println("Customer not found."); // Then print customer not found
            return; // Exit the method
        }
        customer.updateIncome(income); // Update the income of the customer
        if (newLoanRecord != null) { // If a new loan record is there to add
            customer.addLoanRecord(newLoanRecord); // Add new loan record to the customer record
        }
        if (removeLoanRecord != null) { // If there is a loan record to remove
            customer.removeLoanRecord(removeLoanRecord); // Remove the loan record from the customers record
        }
    }
    // A method to print information of a customer with the given customer ID
    public void printCustomerInformation(String customerID) {
        Customer customer = findCustomer(customerID); // Find a given customer with their customer ID
        if (customer != null) { // If the customer is found
            customer.printDetails(); // Then print the details of the found customer
        } else { // Otherwise 
            System.out.println("Customer not found."); // Output saying that the customer was not found
        }
    }
    // Method for printing out all of the customers
    public void printAllCustomersInformation() {
        for (Customer customer : customers) { // Iterate through each customer in the customers list
            customer.printDetails(); // Prints details of each customer
        }
    }
    
    // Private method. To find a customer by their customer ID
    private Customer findCustomer(String customerID) {
        for (Customer customer : customers) { // Iterate through each customer in the customer list
            if (customer.getCustomerID().equals(customerID)) { // If customer id matches given id
                return customer; // Return the found customer
            }
        }
        return null; // Otherwise if the customer is not found, then return null.
    }

    // Nested class representing an Auto loan
    static class AutoLoan extends Loan {
    	// Constructor. For auto loan class.
        public AutoLoan(String recordID, double interestRate, double amountLeft, int termLeft) {
            super(recordID, "Auto", interestRate, amountLeft, termLeft); // Calling constructor of superclass
        }

        @Override
        void updateInformation(int termLeft) {
            this.termLeft = termLeft; // Update the term left for the Auto loan
        }

        @Override
        double calculateMonthlyPayment() {
            double monthlyInterestRate = interestRate / 12.0; // Calculate the monthly interest
            int totalPayments = termLeft * 12; // Calculate the total number of payments
            // Calculate the monthly payment using loan formula
            return (amountLeft * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -totalPayments));
        }

        @Override
        double calculateTotalPayment() {
            return calculateMonthlyPayment() * termLeft * 12; // Calculate and return the total payment
        }
    }

    // Nested class representing a Mortgage loan
    static class MortgageLoan extends Loan {
        private double overpayment; // Additional payment for the Mortgage loan

        public MortgageLoan(String recordID, double interestRate, double amountLeft, int termLeft, double overpayment) {
            super(recordID, "Mortgage", interestRate, amountLeft, termLeft); // Calling the constructor of the superclass
            this.overpayment = overpayment; // Setting the overpayment for the Mortgage loan
        }

        @Override
        void updateInformation(int termLeft) {
            this.termLeft = termLeft; // Update the term left for the Auto loan
        }

        @Override
        double calculateMonthlyPayment() {
            double monthlyInterestRate = interestRate / 12.0; // Calculate monthly interest rate
            int totalPayments = termLeft * 12; // Calculate total number of payments
            return (amountLeft * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -totalPayments));
        }

        @Override
        double calculateTotalPayment() {
            return calculateMonthlyPayment() * termLeft * 12; // Calculate and return the total payment
        }
    }

    public static void main(String[] args) {
        XYZBank bank = new XYZBank(); // Creating an instance of XYZBank
        Scanner scanner = new Scanner(System.in); // Creating a Scanner object for user input
        DecimalFormat df = new DecimalFormat("#.##");

        System.out.println("Welcome to XYZBank!");

        System.out.println("Enter number of customers:"); // Prompting user to enter the number of customers
        int numCustomers = scanner.nextInt(); // Reading the number of customers entered by the user
        scanner.nextLine(); // Consume newline character

        for (int i = 0; i < numCustomers; i++) { // Loop to input details for each customer
            System.out.println("Enter details for customer " + (i + 1) + ":");
            System.out.print("Customer ID: ");
            String customerID = scanner.nextLine();
            System.out.print("Customer income: ");
            double income = scanner.nextDouble();
            scanner.nextLine(); // Consume newline character

            Customer customer = new Customer(customerID, income); // Creating a new Customer object with the provided details

            System.out.println("Enter number of loan records for customer " + customerID + ":");
            int numRecords = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            for (int j = 0; j < numRecords; j++) { // Loop to input loan records for each customer
                System.out.println("Enter details for loan record " + (j + 1) + ":");
                System.out.print("Record ID: ");
                String recordID = scanner.nextLine();
                System.out.print("Loan Type(Auto/Mortgage): ");
                String loanType = scanner.nextLine();
                System.out.print("Interest Rate: ");
                double interestRate = scanner.nextDouble();
                System.out.print("Amount Left (in thousands): ");
                double amountLeft = scanner.nextDouble();
                System.out.print("Term Left (in years): ");
                int termLeft = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                Loan loan;
                if (loanType.equalsIgnoreCase("Auto")) {
                    loan = new AutoLoan(recordID, interestRate, amountLeft, termLeft);
                } else if (loanType.equalsIgnoreCase("Mortgage")) {
                    System.out.print("Overpayment (percentage between 0 and 2): ");
                    double overpayment = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    loan = new MortgageLoan(recordID, interestRate, amountLeft, termLeft, overpayment);
                } else {
                    System.out.println("Invalid loan type. Skipping this record."); // Print invalid loan type
                    continue;
                }

                customer.addLoanRecord(loan); // Add loan to customers loan record
            }

            bank.registerCustomer(customer); // Register the customer with the bank
        }

        bank.printAllCustomersInformation(); // Print all information of all customers

        scanner.close(); // Close the scanner
    }
}
