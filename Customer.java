import java.util.ArrayList; // Importing ArrayList class from java.util package
import java.util.List; // Importing List interface from java.util package

// Class representing a customer
class Customer implements CheckerPrinter {
    private String customerID; // Unique identifier for the customer
    private double income; // Income of the customer
    private String eligibilityStatus; // Status indicating eligibility for new loans
    private List<Loan> creditRecords; // List of loan records associated with the customer

    public Customer(String customerID, double income) {
        this.customerID = customerID; // Assigning provided customerID to instance variable
        this.income = income; // Assigning provided income to instance variable
        this.eligibilityStatus = "Unknown"; // Setting eligibility status to "Unknown"
        this.creditRecords = new ArrayList<>(); // Initialise the list of loan records as an empty ArrayList
    }

    // Method to check eligibility of the customer for new loans based on income
    @Override
    public boolean checkEligibility(double income) {
        double totalAmount = 0; // Variable to store total amount of outstanding loans
        for (Loan loan : creditRecords) { // Iterating through each loan record associated with the customer
            totalAmount += loan.amountLeft; // Adding the amount left for each loan to the total amount
        }
        // Checking if the total amount of outstanding loans is less than or equal to 4 times the income
        if (totalAmount <= 4 * income) {
            eligibilityStatus = "YES"; // Setting eligibility status to yes
            return true; // Returning true indicating eligibility
        } else {
            eligibilityStatus = "NO"; // Setting eligibility status to no
            return false; // Return false
        }
    }

    // Method to print details of the customer including loan records
    @Override
    public void printDetails() {
        // Printing customer details header
        System.out.println("================================");
        System.out.println("CustomerID: " + customerID);
        System.out.println("Eligible to arrange new loans: " + eligibilityStatus);
        System.out.println("RecordID\tLoanType\tIntRate\tAmountLeft\tTimeLeft");
        // Iterating through each loan record associated with the customer and printing its details
        for (Loan loan : creditRecords) {
            // Using formatted output to print loan record details in a tabular format
            System.out.printf("%-8s\t%-8s\t%-7.2f\t%-10.2f\t%-8d\n", loan.recordID, loan.loanType, loan.interestRate, loan.amountLeft, loan.termLeft);
        }
        // Printing footer
        System.out.println("================================");
    }


    public void updateIncome(double income) {
        this.income = income; // Updating the income of the customer
        checkEligibility(income); // Re-check eligibility based on the updated income
    }

    // Method to add a new loan record to the customer's records
    public void addLoanRecord(Loan loan) {
        creditRecords.add(loan); // Adding the provided loan record to the list of loan records
    }

    // Method to remove a loan record from the customer's records
    public void removeLoanRecord(Loan loan) {
        creditRecords.remove(loan); // Removing the provided loan record from the list of loan records
    }

    // Getter method to retrieve the customer's ID
    public String getCustomerID() {
        return customerID; // Returning the customer's ID
    }
}
