package homework12DynamicProgrammingI;

import java.io.*;
import java.util.*;

public class CollectingCoinsIIMedium {
	public static void main(String[] args) throws IOException {
		
		InputReader in = new InputReader(System.in);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));  

		int n = in.nextInt(); 
		int m = in.nextInt(); 
		
		int s = in.nextInt(); 
		int a = in.nextInt(); 
		int b = in.nextInt(); 
		int c = in.nextInt(); 
		int d = in.nextInt(); 
		
		int last = s;

		int[][] C = new int[n+1][m+1]; 
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				C[i][j] =  last; 
				last = ( ( (last * a) ^ b) + c) % d; 
			}
		}

		/*
		for (int i = 1; i <= n;i ++) {
			for (int j = 1; j <= m; j++) {
				out.write(C[i][j] + " ");
			}
			out.write("\n");
		}
		*/
		
		int[][] M = new int[n+1][m+1];
		int[][] N = new int[n+1][m+1];
		N[1][1] = 1;
		final int K = 10000;
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (C[i][j] == 0) continue;
				if (M[i][j-1] == 0 && M[i-1][j] == 0 && (i != 1 || j != 1)) continue;
				
				if (M[i][j-1] > M[i-1][j]) {
					M[i][j] += M[i][j-1] + C[i][j];
					N[i][j] = (N[i][j] + N[i][j-1]) % K;
				}
				else if (M[i][j-1] < M[i-1][j]) {
					M[i][j] += M[i-1][j] + C[i][j];
					N[i][j] = (N[i][j] + N[i-1][j]) % K;
				}
				else { // if M[i][j-1] == M[i-1][j]
					M[i][j] += M[i-1][j] + C[i][j];
					N[i][j] = (N[i][j] + N[i][j-1] + N[i-1][j]) % K;
				}
				//System.out.printf("M[%d][%d] = %d, N[%d][%d] = %d\n", i, j, M[i][j], i, j, N[i][j]);
			}
		}
		
		out.write(M[n][m] + " " + N[n][m] + "\n");
		
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