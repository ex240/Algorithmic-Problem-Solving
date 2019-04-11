package homework13DynamicProgrammingII;

import java.io.*;
import java.util.*;

public class ColoringTilesSmall {

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out)); 
		final int M = 1000000000 + 7;
		
		int n = in.nextInt();
		int m = in.nextInt();
		int C = in.nextInt();
		char[][] wall = new char[2][m];
		for (int i = 0; i < 2; i++) {
			wall[i] = in.next().toCharArray();
		}
		
		/*
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < m; j++) {
				out.write(wall[i][j] + " ");
			}
			out.write("\n");
		}
		*/
		
		// T[k][c1][c2] = the # of ways to color the first k columns so that in Column k
		// the first cell has color c1 and the second has color c2
		// Total[k] = the total # of ways to color the first k columns 
		int[][][] T = new int[m+1][C][C];
		int[] Total = new int[m+1];
		Total[0] = 1;
		
		// TC1[k][c1] = the total # of ways to color the first k columns so that 
		// the first cell in Column k has color c1
		// TC2[k][c2] = the total # of ways to color the first k columns so that 
		// the second cell in Column k has color c2
		int[][] TC1 = new int[m+1][C];
		int[][] TC2 = new int[m+1][C];
		
		for (int k = 1; k <= m; k++) {
			if (wall[0][k-1] == '#' && wall[1][k-1] == '#') {
				Total[k] = Total[k-1]; continue;
			}
			
			if (wall[0][k-1] == '#' && wall[1][k-1] == '.') {
				for (int c2 = 0; c2 < C; c2++) {
					TC2[k][c2] = Total[k-1] - T[k-1][c2][c2];
					if (TC2[k][c2] < 0) TC2[k][c2] += M;
					Total[k] = (Total[k] + TC2[k][c2]) % M;
				}
				continue;
			}
			
			if (wall[0][k-1] == '.' && wall[1][k-1] == '#') {
				for (int c1 = 0; c1 < C; c1++) {
					TC1[k][c1] = Total[k-1] - T[k-1][c1][c1];
					if (TC1[k][c1] < 0) TC1[k][c1] += M;
					Total[k] = (Total[k] + TC1[k][c1]) % M;
				}
				continue;
			}
			
			//System.out.printf("k = %d\n", k);
			
			for (int c1 = 0; c1 < C; c1++) {
				for (int c2 = 0; c2 < C; c2++) {
					//System.out.printf("c1 = %d, c2 = %d\n", c1, c2);
					if (c1 != c2) {
						T[k][c1][c2] = Total[k-1] - T[k-1][c1][c1] - T[k-1][c2][c2];
					}
					else { // if c1 == c2
						T[k][c1][c2] = Total[k-1] - TC1[k-1][c1] - TC2[k-1][c1] + T[k-1][c1][c1];
					}
					//System.out.println(T[k][c1][c2]);
					if (T[k][c1][c2] < 0) T[k][c1][c2] += M;
					//System.out.println(T[k][c1][c2]);
					TC1[k][c1] = (TC1[k][c1] + T[k][c1][c2]) % M;
					TC2[k][c2] = (TC2[k][c2] + T[k][c1][c2]) % M;
					Total[k] = (Total[k] + T[k][c1][c2]) % M;
				}
			}
		}
		
		out.write(Total[m] + "\n");
		
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
