package homework12DynamicProgrammingI;

import java.io.*;
import java.util.*;

public class RedBlackCardsIII {

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));  
		
		int n = in.nextInt(); 
		int m = in.nextInt(); 
		int[] R = new int[n+1];
		int[] B = new int[m+1];
		int uR = 0, uB = 0;
		for (int i = 1; i <= n; i++) {
			R[i] = in.nextInt();
			uR += R[i];
		}
		for (int i = 1; i <= m; i++) {
			B[i] = in.nextInt();
			uB += B[i];
		}
		
		int u = Math.min(uR, uB);
		int[] NR = SubsetSum(R, u); // NR[s] = the parity of the # of subsets of R that sum up to s
		int[] NB = SubsetSum(B, u); // NB[s] = the parity of the # of subsets of B that sum up to s
		
		int parity = 0;
		for (int s = 1; s <= u; s++) {
			if (NR[s] == 1 && NB[s] == 1) parity ^= 1;
		}
		
		if (parity == 1) out.write("alice\n");
		else out.write("bob\n");
		out.close();
	}
	
	public static int[] SubsetSum(int[] A, int u) {
		int n = A.length - 1;
		// N[i][s] = the parity of the # of subsets of A[1..i] that sum up to s
		int[][] N = new int[n+1][u+1];
		for (int i = 0; i <= n; i++) {
			N[i][0] = 1;
		}
		
		for (int i = 1; i <= n; i++) {
			for (int s = 1; s <= u; s++) {
				N[i][s] = N[i-1][s];
				if (A[i] <= s) {
					N[i][s] = (N[i][s] + N[i-1][s-A[i]]) % 2;
				}
			}
		}
		return N[n];
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

