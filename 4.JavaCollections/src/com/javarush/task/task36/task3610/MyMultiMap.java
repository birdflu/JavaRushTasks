package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        //напишите тут ваш код
        int size = 0;
        for (List<V> list: map.values()
        ) {
            size = size + list.size();
        }
        return size;
    }

    @Override
    public V put(K key, V value) {
        //напишите тут ваш код
//        V put(K key, V value) - должен добавить элемент value по ключу key.
//        Если в мапе такой ключ уже есть, и количество значений по этому ключу меньше,
//        чем repeatCount - то добавь элемент value в конец листа в объекте map.
//        Если по такому ключу количество значений равняется repeatCount -
//        то удали из листа в объекте map элемент с индексом ноль, и добавь в конец листа value.
//        Метод должен возвращать значение последнего добавленного элемента по ключу key
//        (но не значение, которое мы сейчас добавляем). Если по ключу key значений еще нет - верни null.
//
        V last = null;
        if (map.containsKey(key)) {
            List<V> list = map.get(key);
            if (list == null) {
                if (repeatCount > 0) {
                    List<V> firstValue = new ArrayList<>();
                    firstValue.add(value);
                    map.put(key, firstValue);
                }
            } else {
                int keySize = list.size();
                if (keySize < repeatCount) {
                    last = list.get(list.size() - 1);
                    list.add(value);
                } else if (keySize == repeatCount) {
                    last = list.get(list.size() - 1);
                    list.remove(0);
                    list.add(value);
                }
            }
        } else {
            List<V> firstValue = new ArrayList<>();
            firstValue.add(value);
            map.put(key, firstValue);
        }
        return last;
    }

    @Override
    public V remove(Object key) {
        //напишите тут ваш код
//        должен удалить элемент по ключу key. Если по этому ключу хранится несколько элементов - должен удаляться
//        элемент из листа с индексом ноль. Если по какому-то ключу хранится лист размером ноль элементов
//        - удали такую пару ключ : значение. Метод должен возвращать элемент, который ты удалил.
//        Если в мапе нет ключа key - верни null.
        V deleted = null;
        if (map.containsKey(key)) {
            List<V> list = map.get(key);
            int keySize = list.size();
            if (keySize == 0)
                map.remove(key);
            else {
                deleted = list.get(0);
                list.remove(0);
                if (list.size() == 0) map.remove(key);
            }
        }
    
        return deleted;
    }

    @Override
    public Set<K> keySet() {
        //напишите тут ваш код
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        //напишите тут ваш код
        ArrayList<V> vs = new ArrayList<>();
        for (List<V> list: map.values()
             ) {
            vs.addAll(list);
        }
    return vs;
    }

    @Override
    public boolean containsKey(Object key) {
        //напишите тут ваш код
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        //напишите тут ваш код
        for (List<V> list: map.values()
        ) {
            if (list.contains(value)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}