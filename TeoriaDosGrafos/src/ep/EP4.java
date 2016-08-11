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

public class EP4 {

	private GrafoPonderado G;
	private Fila Qgd;
	private int totalVertices;
	private Vertice[] V;
	private Vertice[] Vtopologico;
	private LinkedList [] uiAdj;
	private Vertice vi;					//um vertice
	private Vertice u;
	private Arco aresta;					//uma aresta
	int tempo;
	private int contadorGd;

	
	public EP4(GrafoPonderado g)
	{
		tempo = 0;
		this.G = new GrafoPonderado();
		this.G = g;
		this.totalVertices = G.getTotalDeVertices();
		this.V = new Vertice[totalVertices];
		this.V = G.getVertices();
		Vtopologico = new Vertice[totalVertices];
		this.uiAdj = new LinkedList[totalVertices];
		this.uiAdj = G.getListasDeAdjacencia();
		this.vi = new Vertice(0,null);					
		this.aresta = new Arco(null,null);
		contadorGd = 0;
		u = new Vertice(0,null);
		
		Qgd = new Fila();
		
				
		for(int i = 0; i < totalVertices; i++)
		{
			V[i].d = Constantes.INFINITO;
			V[i].cor = Cores.BRANCO;
			V[i].chave = Constantes.INFINITO;
			V[i].pai = null;
			V[i].PI = null;
		}
		
		//***********************************************************************//
		//dfs de G
		tempo = 0;
		for (int i = 0; i < totalVertices; i++) {
			
			if(V[i].cor == Cores.BRANCO)
			{
				VisitaDFSg(V[i]);
				contadorGd++;
			}
		}
		ordenaVertices(Qgd);
		
		
		
	}

	
	
	private void VisitaDFSg(Vertice ui)
	{
		tempo = tempo + 1;
		ui.d = tempo;
		ui.cor = Cores.CINZA;
		
		Iterator<Arco> it = uiAdj[ui.getIndice()].iterator();

		while (it.hasNext()) 
		{
			aresta = it.next();
			vi = aresta.getDestino(); 
			
			if(vi.cor == Cores.BRANCO)
			{
				vi.PI = ui;
				VisitaDFSg(vi);
			}
				
		}
		ui.cor = Cores.PRETO;
		tempo = tempo + 1;
		ui.f = tempo;
		V[ui.getIndice()] = ui;
		Qgd.insereNaFila(ui);   //ordenacao topologica
	}
	
	public Fila topologicalSortGd()
	{
		return Qgd;
				
	}
	
	//Ordenacao topologica depois do DFS
	public void ordenaVertices(Fila F)
	{
		Vertice v = new Vertice(0,null);
		int i = totalVertices-1;
		
		while(!F.filaVazia())
		{
			v = (Vertice) F.removeDaFila();
			Vtopologico[i] = v;
			i--;
			
		}
	}
	
	
	
	//pagina 649 Cormen
	public void RELAX(Vertice uii, Vertice vii, int uvPeso)
	{
		if(vii.d > uii.d + uvPeso)
		{
			vii.d = uii.d + uvPeso;
			vii.PI = uii;
		}		
	}
	
	//Calcula Distancias
	public void DAGShorstestPaths(Vertice s)
	{
		V = G.getVertices();
		
		uiAdj = G.getListasDeAdjacencia();
			
					
		for (int i = 0; i < totalVertices; i++) {
			V[i].d = Constantes.INFINITO;
			V[i].cor = Cores.BRANCO;			
		}
		s.d = 0;
		s.cor = Cores.CINZA;
				
		while(!Qgd.filaVazia())
		{
			u = (Vertice) Qgd.removeDaFila();
			int c = u.getIndice();
				
			Iterator<Arco> it = uiAdj[c].iterator();
							
			while (it.hasNext()) 
			{
				aresta = (Arco)it.next();
				vi = aresta.getDestino(); 
				RELAX(u, vi, aresta.getPeso());
			}
				
			u.cor = Cores.PRETO;
		}
	}
	
	public void ImprimeMatrizDistancias(){
		
		int [][] matrizDistancia = new int [totalVertices][totalVertices];
		
		for (int i = 0; i < totalVertices; i++)
		{
			DAGShorstestPaths(G.getVertices()[i]);
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
