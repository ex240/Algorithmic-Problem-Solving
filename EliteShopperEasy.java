package homework9RangeQueries;

import java.util.Scanner;

public class EliteShopperEasy {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in); 
		
		int n = sc.nextInt(); 
		int[] X = new int[n]; 
		
		for ( int i = 0; i < n; i++) {
			X[i] = sc.nextInt(); 
		}
		
		int[] val = new int[4*n];
		build(val, X, 0, 0, n-1);
		
		int q = sc.nextInt(); 
		
		for (int i = 0; i < q; i ++) {
			String query = sc.next(); 
			int l = sc.nextInt(); 
			int r = sc.nextInt(); 
			if (query.equals("q")) {
				System.out.println(getMin(val, 0, 0, n-1, l-1, r-1));
			}
			
			else if (query.equals("c")) {
				update(val, 0, 0, n-1, l-1, r); 
			}
			else {
				
			}
		}



	}
	
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
		val[k] = Math.min(val[2*k+1], val[2*k+2]);
		//System.out.printf("val[%d] = %d\n", k, val[k]);
	}
	
	public static int getMin(int[] val, int k, int nl, int nr, int l, int r) {
		if (r < nl || l > nr) return Integer.MAX_VALUE;
		
		if (l <= nl && r >= nr) return val[k];
		
		int nm = (nl + nr) / 2;
		int ansLeft = getMin(val, 2*k+1, nl, nm, l, r);
		int ansRight = getMin(val, 2*k+2, nm+1, nr, l, r);
		return Math.min(ansLeft, ansRight);
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
		val[k] = Math.min(val[2*k+1], val[2*k+2]);
		
	}

}

