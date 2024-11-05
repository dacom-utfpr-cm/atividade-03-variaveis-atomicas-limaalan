package atividade04;

/**
 * @author Alan Lima Marques
 * @date 17/10/2024
 * @brief Thread que encontra números primos e os exibe.
 */

public class FindPrimeNumbersThread implements  Runnable{
    private final int START;
    private final int END ;
    private final int METHOD;

    public FindPrimeNumbersThread(int START, int END, int METHOD) {
        this.END = END;
        this.START = START;
        this.METHOD = METHOD;
    }


    @Override
    public void run(){

        for ( int numeroAnalisado = START ; numeroAnalisado< END ; numeroAnalisado++) {
            int contadorDivisores = 0 ;
            if (numeroAnalisado!= 2 && numeroAnalisado %2 == 0 ){
                //System.out.println("Numero"+numeroAnalisado+"Não é primo");
                continue;
            }
            int procurarAte = (int) Math.floor(Math.sqrt(numeroAnalisado));
            for (int i = 1 ; i<=procurarAte; i++){
                if (numeroAnalisado % i == 0)
                    contadorDivisores++;
            }
            if (contadorDivisores> 1){
                //System.out.println("Numero"+numeroAnalisado+"Não é primo");
            }
            else {
                //System.out.println("Numero "+numeroAnalisado+" é primo");
                switch (METHOD) {
                    case 0:
                        ContadorPrimos.aumentarContadorSyncronized();
                        break;
                    case 1:
                        ContadorPrimos.aumentarContadorBloco();
                        break;
                    case 2 :
                        ContadorPrimos.aumentarContadorAtomic();
                        break;    
                    default:
                        throw new AssertionError("Método não listado.");
                }
            }

        }
    }

}
