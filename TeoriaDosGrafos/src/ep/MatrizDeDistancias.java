package ep;

import ep0.Grafo;
import ep0.Arco;
import ep0.Vertice;
import ep0.Constantes;
import ep0.Cores;
import ep0.Fila;
import java.util.Iterator;
import java.util.LinkedList;

public class MatrizDeDistancias {

	private Grafo G;
	private Vertice S;
	private Fila Q;
	
	
	public MatrizDeDistancias(Grafo g, Vertice s) {
		
		this.G = g;
		this.S = s;
		Vertice[] V = G.getVertices();
		LinkedList<Vertice>[] uiAdj = G.getListasDeAdjacencia();
				
		for (int i = 0; i < G.getTotalDeVertices(); i++) {
			V[i].d = Constantes.INFINITO;
			V[i].cor = Cores.BRANCO;			
		}
		s.d = 0;
		s.cor = Cores.CINZA;
		Q = new Fila();
		Q.insereNaFila(S);
		LinkedList<Vertice> aux = null;
		int tamanho = 0;
		
		while(!Q.filaVazia())
		{
			Vertice u = (Vertice) Q.removeDaFila();
			int indx = u.getIndice();
			aux = uiAdj[indx];
			tamanho = aux.size();
			if (tamanho != 0) //testa se ha arestas para este vertice
			{
				for(int i = 0; i < tamanho; i++)
				{
					if (aux.get(i).cor == Cores.BRANCO)
					{
						aux.get(i).d = u.d + 1;
						aux.get(i).cor = Cores.CINZA;
						Q.insereNaFila(aux.get(i));						
					}
				}
			}
			
			u.cor = Cores.PRETO;
		}
	}
}
