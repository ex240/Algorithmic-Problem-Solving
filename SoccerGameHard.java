package homework13DynamicProgrammingII;

import java.io.*;
import java.util.*;

public class SoccerGameHard {

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out)); 
		
		int n = in.nextInt(); 	
		int[] S = new int[n+1]; 
		int maxSum = 0;
		for (int i = 1; i <= n; i++) {
			S[i] = in.nextInt(); 
			maxSum += S[i];
		}
		
		boolean[][][] dp = new boolean[n+1][n+1][maxSum * 2 + 1]; 
		int[][][] prev = new int[n+1][n+1][maxSum * 2 + 1]; 
		int mid = maxSum; 
		dp[0][0][mid] = true; 
		
		for (int i = 1; i <= n; i++) {
			for (int k = 0; k <= i && k <= n/2; k++) {
				for (int d = mid - maxSum; d <= mid + maxSum; d++) {
					if (dp[i-1][k][d]) {
						dp[i][k+1][d + S[i]] = true; 
						prev[i][k+1][d + S[i]] = k;
						dp[i][k][d - S[i]] = true; 
						prev[i][k][d - S[i]] = k;
					}
				}
			}
		}
		
		int ans = Integer.MAX_VALUE;
		int minD = -1, minK = -1;
		for (int d = mid - maxSum; d <= mid + maxSum; d++) {
			int absD = Math.abs(d - mid);
			//if (dp[n][n/2][d]) ans = Math.min(ans, Math.abs(d - mid));
			if (dp[n][n/2][d]) {
				if (absD < ans) {
					ans = absD; minD = d; minK = n/2;
				}
			}
			//else if (n % 2 == 0 && dp[n][n/2-1][d]) ans = Math.min(ans, Math.abs(d - mid));
			else if (n % 2 == 0 && dp[n][n/2-1][d]) {
				if (absD < ans) {
					ans = absD; minD = d; minK = n/2-1;
				}
			}
		}
		
		String L = "\n";
		String R = "\n";
		int d = minD, k = minK;
		for (int i = n; i > 0; i--) {
			int preK = prev[i][k][d];
			if (preK < k) {
				L = S[i] + " " + L; d -= S[i];
			}
			else {
				R = S[i] + " " + R; d += S[i];
			}
			k = preK;
		}
		
		out.write(ans + "\n");
		out.write(L);
		out.write(R);
		
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
