package org.example;

public class Main {

    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        System.out.println("=== Basic operations ===");

        map.put("Java", 100);
        map.put("Python", 200);
        map.put("C++", 300);

        System.out.println("Java: " + map.get("Java"));
        System.out.println("Python: " + map.get("Python"));
        System.out.println("Size: " + map.size());

        System.out.println("\n=== Update existing key ===");

        map.put("Java", 150);

        System.out.println("Updated Java: " + map.get("Java"));
        System.out.println("Size after update: " + map.size());

        System.out.println("\n=== Remove key ===");

        System.out.println("Removed Python: " + map.remove("Python"));
        System.out.println("Python after removal: " + map.get("Python"));
        System.out.println("Final size: " + map.size());

        System.out.println("\n=== Collision test ===");

        MyHashMap<Integer, String> collisionMap = new MyHashMap<>();

        collisionMap.put(1, "First");
        collisionMap.put(17, "Second");

        System.out.println("Value for key 1: " + collisionMap.get(1));
        System.out.println("Value for key 17: " + collisionMap.get(17));
        System.out.println("Collision map size: " + collisionMap.size());

        System.out.println("\n=== Remove from collision chain ===");

        System.out.println("Removed key 1: " + collisionMap.remove(1));
        System.out.println("Value for key 17: " + collisionMap.get(17));
        System.out.println("Collision map size after removal: "
                + collisionMap.size());
    }
}