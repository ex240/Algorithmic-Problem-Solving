package homework6CompleteSearch;

import java.util.Scanner;

public class BulbsSmall {
	
	
	public static void main(String[] args) {
		

		Scanner in = new Scanner(System.in); 

		int n = in.nextInt(); 
		int m = in.nextInt(); 

		char[][] input = new char[n][m]; 
		for ( int i = 0; i < n; i++) {
			String next = in.next(); 
			char[] row = next.toCharArray();
			for (int j = 0; j < m; j++) {
				input[i][j] = row[j]; 
			}

		}
		
		
		int row = input.length, col = input[0].length;
		int[][] bulb = new int[row][col];
		int[][] button = new int[row][col];
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++)
				bulb[i][j] = (input[i][j] == '*')? 1 : 0;
		}
		
		int min = -1;
		for (int num = 0; num < Math.pow(2, col); num++) {
			int BM = num;
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					bulb[i][j] = (input[i][j] == '*')? 1 : 0;
					button[i][j] = 0;
				}
			}
			
			int buttons = 0;
			for (int j = col-1; j >= 0; j--) {
				button[0][j] = BM % 2;
				buttons += button[0][j];
				bulb[0][j] ^= button[0][j];
				if (j > 0) bulb[0][j-1] ^= button[0][j];
				if (j < col-1) bulb[0][j+1] ^= button[0][j];
				if (row > 1) bulb[1][j] ^= button[0][j];
				BM = BM >> 1;
			}
		
			for (int i = 1; i < row; i++) {
				for (int j = 0; j < col; j++) {
					button[i][j] = bulb[i-1][j];
					buttons += button [i][j];
					bulb[i][j] ^= button[i][j];
					bulb[i-1][j] ^= button[i][j];
					if (i < row-1) bulb[i+1][j] ^= button[i][j];
					if (j > 0) bulb[i][j-1] ^= button[i][j];
					if (j < col-1) bulb[i][j+1] ^= button[i][j];
				}
				
			}
		
			boolean allOff = true;
			for (int j = 0; j < col; j++) {
				if (bulb[row-1][j] == 1) {
					allOff = false; break;
				}
			}
			if (allOff) {
				if (min == -1) min = buttons;
				else min = Math.min(min, buttons);
			}
		}
		
		System.out.println(min);
		
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

	
