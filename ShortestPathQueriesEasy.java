package homework11GraphAlgorithmsII;

import java.util.*;

public class ShortestPathQueriesEasy {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		// edge[i] is the distance of (i, i+1), 1 <= i <= n-1
		int[] edge = new int[n];
		for (int i = 0; i < m; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int d = sc.nextInt();
			int min = Math.min(x, y);
			edge[min] = d;
		}

		int[] distFrom1 = new int[n+1];
		int[] distFromN = new int[n+1];
		for (int i = 2; i <= n; i++) {
			distFrom1[i] = distFrom1[i-1] + edge[i-1];
		}
		for (int i = n-1; i >= 1; i--) {
			distFromN[i] = distFromN[i+1] + edge[i];
		}

		int q = sc.nextInt();
		for (int i = 0; i < q; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int d = sc.nextInt();
			int min = Math.min(x, y);
			int max = Math.max(x, y);
			int newDist = distFrom1[min] + d + distFromN[max];
			System.out.println(Math.min(distFrom1[n], newDist));
		}

		sc.close();

	}

}