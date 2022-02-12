import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 평균은넘겠지버퍼리더 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] arr;

		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;

		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine()," ");

			int student = Integer.parseInt(st.nextToken());
			arr = new int[student];

			double sum = 0;

			//성적입력부분
			for (int j = 0; j < student; j++) {
				int val = Integer.parseInt(st.nextToken()); //성적 저장
				arr[j] = val;
				sum += val;
			}

			double mean = sum / student;
			double count = 0;

			//평균 넘는 학생 비율 찾기
			for (int j = 0; j < student; j++) {
				if (arr[j] > mean) {
					count++;
				}
			}

			System.out.printf("%.3f%%\n", (count / student) * 100);

		}
	}
}
