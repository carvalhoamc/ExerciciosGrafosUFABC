/* 
2016mai19

Implementacao do exercicio 2 do EP0.

Le do teclado um grafo ponderado, o tamanho de uma sequencia de vertices 
e a sequencia dos vertices.
Na saida, imprime o comprimento se a sequencia é um caminho no grafo.
Caso contrario, avisa o usuario que a sequencia nao é um caminho.

--

Exemplo:                    (0 1 2 3 4 -> indices internos no grafo)
Dado o grafo com 5 vertices: s,t,x,y,z
e 16 arcos ponderados: 0 1 2, 0 3 5, 0 4 7, 1 0 2, 1 2 4, 1 3 8, 2 1 4, 2 3 3, 2 4 8, 3 0 5, 3 1 8, 3 2 3, 3 4 1, 4 0 7, 4 2 8, 4 3 1.
                      (s t 2, s y 5, s z 7, t s 2, t x 4, t y 8, x t 4, x y 3, x z 8, y s 5, y t 8, y x 3, y z 1, z s 7, z x 8, z y 1.
Verificar se a sequencia (0,1,2,3,4,2) é caminho.
Caso afirmativo, imprimir o comprimento do caminho.

Entrada:
5
s
t
x
y
z
16
0
1
2
0
3
5
0
4
7
1
2
4
1
3
8
1
0
2
2
4
8
2
1
4
2
3
3
3
1
8
3
2
3
3
4
1
3
0
5
4
0
7
4
2
8
4
3
1
6
0
1
2
3
4
2

Saida:
A sequencia é o caminho (s,t,x,y,z,x) de comprimento 18.

--

Exemplo:                    (0 1 2 3 4 -> indices internos no grafo)
Dado o grafo com 5 vertices: s,t,x,y,z
e 16 arcos ponderados: 0 1 2, 0 3 5, 0 4 7, 1 0 2, 1 2 4, 1 3 8, 2 1 4, 2 3 3, 2 4 8, 3 0 5, 3 1 8, 3 2 3, 3 4 1, 4 0 7, 4 2 8, 4 3 1.
                      (s t 2, s y 5, s z 7, t s 2, t x 4, t y 8, x t 4, x y 3, x z 8, y s 5, y t 8, y x 3, y z 1, z s 7, z x 8, z y 1.
Verificar se a sequencia (0,3,1,4) é caminho.
Caso afirmativo, imprimir o comprimento do caminho.

Entrada:
5
s
t
x
y
z
16
0
1
2
0
3
5
0
4
7
1
2
4
1
3
8
1
0
2
2
4
8
2
1
4
2
3
3
3
1
8
3
2
3
3
4
1
3
0
5
4
0
7
4
2
8
4
3
1
4
0
3
1
4

Saida:
A sequencia nao é um caminho.

*/

package exemplos;

import ep0.*;
import java.util.*;

public class Ex2 {

    public static void main( String args[] ) {
        GrafoPonderado G = new GrafoPonderado();
        // Leitura do grafo via teclado.
        G.leDoTeclado();

        // Leitura da sequencia de vertices.
        int tamanhoDaSequencia = Keyboard.readInt();
        int[] sequencia = new int[tamanhoDaSequencia];
        for (int k = 0; k < tamanhoDaSequencia; k++)
            sequencia[k] = Keyboard.readInt();

        // Verifica se a sequencia é um caminho no grafo.
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
        // Se a sequencia é um caminho, imprime a sequencia de vertices
        // e o comprimento do caminho.
        if (sequenciaÉCaminho) {
            int comprimento = 0;
            Vertice[]  verts = G.getVertices();
            i = sequencia[0];
            System.out.print("A sequencia é o caminho ("+verts[i].getNome());
            for (int k = 1; k < tamanhoDaSequencia; k++) {
                int j = sequencia[k];
                System.out.print(","+verts[j].getNome());
                comprimento += madj[i][j].getPeso();
                i = j;
            }
            System.out.println(") de comprimento "+comprimento+".");
        }
        // Se nao é caminho...
        else
            System.out.println("A sequencia nao é um caminho.");
    }
}
