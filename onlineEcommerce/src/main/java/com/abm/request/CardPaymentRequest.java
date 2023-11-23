package com.abm.request;

public class CardPaymentRequest {
	
	 private String cardNumber;
	    private String cardholderName;
	    private String expirationDate;
	    private String cvv;

	  

	    public CardPaymentRequest() {
	    }

	    public CardPaymentRequest(String cardNumber, String cardholderName, String expirationDate, String cvv) {
	        this.cardNumber = cardNumber;
	        this.cardholderName = cardholderName;
	        this.expirationDate = expirationDate;
	        this.cvv = cvv;
	    }

	    public String getCardNumber() {
	        return cardNumber;
	    }

	    public void setCardNumber(String cardNumber) {
	        this.cardNumber = cardNumber;
	    }

	    public String getCardholderName() {
	        return cardholderName;
	    }

	    public void setCardholderName(String cardholderName) {
	        this.cardholderName = cardholderName;
	    }

	    public String getExpirationDate() {
	        return expirationDate;
	    }

	    public void setExpirationDate(String expirationDate) {
	        this.expirationDate = expirationDate;
	    }

	    public String getCvv() {
	        return cvv;
	    }

	    public void setCvv(String cvv) {
	        this.cvv = cvv;
	    }


}
