package com.javarush.task.task27.task2712.ad;

import java.util.List;
import java.util.stream.Collectors;

public class StatisticAdvertisementManager {
  private static StatisticAdvertisementManager instance;
  private final AdvertisementStorage storage = AdvertisementStorage.getInstance();

  private StatisticAdvertisementManager() {

  }

  public static StatisticAdvertisementManager getInstance() {
    if (instance == null)
      instance = new StatisticAdvertisementManager();
    return instance;
  }

  public List<Advertisement> getActiveVideoSet() {
    return (List<Advertisement>) storage.list().stream().filter(AdvertisementStorage::isActive).collect(Collectors.toList());
  }

  public List<Advertisement> getArchivedVideoSet() {
    return (List<Advertisement>) storage.list().stream().filter(AdvertisementStorage::isArchive).collect(Collectors.toList());
  }
}
