package atividade01;
import java.util.concurrent.atomic.AtomicLong;

// Implemente um gerador de números sequenciais não
// bloqueante usando AtomicLong e o compareAndSet.
public class NonBlockingSequence {
    private AtomicLong sequence = new AtomicLong(0);
    long prev , next ;

    public int generateNumber (){
        do { 
            prev = sequence.get();
            next = prev+1;
            //System.out.println("trying " + sequence +" " + prev + " " + next);
        } while (!sequence.compareAndSet(prev, next));

        return (int) sequence.get();
    }
}
