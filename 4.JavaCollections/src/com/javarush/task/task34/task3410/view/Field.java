package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.GameObject;

import javax.swing.*;
import java.awt.*;

public class Field extends JPanel {
  private View view;

  public Field(View view) {
    this.view = view;
  }
  private EventListener eventListener;

  public void paint(Graphics g) {
    g.setColor(Color.BLACK);
    g.fillRect(getX(), getY(), getWidth(), getHeight());
    for (GameObject gameObject : view.getGameObjects().getAll()) {
      gameObject.draw(g);
    }

  }

  public void setEventListener(EventListener eventListener) {
    this.eventListener = eventListener;
  }

}
