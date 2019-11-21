package part1.lesson03.task03;

import java.util.Arrays;
import java.util.Map;

/**
 * @param <K>
 * @param <V>
 */
class MyHashMap<K, V> {
       private Entry[] buckets;
       private float loadFactor;
       private int size = 0;

       private   static final int DEFAULT_INITIAL_CAPACITY = 16;
       private  static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * @param <K>
     * @param <V>
     */
       static class Entry<K, V> implements Map.Entry<K, V> {
           final int hash;
           final K key;
           V value;
           Entry<K, V> next;

           public Entry(K key, V value, Entry<K, V> next) {
               this.hash = key.hashCode();
               this.key = key;
               this.value = value;
               this.next = next;

           }



           @Override
           public K getKey() {
               return key;
           }

           @Override
           public V getValue() {
               return value;
           }

           @Override
           public V setValue(V value) {
               if (value == null)
                   throw new NullPointerException();
               V oldValue = this.value;
               this.value = value;
               return oldValue;
           }

           public void setNext(Entry<K, V> next) {
               this.next = next;
           }
       }


       public MyHashMap() {
           buckets = new Entry[DEFAULT_INITIAL_CAPACITY];
           loadFactor = DEFAULT_LOAD_FACTOR;
       }

       public MyHashMap(float loadFactor) {
           buckets = new Entry[DEFAULT_INITIAL_CAPACITY];
           this.loadFactor = loadFactor;
       }


       public MyHashMap(int capacity, float loadFactor) {
           buckets = new Entry[capacity];
           this.loadFactor = loadFactor;
       }


    /**
     * if contains same key , change value
     * @param key
     * @param value
     * @return
     */
       public V put(K key, V value) {
           if (key == null) {
               throw new NullPointerException();
           }

           if (size + 1 > loadFactor * buckets.length) {//
               growBuckets();
           }
           Entry<K, V> entry = new Entry<>(key, value, null);
           size++;
           int hash = hash(entry.hash);
           if (buckets[hash] == null) {
               buckets[hash] = entry;
               return entry.getValue();
           } else {
               Entry<K, V> current = buckets[hash];
               while (current != null) {
                   if (current.getKey().equals(entry.getKey())) {
                       current.setValue(entry.getValue());
                       return entry.getValue();
                   }
                   current = current.next;
               }
               current.setNext(entry);
               return entry.getValue();
           }

       }


    /**
     * map not contains elements
     * buckets contains same key
     * @param key
     * @return null
     */
       public V get(K key) {
           int hash = hash(key.hashCode());
           if (buckets[hash] == null) {
               return null;
           }
           Entry<K, V> currentWithSameKeyHashCode = buckets[hash];

           while (currentWithSameKeyHashCode != null) {
               if (currentWithSameKeyHashCode.getKey().equals(key)) {
                   return currentWithSameKeyHashCode.getValue();
               }
               currentWithSameKeyHashCode = currentWithSameKeyHashCode.next;
           }
           return null;

       }


    /**
     * map not contains with such key
     * delete first  entry node
     * @param key
     * @return
     */
       public V remove(K key) {
           int hash = hash(key.hashCode());
           if (buckets[hash] == null) {
               return null;
           }


           size--;

           Entry<K, V> previous = null;
           Entry<K, V> currentWithSameKeyHashCode = buckets[hash];

           while (currentWithSameKeyHashCode != null) {
               if (currentWithSameKeyHashCode.key.equals(key)) {
                   if (previous == null) {
                       buckets[hash] = buckets[hash].next;

                   } else {
                       previous.next = currentWithSameKeyHashCode.next;
                       return currentWithSameKeyHashCode.getValue();
                   }
               }
               previous = currentWithSameKeyHashCode;
               currentWithSameKeyHashCode = currentWithSameKeyHashCode.next;
           }
           return null;
       }


       private int hash(int hashCode) {
           return Math.abs(hashCode) % buckets.length;
       }


    /**
     * увеличиваем масси в 2 раза  и прохлдим элементы и помещаем в новый массив
     */
       private void growBuckets() {
           size = 0;// сделаем размер 0
           Entry<K, V>[] temp = buckets;
           buckets = new Entry[buckets.length * 2];
           for (Entry<K, V> t : temp) {
               if (t == null) {
                   continue;
               }
               Entry<K, V> current = t;
               put(current.getKey(), current.getValue());
               while (current.next != null) {
                   current = current.next;
                   put(current.getKey(), current.getValue());
               }
           }
       }


    @Override
    public String toString() {
        return "MyHashMap{" +
                "buckets=" + Arrays.toString(buckets) +
                ", loadFactor=" + loadFactor +
                ", size=" + size +
                '}';
    }

    public int getSize() {
           return size;



       }

}
