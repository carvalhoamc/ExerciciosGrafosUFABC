/*

2016mai19

Representacao de uma fila de prioridade simplificada.
Esta implementacao é baseada em um vetor.
Neste caso, a fila é um vetor de tamanho fixo.
O construtor recebe um vetor de elementos que é usado para inicializar
a fila de prioridade.
A remocao de um elemento da fila resultará em uma posicao null no vetor.
Os elementos sao vertices de um grafo, com atributo "d" usado como chave
(classe Vertice).

Operacoes com a fila de prioridades:
.FilaDePrioridade(Vertice V[]): o construtor inicializa a fila com
                                         os elementos de um vetor de vertices.
.filaVazia(): devolve verdadeiro se fila vazia, falso caso contrario.
.extraiMinimo(): devolve um vértice com a chave mínima.
.estaNaFila(x): devolve verdadeiro se o elemento x está na fila, 
                falso caso contrario.
*/

package ep0;

public class FilaDePrioridade {
    // Vetor para os elementos da fila de prioridade.
    protected Vertice[] fila; 

    // Construtor recebe um vetor de elementos a serem inseridos
    // na fila de prioridade.
    public FilaDePrioridade(Vertice Vetor[]) { 
        fila = new Vertice[Vetor.length];
        for (int i = 0; i < Vetor.length; i++)
            fila[i] = Vetor[i];
    }
    
    // Devolve verdadeiro se fila vazia.
    // Devolve falso, caso contrario.
    public boolean filaVazia() {
        for (int i = 0; i < fila.length; i++)
            if (fila[i] != null)
                return false;
        // Se todas as posicoes do vetor sao null, entao a fila está vazia.
        return true;  
    }

    // Remove e devolve o elemento com chave minima.
    public Vertice extraiMinimo() {
        int minChave = Constantes.INFINITO, min = -1;
        // Inicializa o minimo como sendo o 1o elemento encontrado.
        int i;
        for (i = 0; i < fila.length; i++)
            if (fila[i] != null) {
                // Podemos acessar diretamente o atributo "d" do vertice.
                minChave = fila[i].chave; 
                min = i;
                break;
            }
        // Continua a procurar pelo minimo no restante do vetor.
        for (; i < fila.length; i++)
            if (fila[i] != null && fila[i].chave < minChave) {
                minChave = fila[i].chave;
                min = i;
            }
        // Assume que a fila nao está vazia, ou seja, que existe um minimo.
        Vertice v = fila[min];
        fila[min] = null;  // Remove o elemento da fila.
        return v;
    }

    // Devolve verdadeiro se o elemento está na fila.
    // Devolve falso, caso contrario.
    public boolean estaNaFila(Vertice x) {
        for (int i = 0; i < fila.length; i++)
            if (fila[i] != null && fila[i] == x)
                return true;
        return false;
    }

}
