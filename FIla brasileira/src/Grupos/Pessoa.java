package Grupos;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Pessoa {
    private String nome;
    private Set<String> grupos;

    public Pessoa(String nome, Set<String> grupos) {
        this.nome = nome;
        this.grupos = grupos;
    }

    public String getNome() {
        return nome;
    }

    public Set<String> getGrupos() {
        return grupos;
    }
}