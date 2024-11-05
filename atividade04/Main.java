package atividade04;
/**
 * @author Alan Lima Marques
 * @date 05/11/2024
 * @brief Inicia 4 threads que buscam números primos.
 * 3 métodos de incremento do contador são testados, a função sincronizada, bloco sincronizado e variável atômica.
 */
public class Main {
    public static long testFindPrimeNumbers(int syncMethod){
        Long startTime = System.currentTimeMillis();

        Thread[] threads = new Thread[4];

        threads[0]= new Thread(new FindPrimeNumbersThread(0,2499,0));
        threads[1]= new Thread(new FindPrimeNumbersThread(2500,4999,0));
        threads[2]= new Thread(new FindPrimeNumbersThread(5000,7499,0));
        threads[3]= new Thread(new FindPrimeNumbersThread(7500,10000,0));
        
        for (Thread thread : threads){
            thread.start();
        }
        
        for (Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        //System.out.println("Total de números primos encontrados : " + ContadorPrimos.getContador());
        Long endTime = System.currentTimeMillis();
        return endTime-startTime;

    }
    public static void main(String[] args) {

        System.out.println("Função sincronizada : "+testFindPrimeNumbers(0) + "ms");
        ContadorPrimos.resetContador();
        System.out.println("Block sincronizado : "+testFindPrimeNumbers(1)+ "ms");
        ContadorPrimos.resetContador();
        System.out.println("Variável atômica : "+testFindPrimeNumbers(2)+ "ms");

    }

}
