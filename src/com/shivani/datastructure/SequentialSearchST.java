package com.shivani.datastructure;

import java.util.LinkedList;
import java.util.Queue;

public class SequentialSearchST<Key, Value> {
    private int keyValuePairs;           // number of key-value pairs
    private Node first;      // the linked list of key-value pairs

    // a helper linked list data type
    private class Node {
        private Key key;
        private Value value;
        private Node next;

        public Node(Key key, Value val, Node next)  {
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

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null"); 
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.value;
        }
        return null;
    }

    public void put(Key key, Value value) {
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

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null"); 
        first = delete(first, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        if (key.equals(x.key)) {
            keyValuePairs--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    
    public Iterable<Key> keys()  {
    	Queue<Key> queue = new LinkedList<>();
        for (Node x = first; x != null; x = x.next)
            queue.add(x.key);
        return queue;
    }


}