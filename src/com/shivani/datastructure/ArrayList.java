package com.shivani.datastructure;
import java.util.Arrays;

import com.shivani.exceptions.ArrayListException;
import com.shivani.interfaces.ArrayListOperations;


public class ArrayList<E> implements ArrayListOperations<E> {
		 
    private E[] elementsStore;
    private int originalSize = 0;
     
    @SuppressWarnings("unchecked")
	public ArrayList(){
        elementsStore = (E[])new Object[10];
      
    }
     
    public E getElement(int index){
        if(index < originalSize){
            return elementsStore[index];
        } else {
            throw new ArrayListException("Index is out of range");
        }
    }
     
    public void addElement(E element){
        if(elementsStore.length-originalSize <= 5){
            increaseListSize();
        }
        elementsStore[originalSize++] = element;
    }
     
    public E removeElement(int index){
        if(index < originalSize){
            E element = elementsStore[index];
            elementsStore[index] = null;
            int indexValue = index;
            while(indexValue < originalSize){
                elementsStore[indexValue] = elementsStore[indexValue+1];
                elementsStore[indexValue+1] = null;
                indexValue++;
            }
            originalSize--;
            return element;
        } else {
            throw new ArrayListException("Element is not removed");
        }
         
    }
     
    public int sizeOfArrayList(){
        return originalSize;
    }
     
    public void increaseListSize(){
        elementsStore = Arrays.copyOf(elementsStore, elementsStore.length*2);
       
    }
  
    public String toString() {
    	
    	
    	StringBuilder builder = new StringBuilder();
    	  for (int i = 0; i < elementsStore.length; ++i) {
    	    builder.append(elementsStore[i]);
    	  }
    	  return builder.toString();
    	
    }
}