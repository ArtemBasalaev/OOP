package ru.academits.basalaev.hash_table;

import java.util.*;

public class MyHashTable<E> implements Collection<E> {
    private LinkedList<E>[] hashTable;
    private int tableCapacity = 100;
    private int size;
    private int modCount;


    @SuppressWarnings("unchecked")
    public MyHashTable() {
        hashTable = (LinkedList<E>[]) new LinkedList[tableCapacity];
    }

    @SuppressWarnings({"unchecked", "unused"})
    public MyHashTable(int tableCapacity) {
        if (tableCapacity < 0) {
            throw new IllegalArgumentException("Вместимость таблицы не может быть отрицательной, передано значение " + tableCapacity);
        }

        hashTable = (LinkedList<E>[]) new LinkedList[tableCapacity];
        this.tableCapacity = tableCapacity;
    }

    @SuppressWarnings("TypeParameterHidesVisibleType")
    private class MyIterator<E> implements Iterator<E> {
        private final int savedModCount;
        private int itemsCount;

        private int currentList = -1;
        private ListIterator<E> currentListIterator;

        public MyIterator() {
            savedModCount = modCount;
            currentListIterator = getListIterator();
        }

        private ListIterator<E> getListIterator() {
            currentList++;

            while (currentList < hashTable.length) {
                if (hashTable[currentList] != null && hashTable[currentList].size() != 0) {
                    currentListIterator = (ListIterator<E>) hashTable[currentList].iterator();

                    break;
                }

                currentList++;
            }

            return currentListIterator;
        }

        public boolean hasNext() {
            return itemsCount < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            if (savedModCount != modCount) {
                throw new ConcurrentModificationException("Коллекция изменилась");
            }

            itemsCount++;

            if (!currentListIterator.hasNext()) {
                currentListIterator = getListIterator();
            }

            return currentListIterator.next();
        }
    }

    private int getElementHash(Object o) {
        return Math.abs(Objects.hashCode(o) % tableCapacity);
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] == null || hashTable[i].size() == 0) {
                continue;
            }

            sb.append("hashValue = ").append(i).append(" : ").append(hashTable[i]);
            sb.append(System.lineSeparator());
        }

        sb.delete(sb.length() - 1, sb.length());

        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MyHashTable<E> hashTable = (MyHashTable<E>) o;

        if (size != hashTable.size) {
            return false;
        }

        Iterator<E> iterator = hashTable.iterator();

        for (E e : this) {
            if (!Objects.equals(e, iterator.next())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        for (LinkedList<E> list : hashTable) {
            hash = prime * hash + Objects.hashCode(list);
        }

        return hash;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        if (hashTable.length == 0 || size == 0) {
            return;
        }

        Arrays.fill(hashTable, null);

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

    @SuppressWarnings("unchecked")
    @Override
    public boolean add(E e) {
        if (hashTable.length == 0) {
            hashTable = (LinkedList<E>[]) new LinkedList[tableCapacity];
        }

        int elementHash = getElementHash(e);

        if (hashTable[elementHash] == null) {
            hashTable[elementHash] = new LinkedList<>();
        }

        hashTable[elementHash].add(e);

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
        int elementHash = getElementHash(o);

        if (hashTable[elementHash] == null) {
            return false;
        }

        if (hashTable[elementHash].remove(o)) {
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

        for (LinkedList<E> list : hashTable) {
            if (list == null || list.size() == 0) {
                continue;
            }

            Iterator<E> listIterator = list.listIterator();

            while (listIterator.hasNext()) {
                if (!c.contains(listIterator.next())) {
                    listIterator.remove();

                    isRemove = true;

                    size--;
                    modCount++;
                }
            }
        }

        return isRemove;
    }

    @Override
    public boolean contains(Object o) {
        int hash = getElementHash(o);

        if (hashTable[hash] == null) {
            return false;
        }

        return hashTable[hash].contains(o);
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

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = Arrays.copyOf(a, size);
        }

        int index = 0;

        for (E element : this) {
            a[index] = (T) element;

            index++;
        }

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }
}