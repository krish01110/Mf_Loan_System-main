package org.ncu.mf_loan_system.service;

import org.ncu.mf_loan_system.entities.Loan;
import org.ncu.mf_loan_system.repository.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    @Transactional(readOnly = true) // Read-only transaction for fetching loans
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true) // Read-only transaction for fetching a single loan
    public Loan getLoanById(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found with id: " + id));
    }

    @Override
    @Transactional // This ensures the loan is saved within a transaction
    public Loan createLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    @Transactional // This ensures the loan update is saved within a transaction
    public Loan updateLoan(Long id, Loan loan) {
        Loan existing = getLoanById(id);
        existing.setPrincipalAmount(loan.getPrincipalAmount());
        existing.setInterestRate(loan.getInterestRate());
        existing.setStartDate(loan.getStartDate());
        existing.setEndDate(loan.getEndDate());
        return loanRepository.save(existing);
    }

    @Override
    @Transactional // This ensures the loan deletion is handled within a transaction
    public void deleteLoan(Long id) {
        Loan loan = getLoanById(id);
        loanRepository.delete(loan);
    }
}
