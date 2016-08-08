package ep;

import ep0.Arco;
import ep0.Fila;
import ep0.FilaDePrioridade;
import ep0.Grafo;
import ep0.GrafoPonderado;
import ep0.Keyboard;
import ep0.OrdenaPorInsercao;
import ep0.Vertice;

public class Teste {

	public static void GrafoGenerico(){
		
		System.out.println("Inicio Grafo Genérico");
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
	
	
	public static void GrafoPonderado(){
		
		System.out.println("Inicio Grafo Ponderado");
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

	
	public static void UsaFila(){
		
		Grafo k33 = Grafo.getK33();
        k33.imprimeNaTelaLista();
        k33.imprimeNaTelaMatriz();
        Fila q = new Fila();
        Vertice[] vertices = k33.getVertices();
        int n = k33.getTotalDeVertices();
        for (int i = n-1; i >= 0; i--) {
            Vertice v = vertices[i];
            q.insereNaFila(v);
        }
        System.out.println("Ordem de chegada na fila: ");
        while (!q.filaVazia()) {
            Vertice u = (Vertice)q.removeDaFila(); // Cast necessario.
            System.out.print(u.getNome()+" ");
        }
        System.out.println();		
	}
	
	public static void UsaFilaDePrioridade(){
		Grafo k33 = Grafo.getK33();
        k33.imprimeNaTelaLista();
        k33.imprimeNaTelaMatriz();
        
        Vertice[] vertices = k33.getVertices();
        FilaDePrioridade q = new FilaDePrioridade(vertices);
        int n = k33.getTotalDeVertices();
        for (int i = 0; i < n; i++) {
            Vertice v = vertices[i];
            v.chave = n-i;
        }
        System.out.println("Ordem de prioridade na fila: ");
        while (!q.filaVazia()) {
            Vertice u = (Vertice)q.extraiMinimo(); // Cast necessario.
            System.out.print(u.getNome()+"(chave="+u.chave+"), ");
        }
        System.out.println();		
	}
	
	public static void UsaOrdenaPorInsercao(){
		System.out.println("\nValores originais sem ordenacao:");
        int[] v1 = {5, 3, 2, 1, 4};
        int tot = v1.length;
        for (int i = 0; i < tot; i++)
            System.out.print(v1[i]+", ");

        System.out.println("\nValores ordenados:");
        OrdenaPorInsercao s = new OrdenaPorInsercao(v1);
        int[] v2 = s.getValoresOrdenados();
        for (int i = 0; i < tot; i++)
            System.out.print(v2[i]+", ");

        System.out.println("\nIndices ordenados:");
        int[] ind = s.getIndicesOrdenados();
        for (int i = 0; i < tot; i++)
            System.out.print(ind[i]+", ");
        System.out.println();
	}
	
	public static void UsaGrafoPonderado(){
		// Cria uma nova instancia da classe GrafoPonderado.
        GrafoPonderado G = new GrafoPonderado();
        // Lê os dados do grafo pelo teclado.
        G.leDoTeclado();
        // Imprime os dados do grafo na tela.
        G.imprimeNaTela();		
	}
	
	public static void main( String args[] ) {
		
		//GrafoGenerico();
		//GrafoPonderado();
		//UsaFila();
		//UsaFilaDePrioridade();
		//UsaOrdenaPorInsercao();
		//UsaGrafoPonderado();
		
		System.out.println("Inicio Grafo Genérico");
        Grafo G = new Grafo();
        // Leitura do grafo via teclado.
        G.leDoTeclado();
        Vertice v = new Vertice(0,null);
		v = G.getVertices()[0];
		
		MatrizDeDistancias ep1 = new MatrizDeDistancias(G, v);
		G.imprimeNaTelaLista();
		G.imprimeNaTelaMatriz();
		
		System.out.println("fim");
		
    }

}
