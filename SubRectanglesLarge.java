package homework12DynamicProgrammingI;

import java.io.*;
import java.util.*;

public class SubRectanglesLarge {

	public static void main(String[] args) throws IOException {
		
		
		InputReader in = new InputReader(System.in);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));  
		
		int n = in.nextInt();
		int m = in.nextInt();
		int s = in.nextInt();
		
		int row = Math.max(n, m);
		int col = Math.min(n, m);
		int[][] A = new int[row+1][col+1];
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				int entry = in.nextInt();
				if (row == n) A[i][j] = entry;
				else A[j][i] = entry;
			}
		}
	
		
		int[][] S = new int[row+1][];
		for (int i = 1; i <= row; i++) {
			S[i] = prefixSum(A[i]);
		}
		
		/*
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				out.write(S[i][j] + " ");
			}
			out.write("\n");
		}
		*/
		
		
		long count = 0;
		for (int j1 = 1; j1 <= col; j1++) {
			for (int j2 = j1; j2 <= col; j2++) {
				int[] R = new int[row+1];
				for (int i = 1; i <= row; i++) {
					R[i] = S[i][j2] - S[i][j1-1];
				}
				int[] T = prefixSum(R);
				/*
				for (int i1 = 1; i1 <= n; i1++) {
					for (int i2 = i1; i2 <= n; i2++) {
						if (T[i2] - T[i1-1] <= s) count += 1;
					}
				}
				*/
				for (int i = 1; i <= row; i++) {
					count += binSearch(T, i, row, s);
				}
			}
		}
		
		out.write(count + "\n");
		
		
		out.close();
		
	}
	
	public static int binSearch(int[] T, int start, int end, int sum) {
		if (T[start] - T[start-1] > sum) return 0;
		
		int l = start, r = end;
		while (l <= r) {
			int mid = l + (r - l) / 2;
			if (T[mid] - T[start-1] <= sum) {
				l = mid + 1;
			}
			else {
				r = mid - 1;
			}
		}
		
		return l - start;
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

