package com.github.zrff.backend.util.Map2;

import java.util.HashMap;

public class HashMap2<K,V> extends HashMap<K,V> implements Map2<K,V> {
    @Override
    public <T> T get(K key, T defaultVal) {
        if (key==null || get(key)==null) return defaultVal;
        return (defaultVal instanceof Integer)?(T)Integer.valueOf(get(key).toString()):
                (T) get(key);
    }
}
