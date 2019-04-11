package homework12DynamicProgrammingI;

import java.io.*;
import java.util.*;

public class CollectingCoinsIIHard {
	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));  

		int n = in.nextInt(); 
		int m = in.nextInt(); 
		
		int s = in.nextInt(); 
		int a = in.nextInt(); 
		int b = in.nextInt(); 
		int c = in.nextInt(); 
		int d = in.nextInt(); 
		
		int[] lastM = new int[m+1];
		int[] curM = new int[m+1];
		int[] lastN = new int[m+1];
		int[] curN = new int[m+1];
		curN[1] = 1;
		
		final int K = 10000;
		
		for (int i = 1; i <= n; i++) {
			CoinGen coins = genCoins(i, m, a, b, c, d, s);
			for (int j = 1; j <= m; j++) {
				if (coins.C[j] == 0) continue;
				if (curM[j-1] == 0 && lastM[j] == 0 && (i != 1 || j != 1)) continue;
				
				if (curM[j-1] > lastM[j]) {
					curM[j] += curM[j-1] + coins.C[j];
					curN[j] = (curN[j] + curN[j-1]) % K;
				}
				else if (curM[j-1] < lastM[j]) {
					curM[j] += lastM[j] + coins.C[j];
					curN[j] = (curN[j] + lastN[j]) % K;
				}
				else {
					curM[j] += lastM[j] + coins.C[j];
					curN[j] = (curN[j] + curN[j-1] + lastN[j]) % K;
				}
			}
			
			lastM = curM; lastN = curN;
			curM = new int[m+1]; curN = new int[m+1];
			s = coins.last;
		}
		
		out.write(lastM[m] + " " + lastN[m] + "\n");
		
		out.close();
		 
	}
	
	public static CoinGen genCoins(int i, int m, int a, int b, int c, int d, int s) {
		CoinGen coins = new CoinGen(m);
		int last = s;
		
		for (int j = 1; j <= m; j++) {
			coins.C[j] =  last; 
			last = ( ( (last * a) ^ b) + c) % d; 
		}
		coins.last = last;
		return coins;
	}
	
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

class CoinGen {
	int[] C;
	int last;
	CoinGen(int m) {
		C = new int[m+1];
	}
}
