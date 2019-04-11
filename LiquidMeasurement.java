package homework10GraphAlgorithmsI;

import java.util.*;

public class LiquidMeasurement {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in); 
		int[] s = new int[5]; 
		for (int i = 0; i < 5; i++) {
			s[i] = sc.nextInt(); 
		}
		
		int v = sc.nextInt(); 
		
		State start = new State(new int[] {s[0], 0, 0, 0, 0}, 0);
		PriorityQueue<State> pq = new PriorityQueue<>();
		pq.offer(start);
		Map<State, Integer> vol = new HashMap<>();
		vol.put(start, 0);
		
		while (!pq.isEmpty()) {
			State cur = pq.poll();
			if (cur.tube[0] == v) {
				System.out.println(cur.pour);
				return;
			}
			if (cur.pour > vol.get(cur)) continue;
			
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (i != j) { // Pour from tube i to tube j
						int pour = Math.min(cur.tube[i], s[j]-cur.tube[j]);
						if (pour > 0) {
							int[] tube = Arrays.copyOf(cur.tube, 5);
							tube[i] -= pour;
							tube[j] += pour;
							State newState = new State(tube, cur.pour + pour);
							int volume = vol.getOrDefault(newState, Integer.MAX_VALUE);
							if (cur.pour + pour < volume) {
								pq.offer(newState);
								vol.put(newState, newState.pour);
							}
						}
					}
				}
			}
		}
		
		System.out.println(-1);	
	}
}

class State implements Comparable<State> {
	int[] tube;
	int pour;
	State(int tube[], int pour) {
		this.tube = tube;
		this.pour = pour;
	}
	
	@Override
	public int compareTo(State s) {
		return this.pour - s.pour;
	}
	
	@Override
	public boolean equals(Object o) {
		return Arrays.equals(this.tube, ((State)o).tube);
	}
	
	@Override
	public int hashCode() {
		return Arrays.hashCode(tube);
	}
}
