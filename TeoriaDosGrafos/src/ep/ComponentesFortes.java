package ep;

import java.util.Iterator;
import java.util.LinkedList;

import ep0.Arco;
import ep0.Constantes;
import ep0.Cores;
import ep0.Fila;
import ep0.Grafo;
import ep0.GrafoPonderado;
import ep0.Vertice;

public class ComponentesFortes {
	
	private Grafo G;
	private Fila Q;
	private int totalVertices;
	private Vertice[] V;
	private LinkedList [] uiAdj;
	private Vertice vi;					//um vertice
	private Arco aresta;					//uma aresta
	int tempo;
	int totalDeComponentesFortes;
	
	public ComponentesFortes(Grafo g)
	{
		tempo = 0;
		this.G = new GrafoPonderado();
		this.G = g;
		
		this.totalVertices = G.getTotalDeVertices();
		
		this.V = new Vertice[totalVertices];
		this.V = G.getVertices();
		
		this.uiAdj = new LinkedList[totalVertices];
		this.uiAdj = G.getListasDeAdjacencia();
		
		this.vi = new Vertice(0,null);					
		this.aresta = new Arco(null,null);
				
		for(int i = 0; i < totalVertices; i++)
		{
			V[i].d = Constantes.INFINITO;
			V[i].cor = Cores.BRANCO;
			V[i].chave = Constantes.INFINITO;
			V[i].pai = null;
		}
		
		tempo = 0;
		for (int i = 0; i < totalVertices; i++) {
			if(V[i].cor == Cores.BRANCO)
			{
				VisitaDFS(V[i]);
			}
		}
		
	}
	
	private void VisitaDFS(Vertice ui)
	{
		tempo = tempo + 1;
		ui.d = tempo;
		ui.cor = Cores.CINZA;
		
		Iterator<Arco> it = uiAdj[ui.getIndice()].iterator();
		
		while (it.hasNext()) 
		{
			aresta = (Arco)it.next();
			vi = aresta.getDestino(); 
			
			if(vi.cor == Cores.BRANCO)
			{
				VisitaDFS(vi);
			}
				
		}
		tempo = tempo + 1;
		ui.f = tempo;
		ui.cor = Cores.PRETO;
		V[ui.getIndice()] = ui;	
	}
	
	
	
	
	public int CalculaComponentesFortes()
	{
		
		
		
		
		
		
		
		return totalDeComponentesFortes;
	}
	
	
	
	
	

}
