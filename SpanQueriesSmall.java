package homework4DataStructuresIIBitmask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class SpanQueriesSmall {


	public static void main(String[] args) throws NumberFormatException, IOException {


		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int q = Integer.parseInt(br.readLine());

		TreeMap<Integer, Integer> tMap = new TreeMap<>(); 

		String [] values = new String[q];

		for (int i = 0; i<q; i++) {


			String[] tokens = br.readLine().split(" ");

			// a = add an integer to collection
			if (tokens[0].equalsIgnoreCase("a")) {

				int addNum = Integer.parseInt(tokens[1]); 

				// add new key,value if doesn't exist in map already
				if (!tMap.containsKey(addNum)) {

					tMap.put(addNum, 1); 
				}


				// increment value of key if it exists in map already
				else {

					tMap.replace(addNum, (tMap.get(addNum) + 1) ); 

				}

			}

			// d = delete an integer x from collection
			else if (tokens[0].equalsIgnoreCase("d")) {

				int dltNum = Integer.parseInt(tokens[1]);


				// nothing to remove if it's not in the treemap 
				if (!tMap.containsKey(dltNum)) {

				}

				// else when key already exists in map
				else {

					// if only one entry of the number to delete
					if (tMap.get(dltNum) == 1) {
						
						// remove <key,value> pair
						tMap.remove(dltNum, 1); 
					}
					// else when more than one entry of number to delete
					else {
						
						// just decrement value of key
					tMap.replace(dltNum, (tMap.get(dltNum) - 1) ); 
					}
				}


			}

			// else when "s"| s = report span of collection 
			else {

				// if empty
				if (tMap.isEmpty()) {

					System.out.println("-1");

				}

				// report span if not empty
				else {


					int span = tMap.lastKey() - tMap.firstKey(); 
					System.out.println(span);

				}



			}

		}








	}

}
