package ru.academits.basalaev.hash_table_main;

import ru.academits.basalaev.hash_table.MyHashTable;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyHashTable<Integer> hashTable1 = new MyHashTable<>();
        MyHashTable<Integer> hashTable2 = new MyHashTable<>();
        MyHashTable<Integer> hashTable3 = new MyHashTable<>();

        for (int i = 1; i < 11; i++) {
            hashTable1.add(i);
            hashTable2.add(i + 10);
        }

        System.out.println("Созданные хэш-таблицы:");
        System.out.println(hashTable1);
        System.out.println(hashTable2);
        System.out.println("Длина хэш-таблиц: lengthHashTable1 = " + hashTable1.size() + ", lengthHashTable1 = " + hashTable2.size());

        hashTable1.add(111);
        hashTable1.add(11);
        System.out.println("Хэш-таблица после добавления элементов:");
        System.out.println(hashTable1);
        System.out.println("Длина хэш-таблицы = " + hashTable1.size());

        hashTable1.remove(5);
        System.out.println("Хэш-таблица после удаления элемента по значению:");
        System.out.println(hashTable1);
        System.out.println("Длина хэш-таблицы = " + hashTable1.size());

        hashTable1.addAll(hashTable2);
        System.out.println("Хэш-таблица после  после добавления коллекции:");
        System.out.println(hashTable1);
        System.out.println("Длина хэш-таблицы = " + hashTable1.size());

        if (hashTable1.containsAll(hashTable2)) {
            System.out.println("Хэш-таблица содержит все элементы переданной коллекции");
        } else {
            System.out.println("Хэш-таблица не содержит все элементы переданной коллекции");
        }

        hashTable1.remove(20);
        System.out.println("Хэш-таблица после удаления элемента по значению:");
        System.out.println(hashTable1);

        System.out.println("Хэш-таблица после удаления совпадающих элементов из другой коллекции:");
        hashTable1.removeAll(hashTable2);
        System.out.println(hashTable1);

        if (hashTable1.contains(111)) {
            System.out.println("Хэш-таблица содержит элемент");
        } else {
            System.out.println("Хэш-таблица не содержит элемент");
        }

        hashTable1.add(12);
        hashTable1.add(112);
        hashTable1.add(12);
        hashTable1.add(13);
        hashTable1.add(14);
        System.out.println(hashTable1);

        System.out.println("Хэш-таблица после после вызова метода retainAll:");
        hashTable1.retainAll(hashTable2);
        System.out.println(hashTable1);

        Integer[] array = new Integer[25];
        Arrays.fill(array, 25);

        System.out.println("Хэш-таблица преобразованная в массив");
        System.out.println(Arrays.toString(hashTable1.toArray(array)));

        System.out.println("Хэш-таблица преобразованная в массив");
        System.out.println(Arrays.toString(hashTable2.toArray()));
    }
}