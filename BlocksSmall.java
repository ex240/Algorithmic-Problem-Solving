package homework8DivideandConquer;

import java.util.Scanner;

public class BlocksSmall {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		// long n = 1000000000000L;
		long n = sc.nextLong(); 
		int m = (int)Math.pow(10, 9)+7;
		System.out.println(tile(n,m));
	}

	public static long tile(long n, int m) {
		long[][] M = new long[][] {
			{0, 1, 0, 0},
			{0, 0, 1, 0},
			{0, 0, 0, 1},
			{2, 4, 1, 1}
		};
		
		long[] v = new long[] {1, 1, 2, 7};
		
		if (n < 4) {
			return v[(int) n];
		}
		
		long[] T = modMatVecMul(modMatExp(M, n-3, m), v, m);
		
		return T[3];
	}
	
	public static long[][] modMatExp(long[][] M, long n, int m) {
		if (n == 0) {
			return new long[][] {
				{1, 0, 0, 0},
				{0, 1, 0, 0},
				{0, 0, 1, 0},
				{0, 0, 0, 1}
			};
		}
		
		long[][] R = modMatExp(M, n/2, m);
		if (n % 2 == 0) {
			return modMatMul(R, R, m);
		}
		else {
			long[][] S = modMatMul(R, R, m);
			return modMatMul(S, M, m);
		}
	}

	public static long[][] modMatMul(long[][] A, long[][] B, int m) {
		long[][] C = new long[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					C[i][j] = (C[i][j] + (A[i][k] * B[k][j]) % m) % m;
				}
			}
		}
		return C;
	}
	
	public static long[] modMatVecMul(long[][] A, long[] x, int m) {
		long[] y = new long[4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				y[i] += A[i][j] * x[j];
			}
			y[i] = y[i] % m;
		}
		return y;
	}
	
}