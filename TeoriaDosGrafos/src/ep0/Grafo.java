/*

2016jun23

Representacao de um grafo com listas de adjacencia e matriz de adjacencia.
Tanto as listas de adjacencia quanto a matriz de adjacencia possuem
referencias para arcos.
Neste caso, podemos acessar os arcos pela matriz ou pelas listas de 
adjacencia e seus atributos, como por exemplo os pesos dos arcos no 
caso do grafo ponderado.
Nesta implementacao, numeramos os N vertices de 0 a N-1, assumindo que os 
indices dos vertices obedecem a ordem de insercao de vertices.

Operacoes:

.leDoTeclado(): faz a leitura de um grafo pela entrada padr�o via teclado.
.imprimeNaTela(): imprime listas de adjacencia e matriz de adjacencia.
.getVertices(): devolve um vetor de refer�ncias aos vertices do grafo.
.getListasDeAdjacencia(): devolve as listas de adjacencia do grafo, 
                          com refer�ncias para arcos.
.getMatrizDeAdjacencia(): devolve uma matriz de adjacencia com refer�ncias 
                          para arcos.
.getTotalDeVertices(): devolve o total de vertices do grafo.
.getTotalDeArcos(): devolve o total de arcos do grafo.
.getGrafoTransposto(): devolve o grafo transposto como uma nova inst�ncia
                       da classe Grafo. 
                       O grafo transposto � obtido invertendo-se a direcao de 
                       todos os arcos do grafo original.
*/

package ep0;

import java.util.Iterator;
import java.util.LinkedList;

public class Grafo {
    // Declaracao dos atributos do grafo.
    
	protected int totalDeVertices; // Total de vertices no grafo.
    
    protected int totalDeArcos;   // Total de arestas no grafo.
    
    // Vetor de vertices baseado no indice do vertice.
    protected Vertice[] vertices;
    
    // Listas de adjacencia do grafo, com referencias para arcos.
    protected LinkedList[] listasDeAdjacencia;
    
    // Matriz  de adjacencia do grafo, com referencias para arcos.
    protected Arco[][] matrizDeAdjacencia; 
    
    // O construtor inicializa os atributos de uma nova instancia 
    // de um grafo vazio, com zero vertices e zero arcos.
    public Grafo() {
        totalDeVertices = 0;
        totalDeArcos = 0;
    }
    
    // Metodo para leitura de um grafo a partir do teclado.
    // O formato basico de entrada de um grafo �:
    // <total de vertices>
    // <nomes dos vertices>
    // <total de arcos>
    // <pares de inteiros representado indices de origem e destino dos arcos>
    public void leDoTeclado() {
        // Leitura do total de vertices.
        totalDeVertices = Keyboard.readInt();
        // Aloca espaco para o vetor de vertices.
        vertices = new Vertice[totalDeVertices];
        // Leitura dos vertices.
        for (int i = 0; i < totalDeVertices; i++) {
            String nome = Keyboard.readString();
            // Cria um vertice com indice na ordem de insercao e um nome.
            Vertice v = new Vertice(i, nome);
            vertices[i] = v;
        }
        // Aloca espaco para as listas de adjacencia.
        listasDeAdjacencia = new LinkedList[totalDeVertices]; 
        for (int i = 0; i < totalDeVertices; i++)
            listasDeAdjacencia[i] = new LinkedList();
        // Aloca espaco para a matriz de adjacencia e a inicializa com zeros.
        matrizDeAdjacencia = new Arco[totalDeVertices][totalDeVertices];
        for (int i = 0; i < totalDeVertices; i++)
            for (int j = 0; j < totalDeVertices; j++)
                matrizDeAdjacencia[i][j] = null;
        // Leitura do total de arcos.
        totalDeArcos = Keyboard.readInt();
        for (int k = 0; k < totalDeArcos; k++) {
            int i = Keyboard.readInt();
            int j = Keyboard.readInt();
            // Insere arco ij no grafo.
            // Tanto as listas de adjacencia quanto a matriz de adjacencia
            // possuem referencias para arcos.
            Arco a = new Arco(vertices[i],vertices[j]);
            listasDeAdjacencia[i].addLast(a);
            matrizDeAdjacencia[i][j] = a;
        }
    }

    // Metodo para imprimir os atributos do grafo na tela.
    // Imprime as listas de adjacencia, a matriz de adjacencia,
    // e o total de vertices e de arcos.
    public void imprimeNaTelaLista() {
        System.out.println("\nListas de Adjacencia:");
        for (int i = 0; i < totalDeVertices; i++) {
            Vertice u = vertices[i];
            System.out.print(u.getNome()+": ");
            Iterator it = listasDeAdjacencia[i].iterator();
            while (it.hasNext()) {
                Arco a = (Arco)it.next();
                Vertice v = a.getDestino();
                System.out.print(v.getNome()+", ");
            }
            System.out.println();
        }
        System.out.println("Total de vertices: "+totalDeVertices);
        System.out.println("Total de arcos: "+totalDeArcos);
    }
        
