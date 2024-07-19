package aplicacao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ModelagemFile {

    public static <T> void serializar(String caminhoFile, ArrayList<T> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoFile))) {
            oos.writeObject(lista);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado na serialização: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro de I/O na serialização: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> desserializar(String caminhoFile) {
        ArrayList<T> listaRetorno = new ArrayList<>();
        File file = new File(caminhoFile);
        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoFile))) {
                listaRetorno = (ArrayList<T>) ois.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado na desserialização: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Erro de I/O na desserialização: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println("Exceção de Classe não encontrada na desserialização: " + e.getMessage());
            }
        } else {
            System.out.println("Arquivo não encontrado ou está vazio.");
            return null;
            /*if (!file.exists()) {
            	criarArquivo(caminhoFile);
            }*/
        }
        return listaRetorno;
    }
}