package homework6CompleteSearch;

import java.util.*;

public class RooksLarge {

	public static void main(String[] args) {

	

		Scanner in = new Scanner(System.in); 
		
		int n = in.nextInt(); 
		
		
		char[][] board = new char[n][n]; 
		for ( int i = 0; i < n; i++) {
			String next = in.next(); 
			char[] row = next.toCharArray();
			for (int j = 0; j < n; j++) {
				board[i][j] = row[j]; 
			}

		}
		
		
		int [] ans = maxRooks(board, 0);
		System.out.printf("%d %d\n\n\n", ans[0], ans[1]);

	}


	public static int[] maxRooks(char[][] board, int startRow) {
		//System.out.printf("maxRooks(board, %d)\n", startRow);
		//printBoard(board);
		int n = board.length - startRow;
		if (n == 1) return maxRooksOneRow(board[startRow]);

		ArrayList<ArrayList<Integer>> posList = findPos(board[startRow]);
		int rooksCurRow = posList.size();

		if (rooksCurRow == 0) {
			//System.out.println("No rook in this row");
			return maxRooks(board, startRow+1);
		}

		int waysCurRow = 1;
		int[] count = new int[rooksCurRow];
		for (int i = 0; i < rooksCurRow; i++) {
			ArrayList<Integer> pos = posList.get(i);
			count[i] = pos.size();
			waysCurRow *= (count[i]+1);
		}

		ArrayList<ArrayList<Integer>> tupList = new ArrayList<>();
		int[] tup = new int[rooksCurRow];
		while (tupList.size() < waysCurRow) {
			ArrayList<Integer> al = new ArrayList<>();
			for (int i = 0; i < rooksCurRow; i++) {
				if (tup[i] < count[i]) al.add(posList.get(i).get(tup[i]));
			}
			tupList.add(al);
			tup  = nextTuple(tup, count);
		}
		//for (ArrayList<Integer> al : tupList) System.out.println(al);

		int maxRooks = 0, maxWays = 0;
		for (ArrayList<Integer> tuple: tupList) {
			mark(board, startRow, tuple);

			int[] ans = maxRooks(board, startRow+1);
			if (tuple.size() + ans[0] > maxRooks) {
				maxRooks = tuple.size() + ans[0];
				maxWays = ans[1];
			}
			else if (tuple.size() + ans[0] == maxRooks) maxWays += ans[1];

			unmark(board, startRow, tuple);
		}

		return new int[] {maxRooks, maxWays};
	} 

	public static int[] maxRooksOneRow(char[] row) {
		int rooks = 0;
		int ways = 1;
		int count = 0;

		for (char c : row) {
			if (c == '.') count++;
			if (c == '#') {
				if (count > 0) {
					rooks += 1; 
					ways *= count;
				}
				count = 0;
			}
		}

		if (count > 0) {
			ways *= count;
			rooks += 1;
		}

		return new int[] {rooks, ways};
	}

	public static ArrayList<ArrayList<Integer>> findPos(char[] row) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		int state = 0;
		ArrayList<Integer> pos = new ArrayList<>();
		for (int i = 0; i < row.length; i++) {
			char c = row[i];
			if (state == 0) {
				if (c == '.') {
					pos.add(i);
					state = 1;
				}
			}
			else { // if state == 1
				if (c == '.') pos.add(i);
				if (c == '#') {
					res.add(pos);
					pos = new ArrayList<>();
					state = 0;
				}
			}
		}

		if (pos.size() > 0) res.add(pos);

		//System.out.println(res.size());
		//for (ArrayList<Integer> al : res) System.out.println(al);

		return res;
	}

	public static int[] nextTuple(int[] tuple, int count[]) {
		int d = tuple.length;
		int[] next = new int[d];
		int sum = tuple[d-1] + 1;
		next[d-1] = sum % (count[d-1]+1);
		int carry = sum / (count[d-1]+1);

		for (int i = d-2; i >= 0; i--) {
			sum = tuple[i] + carry;
			next[i] = sum % (count[i]+1);
			carry = sum / (count[i]+1);
		}

		return next;
	}

	public static void mark(char[][] board, int row, ArrayList<Integer> tuple) {
		//System.out.printf("mark(board, %d, tuple)  tuple = ", row);
		//System.out.println(tuple);
		for (int pos : tuple) {
			for (int i = row+1; i < board.length; i++) {
				if (board[i][pos] == '#') break;
				board[i][pos] = 'x';
			}
		}
		//printBoard(board);
	}

	public static void unmark(char[][] board, int row, ArrayList<Integer> tuple) {
		//System.out.printf("unmark(board, %d, tuple)  tuple = ", row);
		//System.out.println(tuple);
		for (int pos : tuple) {
			for (int i = row+1; i < board.length; i++) {
				if (board[i][pos] == '#') break;
				board[i][pos] = '.';
			}
		}
		//printBoard(board);
	}

	public static void printBoard(char[][] board) {
		int m = board.length, n = board[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.printf("%c ", board[i][j]);
			}
			System.out.println("");
		}
	}
}
