package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage {
  private static AdvertisementStorage instance;
  private final List videos = new ArrayList();
  
  private AdvertisementStorage() {
    Object someContent = new Object();
    add(new Advertisement(someContent, "First Video", 5000, 100, 3 * 60)); // 3 min
    add(new Advertisement(someContent, "четвёртое видео", 6, 0, 2 * 60));  // |   2   |
    add(new Advertisement(someContent, "Second Video", 100, 0, 1 * 60)); //1 min
    add(new Advertisement(someContent, "Third Video", 400, 2, 1 * 60)); //1 min
    add(new Advertisement(someContent, "пятое видео", 3, 3, 3 * 60));  // |     1     |
//    this.add(new Advertisement(someContent, "First Video", 3, 3, 1 * 60));  // | 1 | 1 | 1 | 1 |
//    this.add(new Advertisement(someContent, "Second Video", 6, 3, 1 * 60)); // | 2 |
//    this.add(new Advertisement(someContent, "Third Video", 3, 3, 2 * 60));  // |   1   |
//    this.add(new Advertisement(someContent, "Forth Video", 6, 3, 2 * 60));  // |   2   |

//    this.add(new Advertisement(someContent, "Sixth Video", 6, 3, 3 * 60));  // |     2     |
//    this.add(new Advertisement(someContent, "Seventh Video", 9, 3, 3 * 60));// |     3     |
//    this.add(new Advertisement(someContent, "Eighth Video", 12, 3, 3 * 60));// |     4     |

//  check "Вторичная сортировка - по увеличению стоимости показа одной секунды рекламного ролика"
//  need set order.timeSeconds to longer
//  this.add(new Advertisement(someContent, "Ninth Video", 12, 3, 2 * 60));
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

  public static boolean isActive(Object advertisement) {
    return ((Advertisement) advertisement).getHits() > 0;
  }

  public static boolean isArchive(Object advertisement) {
    return ((Advertisement) advertisement).getHits() == 0;
  }
}
