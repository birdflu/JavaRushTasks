package com.javarush.task.task37.task3707;

import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet implements Serializable, Cloneable, Set {
  private static final Object PRESENT = new Object();
  private transient HashMap<E, Object> map;
  
  public AmigoSet() {
    map = new HashMap<>();
  }
  
  public AmigoSet(Collection<? extends E> collection) {
    map = new HashMap<>(16 > collection.size() / .75f
            ? 16
            : (int) (collection.size() / .75f + 1));
    this.addAll(collection);
  }
  
  @Override
  public Iterator iterator() {
    return map.keySet().iterator();
  }
  
  @Override
  public int size() {
    return map.size();
  }
  
  @Override
  public boolean isEmpty() {
    return map.isEmpty();
  }
  
  @Override
  public boolean contains(Object o) {
    return map.containsKey(o);
  }
  
  @Override
  public void clear() {
    map.clear();
  }
  
  @Override
  public boolean remove(Object o) {
    return map.remove(o) != null;
  }
  
  @Override
  public boolean add(Object o) {
    try {
      return map.put((E) o, PRESENT) == null;
    } catch (Exception e) {
      return false;
    }
  
  }
}
