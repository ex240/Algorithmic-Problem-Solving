package homework11GraphAlgorithmsII;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;


public class WordSolitaire {
	
	public static void main(String[] args) throws IOException {
		
		InputReader in = new InputReader(System.in);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = in.nextInt();
		LinkedList<EdgeWord>[] Adj = new LinkedList[52];
		
		// Construct the graph
		for (int i = 0; i < n; i++) {
			String w = in.next();
			char c1 = w.charAt(0);
			char c2 = w.charAt(w.length()-1);
			int v1 = Character.isUpperCase(c1)? c1-'A' : (26+c1-'a');
			int v2 = Character.isUpperCase(c2)? c2-'A' : (26+c2-'a');
			if (Adj[v1] == null) Adj[v1] = new LinkedList<>();
			Adj[v1].addLast(new EdgeWord(v2, w));
		}
		
		int indeg[] = new int[52];
		int outdeg[] = new int[52];
		for (int x = 0; x < 52; x++) {
			if (Adj[x] != null) {
				outdeg[x] = Adj[x].size();
				for (EdgeWord e : Adj[x]) {
					indeg[e.v] += 1;
				}
			}
			else Adj[x] = new LinkedList<>();
		}
		
		// Check the degrees of all nodes 
		boolean allEven = true;
		int src = -1, sink = -1;
		for (int v = 0; v < 52; v++) {
			int diff = outdeg[v] - indeg[v];
			if (diff == 0) continue;
			else if (diff == 1) {
				if (src == -1) {
					allEven = false;
					src = v;
				}
				else {
					out.write("impossible"+ "\n");
					out.close(); 
					return;
				}
			}
			else if (diff == -1) {
				if (sink == -1) {
					allEven = false;
					sink = v;
				}
				else {
					out.write("impossible"+"\n");
					out.close(); 
					return;
				}
			}
			else {
				out.write("impossible"+"\n");
				out.close(); 
				return;
			}
		}
		
		// If the program reaches this point, then either indeg = outdeg for all nodes, or it holds
		// for all but exactly 2 nodes, one with outdeg - indeg = 1 and the other with outdeg - indeg = -1.
		// Find an Eulerian path or cycle if one exists
		// Do need to check if all nodes are in a single weakly connected component.
		
		LinkedList<String> order = new LinkedList<>();
		if (allEven) {
			for (int v = 0; v < 52; v++) {
				if (indeg[v] == 0 && outdeg[v] == 0) continue;
				Euler(Adj, v, order); break;
			}
		}
		else {
			Euler(Adj, src, order);
		}
		
		if (order.size() == n) {
			for (String w : order) {
				out.write(w+"\n");
			}
		}
		else {
			out.write("impossible" + "\n");
			out.close(); 
		}
		
		out.close();
		
		
	}
	
	public static void Euler(LinkedList<EdgeWord>[] Adj, int x, LinkedList<String> order) {
		while (Adj[x].size() > 0) {
			EdgeWord e = Adj[x].getLast();
			Adj[x].removeLast();
			Euler(Adj, e.v, order);
			order.addFirst(e.w);
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

class EdgeWord {
	int v;
	String w;
	EdgeWord(int v, String w) {this.v = v; this.w = w;}
}
