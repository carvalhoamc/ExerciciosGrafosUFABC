
/*

2016jun23

Esta classe ilustra o uso da classe Fila.

*/

package exemplos;

import ep0.*;

public class UsaFila {

    public static void main( String args[] ) {
        Grafo k33 = Grafo.getK33();
        k33.imprimeNaTela();
        
        Fila q = new Fila();
        Vertice[] vertices = k33.getVertices();
        int n = k33.getTotalDeVertices();
        for (int i = n-1; i >= 0; i--) {
            Vertice v = vertices[i];
            q.insereNaFila(v);
        }
        System.out.println("Ordem de chegada na fila: ");
        while (!q.filaVazia()) {
            Vertice u = (Vertice)q.removeDaFila(); // Cast necessario.
            System.out.print(u.getNome()+" ");
        }
        System.out.println();
    }
}
