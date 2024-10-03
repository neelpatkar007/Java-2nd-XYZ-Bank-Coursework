# Java-Advanced-XYZ-Bank-Coursework

Overview
The Loans Management System is a Java-based application designed to streamline the management of customer loan records for a fictional bank, XYZBank. This project allows for the registration of customers, management of their loan information, and assessment of loan eligibility based on specific financial criteria.

Features
Customer Registration: Easily register new customers with a unique Customer ID and annual income.
Loan Management: Add, update, and remove loan records for existing customers while ensuring compliance with eligibility criteria.
Eligibility Assessment: Automatically check if customers are eligible for new loans based on their income and outstanding amounts.
Detailed Reporting: Print detailed information about a specific customer’s loans or all customers in the system.
Technologies Used
Java: The core programming language for building the application.
Object-Oriented Programming: Utilized abstract classes, interfaces, and inheritance to create a well-structured codebase.
Data Handling: Implemented data storage and management using arrays for customer loan records.
Project Structure
Loan.java: An abstract class representing the common properties of different loan types.
[LoanType].java: Subclasses for specific loan types (Auto, Builder, Mortgage, Personal, Other).
CheckerPrinter.java: An interface to handle eligibility checking and customer information printing.
Customer.java: Class representing bank customers and their associated loans.
XYZBank.java: Main application class that manages user interactions and system functionality.
