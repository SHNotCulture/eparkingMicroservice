package com.eparking.writeLock;

import java.util.Collection;

public interface BaseLock<T> {
    boolean containsKey(String key);
    <T> T get(String key);
    <T> T put(String key, T value);
    Collection<T> allValues();
    void remove(String key);
    void clear();
}
