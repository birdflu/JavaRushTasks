package com.javarush.task.task33.task3310.strategy;

public class FileStorageStrategy implements StorageStrategy {
  
  static final int DEFAULT_INITIAL_CAPACITY = 100;
  static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000;
  FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
  int size = 0;  // not null buckets
  private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
  long maxBucketSize = DEFAULT_BUCKET_SIZE_LIMIT;
  //int resize = 0;
  
  public int hash(Long k) {
//    int h;
//    return (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);

    int h = 0;

    h ^= k.hashCode();

    // This function ensures that hashCodes that differ only by
    // constant multiples at each bit position have a bounded
    // number of collisions (approximately 8 at default load factor).
    h ^= (h >>> 20) ^ (h >>> 12);
    return h ^ (h >>> 7) ^ (h >>> 4);
  }
  private int indexFor(int hash, int length) {
    return (hash & (length - 1));
  }
  
  @Override
  public boolean containsKey(Long key) {
    for (int i = 0; i < table.length; i++) {
      FileBucket fileBucket = table[i];
      if (fileBucket != null && fileBucket.getEntry().getKey().equals(key)) return true;
    }
    return false;
  }
  
  @Override
  public boolean containsValue(String value) {
    for (int i = 0; i < table.length; i++)
    {
      FileBucket fileBucket = table[i];
      if (fileBucket != null && fileBucket.getEntry().getValue().equals(value)) return true;
    }
    return false;
  }
  
  @Override
  public void put(Long key, String value) {
   // System.out.println(" try PUT " + key + " = " + value);
    int i = put(table, key, value);

//    for (int j = 0; j < table.length; j++) {
//      if (table[j] != null)
//      {
//        System.out.print(" i= " + j + " entry = " + table[j].getEntry() + " hash = " + table[j].getEntry().hash);
//        for (Entry e = table[j].getEntry(); e != null; e = e.next) {
//          System.out.print(" {" + e.next + "} ");
//        }
//        System.out.println();
//      }
//    }
    maxBucketSize = Math.max(table[i].getFileSize(), maxBucketSize);
  }
  
  private int put(FileBucket[] table, Long key, String value) {
    int i = indexFor(hash(key), table.length);
    // System.out.println("try put to index = " + i);
    FileBucket fb = table[i];
    
    if (fb != null) {
      Entry receivedEntryForChange = fb.getEntry();
      Entry entry = null;
      for (Entry e = receivedEntryForChange; e != null; e = e.next) {
        entry = e;
      }
      entry.next = new Entry(hash(key), key, value, null);
      
      fb.putEntry(receivedEntryForChange);
    
      if (fb.getFileSize() > maxBucketSize )
      {
        resize(2 * size);
      }
//
//      if (table.length < size + 2)
//        resize(2 * size);
    
    } else {
      table[i] = new FileBucket();
      table[i].putEntry(new Entry(hash(key), key, value, null));
      size++;
    }
    return i;
  }
  
  private void resize(int newCapacity) {
    //resize++;
    //if (resize % 10 == 0) System.out.println(resize);
    FileBucket[] newTable = new FileBucket[newCapacity];
    for (int i = 0; i < table.length; i++) {
      //newTable[i] = table[i];
      FileBucket fileBucket = table[i];
      if (fileBucket != null) {
        Entry entry = fileBucket.getEntry();
        for (Entry e = entry; e != null; e = e.next) {
          put(newTable, e.getKey(), e.getValue());
          }
        }
      }
    table = newTable;
    // System.out.println(Arrays.asList(table).toString());
  }
  
  @Override
  public Long getKey(String value) {
    for (int i = 0; i < table.length; i++)
    {
      FileBucket fileBucket = table[i];
      if (fileBucket != null) {
        Entry entry = fileBucket.getEntry();
        for (Entry e = entry; e != null; e = e.next) {
          if (e.getValue().equals(value)) {
     //       System.out.println("found KEY " + e.getKey());
            return e.getKey();
          }
        }
      }
    }
    return null;
  }
  
  @Override
  public String getValue(Long key) {
   // System.out.println(" KEY = "+ key);
    for (int i = 0; i < table.length; i++)
    {
      FileBucket fileBucket = table[i];
      if (fileBucket != null) {
        Entry entry = fileBucket.getEntry();
        for (Entry e = entry; e != null; e = e.next) {
          if (e.getKey().equals(key)) {
    //        System.out.println("found VALUE " + e.getValue());
            return e.getValue();
          }
        }
      }
    }
    return null;
  }
  
  public long getBucketSizeLimit() {
    return bucketSizeLimit;
  }
  
  public void setBucketSizeLimit(long bucketSizeLimit) {
    this.bucketSizeLimit = bucketSizeLimit;
  }
}
