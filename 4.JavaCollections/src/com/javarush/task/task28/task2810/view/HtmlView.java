package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.List;

public class HtmlView implements View {
  private Controller controller;
  private final String filePath = "./4.JavaCollections/src/" +
          this.getClass().getPackage().getName().replace('.', '/') +
          "/vacancies.html";

  @Override
  public void update(List<Vacancy> vacancies) {
    try {
      String updatedFileContent = getUpdatedFileContent(vacancies);
      updateFile(updatedFileContent);
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }

  @Override
  public void setController(Controller controller) {
    this.controller = controller;
  }

  public void userCitySelectEmulationMethod() {
    controller.onCitySelect("Odessa");
  }

  private String getUpdatedFileContent(List<Vacancy> vacancies) {
    return null;
  }

  private void updateFile(String filePath) {

  }
}

/*
  Пора заняться отображением вакансий.
        1. В методе update класса HtmlView реализуй следующую логику:
        1.1. сформируй новое тело файла vacancies.html, которое будет содержать вакансии,
        1.2. запиши в файл vacancies.html его обновленное тело,
        1.3. Все исключения должны обрабатываться в этом методе - выведи стек-трейс, если возникнет исключение.

        2. Для реализации п.1 создай два пустых private метода:
        String getUpdatedFileContent(List<Vacancy>), void updateFile(String),
        Реализовывать их логику будем в следующих заданиях.

        3. Чтобы добраться до файла vacancies.html, сформируй относительный путь к нему.
        В классе HtmlView создай приватное строковое final поле filePath, присвой ему относительный путь к vacancies.html.
        Путь должен быть относительно корня проекта.
        Формируй путь динамически используя this.getClass().getPackage() и разделитель "/".

        Подсказка: путь должен начинаться с "./".

        */
