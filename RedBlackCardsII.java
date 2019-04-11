package homework11GraphAlgorithmsII;

import java.util.*;

public class RedBlackCardsII {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] num = new int[n][2];
		for (int i = 0; i < n; i++) {
			num[i][0] = sc.nextInt();
			num[i][1] = sc.nextInt();
		}
		sc.close();
		
		int[][] W = new int[n][n];
		for (int x = 0; x < n-1; x++) {
			for (int y = x+1; y < n; y++) {
				W[x][y] = Math.min(num[x][0]^num[y][1], num[x][1]^num[y][0]);	
				W[y][x] = W[x][y];
			}
		}
		
		// Prim's MST algorithm on the complete graph of n nodes.
		int[] cost = new int[n];
		for (int x = 0; x < n; x++) cost[x] = Integer.MAX_VALUE;
		cost[0] = 0;
		boolean[] added = new boolean[n];
		long weightMST = 0;
		int count = 0;
		
		while (count < n) {
			int minCost = Integer.MAX_VALUE;
			int cur=0;
			for (int x = 0; x < n; x++) {
				if (!added[x] && cost[x] < minCost) {
					minCost = cost[x];
					cur = x;
				}
			}
			added[cur] = true;
			weightMST += minCost;
			count += 1;
			for (int y = 0; y < n; y++) {
				if (!added[y] && W[cur][y] < cost[y]) {
					cost[y] = W[cur][y];
				}
			}
		}
		
		System.out.println(weightMST);
	}
}
