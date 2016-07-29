/* 

2016mai19

Este programa le um grafo ponderado da entrada padr�o e exibe as listas de 
adjacencia, a matriz de adjacencia, o total de vertices e o total de arcos.

*/

package exemplos;

import ep0.*;
import java.util.*;

public class UsaGrafoPonderado {

    public static void main( String args[] ) {
        // Cria uma nova instancia da classe GrafoPonderado.
        GrafoPonderado G = new GrafoPonderado();
        // L� os dados do grafo pelo teclado.
        G.leDoTeclado();
        // Imprime os dados do grafo na tela.
        G.imprimeNaTela();
    }
}
