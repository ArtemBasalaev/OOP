package ru.academits.basalaev.array_list;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private E[] elements;
    private int length;
    private int modCount;

    public MyArrayList() {
        elements = (E[]) new Object[10];
    }

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Вместимость списка не может быть отрицательным числом, передано значение " + capacity);
        }

        elements = (E[]) new Object[capacity];
    }

    public MyArrayList(E[] array) {
        if (array == null) {
            throw new NullPointerException("Передана пустая ссылка");
        }

        elements = array.length > 0 ? (E[]) new Object[array.length * 2] : (E[]) new Object[10];

        System.arraycopy(array, 0, elements, 0, array.length);
        length = array.length;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Выход за границы списка. Допустимый диапазон индекса 0 <= index < " + length
                    + ", передано значение: " + index);
        }
    }

    private void increaseCapacity() {
        elements = Arrays.copyOf(elements, elements.length * 2);
    }

    public void ensureCapacity(int capacity) {
        if (elements.length < capacity) {
            elements = Arrays.copyOf(elements, capacity);
        }
    }

    public void trimToSize() {
        if (elements.length > length) {
            elements = Arrays.copyOf(elements, length);
        }
    }

    private class MyIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int savedModCount;

        public MyIterator() {
            savedModCount = modCount;
        }

        public boolean hasNext() {
            return currentIndex + 1 < length;
        }

        public E next() {
            if (currentIndex == length) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            if (savedModCount != modCount) {
                throw new ConcurrentModificationException("Коллекция изменилась");
            }

            currentIndex++;

            return elements[currentIndex];
        }
    }

    @Override
    public String toString() {
        if (length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < length; i++) {
            sb.append(elements[i]).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");

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

        MyArrayList<E> list = (MyArrayList<E>) o;

        if (length != list.length) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            if (!Objects.equals(elements[i], list.elements[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        for (int i = 0; i < length; i++) {
            hash = prime * hash + Objects.hashCode(elements[i]);
        }

        return hash;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public void clear() {
        if (elements.length == 0 || length == 0) {
            return;
        }

        for (int i = 0; i < length; i++) {
            elements[i] = null;
        }

        length = 0;
        modCount++;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);

        E oldElement = elements[index];
        elements[index] = element;

        return oldElement;
    }

    @Override
    public boolean add(E element) {
        add(length, element);

        return true;
    }

    @Override
    public void add(int index, E element) {
        if (elements.length == 0) {
            ensureCapacity(10);
        }

        if (index != length) {
            checkIndex(index);
        }

        if (length == elements.length) {
            increaseCapacity();
        }

        System.arraycopy(elements, index, elements, index + 1, length - index);

        elements[index] = element;

        length++;
        modCount++;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(length, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException("Передана пустая ссылка");
        }

        int collectionLength = c.size();

        if (collectionLength == 0) {
            return false;
        }

        if (index != length) {
            checkIndex(index);
        }

        if (elements.length == 0) {
            ensureCapacity(collectionLength * 2);
        }

        int resultLength = length + collectionLength;

        if (resultLength > elements.length) {
            ensureCapacity(resultLength * 2);
        }

        System.arraycopy(elements, index, elements, index + collectionLength, length - index);

        int i = index;

        for (E element : c) {
            elements[i] = element;

            i++;
        }

        length = resultLength;

        modCount++;

        return true;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E deletedElement = elements[index];

        if (index < length - 1) {
            System.arraycopy(elements, index + 1, elements, index, length - index - 1);
        }

        elements[length] = null;

        length--;
        modCount++;

        return deletedElement;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index != -1) {
            remove(index);

            return true;
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Передана пустая ссылка");
        }

        if (c.size() == 0 || length == 0) {
            return false;
        }

        boolean isRemove = false;

        for (int i = length - 1; i >= 0; i--) {
            if (c.contains(elements[i])) {
                remove(i);

                isRemove = true;
            }
        }

        return isRemove;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Передана пустая ссылка");
        }

        if (c.size() == 0 || length == 0) {
            return false;
        }

        boolean haveDifference = false;

        for (int i = length - 1; i >= 0; i--) {
            if (!c.contains(elements[i])) {
                remove(i);

                haveDifference = true;
            }
        }

        return haveDifference;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Передана пустая ссылка");
        }

        if (c.size() == 0 || length == 0) {
            return false;
        }

        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < length; i++) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = length - 1; i >= 0; i--) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, length);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < length) {
            return (T[]) Arrays.copyOf(elements, length, a.getClass());
        }

        System.arraycopy(elements, 0, a, 0, length);

        if (a.length > length) {
            a[length] = null;
        }

        return a;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    // без реализации
    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    // без реализации
    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    // без реализации
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}