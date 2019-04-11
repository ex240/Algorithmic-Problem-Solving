package homework12DynamicProgrammingI;

import java.io.*;
import java.util.*;

public class SubsetSumLarge {
	
	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));  
		
		int n = in.nextInt(); 
		int u = in.nextInt(); 
		int[] A = new int[n+1];
		for (int i = 1; i <= n; i++) {
			A[i] = in.nextInt();
		}
		
		// N[i][s] = # of subsets of A[1..i] that sum up to s
		int[][] N = new int[n+1][u+1];
		N[0][0] = 1;
		final int M = 1000000000 + 7;
		
		for (int i = 1; i <= n; i++) {
			for (int s = 0; s <= u; s++) {
				N[i][s] = N[i-1][s];
				if (A[i] <= s) {
					N[i][s] = (N[i][s] + N[i-1][s-A[i]]) % M;
				}
			}
		}
		
		for (int s = 0; s <= u; s++) {
			out.write(s + ": " + N[n][s] + "\n");
		}
		
		out.close();
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
