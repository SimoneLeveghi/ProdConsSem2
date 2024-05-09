import java.util.concurrent.Semaphore;

public class ProdConsSem {
    protected static int buffer;
    protected static int tanti = 5;

    public static void main(String[] args) {
        Semaphore pieno = new Semaphore(0);
        Semaphore vuoto = new Semaphore(1);
        ProduciDato prod = new ProduciDato(pieno, vuoto, Operazione.ADDIZIONE, 1);
        ProduciDato prod2 = new ProduciDato(pieno, vuoto, Operazione.MOLTIPLICAZIONE, 2);
        ConsumaDato cons = new ConsumaDato(pieno, vuoto);
        ConsumaDato cons2 = new ConsumaDato(pieno, vuoto);

        prod.start();
        prod2.start();
        cons.start();
        cons2.start();

        System.out.println("Main: termine avvio thread.");
    }
}