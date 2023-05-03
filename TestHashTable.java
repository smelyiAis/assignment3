import java.util.Random;

public class TestHashTable {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();

        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            int id = random.nextInt(1000);
            String name = "Name" + id;
            MyTestingClass key = new MyTestingClass(id, name);
            Student value = new Student(name, id);
            table.put(key, value);
        }

        // print the number of elements in each bucket
        int[] bucketSizes = new int[table.getM()];
        for (int i = 0; i < table.getM(); i++) {
            int size = 0;
            for (MyHashTable.HashNode<MyTestingClass, Student> node : table.getChainArray()[i]) {
                size++;
            }
            bucketSizes[i] = size;
        }
        System.out.println("Bucket sizes:");
        System.out.println(Arrays.toString(bucketSizes));
    }
}
