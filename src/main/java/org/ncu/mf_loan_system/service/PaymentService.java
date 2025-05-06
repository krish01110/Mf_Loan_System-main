package org.ncu.mf_loan_system.service;

import org.ncu.mf_loan_system.entities.Payment;
import java.util.List;

public interface PaymentService {


        // Example method for recording a payment
        public default void recordPayment(String clientId, double amount, Long loanId) {
            // Logic for recording the payment
        }
        List<Payment> getAllPayments();
    Payment getPaymentById(Long id);
    Payment createPayment(Payment payment);
    Payment updatePayment(Long id, Payment payment);
    void deletePayment(Long id);
}