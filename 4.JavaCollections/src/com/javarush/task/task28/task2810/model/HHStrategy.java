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

/**
 * Класс будет реализовывать конкретную стратегию работы с сайтом http://hh.ua/ и http://hh.ru/.
 */
public class HHStrategy implements Strategy {
  private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";
  //private static final String URL_FORMAT = "http://javarush.ru/testdata/big28data.html";


/*  @Override
  public List<Vacancy> getVacancies(String searchString) {
    List<Vacancy> vacancies = new ArrayList<>();

    int page = 0;
    while (true) {
      try {
        Document doc = getDocument(searchString, page);
        List<Vacancy> pageVacancies = getVacanciesFromPage(doc);
        if (pageVacancies.size() == 0) {
          break;
        } else {
          vacancies.addAll(pageVacancies);
          page++;
        }
      } catch (IOException e) {
        //e.printStackTrace();
      }

    }
    return vacancies;
  }*/

  @Override
  public List<Vacancy> getVacancies(String searchString) {
    Document document;
    List<Vacancy> list = new ArrayList<>();
    for (int i = 0;; i++) {
      try {
        document = getDocument(searchString, i);
        Elements elements = document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
        if (!elements.isEmpty()) {
          Vacancy v;
          for (Element e :
                  elements) {
            v = new Vacancy();
            v.setUrl(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").attr("href"));
            v.setTitle(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").text());
            v.setCity(e.getElementsByClass("searchresult__address").text());
            v.setCompanyName(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text());
            v.setSalary(e.getElementsByClass("b-vacancy-list-salary").text());
            v.setSiteName("hh.ua");
            list.add(v);
          }
        } else
          break;

      } catch (IOException e) {}
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


/*  protected List<Vacancy> getVacanciesFromPage(Document doc) {
    List<Vacancy> vacancies = new ArrayList<>();

    Element e;
    e = doc.getElementsByTag("title").first();
    String siteName = e != null ? e.text() : "";

    Elements elements = getElements(doc, "div." + "HH-MainContent*");
    for (Element element : elements) {
      String title = getElementValue(element, "vacancy-serp__vacancy-title");
      String salary = getElementValue(element, "vacancy-serp__vacancy-compensation");
      String city = getElementValue(element, "vacancy-serp__vacancy-address");
      String companyName = getElementValue(element, "vacancy-serp__vacancy-employer");
      String vacancyUrl = getElementValue(element, "vacancy-serp__vacancy-title");

      Vacancy vacancy = new Vacancy();
      vacancy.setTitle(title);
      vacancy.setSalary(salary);
      vacancy.setCity(city);
      vacancy.setCompanyName(companyName);
      vacancy.setUrl(vacancyUrl);
      vacancies.add(vacancy);
      //System.out.println(vacancies.toString());
    }

    return vacancies;
  }*/

/*
  protected String getElementValue(Element element, String attributeName) {
    Element e = element.getElementsByAttributeValue("data-qa", attributeName).first();
    return e != null ? e.text() : "";
  }

  protected Elements getElements(Document doc, String query) {
    Elements u1 = doc.select(query);
    Elements result = new Elements();

    for (Element u2 : u1) {
      for (Element u3 : u2.select(".main-content-wrapper")) {
        for (Element u4 : u3.select(".main-content")) {
          for (Element u5 : u4.select(".main-content")) {
            for (Element u6 : u5.select(".bloko-columns-wrapper")) {
              for (Element u7 : u6.select(".bloko-column_l-16")) {
                for (Element u8 : u7.select(".sticky-container")) {
                  for (Element u9 : u8.select(".HH-StickyParentAreaResizer-Content")) {
                    for (Element u10 : u9.select(".vacancy-serp-wrapper")) {
                      for (Element u11 : u10.select(".bloko-column")) {
                        for (Element u12 : u11.select(".bloko-gap")) {
                          for (Element u13 : u12.select(".vacancy-serp")) {
                            for (Element u : u13.select(".vacancy-serp-item")) {
                              result.add(u);
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return result;
  }
*/


/*  private static void print(String msg, Object... args) {
    System.out.println(String.format(msg, args));
  }

  private static String trim(String s, int width) {
    if (s.length() > width)
      return s.substring(0, width - 1) + ".";
    else
      return s;
  }*/

}


