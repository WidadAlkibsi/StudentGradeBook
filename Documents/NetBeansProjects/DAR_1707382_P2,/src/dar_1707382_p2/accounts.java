/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar_1707382_p2;

//LinkedLists just change to accounts 
public class accounts {

    private account head;

    public account getHead() {
        return head;
    }

    public void setHead(account head) {
        this.head = head;
    }

    public void PrintList() {
        PrintList(head);
    }
    //
    // void | PrintList(LLnode)
    //

    private void PrintList(account head) {
        // We need to traverse...so we need a help ptr
        account helpPtr = head;
        // Traverse to correct insertion point
        while (helpPtr != null) {
            // Print the data value of the node
            System.out.println(helpPtr.getAccountNumber() + ", " + helpPtr.getPinNumber() + ", " + helpPtr.getBalance());
            // Step one node over
            helpPtr = helpPtr.getNext();
        }
        System.out.println();
    }

    public void insert(account acc){ 
        if(isEmpty()){
         head=acc;   
        }
        else{
            account prev=null;
            account hlp=head;
            while((hlp.getNext()!=null)&&(acc.getAccountNumber().compareTo(hlp.getAccountNumber())>0)){
                prev=hlp;
                hlp=hlp.getNext();                
            }
             if(acc.getAccountNumber().compareTo(hlp.getAccountNumber())<0){
                if(prev==null){
                    acc.setNext(head);
                    head=acc;
                }else{
                    prev.setNext(acc);
                    acc.setNext(hlp);
                }
            }
            else {
                hlp.setNext(acc); 
            }
        }
      
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public void displayList() {
        account hpHelper = head;
        while (hpHelper != null) {
            hpHelper.getNext();
            hpHelper = hpHelper.getNext();
        }
    }

    public boolean search(String data) {
        return search(head, data);
    }
    

    private boolean search(account p, String data) {
        account hlp = p;
        while (hlp != null) {
            if (hlp.getAccountNumber().equals(data)) {
                return true;
            }
            hlp = hlp.getNext();
        }
        return false;
    }  
 public account findAccount(String data) {
        return findAccount(head, data);
    }
    

    private account findAccount(account p, String data) {
        account hlp = p;
        while (hlp != null) {
            if (hlp.getAccountNumber().equals(data)) {
                return hlp;
            }
            hlp = hlp.getNext();
        }
        return null;
    }    
    
    
    
}
