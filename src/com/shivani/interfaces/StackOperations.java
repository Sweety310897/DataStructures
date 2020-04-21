package com.shivani.interfaces;

public interface StackOperations<E> {
	public boolean isStackEmpty();
	public boolean isStackFull();
	public void pushItem(E element);
	public E popItem();
	public E peek();
	
}
