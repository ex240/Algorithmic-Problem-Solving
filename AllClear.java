package homework9RangeQueries;

import java.util.Scanner;

public class AllClear {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in); 

		int n = sc.nextInt();
		int k = sc.nextInt(); 

		long[] colArray = new long[n]; 

		long[] delta = new long[n]; 
		long[] prefixSums = new long[n]; 

		long numShots = 0; 

		for (int i = 0; i < n; i ++) {
			colArray[i] = sc.nextLong(); 
		}

		if ( n == 0) {
			System.out.println("0"); 
		}
		else {
			if (k == 1) {
				for (int i = 0; i < n; i++) {
					numShots += colArray[i]; 
				}

			}

			else {
				for (int i = 0; i < n; i++) {


					// if we're on first column
					if ( i == 0) {
						long actualHeight = colArray[i]; 
						if (actualHeight > 0) {
							numShots += actualHeight; 
							delta[i] = delta[i] - actualHeight;  
							prefixSums[i] = delta[i]; 

							if ( (i + k) < n) {
								delta[i+k] = delta[i+k] + actualHeight; 
							}
						}

					}
					// for all the rest of the columns
					else {
						long curr = prefixSums[i-1] + delta[i];
						long actualHeight = curr + colArray[i];
						if ( (actualHeight) > 0) {
							numShots += actualHeight; 

							delta[i] = delta[i] - actualHeight; 
							prefixSums[i] = delta[i] + prefixSums[i-1] ; 

							if ( (i + k) < n) {
								delta[i+k] = delta[i+k] + actualHeight; 
							}
						}

						else {
							prefixSums[i] = delta[i] + prefixSums[i-1]; 
						}
					}

				}


			}
			System.out.println(numShots);
		}

	}

}