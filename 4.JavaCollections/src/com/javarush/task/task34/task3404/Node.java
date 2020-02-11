package com.javarush.task.task34.task3404;

import java.util.ArrayList;

public class Node {
    public Pair data;
    public ArrayList<Node> children;
    public Node parent;

  public Node(int x,  int y) {
    this.data = new Pair(x,y);
    children = new ArrayList<>();
    parent = null;
  }

  public boolean canBeParentOf (Node node) {
    return this.data.x < node.data.x && this.data.y > node.data.y;
  }

  public String toString(){
      return String.format("(%d,%d) -> (%d,%d) -> (%s)"
                      ,
              parent == null?null:parent.data.x,
              parent == null?null:parent.data.y,
              data.x,
              data.y,
              children.toString()
              );
    }

}
