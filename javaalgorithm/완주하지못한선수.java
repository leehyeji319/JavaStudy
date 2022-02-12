import java.util.Arrays;

class Solution {
	public String solution(String[] participant, String[] completion) {
		//두 배열 정렬
		Arrays.sort(participant);
		Arrays.sort(completion);

		int i = 0;
		for (i = 0; i < completion.length; i++) {
			if (!participant[i].equals(completion[i]))
				break;
		}

		//여기까지 왔으면 마지막 주자가 완주하지 못했따는 뜻
		return participant[i];
	}

	public static void main(String[] args) {
		String[] part = {"leo", "kiki", "eden"};
		String[] comp = {"eden", "kiki"};
		Solution sol = new Solution();
		System.out.println(sol.solution(part, comp));

	}
}