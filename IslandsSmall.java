package homework5TrieUnionFind;

import java.util.Scanner;

//Islands

public class IslandsSmall {



	public static int numIslands(int[][] grid, int height) {
		// m is the number of rows, n the number of columns
		int m = grid.length, n = grid[0].length;
		int numIslands = 0;
		boolean[][] visited = new boolean[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] > height && !visited[i][j]) {
					numIslands++;
					DFS(i, j, grid, visited, height);
				}
			}
		}

		return numIslands;
	}

	public static void DFS(int i, int j, int[][] grid, boolean[][] visited, int height) {
		visited[i][j] = true;
		int m = grid.length, n = grid[0].length;

		if (i > 0 && grid[i-1][j] > height && !visited[i-1][j]) {
			DFS(i-1, j, grid, visited, height);
		}

		if (i < m-1 && grid[i+1][j] > height && !visited[i+1][j]) {
			DFS(i+1, j, grid, visited, height);
		}

		if (j > 0 && grid[i][j-1] > height && !visited[i][j-1]) {
			DFS(i, j-1, grid, visited, height);
		}

		if (j < n-1 && grid[i][j+1] > height && !visited[i][j+1]) {
			DFS(i, j+1, grid, visited, height);
		}
	}

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in); 
		int n = in.nextInt(); 
		int m = in.nextInt(); 
		int q = in.nextInt(); 
		
		int[][] grid = new int[n][m]; 
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				grid[i][j] = in.nextInt(); 
			}
		}
		
		for (int k = 0; k < q; k++) {
			int height = in.nextInt(); 
			int numIslands = numIslands(grid, height); 
			System.out.println(numIslands); 
		}
		
		
		
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.printf("%d\t", matrix[i][j]);
			}
			System.out.println("");
		}
		System.out.println("");
	}
}