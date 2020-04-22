package com.javarush.task.task37.task3707;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

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
    return null;
  }
  
  @Override
  public int size() {
    return 0;
  }
  
  @Override
  public void forEach(Consumer action) {
  
  }
  
  @Override
  public boolean removeIf(Predicate filter) {
    return false;
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
