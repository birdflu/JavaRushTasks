package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy {
  private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";

  @Override
  public List<Vacancy> getVacancies(String searchString) {
    Document document = null;
    List<Vacancy> list = new ArrayList<>();
    for (int i = 0;; i++)
    {
      try {
        document = getDocument(searchString, i);
      } catch (IOException e) {}

      Elements elements = document.getElementsByClass("job");
      if (!elements.isEmpty()) {
        Vacancy v;
        for (Element e :
                elements) {
          v = new Vacancy();
          v.setUrl("https://moikrug.ru" + e.getElementsByClass("title").first().getElementsByTag("a").attr("href"));
          v.setTitle(e.getElementsByClass("title").first().text());
          v.setCity(e.getElementsByClass("location").isEmpty() ? "" : e.getElementsByClass("location").first().text());
          v.setCompanyName(e.getElementsByClass("company_name").first().text());
          v.setSalary(e.getElementsByClass("salary").text());
          v.setSiteName("https://moikrug.ru");
          list.add(v);
        }
      } else
        break;
    }
    return list;
  }

  protected Document getDocument(String searchString, int page) throws IOException {
    Connection connect = Jsoup.connect(String.format(URL_FORMAT, searchString, page));

    connect.ignoreHttpErrors(true);
    Document doc = connect
            .userAgent("Mozilla/5.0 (jsoup)")
            .referrer("o-referrer-when-downgrade").get();
    return doc;
  }
}