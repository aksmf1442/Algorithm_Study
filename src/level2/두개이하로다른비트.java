package level2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class 두개이하로다른비트 {

    public static void main(String[] args) {
        두개이하로다른비트_Solution solution = new 두개이하로다른비트_Solution();
//        long[] numbers = {2, 7};
        long[] numbers = {13};
        long[] result = solution.solution(numbers);
        for (long i : result) {
            System.out.print(", " + i);
        }
    }
}

/**
 * goal: 2진수로 변환해서 비트가 다른 지점이 2개 이하인 값 중 가장 작은 값 구하기
 */

class 두개이하로다른비트_Solution {

    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            long number = numbers[i];
            if (number % 2 == 0) {
                answer[i] = number + 1;
                continue;
            }

            String binaryNumber = Long.toBinaryString(number);
            HashSet<String> strSet = Arrays.stream(binaryNumber.split(""))
                .collect(Collectors.toCollection(HashSet::new));
            if (strSet.size() == 1) {
                binaryNumber = "10" + binaryNumber.substring(1);
            } else {
                int lastZeroIdx = binaryNumber.lastIndexOf("0");
                binaryNumber =
                    binaryNumber.substring(0, lastZeroIdx) + "10" + binaryNumber.substring(
                        lastZeroIdx + 2);
            }
            answer[i] = Long.parseLong(binaryNumber, 2);

        }

        return answer;
    }

}
