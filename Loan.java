import java.text.DecimalFormat; // Importing decimalformat from java.text package

// Abstract class representing a loan
abstract class Loan {
    protected String recordID; // A unique identifier for the loan record
    protected String loanType; // Type of loan (e.g., Auto, Mortgage)
    protected double interestRate; // Annual interest rate for the loan
    protected double amountLeft; // Remaining amount of the loan
    protected int termLeft; // Remaining term of the loan (in years)

    // Constructor for Loan class initializing its properties
    public Loan(String recordID, String loanType, double interestRate, double amountLeft, int termLeft) {
    	// Assigning each of the loan properties
        this.recordID = recordID;
        this.loanType = loanType;
        this.interestRate = interestRate;
        this.amountLeft = amountLeft;
        this.termLeft = termLeft;
    }
    
    // Abstract method to update loan information based on remaining term
    abstract void updateInformation(int termLeft);
    
    // Abstract method to calculate monthly payment for the loan
    abstract double calculateMonthlyPayment();
    
    // Abstract method to calculate total payment for the loan
    abstract double calculateTotalPayment();
}
