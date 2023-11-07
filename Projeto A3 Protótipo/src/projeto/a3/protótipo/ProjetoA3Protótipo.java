package projeto.a3.protótipo;

import java.io.File;
import java.util.Scanner;

public class ProjetoA3Protótipo {

    public static void main(String args[]) {
        String caminhoArquivo = "src/projeto/a3/protótipo/entrada.txt";
        verificarExistenciaEConhecimento(caminhoArquivo);
    }

    public static void verificarExistenciaEConhecimento(String arquivo) {
        String[] grupos = new String[100]; // Assumindo no máximo 100 pessoas nos grupos
        int numGrupos = 0;

        try {
            File file = new File(arquivo);
            Scanner leitor = new Scanner(file, "UTF-8");

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                String[] partes = linha.split(" ");
                String tipo = partes[0];

                if (tipo.equals("grupo:")) {
                    StringBuilder grupoBuilder = new StringBuilder();
                    for (int i = 1; i < partes.length; i++) {
                        grupoBuilder.append(partes[i]).append(" ");
                    }
                    grupos[numGrupos++] = grupoBuilder.toString().trim();
                } else if (tipo.equals("existe:")) {
                    String pessoa = partes[1];
                    boolean encontrou = false;
                    for (int i = 0; i < numGrupos; i++) {
                        if (grupos[i].contains(pessoa)) {
                            System.out.println("[" + pessoa + "] existe!");
                            encontrou = true;
                            break;
                        }
                    }
                    if (!encontrou) {
                        System.out.println("[" + pessoa + "] NÃO existe!");
                    }
                } else if (tipo.equals("conhece:")) {
                    String pessoa1 = partes[1];
                    String pessoa2 = partes[2];
                    boolean conhecem = false;
                    for (int i = 0; i < numGrupos; i++) {
                        if (grupos[i].contains(pessoa1) && grupos[i].contains(pessoa2)) {
                            conhecem = true;
                            break;
                        }
                    }
                    if (conhecem) {
                        System.out.println("[" + pessoa1 + "] conhece [" + pessoa2 + "]");
                    } else {
                        System.out.println("[" + pessoa1 + "] NÃO conhece [" + pessoa2 + "]");
                    }
                }
            }
            leitor.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}