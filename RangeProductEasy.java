package homework9RangeQueries;

import java.util.*;

public class RangeProductEasy {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in); 
		int n = sc.nextInt(); 
		int q = sc.nextInt(); 
		
		int[] P = new int[n+1]; 
		for (int i = 1; i <= n; i++) {
			P[i] = P[i-1] + trailingZeros(sc.nextInt()); 
		}

		
		for (int i = 0; i < q; i++) {
			String query = sc.next();
			int l = sc.nextInt();
			int r = sc.nextInt();
			System.out.println(P[r] - P[l-1]);
		}
		
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

