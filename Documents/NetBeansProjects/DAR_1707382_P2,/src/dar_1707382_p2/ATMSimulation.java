package dar_1707382_p2;

import java.util.*;
import java.io.*;

public class ATMSimulation {
//WHENMERGE CHECK There!!!!!!!!

    public static void main(String[] args) throws FileNotFoundException, InputMismatchException {

        int arrivaltime = 0;//since first costumer came in 0 minutes 
        int departuretime = 10000;//when will costumer leave
        int departuretime2 = 0; //when did costumer before leave
        boolean serverbusy = false;

        File file = new File("ATMSimulation1.txt");

        if (!file.exists()) {
            System.out.println(" input file does not exists");
            System.exit(0);
        }
        File f2 = new File("ATMSimulation.txt");

        String command;

        customer hp = new customer();
   
        accounts acc = new accounts();
account accs;
        ATMQueues outsideQ = new ATMQueues();
        ATMQueues ATMQ = new ATMQueues();

        currencyStack num50 = new currencyStack(); //first Stack
        currencyStack num100 = new currencyStack();// second stack
        currencyStack num500 = new currencyStack(); //third stack

        currency curr50 = new currency(50);
        currency curr100 = new currency(100);
        currency curr500 = new currency(500);
//read all commands 
        //Add accounts
        //day has how many minutes then make minutes 10000 or another big number

        //queue from linked list
        try (
                //Scanner to read from input
                Scanner input = new Scanner(file);
                //Printwriter to print on output
                PrintWriter out = new PrintWriter(f2)) {

            while (input.hasNext()) {

                command = input.next();
                switch (command) {
                    case "ADDACCOUNT": {
                        //CHECK IF ACCOUNT IS CORRECT
                        String AccNum = input.next();
                        int pinNumber = input.nextInt();
                        int balance = input.nextInt();
                        accs= new account(AccNum, pinNumber, balance);
                         acc.insert(accs);
                         
                        out.println("Account created with account number " + AccNum + " with " + balance + " SAR initial balance.");
                       
                        break;
                    }

                    case "WITHDRAW":
                    case "DEPOSIT":
                    case "BALANCE":
                    case "REFILL": {

                        int time = input.nextInt();
                        String accNum = input.next();
                        int pin = input.nextInt();

                        //Customer should enter the correct pin
                        int waitingtime;
                        if (time < departuretime2) {
                            waitingtime = departuretime2 - time;

                        } else {
                            waitingtime = 0;
                        }

                        int amount = input.nextInt();
                        hp = new customer(accNum, pin, time, waitingtime, amount, command);
                        outsideQ.enqueue(hp);
                        //ATMQ.enqueue(cust);
                        departuretime2 = time + waitingtime + servertime(command);
                        break;

                    }

                }

            }

            ATMQueues hlp = outsideQ;

            while (!outsideQ.isEmpty()) {
                customer custt = hlp.getFront();
                if (custt.getCommand().equals("REFILL")) {
                    int refilltime = 20;
                    int refillAmount = custt.getAmount();
                    arrivaltime = custt.getArrivalTime();
                    account account = acc.findAccount(custt.getAccountNumber());

                    if (arrivaltime < departuretime) {
                        if (!serverbusy) {
                            outsideQ.dequeue();
                            departuretime = arrivaltime + refilltime + custt.getWaitingTime();
                            serverbusy = true;

                            int x500 = refillAmount / 500;
                            for (int i = 0; i < x500; i++) {
                                if (!num500.isFull()) {
                                    num500.push(curr500);
                                }
                            }//eda3 it adds to stacks

                            int x100 = (refillAmount - (500 * x500)) / 100;
                            for (int i = 0; i < x100; i++) {
                                if (!num100.isFull()) {
                                    num100.push(curr100);
                                }
                            }
                            int x50 = (refillAmount - (500 * x500 + 100 * x100)) / 50;
                            for (int i = 0; i < x50; i++) {
                                if (!num50.isFull()) {
                                    num50.push(curr50);
                                }
                            }
                            int refillamount = (200 - x500) * 500 + (200 - x100) * 100 + (200 - x50) * 50;
                            custt.setAmount(refillamount);

                            out.println("\n-------------------------------------------------\n");
                            out.println("The customer arrived at " + getTime(custt.getArrivalTime()));
                            out.println("The customer waited for " + custt.getWaitingTime()+ " minutes");
                            out.println("The customer departed at " + getTime(departuretime));
                            out.println("The status of three currency stacks is as follows:");
                            out.println(x500 + " x 500");
                            out.println(x100 + " x 100");
                            out.println(x50 + " x 50");
                            out.println("The total amount refilled is " + refillamount);

                        } else {

                            ATMQ.enqueue(outsideQ.dequeue());
                        }
                    } else {
                          int x500 = refillAmount / 500;
                            for (int i = 0; i < x500; i++) {
                                if (!num500.isFull()) {
                                    num500.push(curr500);
                                }
                            }
                           int x100 = (refillAmount - (500 * x500)) / 100;
                            for (int i = 0; i < x100; i++) {
                                if (!num100.isFull()) {
                                    num100.push(curr100);
                                }
                            }
                            int x50 = (refillAmount - (500 * x500 + 100 * x100)) / 50;
                            for (int i = 0; i < x50; i++) {
                                if (!num50.isFull()) {
                                    num50.push(curr50);
                                }
                            }
                            int refillamount = (200 - x500) * 500 + (200 - x100) * 100 + (200 - x50) * 50;
                            custt.setAmount(refillamount);
                            
                         if (ATMQ.isEmpty()) {
                            ATMQ.dequeue();
                            departuretime = custt.getArrivalTime() + refilltime + custt.getWaitingTime();
                            out.println("\n-------------------------------------------------\n");
                            out.println("The customer arrived at " + getTime(custt.getArrivalTime()));
                            out.println("The customer waited for " + getTime(custt.getWaitingTime()));
                            out.println("The customer departed at " + getTime(departuretime)); //change hour and minutes
                            out.println("The status of three currency stacks is as follows:");
                            out.println(x500 + " x 500");
                            out.println(x100 + " x 100");
                            out.println(x50 + " x 50");
                            out.println("The total amount refilled is " + refillamount);
                    }}

                } else if (custt.getCommand().equals("BALANCE")) {
                    int time = 1;

                    int nextarrivaltime = custt.getArrivalTime();
                    if (nextarrivaltime < departuretime) {
                        if (!serverbusy) {
                            outsideQ.dequeue();
                            departuretime = custt.getArrivalTime() + time + custt.getWaitingTime();
                            serverbusy = true;

                            out.println("\n-------------------------------------------------\n");
                            out.println("The customer arrived at " + getTime(custt.getArrivalTime()));
                            out.println("The customer waited for " + custt.getWaitingTime()+ " minutes");
                            out.println("The customer departed at " + getTime(departuretime)); //change hour and minutes
                            account account = acc.findAccount(custt.getAccountNumber());
                            if (!Checkpin(custt.getPinNumber(), account)) {
                                out.println("Invalid Pin");

                            } else {
                                out.println("Balance of the account number " + custt.getAccountNumber() + " is " + account.getBalance());
                            }

                        } else { //server is busy

                            ATMQ.enqueue(outsideQ.dequeue());
                        }

                    } else { //departure 
                        if (ATMQ.isEmpty()) {
                            ATMQ.dequeue();
                            departuretime = custt.getArrivalTime() + time + custt.getWaitingTime();
                            out.println("\n-------------------------------------------------\n");
                            out.println("The customer arrived at " + getTime(custt.getArrivalTime()));
                            out.println("The customer waited for " + getTime(custt.getWaitingTime()));
                            out.println("The customer departed at " + getTime(departuretime)); //change hour and minutes
                            account account = acc.findAccount(custt.getAccountNumber());
                            if (!Checkpin(custt.getPinNumber(), account)) {
                                out.println("Invalid Pin");

                            } else {
                                out.println("Balance of the account number " + custt.getAccountNumber() + " is " + account.getBalance());
                            }
                        } else {
                            serverbusy = false;
                            departuretime = 20000;
                        }

                    }
                } /*
                    

                 Balance of the account number 2442707751 is 46956
                 */ else if (custt.getCommand().equals("WITHDRAW")) {
                    int withdrawaltime = 2;

                    int x500 = custt.getAmount() / 500;
                    for (int i = 0; i < x500; i++) {
                        if (!num500.isEmpty()) {
                            num500.pop();
                        }
                    }//eda3 it adds to stacks

                    int x100 = (custt.getAmount() - (500 * x500)) / 100;
                    for (int i = 0; i < x100; i++) {
                        if (!num100.isEmpty()) {
                            num100.pop();
                        }
                    }
                    int x50 = (custt.getAmount() - (500 * x500 + 100 * x100)) / 50;
                    for (int i = 0; i < x50; i++) {
                        if (!num50.isEmpty()) {
                            num50.pop();
                        }
                    }

                    int nextarrivaltime = custt.getArrivalTime();
                    if (nextarrivaltime < departuretime) {
                        if (!serverbusy) {
                            outsideQ.dequeue();
                            departuretime = custt.getArrivalTime() + withdrawaltime + custt.getWaitingTime();
                            serverbusy = true;

                            out.println("\n-------------------------------------------------\n");
                            out.println("The customer arrived at " + getTime(custt.getArrivalTime()));
                            out.println("The customer waited for " + custt.getWaitingTime()+ " minutes");
                            out.println("The customer departed at " + getTime(departuretime)); //change hour and minutes
                            account account = acc.findAccount(custt.getAccountNumber());
                            if (!Checkpin(custt.getPinNumber(), account)) {
                                out.println("Invalid Pin");

                            } else {
                                out.println(custt.getAmount() + " SAR withdrawn from the account number  " + custt.getAccountNumber());
                                out.println(" The amount includes" + x500 + " x 500 + " + x100 + " x 100 + " + x50 + " x 50");
                                out.println("The new balance is " + (account.getBalance() - custt.getAmount()));
                            }

                        } else {

                            ATMQ.enqueue(outsideQ.dequeue());
                        }
                    } else {
                        if (ATMQ.isEmpty()) {
                            ATMQ.dequeue();
                            departuretime = custt.getArrivalTime() + withdrawaltime + custt.getWaitingTime();
                            out.println("\n-------------------------------------------------\n");
                            out.println("The customer arrived at " + getTime(custt.getArrivalTime()));
                            out.println("The customer waited for " + custt.getWaitingTime()+ " minutes");
                            out.println("The customer departed at " + getTime(departuretime)); //change hour and minutes
                            account account = acc.findAccount(custt.getAccountNumber());
                            if (!Checkpin(custt.getPinNumber(), account)) {
                                out.println("Invalid Pin");

                            } else {
                                out.println(custt.getAmount() + " SAR withdrawn from the account number  " + custt.getAccountNumber());
                                out.println("The new balance is " + (account.getBalance() - custt.getAmount()));
                            }

                        } else {
                            serverbusy = false;
                            departuretime = 20000;
                        }
                    }

                } else if (custt.getCommand().equals("DEPOSIT")) {
                    int deposittime = 2;
                    int depositAmount = custt.getAmount();
                    int x500 = depositAmount / 500;

                    for (int i = 0; i < x500; i++) {
                        if (!num500.isFull()) {
                            num500.push(curr500);
                        }
                    }//eda3 it adds to stacks

                    int x100 = (depositAmount - (500 * x500)) / 100;
                    for (int i = 0; i < x100; i++) {
                        if (!num100.isFull()) {
                            num100.push(curr100);
                        }
                    }
                    int x50 = (depositAmount - (500 * x500 + 100 * x100)) / 50;
                    for (int i = 0; i < x50; i++) {
                        if (!num50.isFull()) {
                            num50.push(curr50);
                        }
                    }

                    int nextarrivaltime = custt.getArrivalTime();
                    if (nextarrivaltime < departuretime) {
                        if (!serverbusy) {
                            outsideQ.dequeue();
                            departuretime = custt.getArrivalTime() + deposittime + custt.getWaitingTime();
                            serverbusy = true;
                            out.println("\n-------------------------------------------------\n");
                            out.println("The customer arrived at " +getTime(custt.getArrivalTime()));
                            out.println("The customer waited for " + custt.getWaitingTime()+ " minutes");
                            out.println("The customer departed at " + getTime(departuretime)); //change hour and minutes
                            account account = acc.findAccount(custt.getAccountNumber());
                            if (!Checkpin(custt.getPinNumber(), account)) {
                                out.println("Invalid Pin");

                            } else {
                                out.println(custt.getAmount() + " SAR successfully deposited  into the account number " + custt.getAccountNumber());
                                out.println("The amount includes " + x500 + " x 500 + " + x100 + " x 100 + " + x50 + " x 50");
                                out.println("The new balance is " + (custt.getAmount() + account.getBalance()));

                            }
                        } else {

                            ATMQ.enqueue(outsideQ.dequeue());
                        }

                    } else {
                        if (ATMQ.isEmpty()) {
                            ATMQ.dequeue();
                            departuretime = custt.getArrivalTime() + deposittime + custt.getWaitingTime();
                            out.println("\n-------------------------------------------------\n");
                            out.println("The customer arrived at " + getTime(custt.getArrivalTime()));
                            out.println("The customer waited for " + custt.getWaitingTime()+ " minutes");
                            out.println("The customer departed at " + getTime(departuretime)); //change hour and minutes
                            account account = acc.findAccount(custt.getAccountNumber());
                            if (!Checkpin(custt.getPinNumber(), account)) {
                                out.println("Invalid Pin");
                            } else {
                                out.println(custt.getAmount() + " SAR successfully deposited  into the account number " + custt.getAccountNumber());
                                out.println("The amount includes " + x500 + " x 500 + " + x100 + " x 100 + " + x50 + " x 50");
                                account.setBalance(custt.getAmount() + account.getBalance());
                                out.println("The new balance is " + (account.getBalance()));

                            }
                        } else {
                            serverbusy = false;
                            departuretime = 20000;
                        }

                    }

                 
                }

            }
        }

    }

