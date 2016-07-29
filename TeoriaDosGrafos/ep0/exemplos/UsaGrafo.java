/* 

2016mai19

Este programa le um grafo da entrada padrão e exibe as listas de adjacencia, 
a matriz de adjacencia, o total de vertices e o total de arcos.

*/

package ep0.exemplos;

import ep0.*;
import java.util.*;

public class UsaGrafo {

    public static void main( String args[] ) {
        // Cria uma nova instancia da classe Grafo.
        Grafo G = new Grafo();
        // Lê os dados do grafo pelo teclado.
        G.leDoTeclado();
        // Imprime os dados do grafo na tela.
        G.imprimeNaTela();
    }
}
