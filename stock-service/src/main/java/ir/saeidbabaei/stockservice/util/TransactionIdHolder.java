package ir.saeidbabaei.stockservice.util;

import org.springframework.stereotype.Component;

@Component
public class TransactionIdHolder {

    private final ThreadLocal<String> currentTransactionId = new ThreadLocal<>();

    public String getCurrentTransactionId() {
    	
        return currentTransactionId.get();
        
    }

    public void setCurrentTransactionId(String transactionId) {
    	
        currentTransactionId.set(transactionId);
        
    }

}