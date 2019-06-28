/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar_1707382_p2;

public class account {
 private String accountNumber;
 private int pinNumber;
 private int balance;
 private account next;

  

   public account() {
    }

    public account(String accountNumber, int pinNumber, int balance, account next) {
        this.accountNumber = accountNumber;
        this.pinNumber = pinNumber;
        this.balance = balance;
        this.next = next;
    }

    public account(String accountNumber, int pinNumber, int balance) {
        this.accountNumber = accountNumber;
        this.pinNumber = pinNumber;
        this.balance = balance;
    }

  
    

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public account getNext() {
        return next;
    }

    public void setNext(account next) {
        this.next = next;
    }
 
 
}
