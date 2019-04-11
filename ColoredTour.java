package homework13DynamicProgrammingII;

import java.io.*;
import java.util.*;

public class ColoredTour {

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		//BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out)); 
		
		int n = in.nextInt();
		Point[] spot = new Point[n];
		for (int i = 0; i < n; i++) {
			double x = in.nextInt();
			double y = in.nextInt();
			char c = in.next().charAt(0);
			spot[i] = new Point(x, y, c);
		}
		
		/*
		for (int i = 0; i < n; i++) {
			System.out.printf("(%f, %f, %c)\n", spot[i].x, spot[i].y, spot[i].color);
		}
		*/
		
		// D[S][j] = the length of the shortest path from spot 0 that visits all spots in the subset S
		// exactly once and ends in spot j 
		// Each subset considered includes spot 0
		// Each subset is represented by a bit mask
		// Answer is given by the min{D[N-1][j]: 0 < j < n}
		
		int N = 1 << (n-1);
		double[][] D = new double[N][n];
		D[0][0] = 0;
		
		for (int mask = 1; mask < N; mask++) {
			// S is the subset that mask represents
			ArrayList<Integer> S = new ArrayList<>();
			S.add(0);
			int m = mask, k = 1;
			while (m > 0) {
				if (m % 2 == 1) S.add(k);
				m = m >> 1; k += 1;
			}
			
			D[mask][0] = Double.MAX_VALUE;
			
			for (int p = 1; p < S.size(); p++) {
				int j = S.get(p);
				int mask_minus_j = mask ^ (1 << (j-1));
				D[mask][j] = Double.MAX_VALUE;
				for (int r = 0; r < S.size(); r++) {
					if (r == p) continue;
					int i = S.get(r);
					if (D[mask_minus_j][i] == Double.MAX_VALUE) continue;
					if (spot[i].color == spot[j].color) continue;
					D[mask][j] = Math.min(D[mask][j], D[mask_minus_j][i] + dist(spot[i], spot[j]));
				}
			}
		}
		
		double minDist = Double.MAX_VALUE;
		for (int j = 0; j < n; j++) {
			minDist = Math.min(minDist, D[N-1][j]);
		}
		
		if (minDist < Double.MAX_VALUE) System.out.println(minDist);
		else System.out.println("impossible");
		
	}
	
	public static double dist(Point p, Point r) {
		return Math.sqrt((p.x-r.x)*(p.x-r.x) + (p.y-r.y)*(p.y-r.y));
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

class Point {
	double x;
	double y;
	char color;
	Point(double x, double y, char c) {
		this.x = x; this.y = y; color = c;
	}
}
