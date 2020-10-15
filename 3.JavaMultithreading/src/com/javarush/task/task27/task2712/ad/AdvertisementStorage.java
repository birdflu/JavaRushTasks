package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage {
  private static AdvertisementStorage instance;
  private final List videos = new ArrayList();
  
  private AdvertisementStorage() {
    Object someContent = new Object();
    this.add(new Advertisement(someContent, "v1", 3, 3, 1 * 60)); // | 1 | 1 | 1 | 1 |
    this.add(new Advertisement(someContent, "v2", 6, 3, 1 * 60)); // | 2 |
    this.add(new Advertisement(someContent, "v3", 3, 3, 2 * 60));// |   1   |
    this.add(new Advertisement(someContent, "v4", 6, 3, 2 * 60)); // |   2   |
    this.add(new Advertisement(someContent, "v5", 3, 3, 3 * 60)); // |     1     |
    this.add(new Advertisement(someContent, "v6", 6, 3, 3 * 60)); // |     2     |
    this.add(new Advertisement(someContent, "v7", 9, 3, 3 * 60)); // |     3     |
    this.add(new Advertisement(someContent, "v8", 12, 3, 3 * 60)); //|     4     |
  }
  
  public static AdvertisementStorage getInstance() {
    if (instance == null)
      instance = new AdvertisementStorage();
    return instance;
  }
  
  public List list() {
    return videos;
  }
  
  public void add(Advertisement advertisement) {
    this.videos.add(advertisement);
  }
}
