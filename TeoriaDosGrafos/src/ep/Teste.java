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
		
		System.out.println("Inicio Grafo Gen�rico");
        Grafo G = new Grafo();
        // Leitura do grafo via teclado.
        G.leDoTeclado();

        // Leitura da sequencia de vertices.
        int tamanhoDaSequencia = Keyboard.readInt();
        int[] sequencia = new int[tamanhoDaSequencia];
        for (int k = 0; k < tamanhoDaSequencia; k++)
            sequencia[k] = Keyboard.readInt();
        
        // verifica se a sequencia � um caminho no grafo
        boolean sequencia�Caminho = true;
        Arco[][] madj = G.getMatrizDeAdjacencia();
        int i = sequencia[0];
        for (int k = 1; k < tamanhoDaSequencia; k++) {
            int j = sequencia[k];
            // Se algum par consecutivo nao estiver conectado,
            // entao o caminho � invalido.
            if (madj[i][j] == null) {
                sequencia�Caminho = false;
                break;
            }
            i = j;
        }
        // Se a sequencia � um caminho, imprime a sequencia de vertices.
        if (sequencia�Caminho) {
            Vertice[]  verts = G.getVertices();
            i = sequencia[0];
            System.out.print("A sequencia � o caminho ("+verts[i].getNome());
            for (int k = 1; k < tamanhoDaSequencia; k++) {
                i = sequencia[k];
                System.out.print(","+verts[i].getNome());
            }
            System.out.println(").");
        }
        // Se nao � caminho...
        else
            System.out.println("A sequencia nao � um caminho.");		
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

        // Verifica se a sequencia � um caminho no grafo.
        boolean sequencia�Caminho = true;
        Arco[][] madj = G.getMatrizDeAdjacencia();
        int i = sequencia[0];
        for (int k = 1; k < tamanhoDaSequencia; k++) {
            int j = sequencia[k];
            // Se algum par consecutivo nao estiver conectado,
            // entao o caminho � invalido.
            if (madj[i][j] == null) {
                sequencia�Caminho = false;
                break;
            }
            i = j;
        }
        // Se a sequencia � um caminho, imprime a sequencia de vertices
        // e o comprimento do caminho.
        if (sequencia�Caminho) {
            int comprimento = 0;
            Vertice[]  verts = G.getVertices();
            i = sequencia[0];
            System.out.print("A sequencia � o caminho ("+verts[i].getNome());
            for (int k = 1; k < tamanhoDaSequencia; k++) {
                int j = sequencia[k];
                System.out.print(","+verts[j].getNome());
                comprimento += madj[i][j].getPeso();
                i = j;
            }
            System.out.println(") de comprimento "+comprimento+".");
        }
        // Se nao � caminho...
        else
            System.out.println("A sequencia nao � um caminho.");		
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
        // L� os dados do grafo pelo teclado.
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
		
		//EP1
//		System.out.println("Matriz de Dist�ncias");
//        Grafo G1 = new Grafo();
//        // Leitura do grafo via teclado.
//        G1.leDoTeclado();
//        MatrizDeDistancias ep1 = new MatrizDeDistancias(G1);
//		ep1.CalculaDistancias(G1.getVertices()[0]);
//		ep1.ImprimeMatrizDistancias();
		//End EP1
		
		//EP2
//		System.out.println("MST");
//		GrafoPonderado Gpond = new GrafoPonderado();
//		Gpond.leDoTeclado();
//		Gpond.imprimeNaTela();
//		Gpond.imprimeNaTelaFluxoCapacidade();
//		MST ep2 = new MST(Gpond);
//		System.out.println("Peso arvore geradora minima: " + ep2.CalculaPesoArvoreGMinima());		
		//End EP2
		
		//EP3
//		System.out.println("Componentes Fortes");
//		Grafo G3 = new Grafo();
//		G3.leDoTeclado();
//		G3.imprimeNaTelaLista();
//		G3.imprimeNaTelaMatriz();
//		Grafo G3t = new Grafo();
//		G3t = G3.getGrafoTransposto();
//		ComponentesFortes ep3 = new ComponentesFortes(G3, G3t);
//		System.out.println();
//		ep3.imprimeTopologicalSortGd();
//		ep3.imprimeTopologicalSortGt();
//		//System.out.println("Contador Gd:  " + ep3.getcontadorGd());
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		System.out.println("Total de componentes fortemente conectados:  " + ep3.getcontadorGt());
		//End EP3
		
		//EP4
//		System.out.println("Matriz de Dist�ncias Ponderadas");
//		GrafoPonderado G4 = new GrafoPonderado();
//		G4.leDoTeclado();
//		MDPesos ep4 = new MDPesos(G4);
//		ep4.CalculaDistancias(G4.getVertices()[0]);
//		ep4.ImprimeMatrizDistancias();

		
//EP4 ep4 = new EP4(G4);
//ep4.DAGShorstestPaths(G4.getVertices()[0]);
//ep4.ImprimeMatrizDistancias();
		
		//End EP4
		
		
		System.out.println("fim");
		
    }

}
