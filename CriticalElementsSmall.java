package homework12DynamicProgrammingI;

import java.io.*;
import java.util.*;

public class CriticalElementsSmall {

	public static void main(String[] args) throws IOException {
		
		InputReader in = new InputReader(System.in);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));  
		
		int n = in.nextInt();
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = in.nextInt();
		}
		
		// Find the length and number of LIS ending at each element of A
		int[] Lto = new int[n];
		int[] numLto = new int[n];
		for (int i = 0; i < n; i++) {
			Lto[i] = 1;
			numLto[i] = 1;
		}
		
		for (int i = 0; i < n-1; i++) {
			for (int j = i+1; j < n; j++) {
				if (A[j] > A[i]) {
					if (Lto[i] + 1 > Lto[j]) {
						Lto[j] = Lto[i] + 1;
						numLto[j] = numLto[i];
					}
					else if (Lto[i] + 1 == Lto[j]) {
						numLto[j] += numLto[i];
					}
				}
			}
		}
		
		// Find the length and number of LIS starting from each element of A
		// An LIS starting from an element is an backward LDS ending at it
		int[] Lfrom = new int[n];
		int[] numLfrom = new int[n];
		for (int i = 0; i < n; i++) {
			Lfrom[i] = 1;
			numLfrom[i] = 1;
		}
		
		for (int i = n-1; i > 0; i--) {
			for (int j = i-1; j >= 0; j--) {
				if (A[j] < A[i]) {
					if (Lfrom[i] + 1 > Lfrom[j]) {
						Lfrom[j] = Lfrom[i] + 1;
						numLfrom[j] = numLfrom[i];
					}
					else if (Lfrom[i] + 1 == Lfrom[j]){
						numLfrom[j] += numLfrom[i];
					}
				}
			}
		}
		
		// Find the length and number of LIS
		int maxLen = 0, maxNum = 0;
		for (int i = 0; i < n; i++) {
			if (Lto[i] > maxLen) maxLen = Lto[i];
		}
		for (int i = 0; i < n; i++) {
			if (Lto[i] == maxLen) maxNum += numLto[i];
		}
		//out.write("maxLen = " + maxLen + "\n");
		//out.write("maxNum = " + maxNum + "\n");
		
		// Find the critical elements
		// A critical element is one that is on every LIS
		String critical = "";
		for (int i = 0; i < n; i++) {
			if (Lto[i] + Lfrom[i] - 1 == maxLen && numLto[i] * numLfrom[i] == maxNum) {
				critical += A[i] + " ";
			}
		}
		
		if (critical.length() == 0) out.write(-1 + "\n");
		else out.write(critical + "\n"); 
		
		/*
		out.write("LIS to\n");
		for (int i = 0; i < n; i++) out.write(Lto[i] + " ");
		out.write("\n");
		for (int i = 0; i < n; i++) out.write(numLto[i] + " ");
		out.write("\n");
		out.write("LIS from\n");
		for (int i = 0; i < n; i++) out.write(Lfrom[i] + " ");
		out.write("\n");
		for (int i = 0; i < n; i++) out.write(numLfrom[i] + " ");
		out.write("\n");
		*/
		
		out.close();
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

