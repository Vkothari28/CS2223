package vkothari.hw5;

import algs.days.day19.Graph;

import edu.princeton.cs.algs4.*;

/**
 * Compute the BreadthFirst distance and DepthFirst distance for each vertex from the initial vertex 0.
 * Call these bfsDistToTo[v] and dsfDistTo[v]. 
 * 
 * Observe that bfsDistToTo[v] is always smaller than or equal to dfsDistTo[v].
 * Now define excess[v] = dfsDistTo[v] - bfsDistToTo[v]. This assignment asks you to compute the
 * sum total of excess[v] for all vertices in the graph G.
 * 
 * Note that it is possible that some vertices are unreachable from s, and thus the dfsDistTo[v] and
 * bfsDistToTo[v] would both be INFINITY. 
 */
public class SearchCompare {
	public static int excess(Graph G, int s) {
		int xs=0;
	
		boolean [] bfsMarked = new boolean [G.V()];
	
		boolean [] dfsMarked = new boolean [G.V()];
	
		int [] bfsDistTo = new int [G.V()];
	
		int [] dfsDist = new int [G.V()];
	
		int [] bfsEdgeTo = new int [G.V()];
	
		int [] dfsEdge = new int [G.V()];

	bfs(G, s, bfsMarked, bfsDistTo, bfsEdgeTo);
	dfs(G, s, dfsMarked, dfsEdge);
	for (int n = 0; n<G.V(); n++) {
		dfsDist[n] = dfsDistTo(G, s, n, dfsMarked, dfsEdge);
	}
	
	for (int v = 0; v<G.V(); v++) {
		
		
		xs += (dfsDist[v] - bfsDistTo[v]);
	}

	return xs;
}

public static void bfs(Graph G, int s, boolean [] bfsMarked, int [] bfsDistTo, int [] bfsEdgeTo) {
	Queue<Integer> newQ = new Queue<Integer>();
	for (int v = 0; v < G.V(); v++) {
		bfsDistTo[v] = Integer.MAX_VALUE;
	}

	bfsMarked[s] = true;
	bfsDistTo[s] = 0;
	newQ.enqueue(s);
	while (!newQ.isEmpty()) {
		int v = newQ.dequeue();
	
		for (int w : G.adj(v)) {
			
			if (!bfsMarked[w]) {
				
				bfsEdgeTo[w] = v;
				
				bfsDistTo[w] = bfsDistTo[v] + 1;
				
				bfsMarked[w] = true;
				newQ.enqueue(w);
			}
		}
	}
}

public static void dfs(Graph G, int s, boolean [] dfsMarked, int [] dfsEdge) {
	dfsMarked[s] = true;
	for (int w : G.adj(s))
		if (!dfsMarked[w])
		{
			dfsEdge[w] = s;
			dfs(G, w, dfsMarked, dfsEdge);
		}
}

public static int dfsDistTo(Graph G, int s, int v, boolean[] dfsMarked, int[] dfsEdge) {
	int Vdis = -1;
	if (!dfsMarked[v]) { return Integer.MAX_VALUE; }
	Stack<Integer> path = new Stack<Integer>();
    for (int x = v; x != s; x = dfsEdge[x])
        path.push(x);
    path.push(s);
    
    while (!path.isEmpty()) {
    	path.pop();
    	Vdis++;
    }
    
    return Vdis;
}


	public static void main(String[] args) {
		String input;
		if (args.length != 0) {
			input = args[0];
		} else {
			input = "tinyG.txt";
		}
		In in = new In(input);
		Graph g = new Graph(in);

		
		// Compute and report Excess on tinyG.txt by default
		System.out.println("Excess:" + SearchCompare.excess(g, 0));
		
		for (int N = 4; N <= 1024; N *= 2) {
			System.out.print(N + "\t" );
			for (double p = 0.5; p <= 1.0; p += 0.5) {
				Graph gr = new Graph(N);
				
				// every possible edge exists with probability p
				for (int i = 0; i < N-1; i++) {
					for (int j = i+1; j < N; j++) {
						if (Math.random() < p) {
							gr.addEdge(i, j);
						}
					}
				}
				
				System.out.print(SearchCompare.excess(gr, 0) + "\t");
			}
			System.out.println();
			
		}
	}

}