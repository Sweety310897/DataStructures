package com.shivani.interfaces;

public interface HashMapOperations<K,V> {
	public int size();
	public boolean isEmpty();
	public boolean contains(K key);
	public V get(K key);
	public void put(K key, V val);
	public void delete(K key);
}
