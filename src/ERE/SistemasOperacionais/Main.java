package ERE.SistemasOperacionais;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        Leitor leitor = new Leitor("entrada.txt");
        Escritor escritor =  new Escritor("saida.txt");

        ArrayList<String> entradas = leitor.ler();
        ArrayList<String> saidas = new ArrayList<>();

        FCFS fcfs = new FCFS(entradas);
        ShortestJobFirst shortestJobFirst = new ShortestJobFirst(entradas);
        RoundRobin roundRobin = new RoundRobin(entradas);

        saidas.add(fcfs.escalonamento());
        saidas.add(roundRobin.escalonamento());
        saidas.add(shortestJobFirst.escalonamento());
        System.out.println(saidas);
        escritor.escrever(saidas);
    }
}
