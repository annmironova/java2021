package com.mironova.task2;

import java.util.Stack;

/**
 * MyStack extends java.util.Stack class.
 */
public class MyStack<E> extends Stack<E> {
    private MyList<E> list;

    /**
     * Constructor with array as an argument.
     */
    public MyStack(E[] array) {
        list = new MyList<>(array);
    }

    /**
     * push method adds new element to the stack.
     */
    public E push(E e) {
        list.add(e);
        return e;
    }

    /**
     * pop method removes last element from the stack.
     */
    public E pop() {
        return list.remove(list.size() - 1);
    }

    /**
     * peek method gets last element from the stack.
     */
    public E peek() {
        return list.get(list.size() - 1);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public Object[] toArray() {
        return list.toArray();
    }
}
