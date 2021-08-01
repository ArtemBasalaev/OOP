package ru.academits.basalaev.list_main;

import ru.academits.basalaev.list.ListItem;
import ru.academits.basalaev.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});

        System.out.println("Созданный список:");
        System.out.println(list);
        System.out.println("Длина списка = " + list.size());

        System.out.println("Значение первого элемента = " + list.getItemValue());
        System.out.println("Значение элемента с переданным индексом = " + list.getItemValue(5));

        System.out.println("Старое значение элемента:" + list.setItemValue(5, 74));
        System.out.println("Список после установки значения по индексу:");
        System.out.println(list);

        System.out.println("Значение удаленного элемента = " + list.remove(0));
        System.out.println("Список после удаления элемента по индексу:");
        System.out.println(list);
        System.out.println("Длина списка = " + list.size());

        list.add(new ListItem<>(26));
        System.out.println("Список после добавления элемента в начало:");
        System.out.println(list);
        System.out.println("Длина списка = " + list.size());

        list.add(0, new ListItem<>(260));
        System.out.println("Список после добавления элемента по индексу:");
        System.out.println(list);
        System.out.println("Длина списка = " + list.size());

        System.out.println("Значение удаленного элемента = " + list.remove());
        System.out.println("Список после удаления первого элемента:");
        System.out.println(list);
        System.out.println("Длина списка = " + list.size());

        Integer value = 260;
        System.out.println("Результат удаления элемента по значению: " + list.remove(value));
        System.out.println("Список после удаления элемента:");
        System.out.println(list);

        list.reverse();
        System.out.println("Список после разворота:");
        System.out.println(list);

        System.out.println("Копия списка:");
        System.out.println(list.getCopy());
    }
}