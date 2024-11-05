package atividade02;
import atividade01.NonBlockingSequence;;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        NonBlockingStack<Integer> stack = new NonBlockingStack<>();
        NonBlockingSequence sequence = new NonBlockingSequence();
        Thread[] threads = new Thread[4];

        Runnable pushRunnable = () -> {
            for (int i = 1; i <= 50; i++) {
                int value = sequence.generateNumber(); 
                stack.push(value);
                System.out.println("Push: " + value);
                try {
                    Thread.sleep(10); // Pequena pausa para simular operações concorrentes
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable popRunnable = () -> {
            while(!stack.isEmpty()) {
                Integer value = stack.pop();
                System.out.println("Pop: " + value);
                try {
                    Thread.sleep(15); // Pequena pausa para simular operações concorrentes
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };
        // Iniciando duas threads que utilizam push() na stack 
        // e outras duas que utilizam o pop()
        threads[0] = new Thread(pushRunnable);
        threads[1] = new Thread(pushRunnable);
        threads[2] = new Thread(popRunnable);
        threads[3] = new Thread(popRunnable);

        threads[0].start();
        threads[1].start();

        threads[0].join();
        threads[1].join();

        threads[2].start();
        threads[3].start();

        threads[2].join();
        threads[3].join();

        
     
        // Pode ser que a pilha não esteja vazia no final
        if (stack.isEmpty()) {
            System.out.println("A pilha está vazia após todas as operações.");
        } else {
            System.out.println("A pilha não está vazia. Restam elementos.");
        }


    }
}
