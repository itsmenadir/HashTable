import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V Value) {
            this.key = key;
            this.value = value;

        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;

    public MyHashTable() {
        this.chainArray = (HashNode<K, V>[]) new HashNode[M];
        this.size = 0;
    }

    public MyHashTable(int M) {
        this.M = M;
        this.chainArray = (HashNode<K, V>[]) new HashNode[M];
        this.size = 0;

    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % M);
    }


    public void put(K key, V value) {
        int hash = hash(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);
        if (chainArray[hash] == null) {
            chainArray[hash] = newNode;
        } else {
            HashNode<K, V> current = chainArray[hash];
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    public int size() {
        return size;
    }

    public V get(K key) {
        int hash = hash(key);
        HashNode<K, V> current = chainArray[hash];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;

    }

    public V remove(K key) {
        int hash = hash(key);
        HashNode<K, V> current = chainArray[hash];
        HashNode<K, V> prev = null;
        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    chainArray[hash] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    public boolean contains(V value) {
        for (HashNode<K, V> node : chainArray) {
            HashNode<K, V> current = node;
            while (current != null) {
                if (current.value.equals(value)) {
                    return true;
                }
                current = current.next;
            }
        }
        return false;

    }

    public void addRandomElements(int numElements) {
        Random random = new Random();
        for (int i = 0; i < numElements; i++) {
            int randomId = random.nextInt(10000); // Generate random ID
            K key = (K) new MyTestingClass(randomId);
            V value = (V) (Integer) randomId;
            put(key, value);
        }
    }
    public void printBucketSizes() {
        List<Integer> bucketSizes = new ArrayList<>();
        for (HashNode<K, V> node : chainArray) {
            int count = 0;
            HashNode<K, V> current = node;
            while (current != null) {
                count++;
                current = current.next;
            }
            bucketSizes.add(count);
        }
        System.out.println("Number of elements in each bucket:");
        for (int i = 0; i < bucketSizes.size(); i++) {
            System.out.println("Bucket " + i + ": " + bucketSizes.get(i));
        }
    }

    public K getKey(V value){
        for (HashNode<K, V> node: chainArray){
            HashNode<K, V> current = node;
            while (current != null){
                if (current.value.equals(value)){
                    return current.key;
                }
                current = current.next;
            }
        }
        return null;
    }

}
