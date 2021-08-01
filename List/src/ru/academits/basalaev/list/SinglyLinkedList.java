package ru.academits.basalaev.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int length;

    public SinglyLinkedList() {
    }

    public SinglyLinkedList(ListItem<T> listItem) {
        head = listItem;
        length = 1;
    }

    public SinglyLinkedList(T[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Передана пустая ссылка");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("Передан пустой массив");
        }

        ListItem<T> item = new ListItem<>(array[0]);

        head = item;
        length = array.length;

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

        while (index > 0) {
            item = item.getNext();

            index--;
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
            sb.append(item).append(", ");
            item = item.getNext();
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");

        return sb.toString();
    }

    public int size() {
        return length;
    }

    public T getItemValue() {
        checkIndex(0);

        return head.getData();
    }

    public T getItemValue(int index) {
        checkIndex(index);

        return getItem(index).getData();
    }

    public T setItemValue(int index, T data) {
        checkIndex(index);

        ListItem<T> item = getItem(index);

        T itemValue = item.getData();
        item.setData(data);

        return itemValue;
    }

    public T remove(int index) {
        checkIndex(index);

        if (index == 0) {
            return this.remove();
        }

        ListItem<T> prevItem = getItem(index - 1);
        ListItem<T> currentItem = prevItem.getNext();
        ListItem<T> nextItem = currentItem.getNext();

        prevItem.setNext(nextItem);

        length--;

        return currentItem.getData();
    }

    public T remove() {
        checkIndex(0);

        T itemValue = head.getData();
        head = head.getNext();

        length--;

        return itemValue;
    }

    public void add(ListItem<T> item) {
        if (head != null) {
            item.setNext(head);
        } else {
            item.setNext(null);
        }

        head = item;
        length++;
    }

    public void add(int index, ListItem<T> item) {
        checkIndex(index);

        if (index == 0) {
            this.add(item);
        } else {
            ListItem<T> prevItem = getItem(index - 1);
            ListItem<T> currentItem = prevItem.getNext();

            prevItem.setNext(item);
            item.setNext(currentItem);

            length++;
        }
    }

    public boolean remove(T value) {
        if (head == null) {
            throw new UnsupportedOperationException("Список пустой");
        }

        ListItem<T> item = head;
        int index = 0;

        while (item != null) {
            if (item.getData().equals(value)) {
                remove(index);

                return true;
            }

            item = item.getNext();
            index++;
        }

        return false;
    }

    public void reverse() {
        if (head == null) {
            throw new UnsupportedOperationException("Список пустой");
        }

        ListItem<T> prevItem = null;
        ListItem<T> currentItem = head;
        ListItem<T> nextItem = head.getNext();

        while (nextItem != null) {
            currentItem.setNext(prevItem);

            prevItem = currentItem;
            currentItem = nextItem;
            nextItem = currentItem.getNext();
        }

        currentItem.setNext(prevItem);

        head = currentItem;
    }

    public SinglyLinkedList<T> getCopy() {
        if (head == null) {
            throw new UnsupportedOperationException("Список пустой");
        }

        SinglyLinkedList<T> copy = new SinglyLinkedList<>();

        copy.head = new ListItem<>(head.getData());
        copy.length = length;

        ListItem<T> item = head.getNext();
        ListItem<T> newItem = copy.head;

        while (item != null) {
            newItem.setNext(new ListItem<>(item.getData()));
            newItem = newItem.getNext();

            item = item.getNext();
        }

        return copy;
    }
}