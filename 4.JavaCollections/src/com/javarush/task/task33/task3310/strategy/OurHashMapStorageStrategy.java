package com.javarush.task.task33.task3310.strategy;

public class OurHashMapStorageStrategy implements StorageStrategy{
  static final int DEFAULT_INITIAL_CAPACITY = 16;
  static final float DEFAULT_LOAD_FACTOR = 0.75f;
  Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
  int size = 0;
  int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
  float loadFactor = DEFAULT_LOAD_FACTOR;
  
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
  
  public int indexFor(int hash, int length) {
    return (hash & (length-1)) - 1;
  }
  
  public Entry getEntry(Long key) {
    if (size == 0) {
      return null;
    }
  
    int hash = (key == null) ? 0 : hash(key);
    for (Entry e = table[indexFor(hash, table.length)];
         e != null;
         e = e.next) {
      Object k;
      if (e.hash == hash &&
              ((k = e.key) == key || (key != null && key.equals(k))))
        return e;
    }
    return null;
  }
  
  public void resize(int newCapacity) {
    int MAXIMUM_CAPACITY = 1 << 30;
    Entry[] oldTable = table;
    int oldCapacity = oldTable.length;
    if (oldCapacity == MAXIMUM_CAPACITY) {
      threshold = Integer.MAX_VALUE;
      return;
    }
  
    Entry[] newTable = new Entry[newCapacity];
    transfer(newTable);
    table = newTable;
    threshold = (int)Math.min(newCapacity * loadFactor, MAXIMUM_CAPACITY + 1);
  }
  
  public void transfer(Entry[] newTable) {
    int newCapacity = newTable.length;
    for (Entry e : table) {
      while(null != e) {
        Entry next = e.next;
        int i = indexFor(e.hash, newCapacity);
        e.next = newTable[i];
        newTable[i] = e;
        e = next;
      }
    }
  }
  
  public void addEntry(int hash, Long key, String value, int bucketIndex) {
    // System.out.println("add");
    if ((size >= threshold) && (null != table[bucketIndex])) {
      resize(2 * table.length);
      hash = (null != key) ? hash(key) : 0;
      bucketIndex = indexFor(hash, table.length);
    }
    createEntry(hash, key, value, bucketIndex);
  }
  
  public void createEntry(int hash, Long key, String value, int bucketIndex) {
    Entry e = table[bucketIndex];
    table[bucketIndex] = new Entry(hash, key, value, e);
    size++;
    // System.out.println("create index = " + bucketIndex + " element = " + table[bucketIndex] + "next = " + table[bucketIndex].next +  " size = " + size);
  }
  
  @Override
  public boolean containsKey(Long key) {
    return getEntry(key) != null;
  }
  
  @Override
  public boolean containsValue(String value) {
    if (value == null) {
      Entry[] tab = table;
      for (int i = 0; i < tab.length ; i++)
        for (Entry e = tab[i] ; e != null ; e = e.next)
          if (e.value == null)
            return true;
      return false;
    }
  
    Entry[] tab = table;
    for (int i = 0; i < tab.length ; i++)
      for (Entry e = tab[i] ; e != null ; e = e.next)
        if (value.equals(e.value))
          return true;
    return false;
  }
  
  @Override
  public void put(Long key, String value) {
    final Entry[] EMPTY_TABLE = {};
    int MAXIMUM_CAPACITY = 1 << 30;
    if (table == EMPTY_TABLE) {
      int capacity = roundUpToPowerOf2(threshold);
  
      threshold = (int) Math.min(capacity * loadFactor, MAXIMUM_CAPACITY + 1);
      table = new Entry[capacity];
    }
    if (key == null)
       putForNullKey(value);
    int hash = hash(key);
    int i = indexFor(hash, table.length);
 //   System.out.println("IIIIIIII = " + i);
    for (Entry e = table[i]; e != null; e = e.next) {
      Object k;
      if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
        String oldValue = e.value;
        e.value = value;
        //e.recordAccess(this);
        //return oldValue;
      }
    }
  
    //modCount++;
    addEntry(hash, key, value, i);
   /* System.out.println(("PUT " + i));
    for (int j = 0; j < table.length; j++) {
      System.out.println(table[j]);
    }*/
  }
  
  private String putForNullKey(String value) {
    for (Entry e = table[0]; e != null; e = e.next) {
      if (e.key == null) {
        String oldValue = e.value;
        e.value = value;
       // e.recordAccess(this);
        return oldValue;
      }
    }
    //modCount++;
    addEntry(0, null, value, 0);
    //System.out.println("PUT NULL");
    return null;
  }
  
  private static int roundUpToPowerOf2(int number) {
    // assert number >= 0 : "number must be non-negative";
    int MAXIMUM_CAPACITY = 1 << 30;
    return number >= MAXIMUM_CAPACITY
            ? MAXIMUM_CAPACITY
            : (number > 1) ? Integer.highestOneBit((number - 1) << 1) : 1;
  }
  
  @Override
  public Long getKey(String value) {
/*    //System.out.println("get key");
    for (Entry e = table[0]; e != null; e = e.next) {
      if (e.value == value) {
        return e.key;
      }
    }*/
    for (int i = 0; i < table.length; i++) {
      if (table[i].value == value) return table[i].key;
    }
    return null;
  }
  
  @Override
  public String getValue(Long key) {
/*    System.out.println("get value for key = " + key);
    for (int i = 0; i < table.length; i++) {
      System.out.println(" {" +table[i] + "} ");
    }
    for (Entry e = table[0]; e != null; e = e.next) {
      System.out.println("e = " + e + "next = " + e.next);
      if (e.key == key) {
        System.out.println("found value " + e.value + "for key = " + key);
        return e.value;
      }
    }*/
    if (size == 0) {
      return null;
    }
  
    int hash = (key == null) ? 0 : hash(key);
    for (Entry e = table[indexFor(hash, table.length)];
         e != null;
         e = e.next) {
      Object k;
      if (e.hash == hash &&
              ((k = e.key) == key || (key != null && key.equals(k))))
        return e.value;
    }
  
    return null;
  }
}
