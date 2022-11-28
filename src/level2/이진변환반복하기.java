package level2;

public class 이진변환반복하기 {

    public static void main(String[] args) {
        이진변환반복하기_Solution solution = new 이진변환반복하기_Solution();
        String s = "110010101001";
        int[] result = solution.solution(s);
        for (int i : result) {
            System.out.print(i + ",");
        }
    }
}

class 이진변환반복하기_Solution {

    public int[] solution(String s) {
        int[] answer = new int[2];

        while(s.length() > 1) {

            int cntOne = 0;
            for(int i=0; i<s.length(); i++) {

                if(s.charAt(i) == '0') answer[1]++;
                else cntOne++;
            }

            s = Integer.toBinaryString(cntOne);
            answer[0]++;
        }

        return answer;
    }
}
