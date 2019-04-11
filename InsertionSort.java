package homework12DynamicProgrammingI;

import java.io.*;
import java.util.*;

public class InsertionSort {
	
	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));  
		
		int n = in.nextInt();
		int[] X = new int[n+1];
		int[] Y = new int[n+1];
		for (int i = 1; i <= n; i++) {
			X[i] = in.nextInt();
		}
		for (int i = 1; i <= n; i++) {
			Y[i] = in.nextInt();
		}
		
		// Compute the LCS of X and Y
		int[][] L = new int[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			L[i][0] = 0;
			L[0][i] = 0;
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (X[i] == Y[j]) {
					L[i][j] = L[i-1][j-1] + 1;
				}
				else {
					L[i][j] = Math.max(L[i][j-1], L[i-1][j]);
				}
			}
		}
		
		int insert = n - L[n][n];
		out.write(insert + "\n");
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
