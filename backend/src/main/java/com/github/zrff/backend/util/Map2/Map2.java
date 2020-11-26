package com.github.zrff.backend.util.Map2;

import java.util.Map;

public interface Map2<K,V> extends Map<K,V> {
    <T> T get(K key,T defaultVal);
}
