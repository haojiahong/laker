package com.hao.laker.study.javabase.source;

/**
 * 自定义hashmap
 * Created by haojiahong on 17/2/28.
 */
public class MyHashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private MyEntry<K, V>[] table;
    private int capacity;
    private int size;

    private class MyEntry<K, V> {
        int hash;
        K key;
        V value;
        MyEntry<K, V> next;

        public MyEntry(int hash, K key, V value, MyEntry<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public MyHashMap(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        } else {
            this.capacity = capacity;
            size = 0;
            table = new MyEntry[capacity];
        }
    }

    public V put(K key, V value) {
        if (key == null) {
            return putForNullKey(value);
        }
        int hash = hash(key);
        int i = indexFor(hash, table.length);
        for (MyEntry<K, V> e = table[i]; e != null; e = e.next) {
            Object k;
            if (hash == e.hash && ((k = e.key) == key || key.equals(k))) {
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }
        addEntry(hash, key, value, i);
        return null;
    }

    public V get(K key) {
        if (key == null) {
            return getForNullKey();
        }
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        for (MyEntry<K, V> e = table[index]; e != null; e = e.next) {
            if (e.hash == hash && (e.key == key || key.equals(e.key))) {
                return e.value;
            }
        }
        return null;
    }

    private void addEntry(int hash, K key, V value, int bucketIndex) {
        MyEntry<K, V> e = table[bucketIndex];
        table[bucketIndex] = new MyEntry<K, V>(hash, key, value, e); //参数e, 是Entry.next
        //如果size超过threshold，则扩充table大小。再散列,这里没有写
        size++;
    }

    private int indexFor(int h, int length) {
        return h & (length - 1);
//        return 3;
    }

    private int hash(K key) {
        return key.hashCode();
    }

    private V putForNullKey(V value) {
        for (MyEntry<K, V> e = table[0]; e != null; e = e.next) {
            if (e.key == null) {
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }
        addEntry(0, null, value, 0);
        return null;
    }

    private V getForNullKey() {
        for (MyEntry<K, V> e = table[0]; e != null; e = e.next) {
            if (e.key == null)
                return e.value;
        }
        return null;
    }


    public static void main(String[] args) {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();
        myHashMap.put(1, "hao");
        myHashMap.put(2, "haojia");
        myHashMap.put(3, "haojiahong");

        System.out.println(myHashMap);


    }


}
