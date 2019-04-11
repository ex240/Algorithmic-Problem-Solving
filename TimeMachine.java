package homework10GraphAlgorithmsI;

import java.util.*;

public class TimeMachine {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		Edge E[] = new Edge[m];
		for (int e = 0; e < m; e++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int w = sc.nextInt();
			E[e] = new Edge(x, y, w);
		}
		sc.close();
		
		String output;
		int earliest = BellmanFord(E, 1, n);
		output = (earliest == Integer.MIN_VALUE)? "-infinity " : Integer.toString(earliest) + " ";
		
		Edge EN[] = negate(E);
		int latest = BellmanFord(EN, 1, n);
		output += (latest == Integer.MIN_VALUE)? "infinity" : Integer.toString(-latest);
		
		System.out.println(output);

	}
	
	public static int BellmanFord(Edge[] E, int s, int n) {
		int[] dist = new int[n+1];
		for (int v = 1; v <= n; v++) dist[v] = Integer.MAX_VALUE;
		dist[s] = 0;
		
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < E.length; j++) {
				Edge e = E[j];
				int x = e.x, y = e.y, w = e.w;
				if (dist[x] < Integer.MAX_VALUE && dist[x] + w < dist[y]) {
					dist[y] = dist[x] + w;
				}
			}
		}
		
		for (int j = 0; j < E.length; j++) {
			Edge e = E[j];
			int x = e.x, y = e.y, w = e.w;
			if (dist[x] < Integer.MAX_VALUE && dist[x] + w < dist[y]) {
				return Integer.MIN_VALUE;
			}
		}
		
		int min = Integer.MAX_VALUE;
		for (int v = 1; v <= n; v++) {
			if (dist[v] < Integer.MAX_VALUE)
				min = Math.min(dist[v], min);
		}
		return min;
	}
	
	public static Edge[] negate(Edge[] E) {
		int m = E.length;
		Edge[] EN = new Edge[m];
		for (int j = 0; j < E.length; j++) {
			Edge e = E[j];
			EN[j] = new Edge(e.x, e.y, -e.w);
		}
		return EN;
	}
}

class Edge {
	int x;
	int y;
	int w;
	Edge(int x, int y, int w) {
		this.x = x; this.y = y; this.w = w;
	}
}
