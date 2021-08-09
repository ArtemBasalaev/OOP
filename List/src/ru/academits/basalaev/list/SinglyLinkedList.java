package ru.academits.basalaev.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int length;

    public SinglyLinkedList() {
    }

    public SinglyLinkedList(T data) {
        head = new ListItem<>(data);
        length++;
    }

    public SinglyLinkedList(T[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Передана пустая ссылка");
        }

        head = array.length > 0 ? new ListItem<>(array[0]) : null;
        length = array.length;

        ListItem<T> item = head;

        for (int i = 1; i < array.length; i++) {
            item.setNext(new ListItem<>(array[i]));

            item = item.getNext();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Выход за границы списка. Допустимый диапазон индекса 0 <= index < " + length
                    + ", передано значение: " + index);
        }
    }

    private ListItem<T> getItem(int index) {
        ListItem<T> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        return item;
    }

    @Override
    public String toString() {
        if (head == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder("[");

        ListItem<T> item = head;

        while (item != null) {
            sb.append(item.getData()).append(", ");
            item = item.getNext();
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");

        return sb.toString();
    }

    public int getLength() {
        return length;
    }

    public T getFirst() {
        if (length == 0) {
            throw new NoSuchElementException();
        }

        return head.getData();
    }

    public T get(int index) {
        checkIndex(index);

        return getItem(index).getData();
    }

    public T set(int index, T data) {
        checkIndex(index);

        ListItem<T> item = getItem(index);

        T oldData = item.getData();
        item.setData(data);

        return oldData;
    }

    public T removeFirst() {
        if (length == 0) {
            throw new NoSuchElementException();
        }

        T deletedData = head.getData();
        head = head.getNext();

        length--;

        return deletedData;
    }

    public T remove(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<T> previousItem = getItem(index - 1);
        ListItem<T> currentItem = previousItem.getNext();
        ListItem<T> nextItem = currentItem.getNext();

        previousItem.setNext(nextItem);

        length--;

        return currentItem.getData();
    }

    public void addFirst(T data) {
        ListItem<T> item = new ListItem<>(data);

        item.setNext(head);

        head = item;
        length++;
    }

    public void add(int index, T data) {
        if (index == length) {
            getItem(index - 1).setNext(new ListItem<>(data));
            length++;

            return;
        }

        checkIndex(index);

        ListItem<T> item = new ListItem<>(data);

        if (index == 0) {
            addFirst(data);

            return;
        }

        ListItem<T> previousItem = getItem(index - 1);
        ListItem<T> currentItem = previousItem.getNext();

        previousItem.setNext(item);
        item.setNext(currentItem);

        length++;
    }

    public boolean remove(T data) {
        if (head == null) {
            return false;
        }

        ListItem<T> item = head;
        int index = 0;

        while (item != null) {
            if (Objects.equals(item.getData(), data)) {
                remove(index);

                return true;
            }

            item = item.getNext();
            index++;
        }

        return false;
    }

    public void reverse() {
        ListItem<T> previousItem = null;
        ListItem<T> currentItem = head;
        ListItem<T> nextItem = head.getNext();

        while (nextItem != null) {
            currentItem.setNext(previousItem);

            previousItem = currentItem;
            currentItem = nextItem;
            nextItem = currentItem.getNext();
        }

        currentItem.setNext(previousItem);

        head = currentItem;
    }

    public SinglyLinkedList<T> getCopy() {
        SinglyLinkedList<T> copy = new SinglyLinkedList<>();

        copy.head = new ListItem<>(head.getData());
        copy.length = length;

        ListItem<T> item = head.getNext();
        ListItem<T> copyItem = copy.head;

        while (item != null) {
            copyItem.setNext(new ListItem<>(item.getData()));
            copyItem = copyItem.getNext();

            item = item.getNext();
        }

        return copy;
    }
}