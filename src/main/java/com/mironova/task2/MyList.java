package com.mironova.task2;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * MyList implements java.util.List class.
 */
public class MyList<E> implements List<E> {
    private Object[] arr;
    private int size;
    private int currentSize;

    /**
     * Constructor without arguments.
     */
    public MyList() {
        arr = new Object[0];
        size = 0;
        currentSize = 0;
    }

    /**
     * Constructor with array as an argument.
     */
    public MyList(E[] array) {
        arr = Arrays.copyOf(array, array.length);
        size = array.length;
        currentSize = array.length;
    }

    /**
     * resizeArray method changes array's size.
     */
    public void resizeArray() {
        if (currentSize <= size) {
            if (currentSize == 0) {
                currentSize = 1;
            } else {
                currentSize *= 2;
            }
            arr = Arrays.copyOf(arr, currentSize);
        } else if (size <= (currentSize / 2)) {
            currentSize /= 2;
            arr = Arrays.copyOf(arr, currentSize);
        }
    }

    /**
     * add method implements adding of element at the end of list.
     */
    @Override
    public boolean add(E e) {
        resizeArray();
        arr[size] = e;
        size++;
        return true;
    }

    /**
     * add method implements adding of element at the concrete position.
     */
    @Override
    public void add(int index, E e) {
        if (index <= size) {
            resizeArray();
            System.arraycopy(arr, index, arr, index + 1, size);
            arr[index] = e;
            size++;
        }
    }

    /**
     * remove method implements removing of element by its index.
     */
    @Override
    public E remove(int index) {
        if (index < size) {
            Object[] tmpArr = new Object[size - 1];
            E e = (E) arr[index];
            System.arraycopy(arr, 0, tmpArr, 0, index);
            System.arraycopy(arr, index + 1, tmpArr, index, size - index - 1);
            arr = Arrays.copyOf(tmpArr, size - 1);
            resizeArray();
            size--;
            return e;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    /**
     * indexOf method implements getting of element's index.
     */
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == o) {
                return i;
            }
        }
        return -1;
    }

    /**
     * get method implements getting of element by its index.
     */
    @Override
    public E get(int index) {
        if (index < size) {
            return (E) arr[index];
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        arr = Arrays.copyOf(arr, size);
        return arr;
    }

    @Override
    public <E> E[] toArray(E[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}