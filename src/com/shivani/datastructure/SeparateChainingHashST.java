package com.shivani.datastructure;

import java.util.LinkedList;
import java.util.Queue;

import com.shivani.interfaces.HashMapOperations;

public class SeparateChainingHashST<Key, Value> implements HashMapOperations<Key,Value> {
    private int initialCapacity;

    private int keyValuePairs;                                // number of key-value pairs
    private int hashTableSize;                                // hash table size
    private SequentialSearchST<Key, Value>[] hashMap;  // array of linked-list symbol tables

    public SeparateChainingHashST() {
        this.initialCapacity = 4;
    } 
    
    public SeparateChainingHashST(int size) {
        this.hashTableSize = size;
        hashMap = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[size];
        for (int i = 0; i < size; i++)
            hashMap[i] = new SequentialSearchST<Key, Value>();
    } 

    // resize the hash table to have the given number of chains,
    // rehashing all of the keys
    private void resize(int chains) {
        SeparateChainingHashST<Key, Value> tempHashMap = new SeparateChainingHashST<Key, Value>(chains);
        for (int i = 0; i < hashTableSize; i++) {
            for (Key key : hashMap[i].keys()) {
                tempHashMap.put(key, hashMap[i].get(key));
            }
        }
        this.hashTableSize  = tempHashMap.hashTableSize;
        this.keyValuePairs  = tempHashMap.keyValuePairs;
        this.hashMap = tempHashMap.hashMap;
    }

    // hash value between 0 and m-1
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % hashTableSize;
    } 
    public int size() {
        return keyValuePairs;
    } 
    public boolean isEmpty() {
        return size() == 0;
    }
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    } 

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        int i = hash(key);
        return hashMap[i].get(key);
    } 

    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }

        // double table size if average length of list >= 10
        if (keyValuePairs >= 10*hashTableSize) resize(2*hashTableSize);

        int i = hash(key);
        if (!hashMap[i].contains(key)) keyValuePairs++;
        hashMap[i].put(key, val);
    } 

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");

        int i = hash(key);
        if (hashMap[i].contains(key)) keyValuePairs--;
        hashMap[i].delete(key);

        // halve table size if average length of list <= 2
        if (hashTableSize > initialCapacity && keyValuePairs <= 2*hashTableSize) resize(hashTableSize/2);
    } 

    public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<>();
        for (int i = 0; i < hashTableSize; i++) {
            for (Key key : hashMap[i].keys())
                queue.add(key);
        }
        return queue;
    } 

}
