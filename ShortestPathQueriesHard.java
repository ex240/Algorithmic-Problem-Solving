package homework11GraphAlgorithmsII;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;


public class ShortestPathQueriesHard {

	public static void main(String[] args) throws IOException {
		

		InputReader in = new InputReader(System.in);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));  
		
		int n = in.nextInt(); 
		int m = in.nextInt(); 

		ArrayList<int[]>[] Adj = new ArrayList[n+1];
		
		for (int i = 0; i < m; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			int d = in.nextInt();
			if (Adj[x] == null) Adj[x] = new ArrayList<>();
			Adj[x].add(new int[] {y, d});
			if (Adj[y] == null) Adj[y] = new ArrayList<>();
			Adj[y].add(new int[] {x, d});
		}
		
		int[] distFrom1 = new int[n+1];
		Dijkstra(Adj, 1, distFrom1);
		
		int[] distFromN = new int[n+1];
		Dijkstra(Adj, n, distFromN);
		
		int q = in.nextInt();
		for (int i = 0; i < q; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			int d = in.nextInt();
			long newDist = Math.min((long)distFrom1[x]+distFromN[y], (long)distFrom1[y]+distFromN[x]);
			if (newDist < Integer.MAX_VALUE) {
				out.write(Long.toString(Math.min(distFrom1[n], newDist+d)) + "\n"); 
			}
			else out.write(distFrom1[n] + "\n");
		}
		
		out.close(); 
		
	}
	
	public static void Dijkstra(ArrayList<int[]>[] Adj, int s, int dist[]) {
		int n = dist.length - 1;
		for (int v = 1; v <= n; v++) {
			dist[v] = Integer.MAX_VALUE;
		}
		dist[s] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(s, 0));
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			if (cur.d > dist[cur.v]) continue;
			
			for (int i = 0; i < Adj[cur.v].size(); i++) {
				int[] e = Adj[cur.v].get(i);
				int u = e[0];
				int w = e[1];
				if (dist[cur.v] + w < dist[u]) {
					dist[u] = dist[cur.v] + w;
					pq.offer(new Node(u, dist[u]));
				}
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

class Node implements Comparable<Node> {
	int v;
	int d;
	Node(int v, int d) {
		this.v = v; this.d = d;
	}
	
	@Override
	public int compareTo(Node node) {
		return this.d - node.d;
	}
}
