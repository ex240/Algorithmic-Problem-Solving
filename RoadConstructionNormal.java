package homework11GraphAlgorithmsII;

import java.util.*;

public class RoadConstructionNormal {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		ArrayList<Integer>[] Adj = new ArrayList[n+1];
		ArrayList<Integer>[] Adj_T = new ArrayList[n+1];
		
		// Construct the graph
		for (int i = 0; i < m; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			if (Adj[x] == null) Adj[x] = new ArrayList<>();
			Adj[x].add(y);
			if (Adj_T[y] == null) Adj_T[y] = new ArrayList<>();
			Adj_T[y].add(x);
		}
		
		// Compute the SCCs of the graph using two DFS
		Stack<Integer> stack = new Stack<>();
		DFS(Adj_T, stack); // 1st DFS
		
		int numSCC = 0;
		int[] scc = new int[n+1];
		
		// 2nd DFS
		while (!stack.isEmpty()) {
			int v = stack.pop();
			if (scc[v] == 0) {
				numSCC += 1;
				exploreSCC(Adj, v, scc, numSCC);
			}
		}
		
		/*
		System.out.println(numSCC + " SCCs");
		for (int v = 1; v <= n; v++) {
			System.out.printf("scc[%d] = %d\n", v, scc[v]);
		}
		*/
		
		if (numSCC == 1) {
			System.out.println(0);
			return;
		}
		
		int[] indegSCC = new int[numSCC+1];
		Set<Integer>[] sccAdj = new HashSet[numSCC+1];
		int numSrc = 0, numSnk = 0;
		
		for (int x = 1; x <= n; x++) {
			int xcc = scc[x];
			if (sccAdj[xcc] == null) sccAdj[xcc] = new HashSet<>();
			if (Adj[x] != null) {
				for (int y : Adj[x]) {
					int ycc = scc[y];
					if (xcc != ycc && !sccAdj[xcc].contains(ycc)) {
						sccAdj[xcc].add(ycc);
						indegSCC[ycc] += 1;
					}
				}
			}
		} 
		
		for (int i = 1; i <= numSCC; i++) {
			if (indegSCC[i] == 0) numSrc += 1;
			if (sccAdj[i].size() == 0) numSnk += 1;
		}
		
		System.out.println(Math.max(numSrc, numSnk));
		
		/*
		System.out.printf("Source SCC: %d\n", numSrc);
		System.out.printf("Sink SCC: %d\n", numSnk);
		for (int i = 1; i <= numSCC; i++) {
			System.out.printf("SCC %d: ", i);
			System.out.print(sccAdj[i]);
			System.out.println(" indeg = " + indegSCC[i]);
		}
		*/
	}
	
	public static void DFS(ArrayList<Integer>[] Adj, Stack<Integer> stack) {
		int n = Adj.length - 1;
		boolean[] visited = new boolean[n+1];
		for (int v = 1; v <= n; v++) {
			if (!visited[v]) explore(Adj, v, visited, stack);
		}
	}
	
	public static void explore(ArrayList<Integer>[] Adj, int x, boolean[] visited, 
			Stack<Integer> stack) 
	{
		visited[x] = true;
		if (Adj[x] != null) {
			for (int y : Adj[x]) {
				if (!visited[y]) explore(Adj, y, visited, stack);
			}
		}
		
		stack.push(x);
	}
	
	public static void exploreSCC(ArrayList<Integer>[] Adj, int x, int[] scc, int sccNum) {
		scc[x] = sccNum;
		if (Adj[x] != null) {
			for (int y : Adj[x]) {
				if (scc[y] == 0) exploreSCC(Adj, y, scc, sccNum);
			}
		}
	}

}