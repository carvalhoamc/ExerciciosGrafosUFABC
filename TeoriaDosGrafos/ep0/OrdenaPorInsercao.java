
/*

2016mai19

Esta classe implementa o algoritmo de ordenacao por insercao.
Ela ordena um vetor de valores inteiros.
Após a ordenacao, obtém a nova ordem dos indices do vetor original.
Desta forma, podemos usar para ordenar os índices dos vertices de um grafo.

(No EP3, precisaremos visitar os vértices em ordem decrescente
de finalização da primeira busca em profundidade.)

Operacoes:
.OrdenaPorInsercao(int[] val): construtor recebe um vetor de inteiros a serem
                               ordenados.
.getValoresOrdenados(): devolve um vetor dos valores ordenados.
.getIndicesOrdenados(): devolve um vetor dos indices ordenados.

*/

package ep0;

public class OrdenaPorInsercao {
    // Declaracao das variaveis: valores a serem ordenados
    // e os indices do vetor original após a ordenacao.
    private int[] A;
    private int[] indicesOrdenados;

    // Construtor e execucao do algoritmo de ordenacao.
    public OrdenaPorInsercao(int[] val) {
        // Aloca espaco para as variaveis.
        int total = val.length;
        A = new int[total];
        indicesOrdenados = new int[total];
        for (int i = 0; i < total; i++) {
            A[i] = val[i];
            indicesOrdenados[i] = i;
        }
        // Executa a ordenacao por insercao: CLRS p 18.
        for (int j = 1; j < total; j++) {
            int chave = A[j];
            // Insere A[j] na sequencia ordenada A[0..j-1].
            int i = j - 1;
            while(i >= 0 && A[i] > chave) {
                A[i+1] = A[i];
                indicesOrdenados[i+1] = indicesOrdenados[i];
                i--;
            }
            A[i+1] = chave;
            indicesOrdenados[i+1] = j;
        }
    }
    
    // Devolve o vetor dos valores ordenados.
    public int[] getValoresOrdenados() {
        return A;
    }

    // Devolve o vetor dos indices ordenados.
    public int[] getIndicesOrdenados() {
        return indicesOrdenados;
    }
}
