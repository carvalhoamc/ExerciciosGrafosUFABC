
/*

2016jun23

Esta classe ilustra o uso da classe Fila.

*/

package exemplos;

import ep0.*;

public class UsaFilaDePrioridadeDistancia {

    public static void main( String args[] ) {
        Grafo k33 = Grafo.getK33();
        k33.imprimeNaTelaMatriz();

        
        Vertice[] vertices = k33.getVertices();
        FilaDePrioridadeDistancia q = new FilaDePrioridadeDistancia(vertices);
        int n = k33.getTotalDeVertices();
        for (int i = 0; i < n; i++) {
            Vertice v = vertices[i];
            v.d = n-i;
        }
        System.out.println("Ordem de prioridade na fila: ");
        while (!q.filaVazia()) {
            Vertice u = (Vertice)q.extraiMinimo(); // Cast necessario.
            System.out.print(u.getNome()+"(d="+u.d+"), ");
        }
        System.out.println();
    }
}
