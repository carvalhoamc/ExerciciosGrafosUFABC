/* 
2016mai19

Implementacao do exercicio 1 do EP0.

Le um grafo da entrada padrão, o tamanho de uma sequencia de vertices 
e a sequencia dos vertices.
Na saida, imprime se a sequencia é ou nao um caminho no grafo.

--

Exemplo:                    (0 1 2 3 4 5 6 7 -> indices internos no grafo)
Dado o grafo com 8 vertices: r,s,t,u,v,w,x,y
e 20 arcos: 0 1, 0 4, 1 0, 1 5, 2 3, 2 5, 2 6, 3 2, 3 6, 3 7, 4 0, 5 1, 5 2, 5 6, 6 2, 6 3, 6 5, 6 7, 7 3, 7 6
           (r s, r v, s r, s w, t u, t w, t x, u t, u x, u y, v r, w s, w t, w x, x t, x u, x w, x y, y u, y x
Verificar se a sequencia (4,0,1,5,6,7) é caminho.

Entrada:
8
r
s
t
u
v
w
x
y
20
0
1
0
4
1
0
1
5
2
3
2
5
2
6
3
2
3
6
3
7
4
0
5
1
5
2
5
6
6
2
6
3
6
5
6
7
7
3
7
6
6
4
0
1
5
6
7

Saida:
A sequencia é o caminho (v,r,s,w,x,y).

--

Exemplo:                    (0 1 2 3 4 5 6 7 -> indices internos no grafo)
Dado o grafo com 8 vertices: r,s,t,u,v,w,x,y
e 20 arcos: 0 1, 0 4, 1 0, 1 5, 2 3, 2 5, 2 6, 3 2, 3 6, 3 7, 4 0, 5 1, 5 2, 5 6, 6 2, 6 3, 6 5, 6 7, 7 3, 7 6
           (r s, r v, s r, s w, t u, t w, t x, u t, u x, u y, v r, w s, w t, w x, x t, x u, x w, x y, y u, y x
Verificar se a sequencia (4,0,1,2) é caminho.

Entrada:
8
r
s
t
u
v
w
x
y
20
0
1
0
4
1
0
1
5
2
3
2
5
2
6
3
2
3
6
3
7
4
0
5
1
5
2
5
6
6
2
6
3
6
5
6
7
7
3
7
6
4
4
0
1
2

Saida:
A sequencia nao é um caminho.

*/

package ep0.exemplos;

import ep0.*;
import java.util.*;

public class Ex1 {

    public static void main( String args[] ) {
        Grafo G = new Grafo();
        // Leitura do grafo via teclado.
        G.leDoTeclado();

        // Leitura da sequencia de vertices.
        int tamanhoDaSequencia = Keyboard.readInt();
        int[] sequencia = new int[tamanhoDaSequencia];
        for (int k = 0; k < tamanhoDaSequencia; k++)
            sequencia[k] = Keyboard.readInt();
        
        // verifica se a sequencia é um caminho no grafo
        boolean sequenciaÉCaminho = true;
        Arco[][] madj = G.getMatrizDeAdjacencia();
        int i = sequencia[0];
        for (int k = 1; k < tamanhoDaSequencia; k++) {
            int j = sequencia[k];
            // Se algum par consecutivo nao estiver conectado,
            // entao o caminho é invalido.
            if (madj[i][j] == null) {
                sequenciaÉCaminho = false;
                break;
            }
            i = j;
        }
        // Se a sequencia é um caminho, imprime a sequencia de vertices.
        if (sequenciaÉCaminho) {
            Vertice[]  verts = G.getVertices();
            i = sequencia[0];
            System.out.print("A sequencia é o caminho ("+verts[i].getNome());
            for (int k = 1; k < tamanhoDaSequencia; k++) {
                i = sequencia[k];
                System.out.print(","+verts[i].getNome());
            }
            System.out.println(").");
        }
        // Se nao é caminho...
        else
            System.out.println("A sequencia nao é um caminho.");
    }
}
