package ERE.SistemasOperacionais;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Escritor {

    private File file;

    public Escritor(String path) {
        this.file = new File(path);
    }

    public void escrever(ArrayList<String> saidas) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter escritor =  new FileWriter(file);
        BufferedWriter bEscritor =  new BufferedWriter(escritor);

        for (String saida: saidas) {
            bEscritor.write(saida);
            bEscritor.newLine();
        }

        bEscritor.close();
        escritor.close();

    }
}
