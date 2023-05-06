import java.util.LinkedList;

public class MyHashTable<K, V> {
    private class HashNode<K,V>{
        private K key;
        private V value;
        public HashNode(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }
    private LinkedList<HashNode<K,V>>[] chainArray;
    private int M = 11;
    private int size;
    public MyHashTable(){
        chainArray = new LinkedList[M];
        for (int i = 0; i < M; i++) {
            chainArray[i] = new LinkedList<HashNode<K,V>>();
        }
    }
    public MyHashTable(int M){
        this.M = M;
        chainArray = new LinkedList[M];
        for (int i = 0; i < M; i++) {
            chainArray[i] = new LinkedList<HashNode<K,V>>();
        }
    }
    private int hash(K key){
        return Math.abs(key.hashCode()) % M;
    }
    public void put(K key, V value){
        int hash = hash(key);
        for (HashNode<K, V> node : chainArray[hash]) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        chainArray[hash].add(new HashNode<K,V>(key, value));
        size++;
    }
    public V get(K key){
        int hash = hash(key);
        for (HashNode<K, V> node : chainArray[hash]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }
    public V remove(K key){
        int hash = hash(key);
        for (HashNode<K, V> node : chainArray[hash]) {
            if (node.key.equals(key)) {
                chainArray[hash].remove(node);
                size--;
                return node.value;
            }
        }
        return null;
    }
    public boolean contains(V value){
        for (int i = 0; i < M; i++) {
            for (HashNode<K, V> node : chainArray[i]) {
                if (node.value.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }
    public K getKey(V value){
        for (int i = 0; i < M; i++) {
            for (HashNode<K, V> node : chainArray[i]) {
                if (node.value.equals(value)) {
                    return node.key;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();
        for (int i = 0; i < 10000; i++) {
            MyTestingClass key = new MyTestingClass(i);
            Student value = new Student("Student " + i);
            table.put(key, value);
        }
        for (int i = 0; i < table.chainArray.length; i++) {
            System.out.println("Bucket " + i + ": " + table.chainArray[i].size());
        }
    }
}

class MyTestingClass {
    private int value;

    public MyTestingClass(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return value % 11;

