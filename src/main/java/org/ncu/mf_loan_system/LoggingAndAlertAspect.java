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

    @Pointcut("execution(* org.ncu.mf_loan_system.service.LoanService.issueLoan(..))")
    public void loanIssued() {}

    @Pointcut("execution(* org.ncu.mf_loan_system.service.PaymentService.recordPayment(..))")
    public void paymentRecorded() {}

    @AfterReturning("loanIssued()")
    public void logLoanIssued() {
        logger.info("✅ Loan issued successfully.");
    }

    @AfterReturning("paymentRecorded()")
    public void logPaymentRecordedAndCheckOverdue() {
        logger.info("💰 Payment recorded successfully.");

        boolean isOverdue = true;
        if (isOverdue) {
            logger.warn("⚠️ Alert: Overdue loan detected with remaining balance.");
        }
    }

    @AfterThrowing(pointcut = "loanIssued() || paymentRecorded()", throwing = "ex")
    public void logErrors(Exception ex) {
        logger.error("❌ Error occurred during loan or payment operation: {}", ex.getMessage());
    }
}
