package ru.academits.basalaev.hash_table;

import java.util.*;

public class MyHashTable<E> implements Collection<E> {
    public LinkedList<E>[] hashTable;
    private int tableCapacity = 100;
    public int length;
    public int modCount;

    public MyHashTable() {
        hashTable = (LinkedList<E>[]) new LinkedList[tableCapacity];
    }

    public MyHashTable(int tableCapacity) {
        hashTable = (LinkedList<E>[]) new LinkedList[tableCapacity];
        this.tableCapacity = tableCapacity;
    }

    private class MyIterator<E> implements Iterator<E> {
        private final int savedModCount;
        private int elementsCount;
        private int currentList;
        private ListIterator<E> currentListIterator;

        public MyIterator() {
            savedModCount = modCount;
            currentListIterator = iterator();
        }

        private ListIterator<E> iterator() {
            currentListIterator = null;

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
            return elementsCount < length;
        }

        public E next() {
            elementsCount++;

            if (elementsCount > length) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            if (savedModCount != modCount) {
                throw new ConcurrentModificationException("Коллекция изменилась");
            }

            if (!currentListIterator.hasNext()) {
                currentList++;

                currentListIterator = iterator();
            }

            return currentListIterator != null ? currentListIterator.next() : null;
        }
    }

    private int getElementHashCode(Object o) {
        return Math.abs(Objects.hashCode(o) % tableCapacity);
    }

    @Override
    public String toString() {
        if (length == 0) {
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

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MyHashTable<E> hashTable = (MyHashTable<E>) o;

        if (length != hashTable.length) {
            return false;
        }

        if (hashCode() != hashTable.hashCode()) {
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
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }

        int hash = getElementHashCode(o);

        if (hashTable[hash] == null || hashTable[hash].size() == 0) {
            return false;
        }

        return hashTable[hash].contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator<>();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[length];

        int insertPosition = 0;

        for (LinkedList<E> list : hashTable) {
            if (list == null || list.size() == 0) {
                continue;
            }

            Object[] subArray = list.toArray();
            System.arraycopy(subArray, 0, array, insertPosition, subArray.length);

            insertPosition += subArray.length;
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < length) {
            a = (T[]) new Object[length];
        }

        int insertArrayPosition = 0;

        for (LinkedList<E> list : hashTable) {
            if (list == null || list.size() == 0) {
                continue;
            }

            T[] subArray = (T[]) list.toArray();
            System.arraycopy(subArray, 0, a, insertArrayPosition, subArray.length);

            insertArrayPosition += subArray.length;
        }

        return a;
    }

    @Override
    public boolean add(E e) {
        int hash = getElementHashCode(e);

        if (hashTable[hash] == null) {
            hashTable[hash] = new LinkedList<>();
        }

        hashTable[hash].add(e);

        length++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int hash = getElementHashCode(o);

        if (hashTable[hash] == null || hashTable[hash].size() == 0) {
            return false;
        }

        if (hashTable[hash].remove(o)) {
            length--;
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (length == 0 || c == null) {
            return false;
        }

        Iterator<E> iterator = (Iterator<E>) c.iterator();

        while (iterator.hasNext()) {
            E element = iterator.next();

            int hash = getElementHashCode(element);

            if (hashTable[hash] == null || hashTable[hash].size() == 0) {
                return false;
            }

            if (!hashTable[hash].contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) {
            return false;
        }

        Iterator<E> iterator = (Iterator<E>) c.iterator();

        while (iterator.hasNext()) {
            E element = iterator.next();

            int hash = getElementHashCode(element);

            if (hashTable[hash] == null) {
                hashTable[hash] = new LinkedList<>();
            }

            hashTable[hash].add(element);

            modCount++;
        }

        length += c.size();

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            return false;
        }

        boolean isRemove = false;

        Iterator<E> iterator = (Iterator<E>) c.iterator();

        while (iterator.hasNext()) {
            E element = iterator.next();

            int hash = getElementHashCode(element);

            if (hashTable[hash] == null || hashTable[hash].size() == 0) {
                continue;
            }

            if (hashTable[hash].remove(element)) {
                isRemove = true;

                length--;
                modCount++;
            }
        }

        return isRemove;
    }

    public boolean retainAll(Collection<?> c) {
        if (length == 0 || c == null) {
            return false;
        }

        LinkedList<E>[] newHashTable = (LinkedList<E>[]) new Object[tableCapacity];
        int newLength = 0;

        for (E element : this) {
            Iterator<E> collectionIterator = (Iterator<E>) c.iterator();

            while (collectionIterator.hasNext()) {
                E collectionElement = collectionIterator.next();

                if (Objects.equals(element, collectionElement)) {
                    int elementHash = getElementHashCode(element);

                    if (newHashTable[elementHash] == null) {
                        newHashTable[elementHash] = new LinkedList<>();
                    }

                    newHashTable[elementHash].add(element);

                    newLength++;
                    modCount++;

                    break;
                }
            }
        }

        hashTable = newHashTable;
        length = newLength;

        return true;
    }

    @Override
    public void clear() {
        hashTable = (LinkedList<E>[]) new Object[100];

        length = 0;
        modCount = 0;
    }
}