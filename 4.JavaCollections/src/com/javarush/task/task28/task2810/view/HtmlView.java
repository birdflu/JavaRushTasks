package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    } catch (Exception e) {
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
    String content = "";
    try {
      Document document = getDocument();

      Element element = document.getElementsByClass("template").first();
      Element liteElement = element.clone();
      liteElement.removeAttr("style");
      liteElement.removeClass("template");

/*
      for (Element element : elements) {
        System.out.println("element.toString() = " + element.toString());
      }

      for (Element element : liteElement) {
        System.out.println("element.toString() = " + element.toString());
      }
*/

        for (Element e:
                document.getElementsByAttributeValue("class", "vacancy")) {
          e.remove();
        }

        for (Vacancy v : vacancies) {
          Element outerHtml = liteElement.clone();

          outerHtml.getElementsByClass("city").first().text(v.getCity());
          outerHtml.getElementsByClass("companyName").first().text(v.getCompanyName());
          outerHtml.getElementsByClass("salary").first().text(v.getSalary());

          Element eTmp = outerHtml.getElementsByTag("a").first();
          eTmp.text(v.getTitle());
          eTmp.attr("href", v.getUrl());

          element.before(outerHtml);
          content = document.toString();
        }
      } catch (IOException e) {
        e.printStackTrace();
        content = "Some exception occurred";
      }
      return content;

  }

  private void updateFile(String s) {
    try {
      FileWriter fileWriter = new FileWriter(new File(filePath));
      fileWriter.write(s);
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  protected Document getDocument() throws IOException {
    return Jsoup.parse(new File(filePath), "UTF-8");
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
