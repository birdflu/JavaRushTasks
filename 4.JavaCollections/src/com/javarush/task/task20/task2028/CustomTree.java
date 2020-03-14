package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/*
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable  {
  Entry root;

  static class Entry<T> implements Serializable{
    String elementName;
    boolean availableToAddLeftChildren, availableToAddRightChildren;
    Entry<T> parent, leftChild, rightChild;

    public Entry(String elementName) {
      this.elementName = elementName;
      availableToAddLeftChildren = true;
      availableToAddRightChildren = true;
    }

  public boolean isAvailableToAddChildren() {
    return availableToAddLeftChildren || availableToAddRightChildren;
  }

}
  public CustomTree() {
    this.root = new Entry("0");
  }

  @Override
  public int size() {
  // возвращает текущее количество элементов в дереве.
    int size = -1; // without root
    Queue<Entry> queue = new LinkedList<> ();
    queue.add(root);
    while (!queue.isEmpty()){
      Entry node = queue.poll();
      size++;
      if (node.leftChild != null) queue.add(node.leftChild);
      if (node.rightChild != null) queue.add(node.rightChild);
      }
    return size;
  }

  @Override
  public boolean remove(Object o) {
    if (! String.class.getSimpleName().equals(o.getClass().getSimpleName())) throw new UnsupportedOperationException();
    Queue<Entry> queue = new LinkedList<> ();
    queue.add(root);
    while (!queue.isEmpty()){
      Entry node = queue.poll();
      if (node.elementName.equals(o)) {
          if (node.parent.leftChild == node) {
            node.parent.availableToAddLeftChildren = true;
            node.parent.leftChild = null;
          }
          if (node.parent.rightChild == node) {
            node.parent.availableToAddRightChildren = true;
            node.parent.rightChild = null;
          }
          node = null;
        return true;
      }
      if (node.leftChild != null) queue.add(node.leftChild);
      if (node.rightChild != null) queue.add(node.rightChild);
    }
    return false;
  }

  @Override
  public boolean add(String s) {
  // добавляет элементы дерева, в качестве параметра принимает имя элемента (elementName), искать место для вставки начинаем слева направо.
    Entry newNode = new Entry(s);
    Entry node = findEmpty(root);
    if (node == null) return false;
    if (node.availableToAddLeftChildren) {
      node.leftChild = newNode;
      node.availableToAddLeftChildren = false;
    } else if (node.availableToAddRightChildren) {
      node.rightChild = newNode;
      node.availableToAddRightChildren = false;
    }
    newNode.parent = node;
    return true;
  }

  protected Entry findEmpty(Entry root) {
    Queue<Entry> queue = new LinkedList<> ();
    queue.add(root);
    while (!queue.isEmpty()){
      Entry node = queue.poll();
      if (node.isAvailableToAddChildren()) {return node;}
      else {
        queue.add(node.leftChild);
        queue.add(node.rightChild);
      }
    }
    return null;
  }

  public String getParent(String s) {
   // возвращает имя родителя элемента дерева, имя которого было полученного в качестве параметра.
    Queue<Entry> queue = new LinkedList<> ();
    queue.add(root);
    while (!queue.isEmpty()){
      Entry node = queue.poll();
      if (node.elementName.equals(s)) return node.parent.elementName;
      if (node.leftChild != null) queue.add(node.leftChild);
      if (node.rightChild != null) queue.add(node.rightChild);
    }
    return null;
  }

  public void add(int index, String element) {throw new UnsupportedOperationException();}
  public String remove(int index) {throw new UnsupportedOperationException();}
  public String get(int index) {throw new UnsupportedOperationException();}
  public String set(int index, String element) {throw new UnsupportedOperationException();}
  public List<String> subList(int fromIndex, int toIndex) {throw new UnsupportedOperationException();}
  protected void removeRange(int fromIndex, int toIndex) {throw new UnsupportedOperationException();}
  public boolean addAll(int index, Collection<? extends String> c) {throw new UnsupportedOperationException();}


}