    public static boolean CheckAccNum(String AccNum) {
        if (AccNum.length() > 10) {
            return false;
        } else {
            return true;
        }

    }

    public static account findAccount(String accountNumber, account accs) {
        account helpPtr = accs;
        while (helpPtr != null && !helpPtr.getAccountNumber().equals(accountNumber)) {

            helpPtr = helpPtr.getNext();
        }

        return helpPtr; // step one node over		
    }

    public static boolean Checkpin(int pin, account accs) {
        account helpPtr = accs;
        while (helpPtr != null && helpPtr.getPinNumber() != pin) {
            helpPtr = helpPtr.getNext();
        }
        return true;

    }

    public static int servertime(String comm) {
        switch (comm) {
            case "WITHDRAW":
                return 2;
            case "REFILL":
                return 20;
            case "BALANCE":
                return 1;
            case "DEPOSIT":
                return 5;
        }
        return 0;
    }

    public static String getTime(int time) {
        String hours = formatHours(time);
        String minutes = formatMinutes(time);

        return (hours + ":" + minutes);
    }

    public static String formatHours(int time) {
        int hours = time / 60;
        return hours + "";
    }

    public static String formatMinutes(int time) {
        int m = time % 60;
        String minute = m + "";
        if (m < 10) {
            minute = 0 + minute;
        }
        return minute;
    }
}
