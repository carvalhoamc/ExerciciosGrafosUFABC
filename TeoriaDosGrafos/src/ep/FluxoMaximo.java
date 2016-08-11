package ep;

import ep0.GrafoPonderado;
import ep0.Arco;
import ep0.Vertice;
import ep0.Constantes;
import ep0.Cores;
import ep0.FilaDePrioridade;
import ep0.Grafo;

import java.util.Iterator;
import java.util.LinkedList;




public class FluxoMaximo {

	private GrafoPonderado Gp;
	private FilaDePrioridade Qp;
	private int totalVertices;
	private Vertice[] V;
	private LinkedList [] uiAdj;
	private Vertice s;          //calculo do fluxo maximo entre s e t      
	private Vertice t;
	private Vertice uAux;					//um vertice
	private Arco aresta;					//uma aresta
	private int fluxoMaximo;
	
	public FluxoMaximo(GrafoPonderado g) {
		this.Gp = new GrafoPonderado();
		this.Gp = g;
		
		this.totalVertices = Gp.getTotalDeVertices();
		
		this.V = new Vertice[totalVertices];
		this.V = Gp.getVertices();
		
		this.uiAdj = new LinkedList[totalVertices];
		this.uiAdj = Gp.getListasDeAdjacencia();
		
		this.s = new Vertice(0,null);
		this.t = new Vertice(0,null);
		this.uAux = new Vertice(0,null);					
		this.aresta = new Arco(null,null);
		fluxoMaximo = 0;
				
		for(int i = 0; i < totalVertices; i++)
		{
			V[i].d = Constantes.INFINITO;
			V[i].cor = Cores.BRANCO;
			V[i].chave = Constantes.INFINITO;
			V[i].pai = null;
		}
		V[0].chave = 0; //raiz r
		V[0].cor = Cores.PRETO;
		
		this.Qp = new FilaDePrioridade(V);
	
	
	
	
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
