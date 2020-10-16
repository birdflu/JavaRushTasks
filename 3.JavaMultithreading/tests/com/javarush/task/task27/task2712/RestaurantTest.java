package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.AdvertisementComparator;
import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;


public class RestaurantTest {

  @Test
  public void test1() {
    String[] result = {"Eighth Video is displaying... 4, 22"
            , "Seventh Video is displaying... 3, 16"
            , "Second Video is displaying... 2, 33"
            , "First Video is displaying... 1, 16"};
    assertArrayEquals(processVideos(getVideos(), new AdvertisementManager(8 * 60)), result);
  }

  @Test
  public void test2() {
    String[] result = {"Eighth Video is displaying... 4, 22"};
    assertArrayEquals(processVideos(getVideos(), new AdvertisementManager(3 * 60)), result);
  }

  @Test
  public void test3() {
    String[] result = {"Second Video is displaying... 2, 33"
            , "First Video is displaying... 1, 16"};
    assertArrayEquals(processVideos(getVideos(), new AdvertisementManager(2 * 60)), result);
  }

  @Test
  public void test4() {
    List videos = getVideos();
    //  check "Вторичная сортировка - по увеличению стоимости показа одной секунды рекламного ролика"
    videos.add(new Advertisement(new Object(), "Ninth Video", 12, 3, 2 * 60));
    String[] result = {"Eighth Video is displaying... 4, 22"
            , "Ninth Video is displaying... 4, 33"
            , "Seventh Video is displaying... 3, 16"
            , "Second Video is displaying... 2, 33"
            , "Forth Video is displaying... 2, 16"
            , "Sixth Video is displaying... 2, 11"
            , "First Video is displaying... 1, 16"
            , "Third Video is displaying... 1, 8"
            , "Fifth Video is displaying... 1, 5"};
    assertArrayEquals(processVideos(videos, new AdvertisementManager(100 * 60)), result);

  }

  @Test
  public void test5() {
    String[] result = {"Eighth Video is displaying... 4, 22"
            , "Seventh Video is displaying... 3, 16"
            , "Second Video is displaying... 2, 33"
            , "First Video is displaying... 1, 16"};
    AdvertisementManager advertisementManager = new AdvertisementManager(8 * 60);
    List<Advertisement> videos = getVideos();
    for (int i = 0; i < 10; i++) {
      String[] strings = processVideos(videos, advertisementManager);
      List<Advertisement> newVideos = new ArrayList();
      for (Advertisement video : videos) {
        int newHits = video.getHits() == 0 ? 0 : video.getHits() - 1; // <= amountPerOneDisplaying = hits > 0 ? initialAmount / hits : 0;
        newVideos.add(new Advertisement(
                new Object(),
                video.getName(),
                video.getAmountPerOneDisplaying() * newHits,
                newHits,
                video.getDuration()));
      }
      videos = newVideos;

      if (i < 3)  assertArrayEquals(strings, result);
      else assertArrayEquals(strings, new String[]{});

    }


  }

  private String[] processVideos(List videos, AdvertisementManager manager) {

    String[] result = {};

    try {
      Method m = AdvertisementManager.class.getDeclaredMethod("getList", new Class[]{List.class});
      m.setAccessible(true);
      List<Advertisement> list = (List) m.invoke(manager, videos);
      Collections.sort(list, new AdvertisementComparator());
      result = new String[list.size()];
      for (int i = 0; i < list.size(); i++) {
        Advertisement a = list.get(i);
        result[i] = (String.format("%s is displaying... %d, %d"
                , a.getName()
                , a.getAmountPerOneDisplaying()
                , a.getAmountPerOneDisplaying() * 1000 / a.getDuration()));
      }
      return result;
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
    return result;
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
}