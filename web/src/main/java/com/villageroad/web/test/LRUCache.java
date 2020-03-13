package com.villageroad.web.test;

import java.util.LinkedHashMap;

class LRUCache {
    private LinkedHashMap<Integer, Integer> cache = new LinkedHashMap();
    private int capacity = 0;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Integer v = cache.get(key);
        if (v == null) {
            return -1;
        }
        moveToTail(key);
        return v;
    }

    public void put(int key, int value) {
        Integer v = cache.get(key);
        if (v != null) {
            cache.put(key, value);
            moveToTail(key);
        } else {
            if (cache.size() >= capacity) {
                int k = cache.keySet().iterator().next();
                cache.remove(k);
            }
            cache.put(key, value);
        }

    }

    public void moveToTail(int key) {
        int v = cache.get(key);
        cache.remove(key);
        cache.put(key, v);
    }

    public static void main(String[] args) {

    }
}