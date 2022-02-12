import java.util.Scanner;

public class 평균은넘겠지 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int i = 0; i < t; i++) {
			int student = sc.nextInt();
			int [] arr = new int[student];

			double sum = 0;

			//성적입력 시작
			for (int j = 0; j < student; j++) {
				int val = sc.nextInt();
				arr[j] = val;
				sum += val; //성적 누적 합
			}

			double mean = sum / student;
			double count = 0;

			for (int k = 0; k < student; k++) {
				if (arr[k] > mean) {
					count ++;
				}
			}

			//비율 구하기
			System.out.printf("%.3f%%\n", (count / student) * 100);
		}
		sc.close();
	}
}
