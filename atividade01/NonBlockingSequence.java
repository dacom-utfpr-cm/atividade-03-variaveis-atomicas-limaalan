
import java.util.concurrent.atomic.AtomicLong;

// Implemente um gerador de números sequenciais não
// bloqueante usando AtomicLong e o compareAndSet.
public class NonBlockingSequence {
    private AtomicLong sequence = new AtomicLong(0);
    long prev , next ;

    public int generateNumber (){
        do { 
            prev = sequence.get();
            
        } while (sequence.compareAndSet(prev, next));

        return (sequence.intValue());
    }
}
