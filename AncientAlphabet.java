package homework11GraphAlgorithmsII;

import java.util.*;

public class AncientAlphabet {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		// Construct the graph
		ArrayList<Integer>[] Adj = new ArrayList[k];
		String cur = sc.next();
		for (int i = 0; i < n-1; i++) {
			String next = sc.next();
			int j = 0;
			boolean impossible = true;
			while (j < cur.length() && j < next.length()) {
				char c1 = cur.charAt(j);
				char c2 = next.charAt(j);
				if (c1 != c2) {
					int v1 = Character.isUpperCase(c1)? c1-'A' : (26+c1-'a');
					int v2 = Character.isUpperCase(c2)? c2-'A' : (26+c2-'a');
					if (Adj[v1] == null) Adj[v1] = new ArrayList<>();
					Adj[v1].add(v2);
					impossible = false;
					break;
				}
				j += 1;
			}
			if (impossible && cur.length() > next.length()) {
				System.out.println("impossible");
				return;
			}
			cur = next;
		}
		
		
		// Detect if the graph has a cycle. If so output "impossible".
		char[] status = new char[k];
		for (int v = 0; v < k; v++) status[v] = 'U';
		for (int v = 0; v < k; v++) {
			if (status[v] == 'U') {
				if (!DFS(Adj, v, status)) {
					System.out.println("impossible");
					return;
				}
			}
		}
		
		// Topologically sort the graph using Kahn's algorithm.
		String order = Kahn(Adj);
		System.out.println(order);
	}
	
	public static boolean DFS(ArrayList<Integer>[] Adj, int v, char[] status) {
		status[v] = 'D';
		if (Adj[v] != null) {
			for (int u : Adj[v]) {
				if (status[u] == 'D') return false;
				else if (status[u] == 'U') {
					if (!DFS(Adj, u, status)) return false;
				}
			}
		}
		
		status[v] = 'F';
		return true;
	}
	
	public static String Kahn(ArrayList<Integer>[] Adj) {
		String order = "";
		int k = Adj.length;
		int[] indeg = new int[k];
		for (int x = 0; x < k; x++) {
			if (Adj[x] != null) {
				for (int y : Adj[x]) {
					indeg[y] += 1;
				}
			}
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int v = 0; v < k; v++) {
			if (indeg[v] == 0) pq.offer(v);
		}
		
		while (!pq.isEmpty()) {
			int x = pq.poll();
			char c = (x < 26)? (char)('A'+x) : (char)('a'+x-26);
			order += c;
			if (Adj[x] != null) {
				for (int y : Adj[x]) {
					indeg[y] -= 1;
					if (indeg[y] == 0) pq.offer(y);
				}
			}
		}
		
		return order;
	}

}
