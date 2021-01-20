package ds.map;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

class MyHashMap {

    class MapItem {
        int key;
        int val;

        public MapItem(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    static int NUM_BUCKETS = 1000;
    List<List<MapItem>> buckets;

    /** Initialize your data structure here. */
    public MyHashMap() {
        buckets = new ArrayList<>(NUM_BUCKETS);
        for (int i = 0; i < NUM_BUCKETS; i++) {
            buckets.add(new LinkedList<>());
        }
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int index = hash(key);
        for (MapItem i : buckets.get(index)) {
            if (i.key == key) {
                i.val = value;
                return;
            }
        }

        buckets.get(index).add(new MapItem(key, value));
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map
     * contains no mapping for the key
     */
    public int get(int key) {
        List<MapItem> list = buckets.get(hash(key));
        for (MapItem item : list) {
            if (item.key == key) {
                return item.val;
            }
        }
        return -1;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping
     * for the key
     */
    public void remove(int key) {
        List<MapItem> list = buckets.get(hash(key));
        for (MapItem item : list) {
            if (item.key == key) {
                list.remove(item);
                return;
            }
        }
    }

    public int hash(int val) {
        return (val % NUM_BUCKETS);
    }

    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        map.put(1,3);
        map.put(2,3);
        map.put(1, 123);
        System.out.println(map.get(1));
    }
}
