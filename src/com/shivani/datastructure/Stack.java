package com.shivani.datastructure;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.shivani.exceptions.StackException;
import com.shivani.interfaces.StackOperations;

public class Stack implements StackOperations {
	private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private int topElement;   
    private int stackSize;  
    private Object[] stackArr;  
	Stack(int size) {
		stackSize = size;
		topElement = -1;
		stackArr = new Object[stackSize];
	}
	public boolean isStackEmpty() { 
        return (topElement == -1); 
    } 
	public boolean isStackFull() {
		return (topElement == stackSize - 1);
	}
	public void pushItem(Object obj) {
		if(topElement == stackSize-1) {
		    logger.log(Level.INFO, "size is increasing");
		    increaseStackCapacity();
			
		}
		else {
            stackArr[topElement++]= obj;  
            logger.log(Level.INFO, "element pushed successfully" + obj);
		}
	}
	public Object popItem() {
		 if (topElement < 0) { 
			throw new StackException("Stack is Unerflow");
	     } 
	     else { 
	    	 return stackArr[topElement--];
	     } 
	}
	public void increaseStackCapacity(){
        
        Object[] newStack = new Object[stackSize*2];
        for(int i=0;i<stackSize;i++){
            newStack[i] = stackArr[i];
        }
        stackArr = newStack;
        stackSize = stackSize*2;
    }
	public Object peek() {
		if (topElement < 0) { 
			throw new StackException("Stack is UnderFlow");
        } 
        else { 
            return stackArr[topElement]; 
        } 
	}
	public void displayStack() {
		for(Object each: stackArr) {
            logger.log(Level.INFO, "element pushed successfully" + each);
    		
		}
	}
}
