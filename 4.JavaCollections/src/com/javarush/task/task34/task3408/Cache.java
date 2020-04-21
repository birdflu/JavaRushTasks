package com.javarush.task.task34.task3408;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<K, V>();   //TODO add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception {
        if (cache.containsKey(key))
            return cache.get(key);
        else {
            Constructor constructor = clazz.getDeclaredConstructor(key.getClass());
            V value = (V) constructor.newInstance(key);
            cache.put(key, value);
            return value;
        }
    }

    public boolean put(V obj) {
        try {
            Method getKey = obj.getClass().getDeclaredMethod("getKey");
            getKey.setAccessible(true);
            K key = (K) getKey.invoke(obj);
            cache.put(key, obj);
            return true;
        
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return false;
            //e.printStackTrace();
        }
       // return false;
    }

    public int size() {
        return cache.size();
    }
}
