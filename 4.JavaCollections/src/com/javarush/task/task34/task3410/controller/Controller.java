package com.javarush.task.task34.task3410.controller;

import com.javarush.task.task34.task3410.model.Direction;
import com.javarush.task.task34.task3410.model.GameObjects;
import com.javarush.task.task34.task3410.model.Model;
import com.javarush.task.task34.task3410.view.View;

public class Controller implements EventListener{
  Model model;
  View view;

  public Controller() {
    model = new Model();
    view = new View(this);
    view.init();
    model.restart();
  }

  public static void main(String[] args) {
    Controller controller = new Controller();

  }

  public GameObjects getGameObjects() {
    return model.getGameObjects();
  }

  @Override
  public void move(Direction direction) {

  }

  @Override
  public void restart() {

  }

  @Override
  public void startNextLevel() {

  }

  @Override
  public void levelCompleted(int level) {

  }
}