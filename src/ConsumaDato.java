import java.util.concurrent.Semaphore;

public class ConsumaDato extends Thread {
    private Semaphore pieno;
    private Semaphore vuoto;
    private int dato;

    public ConsumaDato(Semaphore s1, Semaphore s2) {
        pieno = s1;
        vuoto = s2;
    }

    public void run() {
        for(int i = 0; i < ProdConsSem.tanti; ++i) {
            pieno.acquireUninterruptibly();
            synchronized (ProdConsSem.class) {
                dato = ProdConsSem.buffer;
            }
            System.out.println("Lettore " + getName() + ": dato letto: " + dato);
            vuoto.release();
        }
    }
}
