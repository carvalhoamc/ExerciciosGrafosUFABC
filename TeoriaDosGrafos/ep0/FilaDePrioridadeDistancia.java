/*

2016jun23

Sobrecarrega o extrai minimo para o atributo "d", usado no Dijkstra (EP4).

*/

package ep0;

public class FilaDePrioridadeDistancia extends FilaDePrioridade {

    // Construtor recebe um vetor de elementos a serem inseridos
    // na fila de prioridade.
    public FilaDePrioridadeDistancia(Vertice Vetor[]) { 
        super(Vetor);
    }
    // Remove e devolve o elemento com atributo "d" minimo.
    public Vertice extraiMinimo() {
        int minChave = Constantes.INFINITO, min = -1;
        // Inicializa o minimo como sendo o 1o elemento encontrado.
        int i;
        for (i = 0; i < fila.length; i++)
            if (fila[i] != null) {
                // Podemos acessar diretamente o atributo "d" do vertice.
                minChave = fila[i].d; 
                min = i;
                break;
            }
        // Continua a procurar pelo minimo no restante do vetor.
        for (; i < fila.length; i++)
            if (fila[i] != null && fila[i].d < minChave) {
                minChave = fila[i].d;
                min = i;
            }
        // Assume que a fila nao está vazia, ou seja, que existe um minimo.
        Vertice v = fila[min];
        fila[min] = null;  // Remove o elemento da fila.
        return v;
    }

}
