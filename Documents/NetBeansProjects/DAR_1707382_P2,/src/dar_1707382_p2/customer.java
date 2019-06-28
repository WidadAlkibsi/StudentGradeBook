/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar_1707382_p2;

public class customer {

    private String accountNumber;
    private int pinNumber;

    @Override
    public String toString() {
        return "customer{" + "accountNumber=" + accountNumber + ", pinNumber=" + pinNumber + ", arrivalTime=" + arrivalTime + ", waitingTime=" + waitingTime + ", amount=" + amount + ", command=" + command + ", next=" + next + '}';
    }
    private int arrivalTime;
    private int waitingTime;
    private int amount;
    private String command;
    private customer next;

    public customer(String accountNumber, int pinNumber, int arrivalTime, int waitingTime, int amount, String command) {
        this.accountNumber = accountNumber;
        this.pinNumber = pinNumber;
        this.arrivalTime = arrivalTime;
        this.waitingTime = waitingTime;
        this.amount = amount;
        this.command = command;
    }

    public customer() {

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

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public customer getNext() {
        return next;
    }

    public void setNext(customer next) {
        this.next = next;
    }

}
