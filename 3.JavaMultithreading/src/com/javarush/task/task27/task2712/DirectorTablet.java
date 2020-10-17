package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet {

  public void printAdvertisementProfit() {
    long total = 0;
    Map<Date, Long> reverseTreeMap = new TreeMap<>(Comparator.reverseOrder());
    reverseTreeMap.putAll(StatisticManager.getInstance().advertisementProfit());
    for (Map.Entry<Date, Long> entry : reverseTreeMap.entrySet()) {
      ConsoleHelper.writeMessage(String.format("%s - %.2f"
              , new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(entry.getKey())
              , entry.getValue() / 100.0));
      total += entry.getValue();
    }
    ConsoleHelper.writeMessage(String.format("Total - %.2f", total / 100.0));
  }

  public void printCookWorkloading() {
    Map<Date, Map<String, Integer>> reverseTreeMap = new TreeMap<>(Comparator.reverseOrder());
    reverseTreeMap.putAll(StatisticManager.getInstance().cookWorkloading());
    for (Map.Entry<Date, Map<String, Integer>> entry : reverseTreeMap.entrySet()) {
      ConsoleHelper.writeMessage(new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(entry.getKey()));
      Map<String, Integer> sortedMap = new TreeMap<>(entry.getValue());
      for (Map.Entry<String, Integer> entry2 : sortedMap.entrySet()) {
        if (Math.ceil(entry2.getValue() / 60.0) > 0) {
          ConsoleHelper.writeMessage(String.format("%s - %.0f min", entry2.getKey(), Math.ceil(entry2.getValue() / 60.0)));
        }
      }
      ConsoleHelper.writeMessage("");
    }
  }

  public void printActiveVideoSet() {

  }

  public void printArchivedVideoSet() {

  }

}
