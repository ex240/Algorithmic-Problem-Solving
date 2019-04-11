package homework10GraphAlgorithmsI;

import java.util.PriorityQueue;
import java.util.Scanner;

public class GridWalkEasy {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in); 
		int n = sc.nextInt(); 
		int m = sc.nextInt();
		int[][] grid = new int[n][m]; 
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				grid[i][j] = sc.nextInt(); 
			}
			
		}
		
		int[][] dist = new int[n][m];
		Dijkstra(grid, 0, 0, n-1, m-1, dist);
		if (dist[n-1][m-1] < Integer.MAX_VALUE) {
			System.out.println(dist[n-1][m-1]);
		}
		else System.out.println("impossible");
	}
	
	public static void Dijkstra(int[][] grid, int sx, int sy, int dx, int dy, int[][] dist) {
		int n = grid.length, m = grid[0].length;
		for (int i = 0; i < n; i++) 
			for (int j = 0; j < m; j++)
				dist[i][j] = Integer.MAX_VALUE;
		dist[sx][sy] = 0;
		PriorityQueue<GridNode> pq = new PriorityQueue<>();
		GridNode source = new GridNode(sx, sy, 0);
		pq.offer(source);
		
		while (!pq.isEmpty()) {
			GridNode cur = pq.poll();
			if (cur.x == dx && cur.y == dy) return;
			if (cur.d > dist[cur.x][cur.y]) continue;
			
			if (cur.x > 0 && grid[cur.x-1][cur.y] != 0 
					&& dist[cur.x][cur.y] + grid[cur.x-1][cur.y] < dist[cur.x-1][cur.y]) {
				dist[cur.x-1][cur.y] = dist[cur.x][cur.y] + grid[cur.x-1][cur.y];
				GridNode node = new GridNode(cur.x-1, cur.y, dist[cur.x-1][cur.y]);
				pq.offer(node);
			}
			
			if (cur.x < n-1 && grid[cur.x+1][cur.y] != 0 
					&& dist[cur.x][cur.y] + grid[cur.x+1][cur.y] < dist[cur.x+1][cur.y]) {
				dist[cur.x+1][cur.y] = dist[cur.x][cur.y] + grid[cur.x+1][cur.y];
				GridNode node = new GridNode(cur.x+1, cur.y, dist[cur.x+1][cur.y]);
				pq.offer(node);
			}
			
			if (cur.y > 0 && grid[cur.x][cur.y-1] != 0
					&& dist[cur.x][cur.y] + grid[cur.x][cur.y-1] < dist[cur.x][cur.y-1]) {
				dist[cur.x][cur.y-1] = dist[cur.x][cur.y] + grid[cur.x][cur.y-1];
				GridNode node = new GridNode(cur.x, cur.y-1, dist[cur.x][cur.y-1]);
				pq.offer(node);
			}
			
			if (cur.y < m-1 && grid[cur.x][cur.y+1] != 0
					&& dist[cur.x][cur.y] + grid[cur.x][cur.y+1] < dist[cur.x][cur.y+1]) {
				dist[cur.x][cur.y+1] = dist[cur.x][cur.y] + grid[cur.x][cur.y+1];
				GridNode node = new GridNode(cur.x, cur.y+1, dist[cur.x][cur.y+1]);
				pq.offer(node);
			}
		}
	}
}

class GridNode implements Comparable<GridNode>{
	int x;
	int y;
	int d;
	GridNode(int x, int y, int d) {this.x = x; this.y = y; this.d = d;}
	@Override
	public int compareTo(GridNode v) {
		return this.d - v.d;
	}
}
