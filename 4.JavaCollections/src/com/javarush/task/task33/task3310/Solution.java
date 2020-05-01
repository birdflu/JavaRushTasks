package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
  public static void main(String[] args) {
    
    testStrategy(new HashMapStorageStrategy(), 10000);
    testStrategy(new OurHashMapStorageStrategy(), 10000);
    testStrategy(new FileStorageStrategy(), 10);
    testStrategy(new OurHashBiMapStorageStrategy(), 10000);
    testStrategy(new HashBiMapStorageStrategy(), 10000);
    testStrategy(new DualHashBidiMapStorageStrategy(), 10000);
    
    /*FileBucket fb = new FileBucket();
    Entry e3 = new Entry(3, 3L, "3", null);
    Entry e2 = new Entry(2, 2L, "2", e3);
    Entry e1 = new Entry(1, 1L, "1", e2);

    fb.putEntry(e1);

    Entry e = fb.getEntry();
    System.out.println(e);
    */
  }
  
  
  public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
    Set<Long> set = new HashSet<>();
    for (String string : strings)
      set.add(shortener.getId(string));
    return set;
  }
  
  public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
    Set<String> set = new HashSet<>();
    for (Long key : keys)
      set.add(shortener.getString(key));
    return set;
  }
  
  public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
    Helper.printMessage(strategy.getClass().getSimpleName());
    Set<String> set = new HashSet<>();
    for (int i = 0; i < elementsNumber; i++) {
      set.add(Helper.generateRandomString());
    }
    Shortener shortener = new Shortener(strategy);
    Date startIds = new Date();
    Set<Long> ids = getIds(shortener, set);
    Date endIds = new Date();
    long deltaTime = endIds.getTime() - startIds.getTime();
    Helper.printMessage(Long.toString(deltaTime));
//    Helper.printMessage("getIds takes " + String.valueOf(endIds.getTime() - startIds.getTime()) + " ms");

    Date startStrings = new Date();
    Set<String> strings = getStrings(shortener, ids);
    Date endStrings = new Date();
    deltaTime = endStrings.getTime() - startStrings.getTime();
    Helper.printMessage(Long.toString(deltaTime));
//    Helper.printMessage("getStrings takes " + String.valueOf(endStrings.getTime() - startStrings.getTime()) + " ms");
    
    if (set.containsAll(strings) && strings.containsAll(set))
      Helper.printMessage( "Тест пройден.");
    else
    {
      Helper.printMessage( "Тест не пройден.");
    }
  
//    System.out.println(strings.size());
//    System.out.println(Arrays.toString(strings.toArray()));
//    System.out.println(set.size());
  }
}
