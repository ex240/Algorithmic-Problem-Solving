package homework8DivideandConquer;

import java.util.Scanner;

public class XORQuadrupletsLarge {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in); 
		int n = sc.nextInt(); 
		int[] A = new int[n]; 
		int[] B = new int[n]; 
		
		for (int i = 0; i < n; i++) {
			A[i] = sc.nextInt(); 
		}
		for (int j = 0; j < n; j++) {
			B[j] = sc.nextInt(); 
		}
		
		
		Trie trie = new Trie();
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				trie.insert(A[i]^A[j]);
			}
		}
		
		long count = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				int xor = B[i] ^ B[j];
				count += trie.search(xor);
			}
		}
		
		System.out.println(count);
	}

}
/*
class Trie {
	static final int size = 30;
	
	private TrieNode root; 
	  
	public Trie() {
		root = new TrieNode();
	}
	  
	public void insert(int val) {
		TrieNode node = root;
	    for (int i = size-1; i >= 0; i--) {
	    	int bit = (val >> i) % 2; // The i-th bit of val
	    	if (!node.containsKey(bit)) {
	    		node.put(bit, new TrieNode());
	    	}
	    	node = node.get(bit);
	    }
	    node.freq += 1;
	}
	
	public long search(int val) {
		TrieNode node = root;
	    for (int i = size-1; i >= 0; i--) {
	    	int bit = (val >> i) % 2; // The i-th bit of val
	    	if (!node.containsKey(bit)) {
	    		return 0;
	    	}
	    	node = node.get(bit);
	    }
	    return node.freq;
	}
	
	
}

class TrieNode {
	private TrieNode[] child;
	long freq;
	  
	TrieNode() {
	    child = new TrieNode[2];
	}
	  
	boolean containsKey(int b) {
		return child[b] != null; 
	}
	  
	TrieNode get(int b) {
		return child[b];
	}
	  
	void put(int b, TrieNode node) {
		child[b] = node;
	}
}
*/