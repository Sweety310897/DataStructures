package com.shivani.datastructure;
import java.util.Arrays;

import com.shivani.exceptions.ArrayListException;
import com.shivani.interfaces.ArrayListOperations;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ArrayList implements ArrayListOperations {
	private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		 
    private Object[] elementsStore;
    private int originalSize = 0;
     
    public ArrayList(){
        elementsStore = new Object[10];
    }
     
    public Object getElement(int index){
        if(index < originalSize){
            return elementsStore[index];
        } else {
            throw new ArrayListException("Index is out of range");
        }
    }
     
    public void addElement(Object obj){
        if(elementsStore.length-originalSize <= 5){
            increaseListSize();
        }
        elementsStore[originalSize++] = obj;
    }
     
    public Object removeElement(int index){
        if(index < originalSize){
            Object obj = elementsStore[index];
            elementsStore[index] = null;
            int tmp = index;
            while(tmp < originalSize){
                elementsStore[tmp] = elementsStore[tmp+1];
                elementsStore[tmp+1] = null;
                tmp++;
            }
            originalSize--;
            return obj;
        } else {
            throw new ArrayListException("Element is not removed");
        }
         
    }
     
    public int sizeOfArrayList(){
        return originalSize;
    }
     
    public void increaseListSize(){
        elementsStore = Arrays.copyOf(elementsStore, elementsStore.length*2);
        logger.log(Level.INFO, "New length is: "+elementsStore.length);
    }
  
    public void displayArrayList() {
    	for(Object each: elementsStore) {
    	    logger.log(Level.INFO, "Elements are : "+each);
    	    
    	}
    }
}