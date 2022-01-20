import java.util.Scanner;

public class X보다작은수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n, x;
		n = sc.nextInt();
		x = sc.nextInt();
		int[] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		for (int i = 0; i < n; i++) {
			if (arr[i] < x) {
				System.out.print(arr[i] + " ");
			}
		}
	}
}
