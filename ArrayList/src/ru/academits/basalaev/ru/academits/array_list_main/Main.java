package ru.academits.basalaev.ru.academits.array_list_main;

import ru.academits.basalaev.ru.academits.array_list.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.capacity());


        list.add(11);
        System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.capacity());

        list.add(11, 22);
        System.out.println(list);
        System.out.println(list.size());


        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(13, 14, 15, 16, 17, 18, 19, 20, 21, 22));
        ArrayList<Double> list3 = new ArrayList<>(Arrays.asList(13.0, 14.0, 15.0, 16.0));


        list.addAll(2, list2);
        System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.capacity());

        System.out.println(list.contains(Double.valueOf(22)));
        System.out.println(list.lastIndexOf((22)));
        System.out.println(list.indexOf(22));

        list.set(0, 22);
        System.out.println(list);
        System.out.println("removeAll");
        list.retainAll(list2);
        System.out.println(list);

        /*list.clear();
        System.out.println(list);
        System.out.println(list.size());*/
    }
}
