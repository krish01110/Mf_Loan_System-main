package org.ncu.mf_loan_system;



import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAndAlertAspect {

    private final Logger logger = LoggerFactory.getLogger(LoggingAndAlertAspect.class);

    // Pointcut for issuing a loan
    @Pointcut("execution(* org.ncu.mf_loan_system.service.LoanService.issueLoan(..))")
    public void loanIssued() {}

    // Pointcut for recording a payment
    @Pointcut("execution(* org.ncu.mf_loan_system.service.PaymentService.recordPayment(..))")
    public void paymentRecorded() {}

    // Log successful loan issuance
    @AfterReturning("loanIssued()")
    public void logLoanIssued() {
        logger.info("✅ Loan issued successfully.");
    }

    // Log successful payment recording + check for overdue
    @AfterReturning("paymentRecorded()")
    public void logPaymentRecordedAndCheckOverdue() {
        logger.info("💰 Payment recorded successfully.");

        // Simulated overdue loan check (stub logic)
        // Replace this logic with real overdue loan scan if needed
        boolean isOverdue = true; // simulate condition
        if (isOverdue) {
            logger.warn("⚠️ Alert: Overdue loan detected with remaining balance.");
        }
    }

    // Log any exceptions thrown during loan issuance or payment
    @AfterThrowing(pointcut = "loanIssued() || paymentRecorded()", throwing = "ex")
    public void logErrors(Exception ex) {
        logger.error("❌ Error occurred during loan or payment operation: {}", ex.getMessage());
    }
}
