package org.example;

public class MyHashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private Entry<K, V>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        buckets = (Entry<K, V>[]) new Entry[DEFAULT_CAPACITY];
        size = 0;
    }

    private int getIndex(K key) {
        if (key == null) {
            return 0;
        }

        return (key.hashCode() & 0x7fffffff) % buckets.length;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        Entry<K, V> current = buckets[index];

        while (current != null) {
            if (keysEqual(current.key, key)) {
                current.value = value;
                return;
            }

            current = current.next;
        }

        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = buckets[index];
        buckets[index] = newEntry;
        size++;

        if ((float) size / buckets.length > LOAD_FACTOR) {
            resize();
        }
    }

    public V get(K key) {
        int index = getIndex(key);
        Entry<K, V> current = buckets[index];

        while (current != null) {
            if (keysEqual(current.key, key)) {
                return current.value;
            }

            current = current.next;
        }

        return null;
    }

    public V remove(K key) {
        int index = getIndex(key);
        Entry<K, V> current = buckets[index];
        Entry<K, V> previous = null;

        while (current != null) {
            if (keysEqual(current.key, key)) {
                if (previous == null) {
                    buckets[index] = current.next;
                } else {
                    previous.next = current.next;
                }

                size--;
                return current.value;
            }

            previous = current;
            current = current.next;
        }

        return null;
    }

    private boolean keysEqual(K first, K second) {
        return first == second
                || (first != null && first.equals(second));
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Entry<K, V>[] oldBuckets = buckets;
        buckets = (Entry<K, V>[]) new Entry[oldBuckets.length * 2];

        for (Entry<K, V> head : oldBuckets) {
            Entry<K, V> current = head;

            while (current != null) {
                Entry<K, V> next = current.next;

                int index = getIndex(current.key);
                current.next = buckets[index];
                buckets[index] = current;

                current = next;
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}