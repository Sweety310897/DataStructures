package com.shivani.interfaces;

public interface HashMapOperations<Key,Value> {
	public int size();
	public boolean isEmpty();
	public boolean contains(Key key);
	public Value get(Key key);
	public void put(Key key, Value val);
	public void delete(Key key);
}
