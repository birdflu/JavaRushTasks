package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {
  @Test
  public void testHashMapStorage() {
    Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
    Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
  
    int elementsNumber = 10000;
    Set<String> origStrings = new HashSet<>();
    for (int i = 0; i < elementsNumber; i++) {
      origStrings.add(Helper.generateRandomString());
    }
    Set<Long> ids1 = new HashSet<>();
    long timeToGetIds1 = getTimeToGetIds(shortener1, origStrings, ids1);
  
    Set<Long> ids2 = new HashSet<>();
    long timeToGetIds2 = getTimeToGetIds(shortener2, origStrings, ids2);
  
    assert(timeToGetIds1-timeToGetIds2 > 0);
  
    Set<String> str1 = new HashSet<>();
    long timeToGetStrings1 = getTimeToGetStrings(shortener1, ids1, str1);
  
    Set<String> str2 = new HashSet<>();
    long timeToGetStrings2 = getTimeToGetStrings(shortener2, ids2, str2);
  
    Assert.assertEquals(timeToGetStrings1, timeToGetStrings2, 30);
  
  }
  
  public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
    Date startIds = new Date();
    for (String string : strings)
      ids.add(shortener.getId(string));
    Date endIds = new Date();
    long deltaTime = endIds.getTime() - startIds.getTime();
    return deltaTime;
  }
  
  public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
    Date startIds = new Date();
    for (Long key : ids)
      strings.add(shortener.getString(key));
    Date endIds = new Date();
    long deltaTime = endIds.getTime() - startIds.getTime();
    return deltaTime;
  }
  
  
}
