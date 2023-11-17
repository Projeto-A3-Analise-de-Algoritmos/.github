package projeto.a3.protótipo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProjetoA3Protótipo {
    
//Fase 1
    
    public static void main(String args[]) {
        String caminhoArquivo = "src/projeto/a3/protótipo/entrada.txt";
        processarArquivo(caminhoArquivo);
    }

    public static void processarArquivo(String arquivo) {
        List<String> grupos = new ArrayList<>();
        List<String> filas = new ArrayList<>();

        try {
            File file = new File(arquivo);
            Scanner leitor = new Scanner(file, "UTF-8");

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                String[] partes = linha.split(" ");
                String tipo = partes[0];

                switch (tipo) {
                    case "grupo:":
                        processarGrupo(partes, grupos);
                        break;
                    case "existe:":
                        processarExistencia(partes, grupos);
                        break;
                    case "conhece:":
                        processarConhecimento(partes, grupos);
                        break;
                    case "criaFila:":
                        processarCriaFila(partes, filas);
                        break;
                    case "desiste:":
                        processarDesiste(partes, filas);
                        break;
                    case "imprime:":
                        processarImprime(filas);
                        break;
                    case "atendeFila:":
                        processarAtendeFila(partes, filas);
                        break;
                }
            }

            leitor.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void processarGrupo(String[] partes, List<String> grupos) {
        StringBuilder grupoBuilder = new StringBuilder();
        for (int i = 1; i < partes.length; i++) {
            grupoBuilder.append(partes[i]).append(" ");
        }
        grupos.add(grupoBuilder.toString().trim());
    }

    private static void processarExistencia(String[] partes, List<String> grupos) {
        String pessoa = partes[1];
        boolean encontrou = false;
        for (String grupo : grupos) {
            if (grupo != null && grupo.contains(pessoa)) {
                System.out.println("[" + pessoa + "] existe!");
                encontrou = true;
                break;
            }
        }
        if (!encontrou) {
            System.out.println("[" + pessoa + "] NÃO existe!");
        }
    }

    private static void processarConhecimento(String[] partes, List<String> grupos) {
        String pessoa1 = partes[1];
        String pessoa2 = partes[2];
        boolean conhecem = false;
        for (String grupo : grupos) {
            if (grupo != null && grupo.contains(pessoa1) && grupo.contains(pessoa2)) {
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
    
//Fase 2
    
    private static void processarCriaFila(String[] partes, List<String> filas) {
        String nomeFila = partes[1];
        String novaFila = "#" + nomeFila;
        filas.add(novaFila);
    }

    private static void processarDesiste(String[] partes, List<String> filas) {
        for (int i = 1; i < partes.length; i++) {
            String pessoa = partes[i];
            for (int j = 0; j < filas.size(); j++) {
                String fila = filas.get(j);
                if (fila != null && fila.contains(pessoa)) {
                    filas.set(j, null);
                    System.out.println("[" + pessoa + "] desistiu e foi removido da fila");
                    break;
                }
            }
        }
    }

    private static void processarImprime(List<String> filas) {
        for (String fila : filas) {
            if (fila != null) {
                System.out.println(fila);
            }
        }
    }

    private static void processarAtendeFila(String[] partes, List<String> filas) {
        for (int i = 1; i < partes.length; i++) {
            String guiche = partes[i];
            for (String fila : filas) {
                if (fila != null && fila.startsWith("#" + guiche)) {
                    String[] pessoas = fila.split(" ");
                    if (pessoas.length > 1) {
                        String atendido = pessoas[1];
                        fila = fila.replaceFirst(atendido, "").trim();
                        filas.set(filas.indexOf(fila), fila);
                        break;
                    }
                }
            }
        }
    }
    
//Falta criar o método processarChegou() e testar pra ver se o código da Fase 2 funciona.
//Mudar o nome do projeto e o caminho do arquivo txt após mudar o nome do projeto.
//Opcional: modificar o nome dos métodos e das variáveis caso queira deixar o código mais fácil de se ler.
    
}