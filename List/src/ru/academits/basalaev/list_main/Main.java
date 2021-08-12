package ru.academits.basalaev.list_main;

import ru.academits.basalaev.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});

        System.out.println("Созданный список:");
        System.out.println(list);
        System.out.println("Длина списка = " + list.getLength());

        System.out.println("Значение первого элемента = " + list.getFirst());
        System.out.println("Значение элемента с переданным индексом = " + list.get(5));

        System.out.println("Старое значение элемента:" + list.set(5, 74));
        System.out.println("Список после установки значения по индексу:");
        System.out.println(list);

        System.out.println("Значение удаленного элемента = " + list.remove(0));
        System.out.println("Список после удаления элемента по индексу:");
        System.out.println(list);
        System.out.println("Длина списка = " + list.getLength());

        list.addFirst(26);
        System.out.println("Список после добавления элемента в начало:");
        System.out.println(list);
        System.out.println("Длина списка = " + list.getLength());

        list.add(8, null);
        System.out.println("Список после добавления элемента по индексу:");
        System.out.println(list);
        System.out.println("Длина списка = " + list.getLength());

        Integer value = 8;
        System.out.println("Результат удаления элемента по значению: " + list.remove(value));
        System.out.println("Список после удаления элемента:");
        System.out.println(list);

        System.out.println("Значение удаленного элемента = " + list.removeFirst());
        System.out.println("Список после удаления первого элемента:");
        System.out.println(list);
        System.out.println("Длина списка = " + list.getLength());

        list.reverse();
        System.out.println("Список после разворота:");
        System.out.println(list);

        System.out.println("Копия списка:");
        System.out.println(list.getCopy());
    }
}