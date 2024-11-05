package atividade04;

import java.util.concurrent.atomic.AtomicInteger;
/**
 * @author Alan Lima Marques
 * @date 05/11/2024
 * @brief Classe que contabiliza a quantidade de números primos encontrados
 * O incremento é feito de forma sincronizada utilizando syncronized ou variáveis atômicas.
 */
public class ContadorPrimos {
    private static int contador = 0 ;
    private static AtomicInteger contadorAtomic = new  AtomicInteger(0);

    public static synchronized  void aumentarContadorSyncronized(){
        contador ++;
    }

    public static void aumentarContadorBloco(){
        synchronized (ContadorPrimos.class) {
            contador ++;
        }
    }

    public static void aumentarContadorAtomic(){
        contadorAtomic.incrementAndGet();
    }

    public static int getContador() {
        return contador;
    }

    public static void resetContador() {
        contador = 0;
    }
}
