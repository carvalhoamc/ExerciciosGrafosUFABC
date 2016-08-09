/*

2016jun23

Representacao de grafo ponderado.
Durante o curso de grafos, assumimos pesos nao-negativos para os arcos,
ou seja, pesos >= 0.
Se n�o existir um dado arco, assumimos que seu peso vale zero.

Al�m das operacoes disponiveis na classe ancestral Grafo, temos as seguintes
operacoes:

.leDoTeclado(): faz a leitura de um grafo pela entrada padr�o via teclado.
                (Sobrecarrega m�todo original para leitura de grafos 
                ponderados).
.imprimeNaTela(): imprime listas de adjacencia e matriz de adjacencia.
                (Sobrecarrega m�todo original para impressao de grafos 
                ponderados).
.imprimeNaTelaFluxoCapacidade(): impressao do fluxo e da capacidade de cada
                                 arco de um grafo ponderado representado uma
                                 rede de fluxo.

*/

package ep0;

import java.util.Iterator;
import java.util.LinkedList;

// Esta classe � derivada da classe Grafo, ou seja, herda os atributos
// e m�todos da classe ancestral.
public class GrafoPonderado extends Grafo {
    
    // O construtor chama o construtor da classe ancestral, usando
    // a palavra-chave "super".
    public GrafoPonderado() {
        super();
    }

    // Metodo para leitura de um grafo a partir do teclado.
    // O formato basico de entrada de um grafo �:
    // <total de vertices>
    // <nomes dos vertices>
    // <total de arcos>
    // <triplas de inteiros representado indices de origem e destino 
    //  dos arcos, e o peso do arco>
    public void leDoTeclado() {
        // Leitura do total de vertices.
        totalDeVertices = Keyboard.readInt();
        // Aloca espaco para o vetor de vertices.
        vertices = new Vertice[totalDeVertices];
        // Leitura dos vertices.
        for (int i = 0; i < totalDeVertices; i++) {
            String nome = Keyboard.readString();
            Vertice v = new Vertice(i, nome, 0);
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
            int p = Keyboard.readInt();
            // Insere arco ponderado ij no grafo.
            Arco ap = new Arco(vertices[i],vertices[j],p);
            listasDeAdjacencia[i].addLast(ap);
            matrizDeAdjacencia[i][j] = ap;
        }
    }

    // Metodo para imprimir os atributos do grafo na tela.
    // Imprime as listas de adjacencia, a matriz de adjacencia,
    // e o total de vertices e de arcos.
    public void imprimeNaTela() {
        System.out.println("\nListas de Adjacencia:");
        for (int i = 0; i < totalDeVertices; i++) {
            Vertice u = vertices[i];
            System.out.print(u.getNome()+": ");
            Iterator it = listasDeAdjacencia[i].iterator();
            while (it.hasNext()) {
                Arco ap = (Arco)it.next();
                Vertice v = (Vertice) ap.getDestino();
                System.out.print(v.getNome()+"("+ap.getPeso()+"), ");
            }
            System.out.println();
        }
        System.out.println("\nMatriz de Adjacencia:");
        for (int i = 0; i < totalDeVertices; i++) {
            for (int j = 0; j < totalDeVertices; j++)
                if (matrizDeAdjacencia[i][j] != null)
                    System.out.print(matrizDeAdjacencia[i][j].getPeso()+" ");
                else
                    System.out.print("0 ");
            System.out.println();
        }
        
        System.out.println("Total de vertices: "+totalDeVertices);
        System.out.println("Total de arcos: "+totalDeArcos);
    }
    
