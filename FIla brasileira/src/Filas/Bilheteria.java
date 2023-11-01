package Filas;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import Grupos.Pessoa;



public class Bilheteria {

	public static void main(String[] args) {
		String nomeArquivo = "/home/hiago/Atividade Github/FIla brasileira/src/Filas/grupos.txt"; // Nome do arquivo de texto
        Map<String, List<Pessoa>> grupos = new HashMap<>();
        Set<Pessoa> pessoasUnicas = new HashSet<>();
 
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.toLowerCase().startsWith("grupo:")) {
                    String[] nomes = linha.substring(7).trim().split("\s+");
                    Set<String> gruposDaPessoa = new HashSet<>();
                    for (String nome : nomes) {
                        gruposDaPessoa.add("grupo" + (grupos.size() + 1));
                        Pessoa pessoa = new Pessoa(nome, gruposDaPessoa);
                        pessoasUnicas.add(pessoa);
                        grupos.computeIfAbsent("grupo" + (grupos.size() + 1), k -> new ArrayList<>()).add(pessoa);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        // Exemplo de como acessar a quarta lista sem elementos repetidos
        System.out.println("Quarta lista sem elementos repetidos:");
        for (Pessoa pessoa : pessoasUnicas) {
            System.out.println("Nome: " + pessoa.getNome() + ", Grupos: " + pessoa.getGrupos());
        }
        
      
    }
}
	