    public void imprimeNaTelaMatriz(){
    	System.out.println("\nMatriz de Adjacencia:");
    	for (int i = 0; i < totalDeVertices; i++) {
    		for (int j = 0; j < totalDeVertices; j++)
    			if (matrizDeAdjacencia[i][j] != null)
    				System.out.print("1 ");
    		    else
    		    	System.out.print("0 ");
    		
    	System.out.println();
    	}
        
        System.out.println("Total de vertices: "+totalDeVertices);
        System.out.println("Total de arcos: "+totalDeArcos);
    }
    
    // M�todos para acesso ao dados encapsulados nesta classe.

    // Devolve o vetor de vertices.
    public Vertice[] getVertices() {
        return vertices;
    }

    // Devolve as listas de adjacencia.
    public LinkedList[] getListasDeAdjacencia() {
        return listasDeAdjacencia;
    }

    // Devolve a matriz de adjacencia.
    public Arco[][] getMatrizDeAdjacencia() {
        return matrizDeAdjacencia;
    }
    
    // Devolve o total de vertices do grafo.
    public int getTotalDeVertices() {
        return totalDeVertices;
    }

    // Devolve o total de arcos do grafo.
    public int getTotalDeArcos() {
        return totalDeArcos;
    }
    
    // Constroi e devolve o grafo transposto.
    // O grafo transposto Gt � obtido invertendo-se a direcao de todos
    // os arcos do grafo original G.
    // Esta funcao ser� usada no calculo dos componentes fortemente
    // conectados (EP3).
    public Grafo getGrafoTransposto() {
        // Cria uma nova instancia para o grafo transposto.
        Grafo Gt = new Grafo();
        Gt.totalDeVertices = totalDeVertices;
        Gt.vertices = new Vertice[totalDeVertices];
        // Gt cria copias dos v�rtices de G.
        for (int i = 0; i < totalDeVertices; i++) {
            String nome = vertices[i].getNome();
            Vertice v = new Vertice(i, nome); // Nova instancia de vertice.
            Gt.vertices[i] = v;
        }
        // Gt cria copias dos arcos de G, mas com direcao invertida.
        Gt.listasDeAdjacencia = new LinkedList[totalDeVertices]; 
        for (int i = 0; i < totalDeVertices; i++)
            Gt.listasDeAdjacencia[i] = new LinkedList();
        Gt.matrizDeAdjacencia = new Arco[totalDeVertices][totalDeVertices];
        Gt.totalDeArcos = totalDeArcos;
        for (int i = 0; i < totalDeVertices; i++)
            for (int j = 0; j < totalDeVertices; j++)
                Gt.matrizDeAdjacencia[i][j] = null;
        for (int i = 0; i < totalDeVertices; i++)
            for (int j = 0; j < totalDeVertices; j++)
                // Se existe arco ij em G...
                if (matrizDeAdjacencia[i][j] != null) { 
                    // ...insere arco ji (direcao invertida)
                    // no grafo transposto.
                    Arco a = new Arco(Gt.vertices[j],Gt.vertices[i]);
                    Gt.listasDeAdjacencia[j].addLast(a);
                    Gt.matrizDeAdjacencia[j][i] = a;
                }
        return Gt;
    }
    
    // Para ilustrar, construimos o grafo bipartido K_{3,3}.
    public static Grafo getK33() {
        String[] nomes = {"x1","x2","x3","y1","y2","y3"};
        Grafo k33 = new Grafo();
        k33.totalDeVertices = 6;
        k33.vertices = new Vertice[k33.totalDeVertices];
        for (int i = 0; i < k33.totalDeVertices; i++) {
            Vertice v = new Vertice(i, nomes[i]);
            k33.vertices[i] = v;
        }
        k33.listasDeAdjacencia = new LinkedList[k33.totalDeVertices]; 
        for (int i = 0; i < k33.totalDeVertices; i++)
            k33.listasDeAdjacencia[i] = new LinkedList();
        k33.matrizDeAdjacencia = new Arco[k33.totalDeVertices][k33.totalDeVertices];
        k33.totalDeArcos = 9;
        for (int i = 0; i < k33.totalDeVertices; i++)
            for (int j = 0; j < k33.totalDeVertices; j++)
                k33.matrizDeAdjacencia[i][j] = null;
        for (int i = 0; i <= 2; i++)
            for (int j = 3; j <= 5; j++) {
                Arco a = new Arco(k33.vertices[i],k33.vertices[j]);
                k33.listasDeAdjacencia[i].addLast(a);
                k33.matrizDeAdjacencia[i][j] = a;
                Arco b = new Arco(k33.vertices[j],k33.vertices[i]);
                k33.listasDeAdjacencia[j].addLast(b);
                k33.matrizDeAdjacencia[j][i] = b;
            }
        return k33;
    }
}

