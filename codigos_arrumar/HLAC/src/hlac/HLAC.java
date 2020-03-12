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
public class HLAC implements Estado {

    @Override
    public String getDescricao() {
        return "Problema do homem, lobo, alface e carneiro\n\n";
    }

    final char homem, lobo,alface,carneiro; // preferir "immutable objects"
    final String op; // operacao que gerou o estado

    /**
     * construtor para o estado, recebe cada valor de atributo
     */
    public HLAC(char homem, char lobo,char alface,char carneiro,String o) {
        this.homem = homem;
        this.lobo= lobo;
        this.alface = alface;
        this.carneiro= carneiro;
        op = o;
    }

    /**
     * verifica se o estado e meta
     */
    @Override
    public boolean ehMeta() {
        return ((this.homem == 'd' && this.lobo == 'd' && this.alface == 'd' && this.carneiro == 'd'));
    }

    /**
     * Custo para geracao do estado
     */
    @Override
    public int custo() {
        return 1;
    }

    char ladoOposto(char margem){
        if(margem == 'd'){
            return 'e';
        }else{
            return 'd';
        }
    }
    
    /**
     * gera uma lista de sucessores do nodo.
     */
    @Override
    public List<Estado> sucessores() {
        List<Estado> visitados = new LinkedList<Estado>(); // a lista de sucessores

        levarNada(visitados);
        levarLobo(visitados);
        levarAlface(visitados);
        levarCarneiro(visitados);
        
        return visitados;
    }
    
    boolean ehValido(char homem){
        if((homem != this.lobo && this.lobo == this.carneiro) || (homem != this.carneiro && this.carneiro == this.alface)){
            return false;
        }
        return true;
    }
    
    private void levarNada(List<Estado> visitados) {
        //validar a restrição dos lados
        char margem = ladoOposto(homem);
        HLAC novo = new HLAC(margem,lobo,alface,carneiro,"Levando apenas o homem para o outro lado");
        if (!visitados.contains(novo)) visitados.add(novo);
    }
    
    private void levarLobo(List<Estado> visitados) {
        if(this.homem == this.lobo){
            char margem = ladoOposto(homem);
            HLAC novo = new HLAC(margem,margem,alface,carneiro,"Levando apenas o homem para o outro lado");
            if (!visitados.contains(novo)) visitados.add(novo);
        }
        
    }

    private void levarAlface(List<Estado> visitados) {
        Jarras novo = new Jarras(0, jarra2,"Esvaziar a Jarra1");
        if (!visitados.contains(novo)) visitados.add(novo);
    }
    
    private void levarCarneiro(List<Estado> visitados) {
        Jarras novo = new Jarras(jarra1, 0,"Esvaziar a Jarra2");
        if (!visitados.contains(novo)) visitados.add(novo);
    }

    
    /**
     * verifica se um estado e igual a outro (usado para poda)
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof HLAC) {
            HLAC e = (HLAC) o;
            return this.homem == e.homem && this.lobo == e.lobo && this.alface == e.alface && this.carneiro == e.carneiro;
        }
        return false;
    }

    /**
     * retorna o hashCode desse estado (usado para poda, conjunto de fechados)
     */
    @Override
    public int hashCode() {
        return ("" + homem + lobo+ alface + carneiro).hashCode();
    }

    public String toString() {
        return String.format("(%c,%c,%c,%c) - %s\n", homem, lobo , alface , carneiro , op);
    }

    public static void main(String[] a) {
        HLAC estadoInicial = new HLAC('e','e','e','e',"estado inicial");

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
