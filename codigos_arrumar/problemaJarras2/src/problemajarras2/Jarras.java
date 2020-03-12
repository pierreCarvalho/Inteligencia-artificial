
import java.util.LinkedList;
import java.util.List;

import busca.BuscaLargura;
import busca.BuscaProfundidade;
import busca.Estado;
import busca.MostraStatusConsole;
import busca.Nodo;

/**
 * Representa um estado do mundo: em que lado estao o homem, o lobo, o alface e
 * o carneiro
 */
public class Jarras implements Estado {

    @Override
    public String getDescricao() {
        return "Problema das Jarras\n\n";
    }

    final int jarra1, jarra2; // preferir "immutable objects"
    final String op; // operacao que gerou o estado

    /**
     * construtor para o estado, recebe cada valor de atributo
     */
    public Jarras(int j1, int j2, String o) {
        this.jarra1 = j1;
        this.jarra2 = j2;
        op = o;
    }

    /**
     * verifica se o estado e meta
     */
    @Override
    public boolean ehMeta() {
        return ((jarra1 == 2 && jarra2 == 0) || (jarra1 == 0 && jarra2 == 2));
    }

    /**
     * Custo para geracao do estado
     */
    @Override
    public int custo() {
        return 1;
    }

    /**
     * gera uma lista de sucessores do nodo.
     */
    @Override
    public List<Estado> sucessores() {
        List<Estado> visitados = new LinkedList<Estado>(); // a lista de sucessores

        encherJarra1(visitados);
        encherJarra2(visitados);
        esvaziarJarra1(visitados);
        esvaziarJarra2(visitados);
        despejarJ1EmJ2(visitados);
        despejarJ2EmJ1(visitados);

        return visitados;
    }

    private void encherJarra2(List<Estado> visitados) {
        Jarras novo = new Jarras(jarra1, 3,"Encher a Jarra2");
        if (!visitados.contains(novo)) visitados.add(novo);
    }
    
    private void encherJarra1(List<Estado> visitados) {
        Jarras novo = new Jarras(4, jarra2,"Encher a Jarra1");
        if (!visitados.contains(novo)) visitados.add(novo);
    }

    private void esvaziarJarra1(List<Estado> visitados) {
        Jarras novo = new Jarras(0, jarra2,"Esvaziar a Jarra1");
        if (!visitados.contains(novo)) visitados.add(novo);
    }
    
    private void esvaziarJarra2(List<Estado> visitados) {
        Jarras novo = new Jarras(jarra1, 0,"Esvaziar a Jarra2");
        if (!visitados.contains(novo)) visitados.add(novo);
    }
    
    private void despejarJ1EmJ2(List<Estado> visitados) {
        int j1 = jarra1; //lembrar que devemos clonar
        int j2 = jarra2; //lembrar que devemos clonar
        
        if (j1 > (3 - j2)){
            j1 = j1 - (3 - j2);
            j2 = 3;
        } else {
            j2 = j2 + j1;
            j1 = 0;
        }
        Jarras novo = new Jarras(j1, j2,"Despejar Jarra1 em Jarra2");
        if (!visitados.contains(novo)) visitados.add(novo);
    }
    
    private void despejarJ2EmJ1(List<Estado> visitados) {
        int j1 = jarra1; //lembrar que devemos clonar
        int j2 = jarra2; //lembrar que devemos clonar
        
        if (j2 > (4 - j1)){
            j2 = j2 - (4 - j1);
            j1 = 4;
        } else {
            j1 = j1 + j2;
            j2 = 0;
        }
        Jarras novo = new Jarras(j1, j2,"Despejar Jarra2 em Jarra1");
        if (!visitados.contains(novo)) visitados.add(novo);
    }
    
    
    /**
     * verifica se um estado e igual a outro (usado para poda)
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Jarras) {
            Jarras e = (Jarras) o;
            return jarra1 == e.jarra1 && jarra2 == e.jarra2;
        }
        return false;
    }

    /**
     * retorna o hashCode desse estado (usado para poda, conjunto de fechados)
     */
    @Override
    public int hashCode() {
        return ("" + jarra1 + jarra2).hashCode();
    }

    public String toString() {
        return String.format("(%d,%d) - %s\n", jarra1, jarra2, op);
    }

    public static void main(String[] a) {
        Jarras estadoInicial = new Jarras(0,0, "estado inicial");

        // chama busca em largura
        System.out.println("busca em ...");
        Nodo n = new BuscaLargura(new MostraStatusConsole()).busca(estadoInicial);
        if (n == null) {
            System.out.println("sem solucao!");
        } else {
            System.out.println("solucao:\n" + n.montaCaminho() + "\n\n");
        }
    }
}