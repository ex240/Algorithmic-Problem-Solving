package homework1Warmup;	
import java.util.Scanner;

public class SimpleMax {

	public static void main(String[] args) {


		Scanner in = new Scanner(System.in);

		int n = in.nextInt(); 

		int max = -100; 

		for ( int i = 0; i < n; i++) {
			int compare = in.nextInt();

			if (compare > max) {
				max = compare; 
			}


		}

		System.out.print(max);
	}


}
