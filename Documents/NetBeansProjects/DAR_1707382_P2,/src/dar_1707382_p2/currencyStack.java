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
public class currencyStack {
  private currency[] stack;  
  private int top;
  private int maxSize;

    public currencyStack() {
        this.maxSize = 200;
        stack= new currency[maxSize];
        top=-1; 
    }

    public currency[] getStack() {
        return stack;
    }

    public void setStack(currency[] stack) {
        this.stack = stack;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
  
    public boolean isEmpty(){
        return(top==-1);
   }
    public boolean isFull(){
        return(top==maxSize-1);
    }
    public void push(currency ammount){
        if(isFull())
          System.out.println("Cannot push because stack is full");
        else 
            stack[++top]=ammount;
     
    }
    public currency pop(){
     if(isEmpty()){
      System.out.println("Cannot push because stack is full");
     return null;}
     else 
         return stack[top--];
     }
    public int top(){
        if(isEmpty()){
       System.out.println("This Stack is Empty");     
        return top=-1;}
        else 
            return top;
    }
   
}
