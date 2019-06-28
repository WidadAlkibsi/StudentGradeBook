/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar_1707382_p2;

/**
 *
 * @author widad
 */
public class ATMQueues {
  
private customer front;
  private customer back;

    public customer getFront() {
        return front;
    }

    public void setFront(customer front) {
        this.front = front;
    }


  
    public customer getBack() {
        return back;
    }

    public void setBack(customer back) {
        this.back = back;
    }
  
 public ATMQueues() {
front = null;
back = null;
}

  public customer dequeue() {
        customer temp = front;
        front = dequeue(front);
        if (front == null) {
            back = null;
        }
        return temp;
    }
    //
    // QueueNode | dequeue(QueueNode)
    //

    private customer dequeue(customer front) {
        front = front.getNext();
        return front;
    }

 
 public void enqueue(customer data) {
        if (isEmpty()) {
            front = back = enqueue(front, back, data);
        } else {
            back = enqueue(front, back, data);
        }

    }
private customer enqueue(customer front, customer back, customer data) {
        // Make a new QueueNode with "data" as the data value
         customer temp = data;

        // Now, if the list is empty, return the reference for temp
        // and save this reference into both "front" and "back"
        // Why? Since this is the only node in the queue, it will be the front and back node
        if (isEmpty()) {
            return temp;
        } // ELSE, the queue is not empty. We need to insert temp at the back of the queue.
        // So save the address of the new node into the next of back.
        // Finally, return the updated node.
        else {
            back.setNext(temp);
            return temp;
        }
    }
  public boolean isEmpty() {
        return (front == null);
    }
     public void displayList() {
        customer hpHelper = front;
        while (hpHelper != null) {
            System.out.println(hpHelper.getCommand());
            hpHelper.getNext();
            hpHelper = hpHelper.getNext();
        }
   
}
}