package com.javarush.task.task37.task3707;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class AmigoSet<E> extends AbstractSet implements Serializable, Cloneable, Set {
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
  

}
