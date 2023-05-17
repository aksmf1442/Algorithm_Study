package 복습;

public class 기지국설치복습 {

    public static void main(String[] args) {
        기지국설치_Solution solution = new 기지국설치_Solution();
        int n = 16;
        int[] stations = {9};
        int w = 2;
        System.out.println("result = " + solution.solution(n, stations, w));
    }
}

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

