package com.shivani.datastructure;

import java.util.LinkedList;
import java.util.Queue;

import com.shivani.interfaces.HashMapOperations;

public class HashMap<K, V> implements HashMapOperations<K,V> {
    private int initialCapacity;

    private int keyValuePairs;                                // number of key-value pairs
    private int hashTableSize;                                // hash table size
    private SequentialSearchST<K, V>[] hashArr;  // array of linked-list symbol tables

    public HashMap() {
        this.initialCapacity = 4;
    } 
    
    @SuppressWarnings("unchecked")
	public HashMap(int size) {
        this.hashTableSize = size;
        hashArr = new SequentialSearchST[size];
        for (int i = 0; i < size; i++)
            hashArr[i] = new SequentialSearchST<>();
    } 

    // resize the hash table to have the given number of chains,
    // rehashing all of the keys
    private void resize(int chains) {
        HashMap<K, V> tempHashMap = new HashMap<>(chains);
        for (int i = 0; i < hashTableSize; i++) {
            for (K key : hashArr[i].keys()) {
                tempHashMap.put(key, hashArr[i].get(key));
            }
        }
        this.hashTableSize  = tempHashMap.hashTableSize;
        this.keyValuePairs  = tempHashMap.keyValuePairs;
        this.hashArr = tempHashMap.hashArr;
    }

    // hash value between 0 and m-1
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % hashTableSize;
    } 
    public int size() {
        return keyValuePairs;
    } 
    public boolean isEmpty() {
        return size() == 0;
    }
    public boolean contains(K key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    } 

    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        int i = hash(key);
        return hashArr[i].get(key);
    } 

    public void put(K key, V val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }

        // double table size if average length of list >= 10
        if (keyValuePairs >= 10*hashTableSize) resize(2*hashTableSize);

        int i = hash(key);
        if (!hashArr[i].contains(key)) keyValuePairs++;
        hashArr[i].put(key, val);
    } 

    public void delete(K key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");

        int i = hash(key);
        if (hashArr[i].contains(key)) keyValuePairs--;
        hashArr[i].delete(key);

        // halve table size if average length of list <= 2
        if (hashTableSize > initialCapacity && keyValuePairs <= 2*hashTableSize) resize(hashTableSize/2);
    } 

    public Iterable<K> keys() {
        Queue<K> queue = new LinkedList<>();
        for (int i = 0; i < hashTableSize; i++) {
            for (K key : hashArr[i].keys())
                queue.add(key);
        }
        return queue;
    } 

}
