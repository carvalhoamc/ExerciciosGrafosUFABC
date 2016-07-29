/*

2016jun23

Representacao de um arco:
- possui duas extremidades, um vertice origem e um vertice destino;
- como atributos adicionais, pode possuir um peso e um valor de fluxo.

No caso de grafos ponderados com pesos nas arestas, assumimos pesos >= 0.
Se não existir um dado arco, assumimos que seu peso vale zero.

*/

package ep0;

public class Arco {
    private Vertice origem, destino; // As duas extremidades deste arco.
    private int peso; // O peso deste arco.
    
    // Publico intencionalmente para ser acessado diretamente pelos algoritmos.
    public int f;    // O fluxo neste arco. (Será usado no EP5.)
    
    // O construtor recebe as duas extremidades do arco, a origem e o destino.
    public Arco(Vertice u, Vertice v) {
        origem = u; destino = v;
        peso = 0; f = 0; // Valor padrao inicial será zero.
    }

    public Arco(Vertice u, Vertice v, int p) {
        this(u, v);
        peso = p;
    }

    // Devolve a extremidade de origem do arco.
    public Vertice getOrigem() {
        return origem;
    }
    
    // Devolve a extremidade de destino do arco.
    public Vertice getDestino() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }
    
    public int getFluxo() {
        return f;
    }
    
}
