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
 * 풀이 공식
 * 1. stations들의 사이 값(11 - 4) - 전파가능범위 공식(2W + 1(3)) = 4
 * 2. 1에서 나온 값을 2W+1로 나누고 나눈 값의 몫을 더하고 나머지가 있다면 +1을 한다.
 *    ex: 몫[4 // (2W+1) = 1] + 나머지면 + 1[4 % (2W+1) != 0]
 * 3. 여기서 중요한 것은 몫이 1이상일 경우만 나머지를 확인 한다.
 * 4. 처음 기지국과 마지막 기지국은 끝 기지국ㄱ
 */

/**
 * 풀이방법
 * 1. 제한사항을 보니 완전탐색으로 하면 시간초과가 발생할듯 하다.
 * 2. 일단 stations를 통해서 전파가 닿는 부분을 체크해야 할듯
 * 3. W의 크기를 확인하면 1: 3, 2: 5, 3: 7, 4: 9라는 규칙 발견 가능(W: 전파가능범위) - 2W + 1의 공식을 만들 수 있음
 * 4. 이 공식을 활용하여 위의 풀이 공식대로 문제를 해결
 * 5. 위의 공식대로 하면 해결이 되지만 남은 부분이 만약
 *    (맨 처음 기지국 - W - 1  > 0이 된다면 위의 풀이 공식 적용) , (N - 맨 뒤의 기지국 - W > 0이 된다면 풀이 공식 적용)
 */

class 기지국설치_Solution {

    int possibleDistance;

    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        possibleDistance = (2 * w) + 1;

        // 맨 처음 확인
        if (stations[0] - w > 1) {
            answer += requiredNumberOfObject(stations[0] - w - 1, 0);
        }

        // 맨 뒤 확인
        if (stations[stations.length - 1] + w < n) {
            answer += requiredNumberOfObject(n, stations[stations.length - 1] + w);
        }

        for (int i = 1; i < stations.length; i++) {
            answer += requiredNumberOfObject(stations[i] - w - 1, stations[i - 1] + w);
        }

        return answer;
    }

    private int requiredNumberOfObject(int station1, int station2) {
        int result = 0;
        int diffStation = station1 - station2;
        int quotient = diffStation / possibleDistance;
        int remainder = diffStation % possibleDistance;

        result += quotient;
        if (remainder > 0) {
            result += 1;
        }

        return result;
    }
}
