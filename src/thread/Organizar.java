package thread;

import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.Semaphore;

import model.CircularDupla;
import model.No;

public class Organizar extends Thread {
	private Queue<String> chegada;
	Semaphore semaforo;
	private CircularDupla<HashMap<String, Queue<String>>> lista;
	public Organizar(Queue<String> chegada,CircularDupla<HashMap<String, Queue<String>>> lista, Semaphore semaforo) {
		this.chegada = chegada;
		this.lista = lista;
		this.semaforo = semaforo;
	}
	@Override
	public void run() {
		super.run();
		try {
			organiza();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private void organiza() throws InterruptedException {
		No<HashMap<String, Queue<String>>> ponteiro = lista.getLast();
		while(true) {
			if(!chegada.isEmpty()) {
				if(ponteiro.getValor().containsKey(chegada.peek())) {
					semaforo.acquire();
					String chave = chegada.remove();
					ponteiro.getValor().get(chave).add(chave);
					semaforo.release();
				}
				sleep(20);
				ponteiro = ponteiro.getProximo();
			}
		}
	}
}
