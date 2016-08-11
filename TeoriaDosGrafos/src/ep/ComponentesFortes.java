package ep;

import java.util.Iterator;
import java.util.LinkedList;

import ep0.Arco;
import ep0.Constantes;
import ep0.Cores;
import ep0.Fila;
import ep0.Grafo;
import ep0.GrafoPonderado;
import ep0.OrdenaPorInsercao;
import ep0.Vertice;
import ep.DFO;

public class ComponentesFortes {
	
	private Grafo G;
	private Grafo Gtransp;
	private Fila Qgd;
	private Fila Qgt;
	private int totalVertices;
	private Vertice[] V;
	private Vertice[] Vtransp;
	private Vertice[] Vordenado;
	private LinkedList [] uiAdj;
	private LinkedList [] uiAdjtransp;
	private Vertice vi;					//um vertice
	private Arco aresta;					//uma aresta
	int tempo;
	int totalDeComponentesFortes;
	private boolean direto = true; //true grafo direto, false grafo transposto
	private int[] idOrdenado;
	private int[] idindexOrdenado;
	private int contadorGd;
	private int contadorGt;
	
	private boolean[] marked; // reached vertices
	private int[] id; // component identifiers
	private int count; // number of strong components
	
	
	public ComponentesFortes(Grafo g, Grafo gtransposto)
	{
		totalDeComponentesFortes = 0;
		tempo = 0;
		this.G = new Grafo();
		this.G = g;
		
		this.Gtransp = new Grafo();
		this.Gtransp = gtransposto;
		
		this.totalVertices = G.getTotalDeVertices();
		
		this.V = new Vertice[totalVertices];
		this.V = G.getVertices();
		
		Vtransp = new Vertice[totalVertices];
		Vtransp = Gtransp.getVertices();
		
		Vordenado = new Vertice[totalVertices];
		
		this.uiAdj = new LinkedList[totalVertices];
		this.uiAdj = G.getListasDeAdjacencia();
		
		uiAdjtransp = new LinkedList[totalVertices];
		uiAdjtransp = Gtransp.getListasDeAdjacencia();
		
		this.vi = new Vertice(0,null);					
		this.aresta = new Arco(null,null);
		
		id = new int[totalVertices];
		contadorGd = 0;
		contadorGt = 0;
		
		Qgd = new Fila();
		Qgt = new Fila();
				
		for(int i = 0; i < totalVertices; i++)
		{
			V[i].d = Constantes.INFINITO;
			V[i].cor = Cores.BRANCO;
			V[i].chave = Constantes.INFINITO;
			V[i].pai = null;
			V[i].PI = null;
			
			Vtransp[i].d = Constantes.INFINITO;
			Vtransp[i].cor = Cores.BRANCO;
			Vtransp[i].chave = Constantes.INFINITO;
			Vtransp[i].pai = null;
			Vtransp[i].PI = null;
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
		//dfs de Gt
		tempo = 0;
		int auxiliar = 0;
		for (int i = 0; i < totalVertices; i++) {
			
			auxiliar = Vordenado[i].getIndice();
			if(Vtransp[auxiliar].cor == Cores.BRANCO)
			{
				VisitaDFSgt(Vtransp[auxiliar]);
				contadorGt++;
			}
		}
		
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
	
	private void VisitaDFSgt(Vertice ui)
	{
		tempo = tempo + 1;
		ui.d = tempo;
		ui.cor = Cores.CINZA;
		
		Iterator<Arco> it = uiAdjtransp[ui.getIndice()].iterator();

		while (it.hasNext()) 
		{
			aresta = it.next();
			vi = aresta.getDestino(); 
			
			if(vi.cor == Cores.BRANCO)
			{
				vi.PI = ui;
				VisitaDFSgt(vi);
			}
				
		}
		ui.cor = Cores.PRETO;
		tempo = tempo + 1;
		ui.f = tempo;
		Vtransp[ui.getIndice()] = ui;
		Qgt.insereNaFila(ui);   //ordenacao topologica
	}
	
	public Fila topologicalSortGd()
	{
		return Qgd;
				
	}
	
	public Fila topologicalSortGt()
	{
		return Qgt;
				
	}
	
	public void ordenaVertices(Fila F)
	{
		Vertice v = new Vertice(0,null);
		int i = totalVertices-1;
		
		while(!F.filaVazia())
		{
			v = (Vertice) F.removeDaFila();
			Vordenado[i] = v;
			i--;
			
		}
	}
	
	
	
	public void imprimeTopologicalSortGd()
	{
		ordenaVertices(Qgd);
		Vertice v = new Vertice(0,null);
		System.out.println();
		System.out.println("Grafo Direto");
		System.out.print("Vertices Ordenados Topologicamente:  ");
		
//		while(!Qgd.filaVazia())
//		{
//			v = (Vertice) Qgd.removeDaFila();
//			System.out.print(" " + v.f);
//		}
		
		for(int i = 0; i < totalVertices; i ++)
		{
			System.out.print(" " + Vordenado[i].f);
		}
		
		System.out.println();
	}
	
	public void imprimeTopologicalSortGt()
	{
		ordenaVertices(Qgt);
		Vertice v = new Vertice(0,null);
		System.out.println();
		System.out.println("Grafo Transposto");
		System.out.print("Vertices Ordenados Topologicamente:  ");
		
//		while(!Qgt.filaVazia())
//		{
//			v = (Vertice) Qgt.removeDaFila();
//			System.out.print(" " + v.f);
//		}
		
		for(int i = 0; i < totalVertices; i ++)
		{
			System.out.print(" " + Vordenado[i].f);
		}
		
		System.out.println();
	}
	
	
	
	
	
	public int getcontadorGd()
	{
		return contadorGd;
	}
	
	public int getcontadorGt()
	{
		return contadorGt;		
	}
		
	
		
	
	
	
	
	

}
