package ERE.SistemasOperacionais;

import java.util.ArrayList;

public class FCFS {
    private ArrayList<String> entradas;

    public FCFS (ArrayList<String> entradas) {
        this.entradas = entradas;
    }

    public String escalonamento() {
        ArrayList<Integer> chegadas = new ArrayList<>();
        ArrayList<Integer> duracoes = new ArrayList<>();

        float tempoEsperaTotal = 0;
        float tempoRetornoTotal = 0;

        for (String entrada: entradas) {
            chegadas.add(Integer.parseInt(entrada.split(" ")[0]));
            duracoes.add(Integer.parseInt(entrada.split(" ")[1]));
        }
        int tempoProcesso = 0;
        ArrayList<Integer> tempoEsperas = new ArrayList<>();
        ArrayList<Integer> fila = new ArrayList<>();
        int executando = 0;
        int maiorTempoEntrada = 0;
        int tempoExecucao = 0;
        int tempoEsperaAnterior = 0;
        for (int i = 0; i < chegadas.size(); i++) {
            if (i == 0) {
                maiorTempoEntrada = chegadas.get(i);
            }
            if (maiorTempoEntrada <= chegadas.get(i)) {
                maiorTempoEntrada = chegadas.get(i);
            }
        }

        while(true) {
            for (int i = 0; i < chegadas.size(); i++) {
                if (chegadas.get(i) == tempoProcesso) {
                    fila.add(duracoes.get(i));
                    tempoEsperas.add(0);
                }
            }
            if (executando <= 0 && fila.size() != 0) {
                executando = fila.get(0);
                tempoRetornoTotal += tempoExecucao + tempoEsperaAnterior;
                tempoEsperaTotal+= tempoEsperas.get(0);
                tempoEsperaAnterior = tempoEsperas.get(0);
                tempoEsperas.remove(0);
                fila.remove(0);
                tempoExecucao = 0;
            }
            if (executando != 0) {
                executando--;
                tempoExecucao++;
            }
            tempoProcesso++;
            for (int i = 0; i < tempoEsperas.size(); i++) {
                int t = tempoEsperas.get(i);
                t++;
                tempoEsperas.set(i, t);
            }
            if (fila.size() == 0 && executando <= 0 && tempoProcesso > maiorTempoEntrada) {
                tempoRetornoTotal += tempoExecucao + tempoEsperaAnterior;
                break;
            }
        }
        return "FCFS "
                + tempoRetornoTotal/chegadas.size()
                + " "
                + tempoEsperaTotal/chegadas.size()
                + " "
                + tempoEsperaTotal/chegadas.size();
    }
}
