package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {

    @Test
    void shouldPutAndGetValue() {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("Java", 100);

        assertEquals(100, map.get("Java"));
        assertEquals(1, map.size());
    }

    @Test
    void shouldUpdateExistingKey() {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("Java", 100);
        map.put("Java", 200);

        assertEquals(200, map.get("Java"));
        assertEquals(1, map.size());
    }

    @Test
    void shouldRemoveKey() {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("Java", 100);

        assertEquals(100, map.remove("Java"));
        assertNull(map.get("Java"));
        assertEquals(0, map.size());
    }

    @Test
    void shouldHandleCollisions() {
        MyHashMap<Integer, String> map = new MyHashMap<>();

        map.put(1, "First");
        map.put(17, "Second");

        assertEquals("First", map.get(1));
        assertEquals("Second", map.get(17));
        assertEquals(2, map.size());
    }

    @Test
    void shouldPreserveCollisionChainAfterRemoval() {
        MyHashMap<Integer, String> map = new MyHashMap<>();

        map.put(1, "First");
        map.put(17, "Second");

        map.remove(1);

        assertNull(map.get(1));
        assertEquals("Second", map.get(17));
        assertEquals(1, map.size());
    }

    @Test
    void shouldReturnNullForMissingKey() {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        assertNull(map.get("Unknown"));
        assertNull(map.remove("Unknown"));
        assertEquals(0, map.size());
    }

    @Test
    void shouldReportEmptyMapCorrectly() {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        assertTrue(map.isEmpty());

        map.put("Java", 100);

        assertFalse(map.isEmpty());
    }

    @Test
    void shouldSupportNullKey() {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put(null, 100);
        map.put("Java", 200);

        assertEquals(100, map.get(null));
        assertEquals(200, map.get("Java"));
        assertEquals(2, map.size());

        assertEquals(100, map.remove(null));
        assertNull(map.get(null));
        assertEquals(1, map.size());
    }

    @Test
    void shouldResizeWhenLoadFactorIsExceeded() {
        MyHashMap<Integer, Integer> map = new MyHashMap<>();

        for (int i = 0; i < 13; i++) {
            map.put(i, i * 10);
        }

        assertEquals(13, map.size());

        for (int i = 0; i < 13; i++) {
            assertEquals(i * 10, map.get(i));
        }
    }
}