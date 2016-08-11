package ep;

import ep0.Grafo;
import ep0.Arco;
import ep0.Vertice;
import ep0.Constantes;
import ep0.Cores;
import ep0.Fila;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class DFO {
	
	private boolean[] marked;
	private Queue<Integer> pre; // vertices in preorder
	private Queue<Integer> post; // vertices in postorder
	private Stack<Integer> reversePost; // vertices in reverse postorder
	private Grafo G;
	private Fila Q;
	private int totalVertices;
	private Vertice[] V;
	@SuppressWarnings("rawtypes")
	private LinkedList [] uiAdj;
	private Vertice u; 						//um vertice
	private Vertice vi;					//um vertice
	private Arco aresta;					//uma aresta
	
	
	public DFO(Grafo g)
	{
		this.G = new Grafo();
		this.G = g;
		this.totalVertices = G.getTotalDeVertices();
		this.V = new Vertice[totalVertices];
		this.uiAdj = new LinkedList[totalVertices];
		uiAdj = G.getListasDeAdjacencia();
		this.Q = new Fila();
		this.u = new Vertice(0,null); 					//um vertice
		this.vi = new Vertice(0,null);					//um vertice
		this.aresta = new Arco(null,null);				//uma aresta
		
		pre  = new LinkedList<Integer>(); 
		post = new LinkedList<Integer>();
		reversePost = new Stack<Integer>();
		marked = new boolean[totalVertices];
		
		for (int v = 0; v < totalVertices; v++)
		{
			if (!marked[v])
			{
				dfs(G, v);
			}
		}
			
	}
	
	private void dfs(Grafo G, int v)
	{
		pre.add(v);
		marked[v] = true;
		
		@SuppressWarnings("unchecked")
		Iterator<Arco> it = uiAdj[v].iterator();

		while (it.hasNext()) 
		{
			aresta = (Arco)it.next();
			vi = aresta.getDestino();
			if(!marked[vi.getIndice()])
			{
				dfs(G,vi.getIndice());				
			}
		}
			
		post.add(v);
		reversePost.push(v);
	}
	
	public Iterable<Integer> pre()
	{ 
		return pre; 
	}
	
	public Iterable<Integer> post()
	{ 
		return post; 
	}
	
	public Iterable<Integer> reversePost()
	{ 
		return reversePost;
	}

}
