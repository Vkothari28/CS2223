package vkothari.hw5;

import algs.days.day18.AVL;
import algs.days.day20.DepthFirstPaths;
import algs.days.day20.DepthFirstSearch;
import algs.days.day21.BreadthFirstPaths;

import java.util.Iterator;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

/** Standard undirected Grah implementation, as starting point for Q2 and Q3 on HW5. */
import algs.days.day18.AVL;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

/** Standard undirected Graph implementation, as starting point for Q2 and Q3 on HW5. */
public class Graph {

	final int V;
	int E;
	Bag<Integer>[] adj;

	/**
	 * Initializes an empty graph with <tt>V</tt> vertices and 0 edges.
	 * param V the number of vertices
	 *
	 * @param  V number of vertices
	 * @throws IllegalArgumentException if <tt>V</tt> < 0
	 */
	public Graph(int V) {
		if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}
	}

	/** Added this method for day20 to load graph from file. */
	public Graph (In in) {
		this (in.readInt());
		int E = in.readInt();
		for (int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			addEdge (v,w);
		}
	}

	public int V() { return V; }
	public int E() { return E; }


	/** Adds the undirected edge v-w to this graph. */
	public void addEdge(int v, int w) {
		E++;
		adj[v].add(w);
		adj[w].add(v);
	}


	/** Returns the vertices adjacent to vertex <tt>v</tt>. */
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}

	/** Returns the degree of vertex <tt>v</tt>. */
	public int degree(int v) {
		return adj[v].size();
	}

	/** Fill in this method to determine if undirected graph is connected. */
	public boolean connected() {
		return connectedcheck(this, 0);
	}
	
	public boolean connectedcheck(Graph G, int x) {
		boolean [] marked = new boolean[G.V()];
		int [] edgeTo = new int[G.V()];
		int [] distTo = new int [G.V()]; 
		Queue<Integer> Queue = new Queue<Integer>();
		for (int v = 0; v < G.V(); v++)
			
			distTo[v] = Integer.MAX_VALUE;
		
		distTo[x] = 0;

		
		marked[x] = true;
		Queue.enqueue(x);

		while (!Queue.isEmpty()) {
			int v = Queue.dequeue();
			for (int o : G.adj(v)) {
				if (!marked[o]) {
					edgeTo[o] = v;
					distTo[o] = distTo[v] + 1;
					marked[o] = true;
					
					Queue.enqueue(o);
				}
			}
		}
		boolean connects = true;
		for (boolean b : marked) {
			if (!b) {
				connects = false;
			}
		}
		return connects;
	}


	/** 
	 * The diameter of graph is the maximum distance between any pair of vertices. 
	 * 
	 * If a graph is not connected, then Integer.MAX_VALUE must be returned.
	 * @return
	 */
	public int diameter() {
		int d = Integer.MAX_VALUE;// setting the value of the diameter
		
		for (int i=0; i<this.adj.length; i++) {
		
			int [] dist = distancechecker(this, i);
			
			for (int di : dist) {
				if (di >d || (d == Integer.MAX_VALUE && di != 0)) {
				
					d = di;
				}
			}
		}
		return d;// returning max value 
	}
	
	public int[] distancechecker(Graph G, int x) {
		boolean [] marked = new boolean[G.V()];
		int [] edgeTo = new int[G.V()];
		int [] distTo = new int [G.V()]; 
		Queue<Integer> q = new Queue<Integer>();
		for (int v = 0; v < G.V(); v++)
			distTo[v] = Integer.MAX_VALUE;
		distTo[x] = 0;

		marked[x] = true;
		q.enqueue(x);

		while (!q.isEmpty()) {
			int v = q.dequeue();
			for (int w : G.adj(v)) {
				if (!marked[w]) {
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
					marked[w] = true;
					q.enqueue(w);
				}
			}
		}
		return distTo;
	}
	


	/** 
	 * The status of a given vertex, v.
	 * 
	 * If the graph is not connected, then this method is not responsible for return value.
	 */
	public int status(int v) {
		return statuscheck(this, v);
	}

	public int statuscheck(Graph G, int x) {
		int stats = 0;
		
		int [] edgeTo = new int[G.V()];
		
		boolean [] marked = new boolean[G.V()];
		
		
		
		int [] distTo = new int [G.V()]; 
	Queue<Integer> newQ = new Queue<Integer>();
		for (int v = 0; v < G.V(); v++)
			distTo[v] = Integer.MAX_VALUE;
		distTo[x] = 0;

		marked[x] = true;
		newQ.enqueue(x);

		while (!newQ.isEmpty()) {
			int dq = newQ.dequeue();
			for (int o : G.adj(dq)) {
				if (!marked[o]) {
					
					edgeTo[o] = dq;
					
					distTo[o] = distTo[dq] + 1;
					
					marked[o] = true;
					newQ.enqueue(o);
				}
			}
		}
		for (int j = 0; j<G.V(); j++) {
			if (distTo[j] != Integer.MAX_VALUE) {
				stats += distTo[j];
			}
		}
		return stats;
	}

		
	/**
	 * Determine if all status(v) values within the graph represent different values.
	 * 
	 */
	public boolean statusInjective() {
		AVL<Integer> myTree = new AVL<Integer>();
		
		int adlength = this.adj.length;
		for (int l = 0; l < adlength; l++) {
			if (myTree.contains(status(l))) {
				
				return false;
			}
			else {
		
				myTree.insert(status(l));
			}
		}
		return true;
	}

	/**
	 * Returns a string representation of this graph.
	 *
	 * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
	 *         followed by the <em>V</em> adjacency lists
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges " + "\n");
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (int w : adj[v]) {
				s.append(w + " ");
			}
			s.append("\n");
		}
		return s.toString();
	}

}