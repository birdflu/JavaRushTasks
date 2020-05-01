package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest {
  @Test
  public void testHashMapStorageStrategy() {
    HashMapStorageStrategy hashMapStorageStrategy = new HashMapStorageStrategy();
    Shortener shortener = new Shortener(hashMapStorageStrategy);
    testStorage(shortener);
  }
  
  @Test
  public void testOurHashMapStorageStrategy() {
    OurHashMapStorageStrategy ourHashMapStorageStrategy = new OurHashMapStorageStrategy();
    Shortener shortener = new Shortener(ourHashMapStorageStrategy);
    testStorage(shortener);
  }
  
  @Test
  public void testFileStorageStrategy() {
    FileStorageStrategy fileStorageStrategy = new FileStorageStrategy();
    Shortener shortener = new Shortener(fileStorageStrategy);
    testStorage(shortener);
  }
  
  @Test
  public void testHashBiMapStorageStrategy() {
    HashBiMapStorageStrategy hashBiMapStorageStrategy = new HashBiMapStorageStrategy();
    Shortener shortener = new Shortener(hashBiMapStorageStrategy);
    testStorage(shortener);
  }
  
  @Test
  public void testDualHashBidiMapStorageStrategy() {
    DualHashBidiMapStorageStrategy dualHashBidiMapStorageStrategy = new DualHashBidiMapStorageStrategy();
    Shortener shortener = new Shortener(dualHashBidiMapStorageStrategy);
    testStorage(shortener);
  }
  
  @Test
  public void testOurHashBiMapStorageStrategy() {
    OurHashBiMapStorageStrategy ourHashBiMapStorageStrategy = new OurHashBiMapStorageStrategy();
    Shortener shortener = new Shortener(ourHashBiMapStorageStrategy);
    testStorage(shortener);
  }

  
  public void testStorage(Shortener shortener) {
    String s1 = Helper.generateRandomString();
    String s2 = Helper.generateRandomString();
    String s3 = s1;
    //Set<Long> ids = Solution.getIds(shortener,Set.of(s1, s2, s3));
    Long id1 = shortener.getId(s1);
    Long id2 = shortener.getId(s2);
    Long id3 = shortener.getId(s3);
    
    Assert.assertNotEquals(id1, id2);
    Assert.assertNotEquals(id3, id2);
    Assert.assertEquals(id1, id3);
    
    String newS1 = shortener.getString(id1);
    String newS2 = shortener.getString(id2);
    String newS3 = shortener.getString(id3);
    
    Assert.assertEquals(newS1, s1);
    Assert.assertEquals(newS2, s2);
    Assert.assertEquals(newS3, s3);
  }

}
