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


public class MST {
	
	private GrafoPonderado Gp;
	private FilaDePrioridade Qp;
	private int totalVertices;
	private Vertice[] V;
	private LinkedList [] uiAdj;
	private Vertice u;
	private Vertice uAux;					//um vertice
	private Arco aresta;					//uma aresta
	private int pesoArvoreGeradoraMinima;
	
	public MST(GrafoPonderado g) {
		
		pesoArvoreGeradoraMinima = 0;
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
		}
		V[0].chave = 0; //raiz r
		V[0].cor = Cores.PRETO;
		
		this.Qp = new FilaDePrioridade(V);
	}
	
	public int CalculaPesoArvoreGMinima()
	{
		while(!Qp.filaVazia())
		{
			u = (Vertice) Qp.extraiMinimo();
			Iterator<Arco> it = uiAdj[u.getIndice()].iterator();
			
			while (it.hasNext()) 
			{
				aresta = (Arco)it.next();
				uAux = aresta.getDestino(); 
				
				if ((Qp.estaNaFila(uAux)) && (aresta.getPeso() < uAux.chave))
				{
					uAux.chave = aresta.getPeso();
					uAux.pai = u;
					V[uAux.getIndice()] = uAux;
					V[uAux.getIndice()].cor = Cores.PRETO;
				}
			}
		}
		
		for(int i = 0; i < totalVertices; i++)
		{
			pesoArvoreGeradoraMinima += V[i].chave;			
		}

		
		return pesoArvoreGeradoraMinima;
	}
	
	
	
	
	

}
