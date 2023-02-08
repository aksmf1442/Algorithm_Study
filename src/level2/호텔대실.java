package level2;

import java.util.Arrays;

public class 호텔대실 {
    public static void main(String[] args) {
        String[][] book_time = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};
        호텔대실_Solution solution = new 호텔대실_Solution();
        System.out.println(solution.solution(book_time));
    }
}
// 1. 일단 0~24시를 초로 변환해서 누적합을 통해 값을 구함
// 2. 해당 배열의 최대값을 구하면 결과 나옴.
class 호텔대실_Solution {
    public int solution(String[][] book_time) {
        int[] seconds = new int[timeToSecond("24:10")];

        for (String[] time : book_time) {
            String startTime = time[0];
            String endTime = time[1];

            seconds[timeToSecond(startTime)] += 1;
            seconds[timeToSecond(endTime) + timeToSecond("00:10")] -= 1;
        }

        for (int i = 1; i < seconds.length; i++) {
            seconds[i] += seconds[i - 1];
        }

        return Arrays.stream(seconds).max().getAsInt();
    }

    private int timeToSecond(String time) {
        String[] splitTime = time.split(":");
        int hour = Integer.parseInt(splitTime[0]);
        int minute = Integer.parseInt(splitTime[1]);
        int second = 0;

        second += hour * 3600;
        second += minute * 60;
        return second;
    }
}