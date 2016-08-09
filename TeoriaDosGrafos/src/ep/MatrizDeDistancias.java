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
	private Fila Q;
	private int totalVertices;
	private Vertice[] V;
	private LinkedList [] uiAdj;
	private Vertice u; 						//um vertice
	private Vertice uAux;					//um vertice
	private Arco aresta;					//uma aresta
	
	public MatrizDeDistancias(Grafo g)
	{
		this.G = new Grafo();
		this.G = g;
		this.totalVertices = G.getTotalDeVertices();
		this.V = new Vertice[totalVertices];
		this.uiAdj = new LinkedList[totalVertices];
		this.Q = new Fila();
		this.u = new Vertice(0,null); 					//um vertice
		this.uAux = new Vertice(0,null);					//um vertice
		this.aresta = new Arco(null,null);				//uma aresta
		
	}
	
	//Calcula distancias em relacao a s
	public void CalculaDistancias(Vertice s) {
		
		int [][] matrizDistancia = new int [totalVertices][totalVertices];
		V = G.getVertices();
		
		uiAdj = G.getListasDeAdjacencia();
		
				
		for (int i = 0; i < totalVertices; i++) {
			V[i].d = Constantes.INFINITO;
			V[i].cor = Cores.BRANCO;			
		}
		s.d = 0;
		s.cor = Cores.CINZA;
		
		Q.insereNaFila(s);
				
		while(!Q.filaVazia())
		{
			u = (Vertice) Q.removeDaFila();
			
			Iterator<Arco> it = uiAdj[u.getIndice()].iterator();
						
			while (it.hasNext()) 
			{
				aresta = (Arco)it.next();
				uAux = aresta.getDestino(); 
				if(uAux.cor == Cores.BRANCO)
				{
					uAux.d = u.d + 1;
					uAux.cor = Cores.CINZA;
					Q.insereNaFila(uAux);
					V[uAux.getIndice()] = uAux;
				} 
			}
			
			u.cor = Cores.PRETO;
		}
	}

	public void ImprimeMatrizDistancias(){
		
		int [][] matrizDistancia = new int [totalVertices][totalVertices];
		
		for (int i = 0; i < totalVertices; i++)
		{
			CalculaDistancias(G.getVertices()[i]);
			for (int j = 0; j < totalVertices; j++)
			{
				matrizDistancia[i][j] = V[j].d;
				if(matrizDistancia[i][j] == Constantes.INFINITO)
				{
					System.out.print("." +" ");
				}
				else{
					System.out.print(matrizDistancia[i][j] +" ");
				}
				
			}
			System.out.println();						
		}
		
		//int j = 0;
		/*Vertice[] V = G.getVertices();
		
		int [][] matrizDistancia = new int [totalVertices][totalVertices];
		
		do {*/
			//CalculaDistancias(V[j]);j++;
			
			/*for (int i = 0; i < totalVertices; i++) {
    		
				
    			matrizDistancia[i][j] = V[i].d;
    			System.out.print(matrizDistancia[i][j] +" ");
    		}
			System.out.println();
			j++;
		} while (j < totalVertices);*/		
	}
}
