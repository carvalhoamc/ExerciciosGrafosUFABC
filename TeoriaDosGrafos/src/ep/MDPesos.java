package ep;

import ep0.Grafo;
import ep0.GrafoPonderado;
import ep0.Arco;
import ep0.Vertice;
import ep0.Constantes;
import ep0.Cores;
import ep0.Fila;
import ep0.FilaDePrioridade;

import java.util.Iterator;
import java.util.LinkedList;


public class MDPesos {
	
	
	private GrafoPonderado Gp;
	private Fila Qp;
	private int totalVertices;
	private Vertice[] V;
	private LinkedList [] uiAdj;
	private Vertice u;
	private Vertice uAux;					//um vertice
	private Arco aresta;					//uma aresta
	
	
	
	
	public MDPesos(GrafoPonderado g)
	{
		this.Gp = new GrafoPonderado();
		this.Gp = g;
		
		this.totalVertices = Gp.getTotalDeVertices();
		
		this.V = new Vertice[totalVertices];
		this.V = Gp.getVertices();
		
		this.uiAdj = new LinkedList[totalVertices];
		this.uiAdj = Gp.getListasDeAdjacencia();
		
		this.u = new Vertice(0,null);
		this.uAux = new Vertice(0,null);					
		this.aresta = new Arco(null,null);
				
		for(int i = 0; i < totalVertices; i++)
		{
			V[i].d = Constantes.INFINITO;
			V[i].cor = Cores.BRANCO;
			V[i].chave = Constantes.INFINITO;
			V[i].pai = null;
			V[i].PI = null;
		}
		V[0].chave = 0; //raiz r
		V[0].cor = Cores.PRETO;
		
		this.Qp = new Fila();
		
		
	}
	
	//Calcula distancias em relacao a s
	public void CalculaDistancias(Vertice s) {
			
		V = Gp.getVertices();
			
		uiAdj = Gp.getListasDeAdjacencia();
			
					
		for (int i = 0; i < totalVertices; i++) {
			V[i].d = Constantes.INFINITO;
			V[i].cor = Cores.BRANCO;			
		}
		s.d = 0;
		s.cor = Cores.CINZA;
			
		Qp.insereNaFila(s);
					
		while(!Qp.filaVazia())
		{
			u = (Vertice) Qp.removeDaFila();
			int c = u.getIndice();
				
			Iterator<Arco> it = uiAdj[c].iterator();
							
			while (it.hasNext()) 
			{
				int a = 0;
				int b = 0;
				aresta = (Arco)it.next();
				uAux = aresta.getDestino(); 
				if(uAux.cor == Cores.BRANCO)
				{
					b = aresta.getPeso();
					uAux.d = u.d + b;
					uAux.cor = Cores.CINZA;
					Qp.insereNaFila(uAux);
					a = uAux.getIndice();
					V[a] = uAux;
				} 
			}
				
			u.cor = Cores.PRETO;
		}
	}

		public void ImprimeMatrizDistancias(){
			
			int [][] matrizDistancia = new int [totalVertices][totalVertices];
			
			for (int i = 0; i < totalVertices; i++)
			{
				CalculaDistancias(Gp.getVertices()[i]);
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
	
		}
}
