package 프로그래머스.해시;

//프로그래머스 해시 lv2 전화번호목록
import java.util.HashMap;
import java.util.Map;

class 전화번호목록 {

    public boolean solution(String[] phoneBook) {
        
        boolean answer = true;
        

        //해시맵으로 처리
        Map<String, Integer> hashMap = new HashMap<String, Integer>();

        for (int i = 0; i < phoneBook.length; i++) {
            hashMap.put(phoneBook[i], i);
        }

        for (int i = 0; i <phoneBook.length; i++) {
            String phoneNum = "";
            for (int j = 0; j < phoneBook[i].length(); j++) { 
                //전화번호 슬라이싱
                phoneNum += Character.toString(phoneBook[i].charAt(j));
                //해시맵 번호 비교 
                if (hashMap.get(phoneNum) != null && !phoneNum.equals(phoneBook[i]))
                answer = false;
            }
        }
        
        return answer;
    }
}