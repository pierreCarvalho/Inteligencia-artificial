
import busca.BuscaLargura;
import busca.BuscaProfundidade;
import busca.Estado;
import busca.MostraStatusConsole;
import busca.Nodo;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import javax.swing.JOptionPane;
// Pierre e João victor
public class Principal implements Estado{
    
    @Override
    public String getDescricao() {
        return "Problema das Jarras\n\n";
    }

    final int matriz[][]; // preferir "immutable objects"
    final int linha,coluna;
    final String op; // operacao que gerou o estado

    /**
     * construtor para o estado, recebe cada valor de atributo
     */
    public Principal(int matriz[][],int l,int c, String o) {
        this.matriz = matriz;
        this.linha = l;
        this.coluna = c;
        op = o;
    }

    /**
     * verifica se o estado e meta
     */
    @Override
    public boolean ehMeta() {
        /*
        LinkedList vetor = new LinkedList();
        for (int i = 0; i < matriz.length * matriz.length; i++) {
            vetor.add(i);
        }
        */
        StringBuffer estadoAtual = new StringBuffer();
        StringBuffer meta = new StringBuffer();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                estadoAtual.append(matriz[i][j]);
            }
            
        }
        for (int i = 0; i < matriz.length * matriz.length; i++) {
            meta.append(i);
            
        }
        if(estadoAtual.toString().equals(meta.toString())){
            return true;
        }else{
            return false;
        }
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

        moverCima(visitados);
        moverBaixo(visitados);
        moverDireita(visitados);
        moverEsquerda(visitados);
        
        return visitados;
    }
/*    
    private boolean ehValido(Stack origem, Stack destino){
        if( origem.isEmpty() || !destino.isEmpty() || (int) origem.peek() > (int) destino.peek() ){
            return false;
        }
        return true;
    }
*/
    
    private void moverCima(List<Estado> visitados) {
        int matrizClone[][] = new int[matriz.length][matriz.length];
        int l = linha;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matrizClone[i][j] = matriz[i][j];
                
            }
            
        }        
        //testar limite
        if(linha != 0){
            int aux = matrizClone[linha][coluna];
            matrizClone[linha][coluna] = matrizClone[linha-1][coluna];
            matrizClone[linha-1][coluna] = aux;
            l = l - 1;
            Principal novo = new Principal(matrizClone,l,coluna,"Mover 0 para cima");
            if (!visitados.contains(novo)) visitados.add(novo);
        }
        return;
    }
    
    private void moverBaixo(List<Estado> visitados) {
        int matrizClone[][] = new int[matriz.length][matriz.length];
        int l = linha;
        //clone
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matrizClone[i][j] = matriz[i][j];
                
            }
            
        }        
        //testar limite
        if(linha < matriz.length - 1){
            int aux = matrizClone[linha][coluna];
            matrizClone[linha][coluna] = matrizClone[linha+1][coluna];
            matrizClone[linha+1][coluna] = aux;
            l = l + 1;
            Principal novo = new Principal(matrizClone,l,coluna,"Mover 0 para baixo");
            if (!visitados.contains(novo)) visitados.add(novo);
        }
        return;
    }
    
    private void moverDireita(List<Estado> visitados) {
        int matrizClone[][] = new int[matriz.length][matriz.length];
        int c = coluna;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matrizClone[i][j] = matriz[i][j];
                
            }
            
        }        
        //chamar metodo ehValido
        if(coluna < matriz.length - 1 ){
            int aux = matrizClone[linha][coluna];
            matrizClone[linha][coluna] = matrizClone[linha][coluna+1];
            matrizClone[linha][coluna+1] = aux;
            c = c + 1;
            Principal novo = new Principal(matrizClone,linha,c,"Mover 0 para direita");
            if (!visitados.contains(novo)) visitados.add(novo);
        }
        return;
    }
    private void moverEsquerda(List<Estado> visitados) {
        int matrizClone[][] = new int[matriz.length][matriz.length];
        int c = coluna;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matrizClone[i][j] = matriz[i][j];
                
            }
            
        }        
        //chamar metodo ehValido
        if(coluna != 0){
            int aux = matrizClone[linha][coluna];
            matrizClone[linha][coluna] = matrizClone[linha][coluna-1];
            matrizClone[linha][coluna-1] = aux;
            c = c - 1;
            Principal novo = new Principal(matrizClone,linha,c,"Mover 0 para esquerda");
            if (!visitados.contains(novo)) visitados.add(novo);
        }
        return;
    }
    
    
    /**
     * verifica se um estado e igual a outro (usado para poda)
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Principal) {
            Principal e = (Principal) o;
            return matriz.equals(e.matriz);
        }
        return false;
    }

    /**
     * retorna o hashCode desse estado (usado para poda, conjunto de fechados)
     */
    @Override
    public int hashCode() {
        return (""+matriz).hashCode();
    }
    public String toString() {
        StringBuffer estadoAtual = new StringBuffer();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                estadoAtual.append(matriz[i][j]);
            }
            
        }
        return ""+estadoAtual+ " -> "+op+"\n";
    }
    
    
    public static void main(String[] args) {
        
        
        int dimensao = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a dimensão do puzzle"));
        int linha= 0,coluna=0;
        //geração da matriz
        int matriz[][] = new int[dimensao][dimensao];
        LinkedList vetor = new LinkedList();
        for (int i = 0; i < dimensao * dimensao; i++) {
                vetor.add(i);
        }
        Collections.shuffle(vetor); //embaralhamento dos numeros

        int numeros = 0;
        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                matriz[i][j] = (int) vetor.get(numeros);
                if(matriz[i][j] == 0){
                    linha = i;
                    coluna = j;
                    
                }
                numeros++;
            }
        }
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(matriz[i][j]+" \t ");
            }
            System.out.println("");
        }
            
        Principal estadoInicial = new Principal(matriz,linha,coluna,"estado inicial");
        // chama busca em largura
        System.out.println("busca em ...");
        Nodo n = new BuscaLargura(new MostraStatusConsole()).busca(estadoInicial);
        if (n == null) {
            System.out.println("sem solucao!");
        } else {
            System.out.println("solucao:\n" + n.montaCaminho() + "\n\n");
        }
        

    System.exit(0);
    }
}