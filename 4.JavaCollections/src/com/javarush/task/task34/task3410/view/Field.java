package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.Box;
import com.javarush.task.task34.task3410.model.Home;
import com.javarush.task.task34.task3410.model.Player;
import com.javarush.task.task34.task3410.model.Wall;

import javax.swing.*;
import java.awt.*;

public class Field extends JPanel {
  private View view;

  public Field(View view) {
    this.view = view;
  }
  private EventListener eventListener;

  public void paint(Graphics g) {
    new Player(35, 35).draw(g);
    new Box(45, 60).draw(g);
    new Home(50, 70).draw(g);
    new Wall(100, 20).draw(g);
  }

  public void setEventListener(EventListener eventListener) {
    this.eventListener = eventListener;
  }

}
