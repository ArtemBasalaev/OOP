package ru.academits.basalaev.hash_table;

import java.util.*;

public class MyHashTable<E> implements Collection<E> {
    private static final int DEFAULT_ARRAY_LENGTH = 100;
    private final LinkedList<E>[] LISTS;

    private int size;
    private int modCount;

    @SuppressWarnings("unchecked")
    public MyHashTable() {
        LISTS = (LinkedList<E>[]) new LinkedList[DEFAULT_ARRAY_LENGTH];
    }

    public MyHashTable(int arrayLength) {
        if (arrayLength < 1) {
            throw new IllegalArgumentException("Длина массива для хранения хэш-таблицы не может быть меньше 1, передано значение: " + arrayLength);
        }

        //noinspection unchecked
        LISTS = (LinkedList<E>[]) new LinkedList[arrayLength];
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

            while (currentListIndex < LISTS.length) {
                if (LISTS[currentListIndex] != null && LISTS[currentListIndex].size() != 0) {
                    currentListIterator = (ListIterator<E>) LISTS[currentListIndex].iterator();

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
        return Math.abs(Objects.hashCode(o) % LISTS.length);
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("{");

        for (int i = 0; i < LISTS.length; i++) {
            if (LISTS[i] == null || LISTS[i].size() == 0) {
                continue;
            }

            sb.append("tableIndex = ").append(i).append(" : ").append(LISTS[i]);
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
        if (size == 0) {
            return;
        }

        Arrays.fill(LISTS, null);

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
        int tableIndex = getTableIndex(e);

        if (LISTS[tableIndex] == null) {
            LISTS[tableIndex] = new LinkedList<>();
        }

        LISTS[tableIndex].add(e);

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

        if (LISTS[tableIndex] == null) {
            return false;
        }

        if (LISTS[tableIndex].remove(o)) {
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

        if (c.size() == 0 || size == 0) {
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

        for (LinkedList<E> list : LISTS) {
            if (list == null || list.size() == 0) {
                continue;
            }

            int initialListSize = list.size();

            if (list.retainAll(c)) {
                isRemove = true;

                size -= initialListSize - list.size();
            }
        }

        if (isRemove) {
            modCount++;
        }

        return isRemove;
    }

    @Override
    public boolean contains(Object o) {
        int tableIndex = getTableIndex(o);

        return LISTS[tableIndex] != null && LISTS[tableIndex].contains(o);
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

        int i = 0;

        for (E element : this) {
            array[i] = element;

            i++;
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //noinspection unchecked
            a = (T[]) Arrays.copyOf(a, size, a.getClass());
        }

        int i = 0;

        for (E element : this) {
            //noinspection unchecked
            a[i] = (T) element;

            i++;
        }

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }
}