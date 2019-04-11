package homework12DynamicProgrammingI;

import java.io.*;
import java.util.*;

public class SubRectanglesSmall {

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));  
		
		int n = in.nextInt();
		int m = in.nextInt();
		int s = in.nextInt();
		
		int[][] A = new int[n+1][m+1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				A[i][j] = in.nextInt();
			}
		}
		
		int[][] S = new int[n+1][];
		for (int i = 1; i <= n; i++) {
			S[i] = prefixSum(A[i]);
		}
		
		/*
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				out.write(S[i][j] + " ");
			}
			out.write("\n");
		}
		*/
		
		long count = 0;
		for (int j1 = 1; j1 <= m; j1++) {
			for (int j2 = j1; j2 <= m; j2++) {
				int[] R = new int[n+1];
				for (int i = 1; i <= n; i++) {
					R[i] = S[i][j2] - S[i][j1-1];
				}
				int[] T = prefixSum(R);
				for (int i1 = 1; i1 <= n; i1++) {
					for (int i2 = i1; i2 <= n; i2++) {
						if (T[i2] - T[i1-1] <= s) count += 1;
					}
				}
			}
		}
		
		out.write(count + "\n");
		
		out.close();
		
	}
	
	public static int[] prefixSum(int[] A) {
		int n = A.length - 1;
		int[] S = new int[n+1];
		for (int i = 1; i <= n; i++) {
			S[i] = S[i-1] + A[i];
		}
		return S;
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

