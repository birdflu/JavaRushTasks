package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet implements Serializable, Cloneable, Set {
  private static final Object PRESENT = new Object();
  public transient HashMap<E, Object> map;
  
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
  
  @Override
  public Object clone() throws InternalError {
    try {
      AmigoSet copy = (AmigoSet) super.clone();
      copy.map = (HashMap) map.clone();
      return copy;
    } catch (Exception e) {
      throw new InternalError(e);
    }
  }
  
  private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
    objectOutputStream.defaultWriteObject();
    int size = map.size();
    int capacity = HashMapReflectionHelper.callHiddenMethod(map, "capacity");
    float loadFactor  = HashMapReflectionHelper.callHiddenMethod(map, "loadFactor");
    objectOutputStream.writeObject(size);
    objectOutputStream.writeObject(capacity);
    objectOutputStream.writeObject(loadFactor);
    for (E element :  map.keySet()
         ) {
      objectOutputStream.writeObject(element);
    }
  }
  
  private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
    objectInputStream.defaultReadObject();
    int size = (int) objectInputStream.readObject();
    int capacity = (int) objectInputStream.readObject();
    float loadFactor = (float) objectInputStream.readObject();

    map = new HashMap<E, Object>(capacity, loadFactor);
  
    for (int i = 0; i < size; i++) {
      map.put((E)objectInputStream.readObject(), PRESENT);
    }
    
  }
  
  
}
