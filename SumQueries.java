package homework3DataStructuresI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class SumQueries {

	public static void main(String[] args) {



		Scanner in = new Scanner(System.in); 

		int n = in.nextInt(); 
		int q = in.nextInt();

		int[] a = new int[n]; 

		// instantiate array and sort 
		for (int i = 0; i < n; i++) {
			a[i] = in.nextInt(); 
		}


		Arrays.sort(a);

		// for each query
		for (int i = 0; i < q; i++) {

			int s = in.nextInt(); 

			int frontPointer = 0; 
			int backPointer = a.length-1; 

			int totalCount = 0; 

			HashMap <Integer, Integer> newMap = new HashMap<>(); 

			// first traversal: store in hashmap
			for (int j = 0; j < a.length; j++) {

				if (!newMap.containsKey(a[j])) {
					newMap.put(a[j], 1); 
				}

				else {
					newMap.put(a[j], newMap.get(a[j]) + 1); 
				}

			}

			// second traversal: get number of unordered pairs
			for (int k = 0; k < a.length; k++) {

				if (newMap.get(s-a[k]) != null) {

					totalCount += newMap.get(s-a[k]); 

				}

				if ((a[k] + a[k]) == s) {
					totalCount --; 
				}



			}


			System.out.println(totalCount/2); 

		}
	}
}
