package homework13DynamicProgrammingII;

import java.io.*;
import java.util.*;

public class CoinsHard {

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out)); 

		final int MAX_S = 1000;
		final int MAX_K = MAX_S;
		final int M = 1000000000 + 7;
		int n = in.nextInt();
		int[] C = new int[n+1];
		for (int i = 1; i <= n; i++) {
			C[i] = in.nextInt();
		}
		
		// N[s][m] is the number of ways to change a total value of s using the first m types of coins
		// T[s][m][k] is the number of ways to change a total value of s using exactly k of the first m types of coins
		// T[s][m][k] = T[s][m-1][k] + T[s-C[m]][m][k-1]
		
		int[][] N = new int[MAX_S+1][n+1];
		int[][][] T = new int[MAX_S+1][n+1][MAX_K+1];
		for (int m = 0; m <= n; m++) T[0][m][0] = 1;
		
		/*
		for (int s = 1; s <= MAXVALUE; s++) {
			for (int m = 1; m <= n; m++) {
				N[s][m] = N[s][m-1];
				if (s >= C[m]) N[s][m] = (N[s][m] + N[s-C[m]][m]) % M;
			}
		}*/
		
		for (int k = 1; k <= MAX_K; k++) {
			for (int s = 1; s <= MAX_S; s++) {
				for (int m = 1; m <= n; m++) {
					T[s][m][k] = T[s][m-1][k];
					if (s >= C[m]) {
						T[s][m][k] = (T[s][m-1][k] + T[s-C[m]][m][k-1]) % M;
					}
					N[s][m] = (N[s][m] + T[s][m][k]) % M;
				}
			}
		}
		
		int q = in.nextInt();
		for (int i = 0; i < q; i++) {
			int query = in.nextInt();
			int s = in.nextInt();
			if (query == 1) out.write(N[s][n] + "\n");
			else {
				int k = in.nextInt();
				out.write(T[s][n][k] + "\n");
			}
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
