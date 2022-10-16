package level3;

public class 기지국설치 {

    public static void main(String[] args) {
        기지국설치_Solution solution = new 기지국설치_Solution();
        int n = 16;
        int[] stations = {9};
        int w = 2;
        System.out.println("result = " + solution.solution(n, stations, w));
    }
}

/**
 * goal
 * 1. 5g 기지국을 최소로 설치하면서 모든 아파트에 전파를 전달
 * 2. 이미 기지국이 stations의 개수만큼 설치가 되어 있는데, 그것을 제외하고 새로 설치된 기지국을 설치
 */

/**
 * 제한사항
 * 1. N: 200,000,000 이하의 자연수
 * 2. stations의 크기: 10,000 이하의 자연수
 * 3. stations는 오름차순으로 정렬되어 있고, 배열에 담긴 수는 N보다 같거나 작은 자연수입니다.
 * 4. W: 10,000 이하의 자연수
 */

/**
 * 알아야 할 것
 * 1. 전파가능범위 공식 = 2W + 1
 * 2.
 */

/**
 * 풀이방법
 * 1. 제한사항을 보니 완전탐색으로 하면 시간초과가 발생할듯 하다.
 * 2. 일단 stations를 통해서 전파가 닿는 부분을 체크해야 할듯
 * 3. W의 크기를 확인하면 1: 3, 2: 5, 3: 7, 4: 9라는 규칙 발견 가능(W: 전파가능범위) - 2W + 1의 공식을 만들 수 있음
 * 4. 전파가 통하지 않는 구간이 있으면 그 구간을 나눗셈과 나머지를 통해 몇 개의 기지국이 필요한지 구하기
 */

class 기지국설치_Solution {

    int possibleDistance;

    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        possibleDistance = (2 * w) + 1;

        // 맨 앞 계산
        int notElectricWaveSection = stations[0] - w - 1;
        if (notElectricWaveSection > 0) {
            answer += requiredNumberOfObject(notElectricWaveSection);
        }

        // 맨 뒤 계산
        notElectricWaveSection = n - (stations[stations.length - 1] + w);
        if (notElectricWaveSection > 0) {
            answer += requiredNumberOfObject(notElectricWaveSection);
        }

        for (int i = 1; i < stations.length; i++) {
            // 전파가 통하지 않는 구간이 0보다 클 경우 계산
            notElectricWaveSection = (stations[i] - w - 1) - (stations[i - 1] + w);
            if (notElectricWaveSection > 0) {
                answer += requiredNumberOfObject(notElectricWaveSection);
            }
        }

        return answer;
    }

    private int requiredNumberOfObject(int notElectricWaveSection) {
        int result = 0;
        int quotient = notElectricWaveSection / possibleDistance;
        int remainder = notElectricWaveSection % possibleDistance;

        result += quotient;
        if (remainder != 0) {
            result += 1;
        }

        return result;
    }
}
