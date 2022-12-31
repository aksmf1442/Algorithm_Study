package level3;

public class 광고삽입 {

    public static void main(String[] args) {
        String playTime = "99:59:59";
        String advTime = "25:00:00";
        String[] logs = {"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"};
        광고삽입_Solution solution = new 광고삽입_Solution();
        System.out.println(
            "solution.solution(playTime, advTime, logs) = " + solution.solution(playTime, advTime,
                logs));
    }
}

/**
 * 풀이 순서
 * 1. 시간의 로그를 통해서 부분합을 구해준다.
 * 2. 초기값으로 광고가 삽입되는 시간만큼 맨 앞에서부터 부분합을 통해 구한 값을 더해준다.
 * 3. 맨 앞에를 제거, 맨 뒤를 추가하는 방식으로 처음부터 끝까지 확인하면서 2번에서 구한 값보다 큰 값이 나오면 값을 해당 값으로 변경해준다.
 * 4. 끝까지 돌면 결과값이 나온다.
 */

class 광고삽입_Solution {

    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = timeToSecond(play_time);
        int advTime = timeToSecond(adv_time);
        int[] prefixSumArr = findPrefixSumArr(logs, playTime);

        int startIdx = 0;
        long sum = 0;

        for (int i = 0; i < advTime; i++) {
            sum += prefixSumArr[i];
        }

        long maxSum = sum;
        for (int i = 1; i <= playTime - advTime; i++) {
            sum -= prefixSumArr[i - 1];
            sum += prefixSumArr[i + advTime - 1];
            if (sum > maxSum) {
                maxSum = sum;
                startIdx = i;
            }
        }

        return secondToTime(startIdx);
    }

    private int[] findPrefixSumArr(String[] logs, int playTime) {
        int[] arr = new int[playTime + 1];

        for (String log : logs) {
            String[] logTime = log.split("-");
            int start = timeToSecond(logTime[0]);
            int end = timeToSecond(logTime[1]);
            arr[start]++;
            arr[end]--;
        }

        for (int i = 1; i < arr.length; i++) {
            arr[i] += arr[i - 1];
        }
        return arr;
    }

    private String secondToTime(int second) {
        int minute = second / 60;
        second %= 60;

        int hour = minute / 60;
        minute %= 60;

        return (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute) + ":"
                + (second < 10 ? "0" + second : second);
    }

    private int timeToSecond(String time) {
        String[] splitTime = time.split(":");
        int hour = Integer.parseInt(splitTime[0]);
        int minute = Integer.parseInt(splitTime[1]);
        int second = Integer.parseInt(splitTime[2]);
        return (hour * 3600) + (minute * 60) + second;
    }


}
