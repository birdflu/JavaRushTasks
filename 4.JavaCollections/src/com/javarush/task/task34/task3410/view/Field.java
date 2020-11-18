package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.model.Box;
import com.javarush.task.task34.task3410.model.Home;
import com.javarush.task.task34.task3410.model.Player;

import javax.swing.*;
import java.awt.*;

public class Field extends JPanel {
  private View view;

  public Field(View view) {
    this.view = view;
  }

  public void paint(Graphics g) {
//        Для того чтобы проверить как рисуется твой ящик или игрок, ты можешь создать объект
//        типа Box или Player в методе paint() класса Field и вызвать у объекта метод draw().
//        Сделай это исключительно для проверки методов draw(), в дальнейшем метод paint() мы реализуем иначе.
//
    new Player(35, 35).draw(g);
    new Box(45, 60).draw(g);
    new Home(50, 70).draw(g);

  }
}
