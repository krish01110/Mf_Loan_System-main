package org.ncu.mf_loan_system.service;

import org.ncu.mf_loan_system.entities.Loan;
import java.util.List;

public interface LoanService {
    // Example method for issuing a loan
    public default void issueLoan(String clientId, double amount) {
        // Logic for issuing loan
    }

    // Example method for checking overdue loans
    public default void checkOverdueLoan(Long loanId) {
        // Logic for checking if the loan is overdue
    }
    List<Loan> getAllLoans();
    Loan getLoanById(Long id);
    Loan createLoan(Loan loan);
    Loan updateLoan(Long id, Loan loan);
    void deleteLoan(Long id);
}