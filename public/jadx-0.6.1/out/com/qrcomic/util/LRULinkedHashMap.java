package com.qrcomic.util;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final long serialVersionUID = 1;
    private final int maxCapacity;

    public LRULinkedHashMap(int i) {
        super(i, DEFAULT_LOAD_FACTOR, true);
        this.maxCapacity = i;
    }

    protected boolean removeEldestEntry(Entry<K, V> entry) {
        return size() > this.maxCapacity;
    }
}
