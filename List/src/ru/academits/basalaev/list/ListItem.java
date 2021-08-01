package ru.academits.basalaev.list;

public class ListItem<T> {
    private T data;
    private ListItem<T> next;

    public ListItem(T data) {
        this.data = data;
    }

    public ListItem(T data, ListItem<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ListItem<T> getNext() {
        return next;
    }

    public void setNext(ListItem<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return data.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ListItem<T> item = (ListItem<T>) o;

        if (this.getData() == null) {
            return item.getData() == null;
        }

        return this.getData().equals(item.getData());
    }
}