package ERE.SistemasOperacionais;

import java.util.ArrayList;

public class RoundRobin {
    private ArrayList<String> entradas;

    public RoundRobin(ArrayList<String> entradas) {
        this.entradas = entradas;
    }

    public String escalonamento() {
        ArrayList<Integer> chegadas = new ArrayList<>();
        ArrayList<Integer> duracoes = new ArrayList<>();

        for (String entrada : entradas) {
            chegadas.add(Integer.parseInt(entrada.split(" ")[0]));
            duracoes.add(Integer.parseInt(entrada.split(" ")[1]));
        }

        float tempoRetornoTotal = 0;
        float tempoRespostaTotal = 0;
        float tempoEsperaTotal = 0;

        ArrayList<Integer> fila = new ArrayList<>();
        ArrayList<Integer> tempoEsperas = new ArrayList<>();
        ArrayList<Integer> tempoExecucoes = new ArrayList<>();
        ArrayList<Integer> tempoRespostas =  new ArrayList<>();

        int executando = 0;
        int tempoProcesso = 0;
        int maiorTempoEntrada = 0;
        int tempoExecucao = 0;
        int tempoEsperaAnterior = 0;
        int tempoExecucaoAnterior = 0;
        int primeiraExecucao = 0;
        for (int i = 0; i < chegadas.size(); i++) {
            if (i == 0) {
                maiorTempoEntrada = chegadas.get(i);
            }
            if (maiorTempoEntrada <= chegadas.get(i)) {
                maiorTempoEntrada = chegadas.get(i);
            }
        }

        while (true) {
            for (int i = 0; i < chegadas.size(); i++) {
                if (chegadas.get(i) == tempoProcesso) {
                    fila.add(duracoes.get(i));
                    tempoEsperas.add(0);
                    tempoExecucoes.add(0);
                    tempoRespostas.add(0);
                }
            }

            if (executando <= 0 && fila.size() != 0) {
                executando = fila.get(0);
                fila.remove(0);

                if (tempoExecucoes.size() > 0 && tempoEsperas.size() > 0) {
                    tempoExecucaoAnterior = tempoExecucoes.get(0);
                    tempoEsperaAnterior = tempoEsperas.get(0);

                    tempoEsperas.remove(0);
                    tempoEsperas.add(tempoEsperaAnterior);

                    tempoExecucoes.remove(0);
                    tempoExecucoes.add(tempoExecucaoAnterior);
                    tempoExecucao = 0;
                }
            }
            if (executando != 0) {
                executando--;
                tempoExecucao++;
                for (int i = 0; i < tempoEsperas.size() - 1; i++) {
                    int t = tempoEsperas.get(i);
                    t++;
                    tempoEsperas.set(i, t);
                }
            }
            tempoProcesso++;

            if(executando<=0 && tempoExecucoes.size() > 0 && tempoEsperas.size() > 0) {
                tempoRetornoTotal += tempoExecucoes.get(tempoExecucoes.size() - 1) + tempoEsperas.get(tempoExecucoes.size() - 1);
                tempoEsperaTotal += tempoEsperas.get(tempoExecucoes.size() - 1);
                tempoExecucoes.remove(tempoExecucoes.size() - 1);
                tempoEsperas.remove(tempoExecucoes.size());
            }
            if (executando > 0 && tempoExecucao == 2 || executando == 0 && tempoExecucao == 1) {
                fila.add(executando);
                executando = 0;

                if (tempoExecucoes.get(tempoExecucoes.size()-1) == 0){
                    tempoRespostas.set(primeiraExecucao, tempoExecucao);
                    primeiraExecucao++;
                }

                if(tempoExecucoes.size() != 0) {
                    int t = tempoExecucoes.get(tempoExecucoes.size()-1);
                    t += tempoExecucao;
                    tempoExecucoes.set(tempoExecucoes.size()-1, t);
                }
            }
            if (fila.size() == 0 && executando <= 0 && tempoProcesso > maiorTempoEntrada) {
                for (int tempo: tempoRespostas) {
                    tempoRespostaTotal += tempo;
                }
                break;
            }
        }
        return "RR"
                + (tempoRetornoTotal)/chegadas.size()
                + " "
                + (tempoRespostaTotal)/chegadas.size()
                + " "
                + tempoEsperaTotal/chegadas.size();
    }
}
