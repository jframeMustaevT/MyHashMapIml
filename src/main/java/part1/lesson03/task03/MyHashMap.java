package part1.lesson03.task03;

import java.util.Arrays;
import java.util.Map;

class MyHashMap<K, V> {
       private Entry[] buckets;
       private float loadFactor;
       private int size = 0;

       static final int DEFAULT_INITIAL_CAPACITY = 16;
       static final float DEFAULT_LOAD_FACTOR = 0.75f;

       class Entry<K, V> implements Map.Entry<K, V> {
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
    /**
     * default constructor
     */

       public MyHashMap() {
           buckets = new Entry[DEFAULT_INITIAL_CAPACITY];
           loadFactor = DEFAULT_LOAD_FACTOR;
       }

    /**
     * constructor with 1 parameter
     */
       public MyHashMap(float loadFactor) {
           buckets = new Entry[DEFAULT_INITIAL_CAPACITY];
           this.loadFactor = loadFactor;
       }

    /**
     * constructor with 2 values
     */
       public MyHashMap(int capacity, float loadFactor) {
           buckets = new Entry[capacity];
           this.loadFactor = loadFactor;
       }

    /**
     *  method put
     */

       public V put(K key, V value) {
           if (key == null) {
               throw new NullPointerException();
           }

           if (size + 1 > loadFactor * buckets.length) {//
               growBuckets();
           }
           Entry<K, V> entry = new Entry<>(key, value, null);
           size++;//increment size
           int hash = hash(entry.hash);
           if (buckets[hash] == null) { // not element
               buckets[hash] = entry;
               return entry.getValue();
           } else {//bucket cell has element
               Entry<K, V> current = buckets[hash];
               while (current != null) { // last element
                   if (current.getKey().equals(entry.getKey())) { //if contains same key
                       current.setValue(entry.getValue()); //change value
                       return entry.getValue();
                   }
                   current = current.next;
               }
               current.setNext(entry);
               return entry.getValue();
           }

       }

    /**
     *  method finds V value
     */
       public V get(K key) {
           int hash = hash(key.hashCode());
           if (buckets[hash] == null) { //map not contains elements
               return null;
           }
           Entry<K, V> currentWithSameKeyHashCode = buckets[hash];

           while (currentWithSameKeyHashCode != null) {
               if (currentWithSameKeyHashCode.getKey().equals(key)) { // buckets contains same key
                   return currentWithSameKeyHashCode.getValue();
               }
               currentWithSameKeyHashCode = currentWithSameKeyHashCode.next;
           }
           return null;

       }

    /**
     *  method remove
     */
       public V remove(K key) {
           int hash = hash(key.hashCode());
           if (buckets[hash] == null) { //map not contains with such key
               return null;
           }


           size--;

           Entry<K, V> previous = null;
           Entry<K, V> currentWithSameKeyHashCode = buckets[hash];

           while (currentWithSameKeyHashCode != null) {
               if (currentWithSameKeyHashCode.key.equals(key)) {
                   if (previous == null) {// delete first  entry node
                       buckets[hash] = buckets[hash].next;
//                       return (V) buckets[hash].getValue();
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


    /**
     *  method count
     */
       private int hash(int hashCode) {
           return Math.abs(hashCode) % buckets.length;
       }


    /**
     *  method doubles buckets
     */
       private void growBuckets() {
           size = 0;// сделаем размер 0
           Entry<K, V>[] temp = buckets;
           buckets = new Entry[buckets.length * 2]; // увеличение массива в 2 раза
           for (Entry<K, V> t : temp) { //проходим эедементы и помещаем в новый массив
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
