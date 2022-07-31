import java.util.*;

class 조이스틱 {
    public static void main(String[] args) {
        String name = "JEROEN";
        System.out.println(solution(name));
    }

    public static int solution(String name) {
        int answer = 0;
        int nextIdx = 0;
        char[] nameArr = name.toCharArray();

        //기본 최소 좌우이동 횟수는 길이 - 1
        int minMove = name.length() - 1;

        for (int i = 0; i < name.length(); i++) {
            answer += Math.min((int) nameArr[i] - (int) 'A', (int) 'Z' - (int) nameArr[i] + 1);

            //해당 알파벳 다음부터 연속된 A문자열 찾기 
            nextIdx = i + 1;
            while (nextIdx < name.length() && name.charAt(nextIdx) == 'A') {
                nextIdx++;
            }


            //기존 연속된 A의 왼쪽시작 방식, 연속된 A의 오른쪽 시작 방식 비교 및 갱신

            minMove = Math.min(minMove, Math.min(2 * i + name.length() - nextIdx, i + 2 * (name.length() - nextIdx)));
            
        } 

        answer += minMove;
        return answer;
    }
}