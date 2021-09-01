package ru.academits.basalaev.array_list;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private E[] elements;
    private int size;
    private int modCount;

    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Вместимость списка не может быть отрицательным числом, передано значение " + capacity);
        }

        //noinspection unchecked
        elements = (E[]) new Object[capacity];
    }

    public MyArrayList(E[] array) {
        if (array == null) {
            throw new NullPointerException("Передана пустая ссылка");
        }

        //noinspection unchecked
        elements = (E[]) new Object[array.length];

        System.arraycopy(array, 0, elements, 0, array.length);
        size = array.length;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Выход за границы списка. Допустимый диапазон индекса 0 <= index < " + size
                    + ", передано значение: " + index);
        }
    }

    private void increaseCapacity() {
        int capacity = elements.length > 0 ? elements.length * 2 : DEFAULT_CAPACITY;

        elements = Arrays.copyOf(elements, capacity);
    }

    public void ensureCapacity(int capacity) {
        if (elements.length < capacity) {
            elements = Arrays.copyOf(elements, capacity);
        }
    }

    public void trimToSize() {
        if (elements.length > size) {
            elements = Arrays.copyOf(elements, size);
        }
    }

    private class MyIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int savedModCount;

        public MyIterator() {
            savedModCount = modCount;
        }

        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        public E next() {
            if (!hasNext()) {
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
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
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

        //noinspection unchecked
        MyArrayList<E> list = (MyArrayList<E>) o;

        if (size != list.size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
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

        for (int i = 0; i < size; i++) {
            hash = prime * hash + Objects.hashCode(elements[i]);
        }

        return hash;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }

        size = 0;
        modCount++;
    }

    @Override
    public int size() {
        return size;
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
        add(size, element);

        return true;
    }

    @Override
    public void add(int index, E element) {
        if (index != size) {
            checkIndex(index);
        }

        if (size == elements.length) {
            increaseCapacity();
        }

        System.arraycopy(elements, index, elements, index + 1, size - index);

        elements[index] = element;

        size++;
        modCount++;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException("Передана пустая ссылка");
        }

        int collectionSize = c.size();

        if (collectionSize == 0) {
            return false;
        }

        if (index != size) {
            checkIndex(index);
        }

        int resultSize = size + collectionSize;

        ensureCapacity(resultSize);

        System.arraycopy(elements, index, elements, index + collectionSize, size - index);

        int i = index;

        for (E element : c) {
            elements[i] = element;

            i++;
        }

        size = resultSize;

        modCount++;

        return true;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E removedElement = elements[index];

        if (index < size - 1) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }

        elements[size - 1] = null;

        size--;
        modCount++;

        return removedElement;
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

        if (c.size() == 0 || size == 0) {
            return false;
        }

        boolean isRemove = false;

        for (int i = size - 1; i >= 0; i--) {
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

        boolean isRemove = false;

        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(elements[i])) {
                remove(i);

                isRemove = true;
            }
        }

        return isRemove;
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

        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(elements, size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(elements, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    // без реализации
    @SuppressWarnings("ConstantConditions")
    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    // без реализации
    @SuppressWarnings("ConstantConditions")
    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    // без реализации
    @SuppressWarnings("ConstantConditions")
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}