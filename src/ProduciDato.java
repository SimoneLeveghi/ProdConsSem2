import java.util.concurrent.Semaphore;

public class ProduciDato extends Thread {
    private Semaphore pieno;
    private Semaphore vuoto;
    private Semaphore moltiplicazione;
    private final int attesa = 500;

    public ProduciDato(Semaphore s1, Semaphore s2, Semaphore operazione) {
        this.pieno = s1;
        this.vuoto = s2;
        this.moltiplicazione = operazione;
    }

    public void run() {
        int tmp;

        for (int i = 0; i < ProdConsSem.tanti; i++) {
            vuoto.acquireUninterruptibly();
            synchronized (ProdConsSem.class) {
                if(this.moltiplicazione.availablePermits() == 1) {
                    ProdConsSem.buffer *= 2;
                    moltiplicazione.acquireUninterruptibly();
                }
                else {
                    ProdConsSem.buffer += 1;
                    moltiplicazione.release();
                }

                tmp = ProdConsSem.buffer;
            }
            pieno.release();
            System.out.println("Scrittore " + getName() + ": nuovo dato: " + tmp);
            System.out.println("Scrittore: termine scrittura dati.");
        }
    }
}
