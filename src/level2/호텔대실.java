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
        int[] minutes = new int[timeToMinute("24:10")];

        for (String[] time : book_time) {
            String startTime = time[0];
            String endTime = time[1];

            minutes[timeToMinute(startTime)] += 1;
            minutes[timeToMinute(endTime) + timeToMinute("00:10")] -= 1;
        }

        for (int i = 1; i < minutes.length; i++) {
            minutes[i] += minutes[i - 1];
        }

        return Arrays.stream(minutes).max().getAsInt();
    }

    private int timeToMinute(String time) {
        String[] splitTime = time.split(":");
        int hour = Integer.parseInt(splitTime[0]);
        int minute = Integer.parseInt(splitTime[1]);

        minute += hour * 60;
        return minute;
    }
}