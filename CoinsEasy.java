package homework13DynamicProgrammingII;

import java.io.*;
import java.util.*;

public class CoinsEasy {

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out)); 

		final int MAXVALUE = 1000;
		final int M = 1000000000 + 7;
		int n = in.nextInt();
		int[] C = new int[n+1];
		for (int i = 1; i <= n; i++) {
			C[i] = in.nextInt();
		}
		
		// N[s][m] is the number of ways to change a total value of s using the first m types of coins
		int[][] N = new int[MAXVALUE+1][n+1];
		for (int m = 0; m <= n; m++) N[0][m] = 1;
		
		for (int s = 1; s <= MAXVALUE; s++) {
			for (int m = 1; m <= n; m++) {
				N[s][m] = N[s][m-1];
				if (s >= C[m]) N[s][m] = (N[s][m] + N[s-C[m]][m]) % M;
			}
		}
		
		int q = in.nextInt();
		for (int i = 0; i < q; i++) {
			int query = in.nextInt();
			int s = in.nextInt();
			out.write(N[s][n] + "\n");
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
