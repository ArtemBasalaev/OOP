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

    private void checkObjectType(Object o) {
        if (elements[0].getClass() != o.getClass()) {
            throw new ClassCastException("Неприводимые типы: хранимый тип в коллекции " + elements[0].getClass()
                    + ", переданный тип " + o.getClass());
        }
    }

    private void checkElementsType(Collection<?> c) {
        Iterator<?> iterator = c.iterator();
        Object element = iterator.next();

        checkObjectType(element);
    }

    private void increaseCapacity() {
        elements = Arrays.copyOf(elements, elements.length * 2);
    }

    public void ensureCapacity(int capacity) {
        if (elements.length < capacity) {
            elements = Arrays.copyOf(elements, capacity);
        }
    }

    public void trimToSize(int size) {
        if (elements.length > size) {
            elements = Arrays.copyOf(elements, size);
        }

        if (length > size) {
            length = size;
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
            currentIndex++;

            if (currentIndex == length) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            if (savedModCount != modCount) {
                throw new ConcurrentModificationException("Коллекция изменилась");
            }

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

        if (hashCode() != list.hashCode()) {
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
        elements = (E[]) new Object[elements.length];
        length = 0;
        modCount = 0;
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
        if (length == elements.length) {
            increaseCapacity();
        }

        elements[length] = element;

        length++;
        modCount++;

        return true;
    }

    @Override
    public void add(int index, E element) {
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
        if (c == null) {
            return false;
        }

        checkElementsType(c);

        int resultLength = length + c.size();

        if (resultLength > elements.length) {
            ensureCapacity(resultLength * 2);
        }

        Iterator<E> iterator = (Iterator<E>) c.iterator();

        for (int i = length; iterator.hasNext(); i++) {
            elements[i] = iterator.next();
        }

        length = resultLength;
        modCount++;

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c == null) {
            return false;
        }

        checkIndex(index);
        checkElementsType(c);

        int resultLength = length + c.size();

        if (resultLength > elements.length) {
            ensureCapacity(resultLength * 2);
        }

        System.arraycopy(elements, index, elements, index + c.size(), length - index);

        Iterator<E> iterator = (Iterator<E>) c.iterator();

        for (int i = index; iterator.hasNext(); i++) {
            elements[i] = iterator.next();
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
        if (length == 0 || o == null) {
            return false;
        }

        checkObjectType(o);

        for (int i = 0; i < length; i++) {
            if (Objects.equals(elements[i], o)) {
                remove(i);

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (length == 0 || c == null) {
            return false;
        }

        checkElementsType(c);

        Iterator<E> iterator = (Iterator<E>) c.iterator();

        boolean isRemoveAll = false;

        while (iterator.hasNext()) {
            E element = iterator.next();

            for (int i = length - 1; i >= 0; i--) {
                if (Objects.equals(elements[i], element)) {
                    remove(i);

                    isRemoveAll = true;
                }
            }
        }

        return isRemoveAll;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (length == 0 || c == null) {
            return false;
        }

        checkElementsType(c);

        E[] newElements = (E[]) new Object[elements.length];
        int newLength = 0;

        for (int i = 0, j = 0; i < length; i++) {
            Iterator<E> iterator = (Iterator<E>) c.iterator();

            while (iterator.hasNext()) {
                E element = iterator.next();

                if (Objects.equals(elements[i], element)) {
                    newElements[j] = elements[i];

                    j++;
                    newLength++;

                    break;
                }
            }
        }

        elements = newElements;
        length = newLength;

        modCount++;

        return length != 0;
    }

    @Override
    public boolean contains(Object o) {
        if (length == 0 || o == null) {
            return false;
        }

        checkObjectType(o);

        for (int i = 0; i < length; i++) {
            if (Objects.equals(elements[i], o)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (length == 0 || c == null) {
            return false;
        }

        checkElementsType(c);

        Iterator<E> iterator = (Iterator<E>) c.iterator();

        while (iterator.hasNext()) {
            boolean isContainsAll = false;

            E element = iterator.next();

            for (int i = 0; i < length; i++) {
                if (Objects.equals(elements[i], element)) {
                    isContainsAll = true;

                    break;
                }
            }

            if (!isContainsAll) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int indexOf(Object o) {
        if (length == 0 || o == null) {
            return -1;
        }

        checkObjectType(o);

        for (int i = 0; i < length; i++) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (length == 0 || o == null) {
            return -1;
        }

        checkObjectType(o);

        int index = -1;

        for (int i = 0; i < length; i++) {
            if (Objects.equals(elements[i], o)) {
                index = i;
            }
        }

        return index;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, length);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < length) {
            a = (T[]) new Object[length];
        }

        System.arraycopy(elements, 0, a, 0, length);

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