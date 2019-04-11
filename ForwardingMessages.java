package homework11GraphAlgorithmsII;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;


public class ForwardingMessages {

	public static void main(String[] args) throws IOException {
		
		
		InputReader in = new InputReader(System.in);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));  
		
		int n = in.nextInt();
		int m = in.nextInt();
		ArrayList<Integer>[] Adj = new ArrayList[n+1];
		ArrayList<Integer>[] Adj_T = new ArrayList[n+1];
		
		// Construct the graph
		for (int i = 0; i < m; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
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
		
		int[] indegSCC = new int[numSCC+1];
		int[] min = new int[numSCC+1];
		for (int i = 1; i <= numSCC; i++) min[i] = Integer.MAX_VALUE;
		
		for (int x = 1; x <= n; x++) {
			int xcc = scc[x];
			if (x < min[xcc]) min[xcc] = x;
			if (Adj[x] != null) {
				for (int y : Adj[x]) {
					int ycc = scc[y];
					if (xcc != ycc) {
						indegSCC[ycc] += 1;
					}
				}
			}
		} 
		
		ArrayList<Integer> broadcast = new ArrayList<>();
		for (int i = 1; i <= numSCC; i++) {
			if (indegSCC[i] == 0) broadcast.add(min[i]);
		}
		
		Collections.sort(broadcast);
		out.write(broadcast.size() + "\n");
		for (int i : broadcast) out.write(i + " ");
		out.write("\n");
		
		out.close(); 
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
	
	static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

    }

}
