package com.shivani.datastructure;

import java.util.LinkedList;
import java.util.Queue;

public class SequentialSearchST<K, V> {
    private int keyValuePairs;           // number of key-value pairs
    private Node first;      // the linked list of key-value pairs

    // a helper linked list data type
    private class Node {
        private K key;
        private V value;
        private Node next;

        public Node(K key, V val, Node next)  {
            this.key  = key;
            this.value  = val;
            this.next = next;
        }
    }

    /**
     * Initializes an empty symbol table.
     */
    public SequentialSearchST() {
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
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.value;
        }
        return null;
    }

    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null"); 
        if (value == null) {
            delete(key);
            return;
        }

        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        }
        first = new Node(key, value, first);
        keyValuePairs++;
    }

    public void delete(K key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null"); 
        first = delete(first, key);
    }

    private Node delete(Node x, K key) {
        if (x == null) return null;
        if (key.equals(x.key)) {
            keyValuePairs--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    
    public Iterable<K> keys()  {
    	Queue<K> queue = new LinkedList<>();
        for (Node x = first; x != null; x = x.next)
            queue.add(x.key);
        return queue;
    }


}