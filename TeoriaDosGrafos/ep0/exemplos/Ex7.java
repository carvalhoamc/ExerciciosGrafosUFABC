/* 
2016mai19

Exercicio 7 do EP0 para calcular uma rede residual.
Uma rede de fluxo é um grafo ponderado, onde os pesos dos arcos representam
as capacidades de fluxo dos arcos.
Neste caso, podemos calcular o fluxo maximo entre dois vertices, uma origem 
e um destino.
No problema do fluxo maximo, cada arco possui um par de valores: 
fluxo e capacidade de fluxo.
Uma rede residual é formada pelas capacidades residuais, calculadas da 
seguinte maneira.
Dado um arco uv no grafo original representando a rede de fluxo:
.A capacidade residual de "ida" é dada por c(uv) - f(uv),
 ou seja, capacidade menos o fluxo no arco uv.
.A capacidade residual de "volta" é dada pelo proprio fluxo f(vu), 
 mas no sentido contrário, inserindo um novo arco vu na rede residual.

Este programa le do teclado um grafo ponderado, e uma sequencia de inteiros 
representando os fluxos em cada arco, na ordem de insercao dos arcos no grafo 
ponderado.
Na saida, imprime as informacoes da rede residual calculada.

--

Exemplo (do slide da aula):

Entrada:
6
s
v1
v2
v3
v4
t
9
0 
1 
16
0 
2 
13
1 
3 
12
2 
1 
4
2 
4 
14
3 
2 
9
3 
5 
20
4 
3 
7
4 
5 
4
11
8
12
1
11
4
15
7
4


Saida:
Rede: fluxo/capacidade
s: v1(11/16), v2(8/13),
v1: v3(12/12),
v2: v1(1/4), v4(11/14),
v3: v2(4/9), t(15/20),
v4: v3(7/7), t(4/4),
t:
. 16 13 . . .
. . . 12 . .
. 4 . . 14 .
. . 9 . . 20
. . . 7 . 4
. . . . . .
Total de vertices: 6
Total de arcos: 9

Rede residual:
s: v1(5), v2(5),
v1: s(11), v2(1),
v2: s(8), v1(3), v4(3), v3(4),
v3: v1(12), v2(5), t(5), v4(7),
v4: v2(11),
t: v3(15), v4(4),
. 5 5 . . .
11 . 1 . . .
8 3 . 4 3 .
. 12 5 . 7 5
. . 11 . . .
. . . 15 4 .
Total de vertices: 6
Total de arcos: 0


*/

package ep0.exemplos;

import ep0.*;
import java.util.*;

public class Ex7 {

    public static void main( String args[] ) {
        GrafoPonderado G = new GrafoPonderado();
        // Leitura do grafo via teclado.
        G.leDoTeclado();
        
        // Leitura dos fluxo dos arcos.
        int m = G.getTotalDeArcos();
        int[] f = new int[m];
        int k;
        for (k = 0; k < m; k++)
            f[k] = Keyboard.readInt();
        Arco[][] matrizAdjacencia = (Arco[][]) G.getMatrizDeAdjacencia();
        int n = G.getTotalDeVertices();
        k = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (matrizAdjacencia[i][j] != null) {
                    Arco ap = matrizAdjacencia[i][j];
                    ap.f = f[k]; // Podemos acessar o atributo de fluxo diretamente no arco.
                    k++;
                }
                
        // Imprime a rede fluxo/capacidade.
        System.out.println("\nRede: fluxo/capacidade");
        G.imprimeNaTelaFluxoCapacidade();
        
        // Calcula a rede residual e a imprime na tela.
        GrafoPonderado R = G.getRedeResidual();
        System.out.println("\nRede residual:");
        R.imprimeNaTela();
    }
}
