package org.example.queue;

import org.example.command.ICommand;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListCommandQueue implements Queue<ICommand> {
    private static LinkedListCommandQueue instance;
    private final LinkedList<ICommand> elements;

    public LinkedListCommandQueue() {
        this.elements = new LinkedList<>();
    }

    public static synchronized LinkedListCommandQueue getInstance() {
        if (instance == null) {instance = new LinkedListCommandQueue();}
        return instance;
    }

    @Override
    public Iterator<ICommand> iterator() {
        return elements.iterator();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public void push(ICommand c) {
        elements.push(c);
    }

    @Override
    public ICommand peek() {
        return elements.getFirst();
    }

    @Override
    public ICommand pop() {
        Iterator<ICommand> iter = elements.iterator();
        ICommand t = iter.next();
        if (t != null) {
            iter.remove();
            return t;
        }
        return null;
    }

    @Override
    public void clear() {
        elements.clear();
    }
}
