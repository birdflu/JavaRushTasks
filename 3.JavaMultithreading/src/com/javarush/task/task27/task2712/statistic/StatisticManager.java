package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Dish;
import com.javarush.task.task27.task2712.statistic.event.*;

import java.util.*;

public class StatisticManager {
  private static StatisticManager instance;
  private StatisticStorage statisticStorage = new StatisticStorage();
  private Set<Cook> cooks = new HashSet();

  private StatisticManager() {

  }

  private static class StatisticStorage {
    private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

    {
      //    dishes = List.of(Dish.Soup);                          // order 1, duration 2: {v1, v2}
      //    dishes = List.of(Dish.Soup, Dish.Steak);              // order 2, duration 3: {v8}
      //    dishes = List.of(Dish.Water, Dish.Juice, Dish.Fish);  // order 3, duration 8: {v1, v2, v7, v8}
      for (EventType eventType: EventType.values()) {storage.put(eventType,new ArrayList<EventDataRow>());}

      //initStorage();
    }

    private void initStorage() {
      List<Dish> dishes1 = new ArrayList() {{
        add(Dish.Soup);
      }};
      List<Dish> dishes2 = new ArrayList() {{
        add(Dish.Soup);
        add(Dish.Steak);
      }};
      List<Dish> dishes3 = new ArrayList() {{
        add(Dish.Water);
        add(Dish.Juice);
        add(Dish.Fish);
      }};

      storage.put(EventType.COOKED_ORDER, new ArrayList() {{
        add(new CookedOrderEventDataRow("tablet1", "cook1", getTotalCookingTime(dishes1), dishes1));
        add(new CookedOrderEventDataRow("tablet2", "cook2", getTotalCookingTime(dishes2), dishes2));
      }});

      storage.put(EventType.COOKED_ORDER, new ArrayList() {{
        add(new CookedOrderEventDataRow("tablet3", "cook3", getTotalCookingTime(dishes3), dishes3));
      }});

      storage.put(EventType.COOKED_ORDER, new ArrayList() {{
        add(new CookedOrderEventDataRow("tablet1", "cook2", getTotalCookingTime(dishes1), dishes1));
        add(new CookedOrderEventDataRow("tablet3", "cook2", getTotalCookingTime(dishes2), dishes2));
      }});

      storage.put(EventType.SELECTED_VIDEOS, new ArrayList() {{
        add(new VideoSelectedEventDataRow(getVideos().subList(0, 1), 9, 2 * 60));
      }});

      storage.put(EventType.SELECTED_VIDEOS, new ArrayList() {{
        add(new VideoSelectedEventDataRow(getVideos().subList(7, 7), 12, 3 * 60));
      }});

      storage.put(EventType.SELECTED_VIDEOS, new ArrayList() {{
        List list = getVideos().subList(0, 1);
        list.addAll(getVideos().subList(6, 7));
        add(new VideoSelectedEventDataRow(list, 30, 8 * 60));
      }});

      storage.put(EventType.NO_AVAILABLE_VIDEO, new ArrayList() {{
        add(new NoAvailableVideoEventDataRow(1 * 60));
        add(new NoAvailableVideoEventDataRow(3 * 60));
      }});
    }

    private List getVideos() {
      List videos = new ArrayList();
      Object someContent = new Object();
      videos.add(new Advertisement(someContent, "First Video", 3, 3, 1 * 60));  // | 1 | 1 | 1 | 1 |
      videos.add(new Advertisement(someContent, "Second Video", 6, 3, 1 * 60)); // | 2 |
      videos.add(new Advertisement(someContent, "Third Video", 3, 3, 2 * 60));  // |   1   |
      videos.add(new Advertisement(someContent, "Forth Video", 6, 3, 2 * 60));  // |   2   |
      videos.add(new Advertisement(someContent, "Fifth Video", 3, 3, 3 * 60));  // |     1     |
      videos.add(new Advertisement(someContent, "Sixth Video", 6, 3, 3 * 60));  // |     2     |
      videos.add(new Advertisement(someContent, "Seventh Video", 9, 3, 3 * 60));// |     3     |
      videos.add(new Advertisement(someContent, "Eighth Video", 12, 3, 3 * 60));// |     4     |
      return videos;
    }

    private void put(EventDataRow data) {
      storage.get(data.getType()).add(data);
    }

  }

  public static StatisticManager getInstance() {
    if (instance == null)
      instance = new StatisticManager();
    return instance;
  }

  public void register(EventDataRow data) {
    getInstance().statisticStorage.put(data);
  }

  public static int getTotalCookingTime(List<Dish> dishes) {
    return dishes.stream().map(Dish::getDuration).reduce(Integer::sum).orElse(0);
  }

  public void register(Cook cook) {
    cooks.add(cook);
  }

}
