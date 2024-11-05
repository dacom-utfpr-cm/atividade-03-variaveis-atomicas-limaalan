package atividade03;
import atividade01.NonBlockingSequence;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        NonBlockingQueue<Integer> queue = new NonBlockingQueue<>();

        NonBlockingSequence sequence = new NonBlockingSequence();
        Thread[] threads = new Thread[4];

        Runnable producerRunnable = () -> {
            for (int i = 0; i < 10; i++) {
                int value = sequence.generateNumber(); 
                queue.enqueue(value);
                System.out.println("Enqueued: " + value);
                try {
                    Thread.sleep(100); // Simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable consumerRunnable = () -> {
            while (!queue.isEmpty()) {
                Integer value = queue.dequeue();
                if (value != null) {
                    System.out.println("Dequeued: " + value);
                } else {
                    System.out.println("Queue is empty");
                }
                try {
                    Thread.sleep(150); // Simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };
        // Iniciando duas threads que utilizam push() na stack 
        // e outras duas que utilizam o pop()
        threads[0] = new Thread(producerRunnable);
        threads[1] = new Thread(producerRunnable);
        threads[2] = new Thread(producerRunnable);
        threads[3] = new Thread(consumerRunnable);

        threads[0].start();
        threads[1].start();
        threads[2].start();

        threads[0].join();
        threads[1].join();
        threads[2].join();

        threads[3].start();
        threads[3].join();

        
     
        // Pode ser que a pilha não esteja vazia no final
        if (queue.isEmpty()) {
            System.out.println("A fila está vazia após todas as operações.");
        } else {
            System.out.println("A fila não está vazia. Restam elementos.");
        }


    }
}
