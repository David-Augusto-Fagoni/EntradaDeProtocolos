package controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

import model.CircularDupla;
import model.No;
import thread.Entrada;
import thread.Organizar;

public class ListaController {
	Queue<String> chegada;
	public ListaController(Queue<String> chegada) {
		this.chegada = chegada;
	}
	public void teste () throws InterruptedException {
		Semaphore semaforo = new Semaphore(1);
		CircularDupla<HashMap<String, Queue<String>>> lista = new CircularDupla<>();
		Organizar o = new Organizar(chegada, lista,semaforo);
		No<HashMap<String, Queue<String>>> ponteiro = new No<>(null);
		
		// garantia de que o ponteiro não sera null
		while(lista.total() == 0) {
			if(!chegada.isEmpty()) {
				lista.append(novoNo());
				ponteiro = lista.getLast();
			}
		}
		o.start();
		while(true) {
			if(!chegada.isEmpty()) {
//				System.out.println(chegada.peek());
				semaforo.acquire();
				if(!lista.contains(chegada.peek()) && chegada.peek() != null ) {
					No<HashMap<String, Queue<String>>> novoNo = new No<HashMap<String,Queue<String>>>(novoNo());
					novoNo.setProximo(ponteiro);
					novoNo.setAnterior(ponteiro.getAnterior());
					lista.addNo(novoNo);
				}
			}
				if(!ponteiro.getValor().isEmpty()) {
					String chave = ponteiro.getValor().keySet().toString().replace('[',' ').replace(']', ' ').trim();
					System.out.println(lista.toString());
					if(!ponteiro.getValor().get(chave).isEmpty()) {
						System.out.println(ponteiro.getValor().get(chave).remove());
					}
					if (chave.contains("https") && !ponteiro.getValor().get(chave).isEmpty()) {
						System.out.println(ponteiro.getValor().get(chave).remove());
					}
				}
				semaforo.release();
				Thread.sleep(100);
				ponteiro = ponteiro.getProximo();
		}
	}
	private HashMap<String, Queue<String>> novoNo() {
		HashMap<String, Queue<String>> entrada = new HashMap<String, Queue<String>>();
		Queue<String> protocolos = new LinkedList<>();
		protocolos.add(chegada.peek());
		entrada.put(chegada.remove(), protocolos);
		return entrada;
	}
}
 