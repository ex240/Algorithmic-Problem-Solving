package homework7Greedy;

import java.util.*;
import java.io.*;

public class TutorSessionsHard {
	
	// node class with type "Long" because can be 10^12 input
    static class Node {
    	
    	// l is start time (si) 
        Long l;
        // r is duration time (di) 
        Long r; 
        Node(Long l, Long r) {
            this.l = l;
            this.r = r;
        }
    }
    public static void main(String[] args) {
    	
        InputReader in = new InputReader(System.in);
        // number of students for tutoring 
        int n = in.nextInt();
        // uniform duration of each session
        long k = in.nextLong();
        
        // segment array of nodes 
        Node[] seg = new Node[n];
        
        // for each new start time, calculate the ending time 
        // which is start time + (duration time - 1)
        for (int i = 0; i < n; i++) {
            long l = in.nextLong(); 
            long r = l + in.nextLong() - 1;
            // add new node to the array 
            seg[i] = new Node(l, r);
        }
        
        // sort the array based on comparing the starting times of each node
        // he makes a new comparator object and overrides it in the same method
        Arrays.sort(seg, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.l.compareTo(o2.l);
            }
        }
        // * notice this last ")" finishes the sort method
        );
        
        
        // new array of nodes
        Node[] merge = new Node[n];
        // pointer currently at 0 
        int ptr = 0;
        // the new merge array will have seg[0] in merge[0],
        // but after this ptr will become 1 
        merge[ptr++] = seg[0];
        
        // from 1 to the end of sorted seg array
        for (int i = 1; i < n; i++) {
        	
        	// if the starting time of the sorted seg array of nodes is greater than 
        	// the ending time of the last session 
            if (seg[i].l > merge[ptr - 1].r) {
            	// then add a new node to our merge array of nodes
                merge[ptr++] = seg[i];
            } 
            
            // else when the starting time of the sorted seg array of nodes is less than 
            // or equal to the ending time of the last session
            else {
            	// then we just have to change the ending time of the previous session of the merge array
            	// by setting the ending time to which ever is larger: 
            	// the ending time of seg or ending time of last session in merge array
                merge[ptr - 1].r = Math.max(merge[ptr - 1].r, seg[i].r);
            }
        }
        
        
        // records the number of sessions 
        long res = 0;
        // records where the starting point is  
        long st = -1;
        
        for (int i = 0; i < ptr; i++) {
            long l = merge[i].l;
            long r = merge[i].r;
            
            // if our "current" starting point is greater than the ending point 
            // for current node we're on in merge array 
            if (st > r) {
            	continue;
            }
            // if current starting point is less than start time of current node
            if (st < l) {
            	// l becomes new starting point ie st 
            	st = l;
            	}
            // calculates how many sessions we have to take to satisfy ending time of node (r) 
            long step = (r - st + 1 + k - 1) / k;
            // adds new step to our step counter
            res += step;
            // ( remember, st changes to l, if l is greater than our current ending time (st) ) 
            // st is now number of steps multiplied by duration of sessions (k), which becomes the 
            // ending time of the session which satisfies current node in merge array 
            st += step * k;
        }
        System.out.println(res);
    }
    
    
    // just for reading inputs 
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
}