    /* Definicoes:

       Uma rede de fluxo � um grafo ponderado, onde os pesos dos arcos 
       representam as capacidades de fluxo dos arcos.
       Neste caso, podemos calcular o fluxo maximo entre dois vertices, 
       uma origem e um destino.
       No problema do fluxo maximo, cada arco possui um par de valores: 
       fluxo e capacidade de fluxo.
    
       Metodo para imprimir os atributos do grafo na tela.
       Imprime as listas de adjacencia (com capacidade e fluxo),
       a matriz de adjacencia, e o total de vertices e de arcos.
   */
    public void imprimeNaTelaFluxoCapacidade() {
        System.out.println("\nListas de Adjacencia:");
        for (int i = 0; i < totalDeVertices; i++) {
            Vertice u = vertices[i];
            System.out.print(u.getNome()+": ");
            Iterator it = listasDeAdjacencia[i].iterator();
            while (it.hasNext()) {
                Arco ap = (Arco)it.next();
                Vertice v = ap.getDestino();
                System.out.print(v.getNome()+"("+ap.f+"/"+ap.getPeso()+"), ");
            }
            System.out.println();
        }
        System.out.println("\nMatriz de Adjacencia:");
        for (int i = 0; i < totalDeVertices; i++) {
            for (int j = 0; j < totalDeVertices; j++)
                if (matrizDeAdjacencia[i][j] != null)
                    System.out.print(matrizDeAdjacencia[i][j].getPeso()+" ");
                else
                    System.out.print(". ");
            System.out.println();
        }
        
        System.out.println("Total de vertices: "+totalDeVertices);
        System.out.println("Total de arcos: "+totalDeArcos);
    }

    /* Definicoes:

       Uma rede residual � formada pelas capacidades residuais, calculadas 
       da seguinte maneira.
       Dado um arco uv no grafo original representando a rede de fluxo:
       .A capacidade residual de "ida" � dada por c(uv) - f(uv),
        ou seja, capacidade menos o fluxo no arco uv.
       .A capacidade residual de "volta" � dada pelo proprio fluxo f(vu), 
        mas no sentido contr�rio, inserindo um novo arco vu na rede residual.
    
       Este m�todo constroi e devolve o grafo representando uma rede 
       residual R.
       Esta funcao ser� usada no calculo do fluxo m�ximo (EP5).
    */       
    public GrafoPonderado getRedeResidual() {
        // Cria uma nova instancia para a rede residual.
        GrafoPonderado R = new GrafoPonderado();
        R.totalDeVertices = totalDeVertices;
        R.vertices = new Vertice[totalDeVertices];
        // Cria copias (novas inst�ncias) dos v�rtices de G.
        for (int i = 0; i < totalDeVertices; i++) {
            String nome = vertices[i].getNome();
            Vertice v = new Vertice(i, nome, 0);
            R.vertices[i] = v;
        }
        // Cria arcos para representar as capacidades residuais de fluxo.
        R.listasDeAdjacencia = new LinkedList[totalDeVertices]; 
        for (int i = 0; i < totalDeVertices; i++)
            R.listasDeAdjacencia[i] = new LinkedList();
        R.matrizDeAdjacencia = new Arco[totalDeVertices][totalDeVertices];
        for (int i = 0; i < totalDeVertices; i++)
            for (int j = 0; j < totalDeVertices; j++)
                R.matrizDeAdjacencia[i][j] = null;
        R.totalDeArcos = 0;
        for (int i = 0; i < totalDeVertices; i++) {
            Iterator it = listasDeAdjacencia[i].iterator();
            while (it.hasNext()) {
                Arco ap = (Arco)it.next();
                Vertice v = ap.getDestino();
                int j = v.getIndice();
                int capacidade = ap.getPeso();
                int fluxo = ap.f; // Podemos acessar diretamente este atributo.
                int capacidadeResidualDeIda = capacidade - fluxo; 
                if (capacidadeResidualDeIda > 0) {
                    Arco apCopia = new Arco(R.vertices[i],R.vertices[j],capacidadeResidualDeIda);
                    R.listasDeAdjacencia[i].addLast(apCopia);
                    R.matrizDeAdjacencia[i][j] = apCopia;
                }
                int capacidadeResidualDeVolta = fluxo; 
                if (capacidadeResidualDeVolta > 0) {
                    Arco apCopia = new Arco(R.vertices[j],R.vertices[i],capacidadeResidualDeVolta);
                    R.listasDeAdjacencia[j].addLast(apCopia);
                    R.matrizDeAdjacencia[j][i] = apCopia;
                }
            }
        }
        return R;
    }

}

