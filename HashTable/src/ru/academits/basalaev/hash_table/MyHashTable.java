package ru.academits.basalaev.hash_table;

import java.util.*;

public class MyHashTable<E> implements Collection<E> {
    private LinkedList<E>[] lists;
    private int size;
    private int modCount;

    private static final int DEFAULT_ARRAY_LENGTH = 100;

    @SuppressWarnings("unchecked")
    public MyHashTable() {
        lists = (LinkedList<E>[]) new LinkedList[DEFAULT_ARRAY_LENGTH];
    }

    public MyHashTable(int arrayLength) {
        if (arrayLength < 1) {
            throw new IllegalArgumentException("Длина массива для хранения хэш-таблицы не может быть меньше 1, передано значение: " + arrayLength);
        }

        //noinspection unchecked
        lists = (LinkedList<E>[]) new LinkedList[arrayLength];
    }

    @SuppressWarnings("TypeParameterHidesVisibleType")
    private class MyIterator<E> implements Iterator<E> {
        private final int savedModCount;
        private int tableIndex;

        private int currentListIndex = -1;
        private ListIterator<E> currentListIterator;

        public MyIterator() {
            savedModCount = modCount;
            currentListIterator = getListIterator();
        }

        private ListIterator<E> getListIterator() {
            currentListIndex++;

            while (currentListIndex < lists.length) {
                if (lists[currentListIndex] != null && lists[currentListIndex].size() != 0) {
                    currentListIterator = (ListIterator<E>) lists[currentListIndex].iterator();

                    break;
                }

                currentListIndex++;
            }

            return currentListIterator;
        }

        public boolean hasNext() {
            return tableIndex < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            if (savedModCount != modCount) {
                throw new ConcurrentModificationException("Коллекция изменилась");
            }

            tableIndex++;

            if (!currentListIterator.hasNext()) {
                currentListIterator = getListIterator();
            }

            return currentListIterator.next();
        }
    }

    private int getTableIndex(Object o) {
        return Math.abs(Objects.hashCode(o) % lists.length);
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("{");

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] == null || lists[i].size() == 0) {
                continue;
            }

            sb.append("tableIndex = ").append(i).append(" : ").append(lists[i]);
            sb.append(System.lineSeparator());
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(lists, null);

        size = 0;
        modCount++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator<>();
    }

    @Override
    public boolean add(E e) {
        if (lists.length == 0) {
            //noinspection unchecked
            lists = (LinkedList<E>[]) new LinkedList[DEFAULT_ARRAY_LENGTH];
        }

        int tableIndex = getTableIndex(e);

        if (lists[tableIndex] == null) {
            lists[tableIndex] = new LinkedList<>();
        }

        lists[tableIndex].add(e);

        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException("Передана пустая ссылка");
        }

        if (c.size() == 0) {
            return false;
        }

        for (E element : c) {
            add(element);
        }

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int tableIndex = getTableIndex(o);

        if (lists[tableIndex] == null) {
            return false;
        }

        if (lists[tableIndex].remove(o)) {
            size--;
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Передана пустая ссылка");
        }

        if (c.size() == 0) {
            return false;
        }

        boolean isRemove = false;

        for (Object element : c) {
            while (remove(element)) {
                isRemove = true;
            }
        }

        return isRemove;
    }

    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Передана пустая ссылка");
        }

        boolean isRemove = false;

        for (LinkedList<E> list : lists) {
            if (list == null || list.size() == 0) {
                continue;
            }

            int initialListSize = list.size();

            if (list.retainAll(c)) {
                isRemove = true;

                size -= initialListSize - list.size();
                modCount++;
            }
        }

        return isRemove;
    }

    @Override
    public boolean contains(Object o) {
        int tableIndex = getTableIndex(o);

        return lists[tableIndex] != null && lists[tableIndex].contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Передана пустая ссылка");
        }

        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];

        int index = 0;

        for (E element : this) {
            array[index] = element;

            index++;
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //noinspection unchecked
            a = (T[]) Arrays.copyOf(a, size, a.getClass());
        }

        int index = 0;

        for (E element : this) {
            //noinspection unchecked
            a[index] = (T) element;

            index++;
        }

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }
}