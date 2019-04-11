package homework9RangeQueries;

import java.util.Scanner;

public class RangeProductHard {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in); 
		int n = sc.nextInt(); 
		int q = sc.nextInt(); 
		int[] X = new int[n]; 
		
		for (int i = 0; i < n; i++) {
			X[i] = trailingZeros(sc.nextInt()); 
		}
		
		int[] val = new int[4*n];
		build(val, X, 0, 0, n-1);
		
		for (int i = 0; i < q; i ++) {
			String query = sc.next(); 
			int l = sc.nextInt(); 
			int r = sc.nextInt(); 
			if (query.equals("q")) {
				System.out.println(getSum(val, 0, 0, n-1, l-1, r-1));
			}
			else {// if query.equals("s")
				update(val, 0, 0, n-1, l-1, trailingZeros(r));
			}
		}
	}
	
	// build segment tree with the following: 
	// val array (array of size 4*n), A array (array of original n values), 
	// and range (nl, nr) is node k's managed range 
	public static void build(int[] val, int[] A, int k, int nl, int nr) {
		//System.out.printf("k = %d, nl = %d, nr = %d\n", k, nl, nr);
		if (nl == nr) {
			val[k] = A[nl];
			//System.out.printf("val[%d] = %d\n", k, val[k]);
			return;
		}
		int nm = (nl + nr) / 2;
		build(val, A, 2*k+1, nl, nm);
		build(val, A, 2*k+2, nm+1, nr);
		val[k] = val[2*k+1] + val[2*k+2];
		//System.out.printf("val[%d] = %d\n", k, val[k]);
	}
	
	public static int getSum(int[] val, int k, int nl, int nr, int l, int r) {
		if (r < nl || l > nr) return 0;
		
		if (l <= nl && r >= nr) return val[k];
		
		int nm = (nl + nr) / 2;
		int ansLeft = getSum(val, 2*k+1, nl, nm, l, r);
		int ansRight = getSum(val, 2*k+2, nm+1, nr, l, r);
		return ansLeft + ansRight;
	}
	
	public static void update(int[] val, int k, int nl, int nr, int x, int v) {
		if (x < nl || x > nr) return;
		
		if (x == nl && nl == nr) {
			val[k] = v;
			return;
		}
		
		int nm = (nl + nr) / 2;
		update(val, 2*k+1, nl, nm, x, v);
		update(val, 2*k+2, nm+1, nr, x, v);
		val[k] = val[2*k+1] + val[2*k+2];
		
	}
	
	public static int trailingZeros(int n) {
		int zeros = 0;
		while (n % 2 == 0) {
			zeros += 1;
			n = n >> 1;
		}
		return zeros;
	} 

}
