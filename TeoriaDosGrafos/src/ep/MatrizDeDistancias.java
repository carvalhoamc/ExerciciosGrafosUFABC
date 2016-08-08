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
		LinkedList [] uiAdj = G.getListasDeAdjacencia();
				
		for (int i = 0; i < G.getTotalDeVertices(); i++) {
			V[i].d = Constantes.INFINITO;
			V[i].cor = Cores.BRANCO;			
		}
		s.d = 0;
		s.cor = Cores.CINZA;
		Q = new Fila();
		Q.insereNaFila(S);
				
		int indx = 0;
		Vertice u; 						//um vertice
		Vertice uAux;					//um vertice
		Arco aresta;					//uma aresta
		
		while(!Q.filaVazia())
		{
			u = (Vertice) Q.removeDaFila();
			indx = u.getIndice();
			
			for(int i = 0; i < indx; i++)
			{
				aresta = (Arco) uiAdj[indx].get(i);
				uAux = aresta.getDestino();
				if(uAux.cor == Cores.BRANCO)
				{
					uAux.d = u.d++;
					uAux.cor = Cores.CINZA;
					Q.insereNaFila(uAux);
				}
			}
			
			u.cor = Cores.PRETO;
		}
	}

	public void ImprimeMtrizDistancias(){
		
		
		
		
		
		
	}


}
