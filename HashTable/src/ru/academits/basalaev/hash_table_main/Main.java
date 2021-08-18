package ru.academits.basalaev.hash_table_main;

import ru.academits.basalaev.hash_table.MyHashTable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        MyHashTable<Integer> hashTable1 = new MyHashTable<>();
        MyHashTable<Integer> hashTable2 = new MyHashTable<>();
        MyHashTable<Integer> hashTable3 = new MyHashTable<>();



        System.out.println(hashTable3.size());
        Iterator<Integer> iterator3 = hashTable3.iterator();

        while (iterator3.hasNext()) {
            System.out.println(iterator3.next());
        }


        for (int i = 1; i < 11; i++) {
            hashTable1.add(i);
            hashTable1.add(i - 1);
            hashTable2.add(i);
        }

        System.out.println(hashTable1);

        System.out.println(hashTable1.hashCode());
        hashTable1.addAll(hashTable3);
        System.out.println(hashTable1);



        System.out.println(hashTable1);
        System.out.println(hashTable2);

        System.out.println(Objects.equals(hashTable1, hashTable2));
        System.out.println(hashTable2.contains(25));


        hashTable1.remove(10);
        System.out.println(hashTable1);
        hashTable1.add(10);
        System.out.println(hashTable1);

        Integer[] array = new Integer[25];
        System.out.println(Arrays.toString(hashTable1.toArray(array)));

        Iterator<Integer> iterator2 = hashTable2.iterator();

        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }
    }
}
