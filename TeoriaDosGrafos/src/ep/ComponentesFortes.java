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
	private Fila Q;
	private int totalVertices;
	private Vertice[] V;
	private Vertice[] Vtransp;
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
		
		this.uiAdj = new LinkedList[totalVertices];
		this.uiAdj = G.getListasDeAdjacencia();
		
		uiAdjtransp = new LinkedList[totalVertices];
		uiAdjtransp = Gtransp.getListasDeAdjacencia();
		
		this.vi = new Vertice(0,null);					
		this.aresta = new Arco(null,null);
		
		id = new int[totalVertices];
		contadorGd = 0;
		contadorGt = 0;
				
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
//		tempo = 0;
//		for (int i = 0; i < totalVertices; i++) {
//			
//			if(V[i].cor == Cores.BRANCO)
//			{
//				VisitaDFSg(V[i]);
//				contadorGd++;
//			}
//		}
		//dfs de Gt
//		tempo = 0;
//		int indexAuxiliar = 0;
//		for (int i = 0; i < totalVertices; i++) {
//			
//			indexAuxiliar = idindexOrdenado[i];
//			if(Vtransp[indexAuxiliar].cor == Cores.BRANCO)
//			{
//				VisitaDFSgt(Vtransp[indexAuxiliar]);
//				contadorGd++;
//			}
//		}
		
		
		marked = new boolean[totalVertices];
		id = new int[totalVertices];
		DFO order = new DFO(Gtransp);
		
		for (int s : order.reversePost())
		{
			if (!marked[s])
			{ 
				dfs(G, s);
				count++; 
			}
			
		}
		
	}
	
	private void dfs(Grafo G, int v)
	{
		marked[v] = true;
		id[v] = count;
		
		Iterator<Arco> it = uiAdj[v].iterator();

		while (it.hasNext()) 
		{
			aresta = it.next();
			vi = aresta.getDestino();
			if(!marked[vi.getIndice()])
			{
				dfs(G, vi.getIndice());				
			}		
		}
	}
	
	public boolean stronglyConnected(int v, int w)
	{
		return id[v] == id[w]; 
	}
	
	public int id(int v)
	{ 
		return id[v];
	}
	
	public int count()
	{
		return count;
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
		tempo = tempo + 1;
		ui.f = tempo;
		ui.cor = Cores.PRETO;
		V[ui.getIndice()] = ui;
		id[ui.getIndice()] = ui.f;
		ordena();	
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
		tempo = tempo + 1;
		ui.f = tempo;
		ui.cor = Cores.PRETO;
		Vtransp[ui.getIndice()] = ui;
	}
	
	
	
	public int getcontadorGd()
	{
		return contadorGd;
	}
	
	public int getcontadorGt()
	{
		return contadorGt;		
	}
		
	private void ordena()
	{
		OrdenaPorInsercao s = new OrdenaPorInsercao(id);
        idOrdenado = s.getValoresOrdenados();
        idindexOrdenado = s.getIndicesOrdenados();
		
	}
		
		
	
	
	
	
	

}
