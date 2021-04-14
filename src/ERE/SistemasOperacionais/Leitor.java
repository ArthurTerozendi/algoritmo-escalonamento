package ERE.SistemasOperacionais;

import java.io.*;
import java.util.ArrayList;

public class Leitor {
    private File file;

    public Leitor(String path) {
        this.file = new File(path);
    }

    public ArrayList<String> ler() throws IOException {
        ArrayList<String> entrada = new ArrayList<>();

        FileReader leitor = new FileReader(file);
        BufferedReader bLeitor =  new BufferedReader(leitor);

        while(bLeitor.ready()) {
            entrada.add(bLeitor.readLine());
        }
        
        bLeitor.close();
        leitor.close();

        return entrada;
    }

}
