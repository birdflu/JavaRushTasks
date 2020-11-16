package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;
import java.util.stream.Collectors;

public class CurrencyManipulator {
  private String currencyCode; // код валюты, например, USD. Состоит из трех букв.
  private Map<Integer, Integer> denominations; // это Map<номинал, количество>.

  public CurrencyManipulator(String currencyCode) {
    this.currencyCode = currencyCode;
    denominations = new HashMap<>();
  }

  public void addAmount(int denomination, int count) {

    denominations.merge(denomination, count, Integer::sum);
  }

  public int getTotalAmount() {
    return denominations.entrySet().stream()
            .mapToInt(e -> e.getKey() * e.getValue()).sum();
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public boolean hasMoney() {
    return !denominations.isEmpty();
  }

  public boolean isAmountAvailable(int expectedAmount) {
    System.out.println("Total: " + getTotalAmount());
    return getTotalAmount() - expectedAmount >= 0;
  }


  @Override
  public String toString() {
    return "CurrencyManipulator{" +
            "currencyCode='" + currencyCode + '\'' +
            ", denominations=" + denominations.entrySet().stream()
            .map(e -> e.getKey().toString() + " " + e.getValue())
            .collect(Collectors.toList()) +
            '}';
  }

  /**
   * //    2. Логика основного метода withdrawAmount:
   * //    2.1. Внимание!!! Метод withdrawAmount должен возвращать минимальное количество банкнот, которыми набирается запрашиваемая сумма.
   * //            Используй Жадный алгоритм (use google).
   * //    Если есть несколько вариантов, то использовать тот, в котором максимальное количество банкнот высшего номинала.
   * //    Если для суммы 600 результат - три банкноты: 500 + 50 + 50 = 200 + 200 + 200, то выдать первый вариант.
   * //            Пример, надо выдать 600.
   * //    В манипуляторе есть следующие банкноты:
   * //    500 - 2
   * //    200 - 3
   * //    100 - 1
   * //    50 - 12
   * //    Результат должен быть таким:
   * //    500 - 1
   * //    100 - 1
   * //    т.е. всего две банкноты (это минимальное количество банкнот) номиналом 500 и 100.
   * //
   * //    2.2. Мы же не можем одни и те же банкноты выдавать несколько раз, поэтому
   * //    если мы нашли вариант выдачи денег (п.2.1. успешен), то вычесть все эти банкноты из карты в манипуляторе.
   * //
   * //    2.3. метод withdrawAmount должен кидать NotEnoughMoneyException, если купюрами невозможно выдать запрашиваемую сумму.
   * //            Пример, надо выдать 600.
   * //    В манипуляторе есть следующие банкноты:
   * //    500 - 2
   * //    200 - 2
   * //    Результат - данными банкнотами невозможно выдать запрашиваемую сумму. Кинуть исключение.
   * //    Не забудь, что в некоторых случаях картой кидается ConcurrentModificationException.
   * //
   * @param expectedAmount
   * @return
   * @throws NotEnoughMoneyException
   */

  public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
    List<Map.Entry<Integer, Integer>> res = angryWithdraw(expectedAmount);
    balance(res);
    return getMapFromEntries(res);
  }

  protected void balance(List<Map.Entry<Integer, Integer>> res) {
    for (Map.Entry<Integer, Integer> entry : res) {
      addAmount(entry.getKey(), (-1) * entry.getValue());
    }
  }

  protected Map<Integer, Integer> getMapFromEntries(List<Map.Entry<Integer, Integer>> res) {
    Map<Integer, Integer> resMap = new HashMap<>();
    for (Map.Entry<Integer, Integer> e : res) {
      resMap.merge(e.getKey(), e.getValue(), Integer::sum);
    }
    return resMap;
  }

  protected List<Map.Entry<Integer, Integer>> angryWithdraw(int expectedAmount) throws NotEnoughMoneyException {
    List<Integer> sortedNominalValues = getSortedNominals();

    int noun = expectedAmount;
    List<Map.Entry<Integer, Integer>> res = new ArrayList<>();

    for (Integer nominal : sortedNominalValues) {
//      System.out.println("try " + nominal + " - " + denominations.get(nominal).toString());
      noun = withdrawNominal(nominal, noun, res);

      if (noun == 0){
        return res;}
    }

    throw new NotEnoughMoneyException();

  }

  protected List<Integer> getSortedNominals() {
    return denominations.keySet().stream()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());
  }

  protected int withdrawNominal(int nominal, int noun, List<Map.Entry<Integer, Integer>> res) {
    int count = denominations.get(nominal);
    while (count > 0) {
      if (nominal <= noun && count > 0) {
        AbstractMap.SimpleEntry taked = new AbstractMap.SimpleEntry(nominal, 1);
        res.add(taked);
//        System.out.println(taked + " taked");
        noun = noun - nominal;
//        System.out.println("noun = " + noun);
        count--;
      } else {
        break;
      }
    }
    return noun;
  }

  protected ArrayList<Map.Entry<Integer, Integer>> getCopyDenominations() {
    return new ArrayList<>(
            denominations.entrySet().stream()
                    .sorted((e1, e2) -> Integer.compare(e2.getKey(), e1.getKey()))
                    .collect(Collectors.toList()));
  }

}
