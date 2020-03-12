
import java.util.Collections;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pierr
 */
public class teste {
    public static void main(String[] args) {
        int dimensao = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a dimensão do puzzle"));
        int linha= 0,coluna=0;
        //geração da matriz
        int matriz[][] = new int[dimensao][dimensao];
        LinkedList vetor = new LinkedList();
        for (int i = 0; i < dimensao * dimensao; i++) {
                vetor.add(i);
        }
         //embaralhamento dos numeros

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
        StringBuffer estado = new StringBuffer();
        StringBuffer mat = new StringBuffer();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                
                estado.append(matriz[i][j]);
            }
            System.out.println("");
        }
        
        for (int i = 0; i < matriz.length * matriz.length; i++) {
            mat.append(i);
            
        }
        System.out.println(""+mat);
        System.out.println(""+estado);
        if(mat.toString().equals(estado.toString())){
            System.out.println("Verdade");
        }else{
            System.out.println("Falso");
        }
    }
}
