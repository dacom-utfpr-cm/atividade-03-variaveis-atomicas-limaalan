package atividade03;

import java.util.concurrent.atomic.AtomicReference;

public class NonBlockingQueue<T> {
    // Node class to represent each element in the queue
    private static class Node<T> {
        final T value;
        final AtomicReference<Node<T>> next;

        Node(T value) {
            this.value = value;
            this.next = new AtomicReference<>(null);
        }
    }

    // Head and tail of the queue
    private final AtomicReference<Node<T>> head;
    private final AtomicReference<Node<T>> tail;

    // Constructor
    public NonBlockingQueue() {
        Node<T> dummy = new Node<>(null); // Dummy node
        head = new AtomicReference<>(dummy);
        tail = new AtomicReference<>(dummy);
    }

    // Method to enqueue an element
    public void enqueue(T value) {
        Node<T> newNode = new Node<>(value);
        Node<T> oldTail;

        while (true) {
            oldTail = tail.get();
            Node<T> nextNode = oldTail.next.get();

            // If tail is not pointing to the last node, move it forward
            if (nextNode != null) {
                tail.compareAndSet(oldTail, nextNode);
                continue;
            }

            // Try to link the new node at the end of the queue
            if (oldTail.next.compareAndSet(null, newNode)) {
                // Successfully linked, now try to move tail to the new node
                tail.compareAndSet(oldTail, newNode);
                return;
            }
        }
    }

    // Method to dequeue an element
    public T dequeue() {
        Node<T> oldHead;

        while (true) {
            oldHead = head.get();
            Node<T> nextNode = oldHead.next.get();

            // If the queue is empty (nextNode is null), return null
            if (nextNode == null) {
                return null;
            }

            // Try to move head to the next node
            if (head.compareAndSet(oldHead, nextNode)) {
                return nextNode.value;
            }
        }
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return head.get().next.get() == null;
    }
}