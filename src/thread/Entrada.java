package thread;
 
import java.util.Queue;
 
public class Entrada extends Thread {
	private Queue<String> entrada ;
	public Entrada(Queue<String> entrada) {
		this.entrada = entrada;
	}
	
	@Override
	public void run() {
		super.run();
		escreveEntrada();
	}
	private void escreveEntrada() {
		while(true) {
			if (entrada.size() < 100) {
				int num = (int) (Math.random()*4)+1;
				switch (num) {
					case 1 -> entrada.add("https");
					case 2 -> entrada.add("smtp");
					case 3 -> entrada.add("icmp");
					case 4 -> entrada.add("pop");
					default -> entrada.add("erro");
				}
			}
		}
	}
}