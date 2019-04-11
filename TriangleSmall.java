package homework10GraphAlgorithmsI;

import java.util.*;

public class TriangleSmall {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in); 
		int n = sc.nextInt(); 
		int m = sc.nextInt(); 
		
		
		
		
		ArrayList<Integer>[] G = new ArrayList[n+1];
		Set<Integer>[] GR = new HashSet[n+1];
		
		for (int i = 0; i < m; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int min = Math.min(x, y);
			int max = Math.max(x, y);
			if (G[min] == null) G[min] = new ArrayList<>();
			G[min].add(max);
			if (GR[max] == null) GR[max] = new HashSet<>();
			GR[max].add(min);
		}
		
		Map<Integer,Integer>[] G2 = new HashMap[n+1];
		for (int i = 1; i <= n; i++) {
			if (G[i] == null) continue;
			G2[i] = new HashMap<>();
			for (int j : G[i]) {
				if (G[j] == null) continue;
				for (int k : G[j]) {
					int count = G2[i].getOrDefault(k, 0);
					G2[i].put(k, count+1);
				}
			}
		}
		
		int triangles = 0;
		for (int i = 1; i <= n; i++) {
			if (G2[i] == null) continue;
			for (Map.Entry<Integer, Integer> e : G2[i].entrySet()) {
				int k = e.getKey();
				int v = e.getValue();
				if (GR[k] != null && GR[k].contains(i)) triangles += v;
			}
		}
		
		System.out.println(triangles);

	}

}
