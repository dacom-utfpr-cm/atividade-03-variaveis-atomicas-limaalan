package atividade02;
import java.util.concurrent.atomic.AtomicReference;

public class NonBlockingStack<T> {

    private static class Node<T> {
        final T value;
        final Node<T> next;

        Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    private final AtomicReference<Node<T>> top = new AtomicReference<>(null);

    // Método para adicionar um elemento na pilha
    public void push(T value) {
        Node<T> newNode;
        Node<T> currentTop;
        
        do {
            currentTop = top.get();
            newNode = new Node<>(value, currentTop); // Cria o novo nó apontando para o nó atual do topo
        } while (!top.compareAndSet(currentTop, newNode)); // Tenta substituir o topo da pilha
    }

    // Método para remover um elemento da pilha
    public T pop() {
        Node<T> currentTop;
        Node<T> newTop;
        
        do {
            currentTop = top.get();
            if (currentTop == null) { // Verifica se a pilha está vazia
                return null;
            }
            newTop = currentTop.next; // Define o próximo nó como o novo topo
        } while (!top.compareAndSet(currentTop, newTop)); // Tenta substituir o topo da pilha
        
        return currentTop.value;
    }

    // Método para verificar se a pilha está vazia
    public boolean isEmpty() {
        return top.get() == null;
    }
}
