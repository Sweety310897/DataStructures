package com.shivani.datastructure;

import com.shivani.exceptions.StackException;
import com.shivani.interfaces.StackOperations;

public class Stack<E> implements StackOperations<E> {
	private int topElement;   
    private int stackSize;  
    private E[] stackArr;  
	@SuppressWarnings("unchecked")
	Stack(int size) {
		stackSize = size;
		topElement = -1;
		stackArr = (E[])new Object[stackSize];
	}
	public boolean isStackEmpty() { 
        return (topElement == -1); 
    } 
	public boolean isStackFull() {
		return (topElement == stackSize - 1);
	}
	public void pushItem(E element) {
		if(topElement == stackSize-1) {
		    increaseStackCapacity();
			
		}
		else {
            stackArr[topElement++]= element;  
        }
	}
	public E popItem() {
		 if (topElement < 0) { 
			throw new StackException("Stack is Unerflow");
	     } 
	     else { 
	    	 return stackArr[topElement--];
	     } 
	}
	public void increaseStackCapacity(){
        
        @SuppressWarnings("unchecked")
		E[] newStack = (E[])new Object[stackSize*2];
        for(int i=0;i<stackSize;i++){
            newStack[i] = stackArr[i];
        }
        stackArr = newStack;
        stackSize = stackSize*2;
    }
	public E peek() {
		if (topElement < 0) { 
			throw new StackException("Stack is UnderFlow");
        } 
        else { 
            return stackArr[topElement]; 
        } 
	}

	
	public String toString() {	
    	StringBuilder builder = new StringBuilder();
    	  for (int i = 0; i < stackArr.length; ++i) {
    	    builder.append(stackArr[i]);
    	  }
    	  return builder.toString();
    	
    }
}
