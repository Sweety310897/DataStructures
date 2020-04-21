package com.shivani.interfaces;

public interface StackOperations {
	public boolean isStackEmpty();
	public boolean isStackFull();
	public void pushItem(Object obj);
	public Object popItem();
	public void increaseStackCapacity();
	public Object peek();
	public void displayStack();
	
}
