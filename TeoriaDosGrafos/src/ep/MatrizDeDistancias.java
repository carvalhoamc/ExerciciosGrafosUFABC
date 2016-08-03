package ep;

import ep0.Grafo;
import ep0.Arco;
import ep0.Vertice;
import ep0.Constantes;
import ep0.Cores;
import ep0.Fila;
import java.util.Iterator;
import java.util.LinkedList;


////Busca em Largura ou BFS
//public class MatrizDeDistancias {
//	
//	private Grafo G;
//	private int cnt;
//	private int[] ord, st;
//	private void searchC (Arco e){
//		
//		EdgeQueue Q = new EdgeQueue(G.getTotalDeVertices());
//		Q.put(e);
//		while (!Q.empty())
//		{
//			if ( ord[(e = Q.get()).w] == -1)
//			{
//				int v = e.v;
//				int w = e.w;
//				ord[w] = cnt++; st[w] = v;
//				AdjList A = G.getAdjList(w);
//				for( int t = A.beg(); !A.end(); t = A.nxt())
//				{
//					if (ord[t] == -1)
//					{
//						Q.put(new Edge(w,t));
//					}
//				}
//				
//				
//			}
//		}
//	}
//	
//	MatrizDeDistancias(Grafo G, int v){
//		
//		this.G = G;
//		int totalDeVertices = G.getTotalDeVertices();
//		cnt = 0;
//		ord = new int[totalDeVertices];
//		st = new int[totalDeVertices];
//		for(int t = 0; t < totalDeVertices; t++)
//		{
//			ord[t] = -1;
//			st[t] = -1;
//		}
//		for( int t = 0; t < totalDeVertices; t++)
//		{
//			if (ord[t] == -1)
//			{
//				Vertice 
//				searchC(new Arco(t, t));
//			}
//		}
//	}
//	
//	int order(int v){
//		
//		return ord[v];
//	}
//	
//	int ST(int v){
//		
//		return st[v];
//	}
//}

public class MatrizDeDistancias {

	private Grafo G;
	private Vertice S;
	private Fila Q;
	
	
	public MatrizDeDistancias(Grafo g, Vertice s) {
		
		this.G = g;
		this.S = s;
		Vertice[] V = G.getVertices();
		LinkedList[] uiAdj = G.getListasDeAdjacencia();
		LinkedList[] uiAdjItem;
		int n = 0;
		
		for (int i = 0; i < G.getTotalDeVertices(); i++) {
			V[i].d = Constantes.INFINITO;
			V[i].cor = Cores.BRANCO;			
		}
		s.d = 0;
		s.cor = Cores.CINZA;
		Q = new Fila();
		Q.insereNaFila(S);
		
		while(!Q.filaVazia())
		{
			Vertice u = (Vertice) Q.removeDaFila();
			int indx = u.getIndice();
			n = uiAdj[indx].size();
			
			
			for(int i = 0; i < n; i++)
			{
				
				
			}
			
			
			
		}
	}
}
