package view;
 
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import controller.ListaController;
import model.CircularDupla;
import model.No;
import thread.Entrada;
 
public class Principal {
 
	public static void main(String[] args) {
	//Cap 5.3 porem Https tem que tirar 2
		
		Queue<String> chegada = new LinkedList<>();
		Entrada entrada = new Entrada(chegada);
		entrada.start();
		ListaController lController = new ListaController(chegada);
		try {
			Thread.sleep(2000);
			lController.teste();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
}