public class MyHashTable<K, V> {
    private static class HashNode<K, V> {
        K key;
        V value;
        HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final HashNode[] array;
    private int M = 11;
    private int size = 0;

    public MyHashTable() {
        array = new HashNode[M];
    }

    public MyHashTable(int M) {
        this.M = M;
        array = new HashNode[M];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % M;
    }

    public void put(K key, V value) {
        int i = hash(key);
        for (HashNode<K, V> x = array[i]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        }
        HashNode<K, V> newNode = new HashNode<K, V>(key, value);
        newNode.next = array[i];
        array[i] = newNode;
        size++;
    }

    public V get(K key) {
        int i = hash(key);
        for (HashNode<K, V> x = array[i]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.value;
            }
        }
        return null;
    }

    public V remove(K key) {
        int i = hash(key);
        HashNode<K, V> prevNode = null;
        for (HashNode<K, V> x = array[i]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                if (prevNode != null) {
                    prevNode.next = x.next;
                } else {
                    array[i] = x.next;
                }
                size--;
                return x.value;
            }
            prevNode = x;
        }
        return null;
    }

    public boolean contains(V value) {
        for (int i = 0; i < M; i++) {
            for (HashNode<K, V> x = array[i]; x != null; x = x.next) {
                if (value.equals(x.value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (int i = 0; i < M; i++) {
            for (HashNode<K, V> x = array[i]; x != null; x = x.next) {
                if (value.equals(x.value)) {
                    return x.key;
                }
            }
        }
        return null;
    }
}
